# WordCraft AI ✍️

## 📚 Project Description

**Word Craft AI** is a full-stack, AI-powered writing assistant that helps users intelligently **summarize**, **rephrase**, and **expand** text within a rich Markdown editor. The application leverages **transformer-based NLP models** via a local Flask API and integrates with a **Spring Boot backend** and a dynamic **Angular frontend** to deliver a seamless writing experience.

### ✨ Core Features

* 🧠 **AI Actions**: Summarize long texts, rephrase sentences, and expand ideas using advanced transformer models (BART, GPT-2, MEETING\_SUMMARY).
* ✍️ **Live Markdown Editor**: Write with real-time Markdown preview and editing support.
* 📄 **Custom PDF Export**: Generate professional-looking PDFs with custom heading input and optional date stamp.
* 🌗 **Dark Mode Support**: Switch between light and dark themes effortlessly.
* 🌀 **Loader Overlay**: Smooth popup-style loader during AI operations and graceful error handling.


## 🛠️ Tech Stack

| Layer       | Technologies                                    |
|-------------|-------------------------------------------------|
| Frontend    | Angular, Angular Material, ngx-markdown         |
| Backend     | Spring Boot (Java)                              |
| AI Services | Python (Flask, Hugging Face Transformers)       |

---

## 📦 Setup Instructions

### 1. Backend (Spring Boot)

- Go to `spring-backend/`
- Configure `application.properties`:

```properties
local.rephrase.api.url=http://localhost:5000/rephrase
local.expand.api.url=http://localhost:5000/expand
local.summarize.api.url=http://localhost:5000/summarize
````

* Start the backend server:

```bash
./mvnw spring-boot:run
```

---

### 2. AI Flask Service

* Go to `flask-ai-service/`
* Install dependencies:

```bash
pip install flask torch transformers
```

* Run the Flask server:

```bash
python app.py
```

> Ensure Flask runs on **port 5000** to match Spring Boot's configuration.

---

### 3. Frontend (Angular)

* Go to `ai-writing-frontend/`
* Install dependencies:

```bash
npm install
```

* Start the Angular development server:

```bash
ng serve
```

---

## 📁 Project Structure

```
├── ai-writing-frontend/         # Angular frontend
│   ├── pages/
│   │   ├── home/                # Home screen
│   │   └── note-editor/         # Markdown editor with AI integration
│   │   └── pdf-download/        # PDF export popup component
│   ├── services/
│   │   └── ai-api.service.ts    # Angular service to connect with backend
│
├── spring-backend/              # Java Spring Boot backend
│   ├── controller/
│   ├── service/
│   │   ├── AiService.java
│   │   └── actions/
│   │       ├── RephraseService.java
│   │       ├── ExpandService.java
│   │       └── SummarizeService.java
│
├── flask-ai-service/            # Flask-based AI endpoints
│   └── AI_Models.py             # Routes: /rephrase, /expand, /summarize
```

---

## 📸 Screenshots (TODO)

Add images to showcase:

* 📝 Editor interface
  
  ![image](https://github.com/user-attachments/assets/84bc99f3-8c2a-47f3-a051-68442de556ac)


* 🌘 Dark mode view
  
  ![image](https://github.com/user-attachments/assets/e167d3be-7454-445a-9069-a0b6145a1577)


* 🪄 PDF export dialog
  
  ![image](https://github.com/user-attachments/assets/a40c569e-da1f-4634-abb9-d9e93ffb6a6f)


* ⏳ Loader popup with error handling
  
  ![image](https://github.com/user-attachments/assets/2f38f220-740c-408f-922e-69e2d3b16b9a)


---

## 🤝 Contributing

Pull requests, issues, and ideas are welcome!
If you want to contribute:

1. Fork the repo
2. Create a branch: `git checkout -b feature/my-feature`
3. Commit your changes
4. Push and create a PR

---

## 📃 License

MIT License — use freely, modify responsibly.

---

## 👨‍💻 Author

Developed with ❤️ by **Dharaneesh J**

---

## Contact

For questions or collaboration, please reach out:
**Email:** j.dharaneesh12@gmail.com<br>
**GitHub:** https://github.com/J-DHARANEESH

---
