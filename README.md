# Library Management System - Object Databases (OBD)

This project is a Java-based application designed to manage a library's book collection using Object Databases (OBD) principles. It implements the Data Access Object (DAO) pattern and uses POJOs to interact with the database.

## Project Structure

The project is organized into the following components:

- **POJO (Plain Old Java Object):** `Book.java` - Represents the Book entity with attributes like title, author, ISBN, etc.
- **DAO (Data Access Object):** `BookDAO.java` - Contains the logic for database operations (CRUD and specialized queries).
- **Main Class:** `Main.java` - Executes all operations to demonstrate functionality and ensure consistency.

## Book Entity Attributes

| Attribute | Type | Notes |
| :--- | :--- | :--- |
| `id` | Integer | Primary Key (Auto-generated) |
| `title` | String | Title of the book |
| `author` | String | Full name of the author |
| `isbn` | String | International Standard Book Number |
| `publicationYear` | Integer | Year the book was published |
| `genre` | String | Genre/Category |
| `price` | Double | Unit price |
| `availableCopies` | Integer | Number of copies in stock |
| `isBestSeller` | Boolean | Highlighted status |

## Implemented Queries

The `BookDAO` includes the following functionalities:

1.  **Basic CRUD:** Insertion (`insertBook`), deletion by ID, and retrieval by ID or full list.
2.  **Filtering:**
    - By Genre.
    - Low stock (less than 3 copies).
    - Modern books (published from 2000 onwards).
    - Author name search (partial matches).
3.  **Statistics & Analytics:**
    - Top 5 most expensive books.
    - Total count of distinct books.
    - Average price of the entire collection.
    - Identification of the oldest book.
4.  **Grouping:**
    - Count of books per genre.
    - Average price per genre.
    - Genres with high availability (>100 copies).

## How to Run

1.  Ensure you have the required OBD libraries configured in your environment.
2.  The `Main` class will initialize the `EntityManagerFactory` and the `BookDAO`.
3.  It will populate the database with test data and perform the required queries, printing results to the console.

## Evaluation Criteria

This project is evaluated based on:
- **POJO Accuracy:** Correct mapping of fields and types.
- **DAO Quality:** Correct implementation of JPQL/Criteria API queries and proper return types.
- **Execution:** Demonstrable proof of all operations in the `Main` class.
