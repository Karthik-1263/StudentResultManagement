# Student Result Management System

A GUI-based Java Swing application with a modern FlatLaf look-and-feel for managing academic results. This project provides real-time file-based data storage, responsive UI layouts, intuitive navigation, and powerful sorting & filtering features to help educators and administrators manage student results efficiently.

---

## Details of the project

* Built a desktop application using **Java Swing** for the user interface and **FlatLaf** for a modern, native-like appearance.
* Implements persistent storage using simple **text file-based data management** (no SQL database required).
* Supports adding, editing, deleting, and searching student records and their exam results.
* Features responsive UI components and an organized navigation menu for quick access to core functions.
* Includes sorting and filtering capabilities to help users visualize and analyze results easily.

---

## Role of the user

* Designed and implemented the GUI using Java Swing and FlatLaf.
* Implemented file-based data storage and result management logic.
* Built and tested sorting & filtering features to improve data accessibility.
* Improved user experience through responsive layout design and intuitive navigation.

---

## Tools used

* Java (JDK 11+ recommended)
* Java Swing (UI framework)
* FlatLaf (Look and Feel)
* File I/O (for data storage)
* Git (version control)

---

## Features

* Modern FlatLaf-themed GUI
* Add / Edit / Delete student records and results
* Search students by name, ID, class, or other fields
* Sort results by marks, name, or roll number
* Filter results by grade, class, subject, or custom ranges
* Save and load data from text files
* Validation and error handling for user inputs

---

## Getting started (Setup)

### Prerequisites

* Java JDK 11 or newer installed. Verify with:

```bash
java -version
```

### Installation

1. Clone the repository:

```bash
git clone https://github.com/<your-username>/student-result-management.git
cd student-result-management
```

2. Build and run the application:

If using command line:

```bash
javac -d bin src/**/*.java
java -cp bin com.example.Main
```

If running from an IDE (IntelliJ / Eclipse): import the project and run the main class (e.g., `com.example.Main`).

---

## Data Storage (Text File Based)

* Student and result data are stored in text files (e.g., `students.txt` and `results.txt`).
* Each record is stored in a structured format, for example:

**students.txt**

```
ID|RollNumber|FirstName|LastName|Class|DOB
1|R001|John|Doe|10A|2006-04-15
```

**results.txt**

```
StudentID|Subject|Marks|Grade|ExamDate
1|Mathematics|88|A|2025-03-01
```

* Data is loaded automatically at startup and saved on exit or after CRUD operations.

---

## Usage

* Use the **Students** screen to add or edit student details.
* Use the **Results** screen to add exam results for students.
* Use the search box and filters in the toolbar to quickly find records.
* Click column headers to sort. Use filter controls to narrow results.
* Data is automatically saved to text files when modified.

---

## Configuration

* You can configure file paths (optional) in a `config.properties` file if implemented.
* You can switch FlatLaf themes by setting the theme at application startup (look for `FlatLightLaf`, `FlatDarkLaf`, etc.).

---

## Screenshots

*Add screenshots in the `docs/screenshots/` folder and reference them here.*

---

## Troubleshooting

* Ensure text files (`students.txt`, `results.txt`) are located in the correct directory (usually the root of the project or inside a `data/` folder).
* If FlatLaf fails to load, ensure it’s added to the classpath and initialized before creating Swing components:

```java
// Example FlatLaf initialization
try {
  UIManager.setLookAndFeel(new FlatLightLaf());
} catch (Exception ex) {
  System.err.println("Failed to initialize FlatLaf");
}
```

---

## Contributing

Contributions are welcome. Please open issues for bugs or feature requests and submit pull requests for proposed changes. Follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/my-feature`)
3. Commit your changes (`git commit -m "Add feature"`)
4. Push to the branch (`git push origin feature/my-feature`)
5. Open a Pull Request

---

## License

Specify a license (e.g., MIT). Example `LICENSE` file may be added to the repo.

---

## Contact

For questions or demo requests, contact: `your.email@example.com` or open an issue on GitHub.

---

### Notes / Optional Enhancements

* Add user authentication and role-based access control (admin, teacher, viewer).
* Add charting modules to visualize class performance trends.
* Add scheduled backups or cloud sync for data safety.
* Add import/export to CSV or JS
