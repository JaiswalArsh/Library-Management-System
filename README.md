# Library Management System

A **console-based Java application** designed to manage core library operations including book management, borrowing, and returning functionalities. It leverages **MySQL** for efficient data handling and persistence.

---

## 📚 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [System Requirements](#system-requirements)
- [Setup and Installation](#setup-and-installation)
  - [Database Setup](#database-setup)
  - [Project Setup](#project-setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

---

## 🚀 Features

### 📘 Book Management
- Add new books to the library.
- View a list of all available books.
- Search books by title or author.

### 📗 Borrowing & Returning
- Borrow books and update availability.
- Return books and mark them as available.

### 🗃️ Database Integration
- Persistent data storage using **MySQL**.
- Real-time updates on book availability.

### 💻 Console Interface
- User-friendly, menu-driven text interface.

---

## 🛠 Technologies Used

- **Java** – Application logic
- **MySQL** – Relational database
- **JDBC** – Java Database Connectivity API

---

## 🧰 System Requirements

- **JDK**: Version 8 or higher
- **MySQL Server**: Version 8.0 or higher
- **MySQL JDBC Driver**: Connector/J

---

## ⚙️ Setup and Installation

### 📦 Database Setup

1. **Install MySQL**: Download from [MySQL Official Site](https://www.mysql.com/).
2. **Create Database**:
    ```sql
    CREATE DATABASE library;
    ```
3. **Configure Database Credentials**:
    Update the following files if your credentials differ from `root` / `Virus@12`:
    - `/LMS/src/BookManagement.java`
    - `/LMS/src/BorrowReturn.java`
    - `/LMS/src/createdb.java`
    - `/LMS/src/DatabaseSetup.java`
    ```java
    private static final String USER = "your_mysql_username";
    private static final String PASSWORD = "your_mysql_password";
    ```
4. **Create Tables**:
    Run `DatabaseSetup.java` or execute:
    ```sql
    USE library;

    CREATE TABLE IF NOT EXISTS Books (
        book_id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        author VARCHAR(255) NOT NULL,
        available BOOLEAN DEFAULT TRUE
    );

    CREATE TABLE IF NOT EXISTS Borrowers (
        borrow_id INT AUTO_INCREMENT PRIMARY KEY,
        book_id INT NOT NULL,
        borrower_name VARCHAR(255) NOT NULL,
        borrow_date DATE NOT NULL,
        return_date DATE,
        FOREIGN KEY (book_id) REFERENCES Books(book_id)
    );
    ```

### 🧑‍💻 Project Setup

1. **Clone Repository**:
    ```bash
    git clone https://github.com/your-username/Library-Management-System.git
    cd Library-Management-System
    ```

2. **Import in IDE**:
   - Open in **VS Code**, **IntelliJ IDEA**, or **Eclipse**
   - Ensure `src` is marked as source directory

3. **Add JDBC Driver**:
   - Download from [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
   - Place `.jar` file in `lib/` folder
   - Update classpath/build path to include it

---

## ▶️ Usage

1. **Run Application**:
    Launch `LibraryManagementSystem.java` and run the `main` method.

2. **Console Menu**:
    ```
    --- Library Management System ---
    1. Add Book
    2. View Books
    3. Search Books
    4. Borrow Book
    5. Return Book
    6. Exit
    Enter your choice:
    ```

---

## 📁 Project Structure

LMS/
├── .vscode/
│ └── settings.json # VS Code project settings
├── lib/
│ └── mysql-connector-java-x.x.x.jar # JDBC driver
├── src/
│ ├── BookManagement.java # Book-related operations
│ ├── BorrowReturn.java # Borrow/return functionality
│ ├── createdb.java # Create 'library' DB
│ ├── DatabaseSetup.java # Table creation logic
│ └── LibraryManagementSystem.java# Entry point and UI

---

## 🌱 Future Enhancements

- 🔳 **GUI Interface**: Transition from console to JavaFX or Swing.
- 🔒 **Authentication**: Admin login system.
- 🧮 **Fine Calculation**: Based on return delays.
- 📊 **Reports**: Generate usage and trends reports.
- 🌐 **Web Version**: Use Spring Boot for online access.

---

## 🤝 Contributing

Contributions are highly welcome!  
To contribute:

1. Fork the repository
2. Create a new branch: `git checkout -b feature/YourFeature`
3. Commit your changes: `git commit -m 'Add feature'`
4. Push to the branch: `git push origin feature/YourFeature`
5. Open a **Pull Request**

---

## 📄 License

This project currently **does not include a license**.  
If you plan to reuse, distribute, or contribute, please contact the repository owner or suggest a preferred open-source license such as **MIT**, **Apache 2.0**, or **GPLv3** in a pull request.

---
