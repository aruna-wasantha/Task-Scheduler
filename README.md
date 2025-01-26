# Task Scheduler System

## Overview
The Task Scheduler System is a Java Spring Boot application that performs CRUD operations for scheduling tasks. It periodically checks the database every 30 seconds, triggers scheduled events, calls a mock API, and logs the results.

---

## Prerequisites
1. **Docker** installed and running.
2. **Java 21** or later installed.
3. **Postman** to test the API using the provided collection.

---

## Setup Instructions

### Option 1: Run Using Docker

#### Step 1: Set up MongoDB and Services Using Docker
1. Create a `docker-compose.yml` file with the following content:

   ```yaml
   version: '3.8'

   services:
     mongodb-scheduler:
       image: mongo:latest
       container_name: mongodb-scheduler
       ports:
         - "27017:27017"
       volumes:
         - mongodb_data:/data/db
       networks:
         - app-network

     mock-api-service:
       image: arunawasantha/mock-api-service:latest
       container_name: mock-api-service
       ports:
         - "7000:7000"
       networks:
         - app-network

     scheduler-service:
       image: arunawasantha/scheduler-service:latest
       container_name: scheduler-service
       ports:
         - "9000:9000"
       environment:
         - MONGODB_URI=mongodb://mongodb-scheduler:27017/schedules
         - MOCK_API_URL=http://mock-api-service:7000/api/mock-api
       networks:
         - app-network
       depends_on:
         - mongodb-scheduler

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
   This will start MongoDB, the mock API service, and the scheduler service locally.

---

### Option 2: Run Applications Using IntelliJ IDEA

#### Step 1: Clone the Projects
1. Clone the Mock API project:
   ```bash
   git clone https://github.com/aruna-wasantha/mock-api.git
   ```
2. Clone the Task Scheduler project:
   ```bash
   git clone https://github.com/aruna-wasantha/Task-Scheduler.git
   ```

#### Step 2: Run Applications in IntelliJ IDEA
1. Open IntelliJ IDEA and import both projects (`mock-api` and `Task-Scheduler`) as Maven projects.
2. Ensure the required dependencies are downloaded by building the projects:
   ```bash
   ./mvnw clean install
   ```
3. To run the Mock API project:
   - Navigate to the `mock-api` project in IntelliJ.
   - Locate the main class (e.g., `MockApiApplication.java`) and run it.
4. To run the Task Scheduler project:
   - Navigate to the `Task-Scheduler` project in IntelliJ.
   - Locate the main class (e.g., `TaskSchedulerApplication.java`) and run it.

---

### Step 3: Import Postman Collection
1. Download the Postman collection from:
   ```
   https://github.com/aruna-wasantha/scheduller-api.git
   ```
2. Open Postman and import the downloaded collection.
3. Use the provided endpoints to interact with the Task Scheduler API.

---

## Application Features
1. **CRUD Operations**: Create, Read, Update, and Delete scheduled tasks via API.
2. **Event Triggering**: The system checks the database every 30 seconds to find scheduled tasks.
3. **Mock API Integration**: Executes the scheduled events by calling the Mock API and logs the results.

---

## Additional Notes
- Use Docker to simplify the setup and deployment of the services.
- When running locally with IntelliJ IDEA, ensure MongoDB is running.
- Logs will provide details of triggered events and their results.

