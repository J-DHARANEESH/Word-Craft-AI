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

