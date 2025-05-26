# WordCraft AI ✍️

An AI-powered writing tool that helps users summarize, rephrase, and expand Markdown notes using an intuitive Angular interface. Built with:

- 🧠 Hugging Face API for summarization, expansion and rephrasing
- 🔧 Spring Boot backend for API orchestration
- 🌐 Angular frontend with live Markdown editor and advanced PDF export

---

## 🚀 Features

- 🧠 **AI Actions**
  - Summarize, Rephrase, and Expand content with language models
- ✍️ **Markdown Editing**
  - Real-time Markdown editor with live preview
- 📄 **PDF Export**
  - Custom popup dialog for entering export heading
  - Option to include the current date
  - Separate reusable `PdfDownloadComponent`
- 🌑 **Dark Mode Toggle**
  - Seamless light/dark theme switching
- 🔁 **Routing**
  - Route navigation with animations (Home ↔ Editor)
- 🌀 **Loader Overlay**
  - Popup spinner with typing animation
  - Error handling with toast or dialog message

---

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
  
  ![image](https://github.com/user-attachments/assets/b5b9d7a5-1d5b-43da-92d4-67495456b2fc)


* 🌘 Dark mode view
  
  ![image](https://github.com/user-attachments/assets/d3760825-3280-44c9-ad13-32696e1a52ca)


* 🪄 PDF export dialog
  
  ![image](https://github.com/user-attachments/assets/91ade8d6-3996-4e05-898d-05cbf2fa0676)


* ⏳ Loader popup with error handling
  
  ![image](https://github.com/user-attachments/assets/9f1b47c3-1cdc-4dd8-8e97-de58361f6e09)

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

