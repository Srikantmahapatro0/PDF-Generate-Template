# PDF Generation Service

A simple Spring Boot application that generates PDF files from HTML templates using **Thymeleaf** and **Flying Saucer (ITextRenderer)**. It allows you to generate and download PDF documents via REST APIs.

---

## 🛠 Features

- Generate PDF from HTML template
- Save generated PDF to local storage
- Download saved PDFs
- Uses Thymeleaf for dynamic HTML rendering
- Uses Flying Saucer (iText) for PDF rendering

---

## 📦 Project Structure

src
└── main
├── java
│ └── com.pdfgeneration.pdfgeneration
│ ├── Controller
│ │ └── PdfController.java
│ ├── Models
│ │ └── PdfRequest.java
│ └── Service
│ └── PdfService.java
└── resources
├── templates
│ └── pdfTemplate.html
└── application.properties


---

## 📄 API Endpoints

### `POST /api/pdf/generate`

Generates and saves a PDF.

#### Request Body:
```json
{
  "title": "MyDocument",
  "contents": [
    "First line of content",
    "Second line",
    "More content..."
  ]
}

Response :
200 OK
PDF generated and saved successfully

GET /api/pdf/download?title=MyDocument
Downloads a previously generated PDF.

Response:
Returns the PDF as a file attachment.

🧰 Requirements
Java 17+

Maven

Spring Boot

Thymeleaf

Flying Saucer (iTextRenderer)

⚙️ Configuration
Update the path for saving PDFs in PdfService.java:

java
Copy
Edit
private static final String BASE_DIRECTORY = "C:\\Users\\YourUser\\Documents\\PDFGeneration";
🧪 Run the Project
bash
Copy
Edit
# Build the project
mvn clean install

# Run the app
mvn spring-boot:run
📑 Sample Thymeleaf Template (resources/templates/pdfTemplate.html)
html
Copy
Edit
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>PDF Template</title>
</head>
<body>
    <h1 th:text="${title}"></h1>
    <ul>
        <li th:each="item : ${contents}" th:text="${item}"></li>
    </ul>
</body>
</html>
🛡 License
MIT License. Feel free to use and modify.




