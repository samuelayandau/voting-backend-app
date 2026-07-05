# Voting Application Backend

A Spring Boot RESTful API backend for a real-time voting (polling) application. It allows users to create polls with custom voting options, fetch active polls (all or specific ones by ID), and submit votes to increment options.

---

## 🛠️ Tech Stack

- **Java**: Version 17
- **Framework**: Spring Boot (v4.0.6)
  - Spring Web (MVC)
  - Spring Data JPA
- **Database**: MySQL
- **Dependencies**: 
  - Lombok (for reducing boilerplate code)
  - Hibernate (JPA provider)
- **Build Tool**: Maven

---

## ⚙️ Configuration & Prerequisites

Before running the application, make sure you have:
1. **Java Development Kit (JDK) 17** installed.
2. **MySQL Server** installed and running on your system.

### Database Setup
1. Create a database named `voting-app` in MySQL:
   ```sql
   CREATE DATABASE `voting-app`;
   ```
2. Open the [application.properties](file:///c:/Users/HP/Desktop/SPRING-BOOT-PROJECT/votingapp/src/main/resources/application.properties) file and update the database credentials to match your local setup:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/voting-app
   spring.datasource.username=root
   spring.datasource.password=YOUR_PASSWORD
   ```

---

## 🚀 How to Run the Application

You can start the backend application using the Maven wrapper included in the project.

### Windows (PowerShell/CMD):
```powershell
./mvnw.cmd spring-boot:run
```

### Linux / macOS:
```bash
chmod +x mvnw
./mvnw spring-boot:run
```

Once started, the API server will run at: `http://localhost:8080`

---

## 🔌 API Endpoints Documentation

The REST endpoints are exposed under `/api/polls` through the [PollController](file:///c:/Users/HP/Desktop/SPRING-BOOT-PROJECT/votingapp/src/main/java/com/voting/votingapp/controller/PollController.java).

### 1. Create a Poll
Create a new poll with a question and multiple options.

* **Endpoint**: `POST /api/polls`
* **Content-Type**: `application/json`
* **Request Body Sample**:
  ```json
  {
    "question": "What is your favorite programming language?",
    "options": [
      { "voteOption": "Java" },
      { "voteOption": "Python" },
      { "voteOption": "JavaScript" },
      { "voteOption": "C++" }
    ]
  }
  ```
* **Response Sample (200 OK)**:
  ```json
  {
    "id": 1,
    "question": "What is your favorite programming language?",
    "options": [
      { "voteOption": "Java", "voteCount": 0 },
      { "voteOption": "Python", "voteCount": 0 },
      { "voteOption": "JavaScript", "voteCount": 0 },
      { "voteOption": "C++", "voteCount": 0 }
    ]
  }
  ```

---

### 2. Get All Polls
Retrieve a list of all polls and their current option vote tallies.

* **Endpoint**: `GET /api/polls`
* **Response Sample (200 OK)**:
  ```json
  [
    {
      "id": 1,
      "question": "What is your favorite programming language?",
      "options": [
        { "voteOption": "Java", "voteCount": 2 },
        { "voteOption": "Python", "voteCount": 1 },
        { "voteOption": "JavaScript", "voteCount": 0 },
        { "voteOption": "C++", "voteCount": 0 }
      ]
    }
  ]
  ```

---

### 3. Get Poll by ID
Retrieve a specific poll using its unique database identifier.

* **Endpoint**: `GET /api/polls/{id}`
* **Response Sample (200 OK)**:
  ```json
  {
    "id": 1,
    "question": "What is your favorite programming language?",
    "options": [
      { "voteOption": "Java", "voteCount": 2 },
      { "voteOption": "Python", "voteCount": 1 },
      { "voteOption": "JavaScript", "voteCount": 0 },
      { "voteOption": "C++", "voteCount": 0 }
    ]
  }
  ```

---

### 4. Cast a Vote
Submit a vote for a specific option in a poll.

* **Endpoint**: `POST /api/polls/vote`
* **Content-Type**: `application/json`
* **Request Body Sample**:
  ```json
  {
    "pollId": 1,
    "optionIndex": 0
  }
  ```
  *(Note: `optionIndex` is 0-indexed. In the example above, `0` corresponds to the first option, which is "Java".)*
* **Response**: `200 OK` (No response body)

---

## 📂 Project Structure

- **[`VotingappApplication`](file:///c:/Users/HP/Desktop/SPRING-BOOT-PROJECT/votingapp/src/main/java/com/voting/votingapp/VotingappApplication.java)**: The main entry point of the Spring Boot application.
- **[`controller/PollController`](file:///c:/Users/HP/Desktop/SPRING-BOOT-PROJECT/votingapp/src/main/java/com/voting/votingapp/controller/PollController.java)**: Exposes REST API endpoints.
- **[`service/PollService`](file:///c:/Users/HP/Desktop/SPRING-BOOT-PROJECT/votingapp/src/main/java/com/voting/votingapp/service/PollService.java)**: Contains core business logic (e.g., tallying votes and fetching polls).
- **[`repository/PollRepository`](file:///c:/Users/HP/Desktop/SPRING-BOOT-PROJECT/votingapp/src/main/java/com/voting/votingapp/repository/PollRepository.java)**: Manages database persistence via Spring Data JPA.
- **[`model/Poll`](file:///c:/Users/HP/Desktop/SPRING-BOOT-PROJECT/votingapp/src/main/java/com/voting/votingapp/model/Poll.java)** & **[`OptionVote`](file:///c:/Users/HP/Desktop/SPRING-BOOT-PROJECT/votingapp/src/main/java/com/voting/votingapp/model/OptionVote.java)**: Entities defining database structure.
- **[`request/Vote`](file:///c:/Users/HP/Desktop/SPRING-BOOT-PROJECT/votingapp/src/main/java/com/voting/votingapp/request/Vote.java)**: Simple DTO representing a vote input.
