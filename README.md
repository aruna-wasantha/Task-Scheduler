# Task Scheduler System

## Overview
The Task Scheduler System is a Java Spring Boot application that performs CRUD operations for scheduling tasks. It periodically checks the database every 30 seconds, triggers scheduled events, calls a mock API, and logs the results.

---

## Prerequisites
1. **Docker** installed and running.
2. **MongoDB** setup using Docker.
3. **Java 21** or later installed.
4. **Postman** to test the API using the provided collection.

---

## Setup Instructions

### Step 1: Set up MongoDB using Docker
1. Create a `docker-compose.yml` file with the following content:

   ```yaml
   version: '3.8'

   services:
     mongodb:
       image: mongo:latest
       container_name: mongodb
       ports:
         - "27017:27017"
       volumes:
         - mongodb_data:/data/db
       networks:
         - app-network

   volumes:
     mongodb_data:

   networks:
     app-network:
       driver: bridge
   ```

2. Run the following command in the same directory as the `docker-compose.yml` file:
   ```bash
   docker-compose up -d
   ```
   This will start a MongoDB instance locally.

---

### Step 2: Clone the Projects
1. Clone the Mock API project:
   ```bash
   git clone https://github.com/aruna-wasantha/mock-api.git
   ```
2. Clone the Task Scheduler project:
   ```bash
   git clone https://github.com/aruna-wasantha/Task-Scheduler.git
   ```

---

### Step 3: Import Postman Collection
1. Download the Postman collection from:
   ```
   https://github.com/aruna-wasantha/scheduller-api.git
   ```
2. Open Postman and import the downloaded collection.
3. Use the provided endpoints to interact with the Task Scheduler API.

---

### Step 4: Run the Application
1. Start the Mock API project:
    - Navigate to the cloned `mock-api` open with Intelij idea and run the application (requires JDK 21).
2. Start the Task Scheduler project:
    - Navigate to the cloned `Task-Scheduler` directory  open with Intelij idea and run the application (requires JDK 21).

---

## Application Features
1. **CRUD Operations**: Create, Read, Update, and Delete scheduled tasks via API.
2. **Event Triggering**: The system checks the database every 30 seconds to find scheduled tasks.
3. **Mock API Integration**: Executes the scheduled events by calling the Mock API and logs the results.

---

## Additional Notes
- Ensure MongoDB is running locally before starting the application.
- Use the Postman collection for testing and interacting with the APIs.
- Logs will provide details of triggered events and their results.

