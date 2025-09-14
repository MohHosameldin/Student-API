# Student Records API

## Overview
The Student Records API is a minimal, reliable backend service designed to manage student information digitally. It serves as a centralized system to replace manual lists or spreadsheets, offering a simple and persistent way to handle student data through a RESTful API.

## Core Value Proposition
The API provides a straightforward, centralized, and persistent solution for managing student information, enabling efficient data operations without the need for manual record-keeping.

## Key Features
The API delivers the fundamental CRUD (Create, Read, Update, Delete) operations required for student data management:

- **Create**: Add a new student record to the system.
- **Read**: Retrieve a list of all students or look up a single student by their unique ID.
- **Update**: Modify the details of an existing student.
- **Delete**: Remove a student record from the system.

## Technical Viability
The API is built to be a viable product, not just a prototype, due to the following:

- **Persistent Storage**: Connects to a PostgreSQL database to ensure student data is saved permanently and persists across application restarts.
- **Direct Data Access**: Exposes clear and predictable API endpoints (e.g., `/api/students`) for seamless integration with other applications.
- **Basic Data Integrity**: Includes server-side validation to ensure critical fields, such as names and emails, are present and correctly formatted.

## Data Constraints
The API enforces the following constraints to ensure data integrity:

- **National ID**:
  - Must be a valid national ID with a total length 14 digits and a character set of digits only (0-9), no separators or special characters.
  - Cannot be duplicated; each national ID must be unique in the system.
- **Phone Number**:
  - Must be a valid Egyptian phone number which 11 digits total (including the leading mobile prefix). 
  - Cannot be duplicated; each phone number must be unique in the system.
- **Email**:
  - Must be a valid email format (e.g., `user@domain.com`).
  - Cannot be duplicated; each email address must be unique in the system.
- **Name**: Must be a non-empty string.

## Getting Started
1. **Prerequisites**:
   - PostgreSQL database (version 13 or higher recommended).
   - A compatible runtime environment for the API (e.g., Node.js, Python, etc., depending on implementation).

2. **Setup**:
   - Clone the repository: `git clone <https://github.com/MohHosameldin/Student-API>`.
   - Install dependencies: Refer to the project-specific setup instructions (e.g., `npm install` or `pip install -r requirements.txt`).
   - Configure the PostgreSQL database connection in the environment settings (e.g., set `DATABASE_URL` in a `.env` file).
   - Run database migrations to set up the schema, including constraints for unique national ID, phone number, and email.

3. **Running the API**:
   - Start the server: Follow the project-specific instructions (e.g., `npm start` or `python app.py`).
   - The API will be available at `http://localhost:3000/apistudents` (or the configured port).

## API Endpoints
| Method | Endpoint                        | Description                          |
|--------|---------------------------------|--------------------------------------|
| POST   | `/api/students`              | Create a new student record           |
| GET    | `/api/students`              | Retrieve a list of all students      |
| GET    | `/api/students/:id`          | Retrieve a student by ID             |
| PUT    | `/api/students/:id`          | Update an existing student record     |
| DELETE | `/api/students/:id`          | Delete a student record by ID         |

## Example Usage
**Create a Student**:
```bash
curl -X POST http://localhost:3000/api/students \
-H "Content-Type: application/json" \
-d '{"name": "John Doe", "email": "john.doe@example.com", "national_id": "1234567890", "phone_number": "+1-555-555-5555"}'
```

**Retrieve a Student**:
```bash
curl http://localhost:3000/api/students/1
```

## Data Validation
- **Name**: Must be a non-empty string.
- **Email**: Must be a valid email format (e.g., `user@domain.com`) and unique.
- **National ID**: Must follow the valid format for the specific country and be unique.
- **Phone Number**: Must be a valid phone number format and unique.
- Additional fields may be supported depending on the implementation.

## Database Schema
The PostgreSQL database includes a `students` table with the following structure:
```sql
CREATE TABLE students (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  national_id VARCHAR(50) NOT NULL UNIQUE,
  phone_number VARCHAR(20) NOT NULL UNIQUE
);
```

## Contributing
Contributions are welcome! Please submit a pull request or open an issue to discuss improvements or bug fixes.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
