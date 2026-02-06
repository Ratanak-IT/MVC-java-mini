Java MVC – Student CRUD Application
📘 Description

This project is a Java MVC (Model–View–Controller) lesson that demonstrates how to manage a list of students using CRUD operations:

Create

Read (List all students)
Update
Delete
The application uses basic Java and collections to help beginners understand MVC architecture.

🏗 MVC Architecture
Model

Represents the data and business logic
Contains the Student class
Stores student attributes (id, name, gender, dateOfBirth, createdAt)

View

Displays information to the user
Uses console output (System.out)
Handles user input with Scanner
Controller
Controls the application flow
Connects Model and View
Performs CRUD operations

✨ Features

Add new student
View all students
Update student details
Delete student by ID
Clear MVC structure

src/
├── controller/
│   └── StudentController.java
│
├── db/
│   └── StudentDB.java
│
├── exception/
│   └── (Custom exceptions)
│
├── mapper/
│   └── StudentMapper.java
│
├── model/
│   ├── dao/
│   │   ├── StudentDao.java
│   │   └── StudentDaoImpl.java
│   │
│   ├── dto/
│   │   ├── StudentRequestDto.java
│   │   └── StudentResponseDto.java
│   │
│   ├── entities/
│   │   └── Student.java
│   │
│   └── service/
│       ├── StudentService.java
│       └── StudentServiceImpl.java
│
├── view/
│   └── StudentView.java
│
└── StudentApp.java
