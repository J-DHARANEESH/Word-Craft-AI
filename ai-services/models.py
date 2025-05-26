from flask import Flask, request, jsonify
import torch
from transformers import (
    BartForConditionalGeneration,
    BartTokenizer,
    pipeline,
    set_seed,
    AutoTokenizer,
    AutoModelForSeq2SeqLM
)

app = Flask(__name__)

# Device setup
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

# === Load BART Paraphrase Model ===
paraphrase_model_name = 'eugenesiow/bart-paraphrase'
paraphrase_tokenizer = BartTokenizer.from_pretrained(paraphrase_model_name)
paraphrase_model = BartForConditionalGeneration.from_pretrained(paraphrase_model_name).to(device)
paraphrase_model.eval()

# === Load GPT-2 for Text Expansion ===
generator = pipeline('text-generation', model='gpt2', device=0 if torch.cuda.is_available() else -1)
set_seed(42)

# === Load Meeting Summary Model ===
summary_tokenizer = AutoTokenizer.from_pretrained("knkarthick/MEETING_SUMMARY")
summary_model = AutoModelForSeq2SeqLM.from_pretrained("knkarthick/MEETING_SUMMARY").to(device)
summary_model.eval()

@app.route('/rephrase', methods=['POST'])
def rephrase():
    data = request.get_json()
    if not data or 'text' not in data:
        return jsonify({"error": "Missing 'text' in request body"}), 400

    input_text = data['text']
    batch = paraphrase_tokenizer(input_text, return_tensors='pt').to(device)

    generated_ids = paraphrase_model.generate(
        batch['input_ids'],
        num_beams=5,
        num_return_sequences=3,
        temperature=1.5,
        early_stopping=True
    )

    paraphrases = paraphrase_tokenizer.batch_decode(generated_ids, skip_special_tokens=True)
    return jsonify({"rephrased": paraphrases})

@app.route('/expand', methods=['POST'])
def expand():
    data = request.get_json()
    if not data or 'text' not in data:
        return jsonify({"error": "Missing 'text' in request body"}), 400

    input_text = data['text']
    expansions = generator(
        input_text,
        max_length=50,
        num_return_sequences=3,
        do_sample=True,
        temperature=0.9
    )

    expanded_texts = [item['generated_text'] for item in expansions]
    return jsonify({"expanded": expanded_texts})

@app.route('/summarize', methods=['POST'])
def summarize():
    data = request.get_json()
    if not data or 'text' not in data:
        return jsonify({"error": "Missing 'text' in request body"}), 400

    input_text = data['text']
    inputs = summary_tokenizer.encode(
        input_text,
        return_tensors="pt",
        max_length=1024,
        truncation=True
    ).to(device)

    summary_ids = summary_model.generate(
        inputs,
        max_length=150,
        num_beams=4,
        length_penalty=2.0,
        early_stopping=True
    )

    summary = summary_tokenizer.decode(summary_ids[0], skip_special_tokens=True)
    return jsonify({"summary": summary})

if __name__ == '__main__':
    app.run(debug=True, port=5000)
