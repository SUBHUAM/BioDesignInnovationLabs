# BioDesignInnovationLabs Dashboard and Backend

This project is a full-stack web application for managing and visualizing medical data. It consists of a responsive front-end built with React and a robust back-end developed using Spring Boot, connected to a PostgreSQL database. The application handles CRUD operations for patient data, includes advanced security features, and displays the data through interactive charts and tables. The application also includes performance optimization and security enhancements for handling sensitive medical information.

## Table of Contents
- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Assumptions Made](#assumptions-made)

## Screenshots

Here are some screenshots of the Medical Dashboard Application in action:


### 1. Dashboard Overview
![Dashboard Overview](https://github.com/user-attachments/assets/52e258e6-431f-4758-adda-a787ba87d2bd)
*This is the main dashboard view where users can see an overview of patient data and statistics.*

![Dark Mode Toggle](https://github.com/user-attachments/assets/8b8ece7d-1c3f-4b7f-93e7-ed7972e2b486)

### 2. Tables
![Doctor Table](https://github.com/user-attachments/assets/b19df863-95ca-466c-9524-6e63b367ce30)


### 3. Sign Up
![Sign Up](https://github.com/user-attachments/assets/1e3b7764-e8e0-4a4d-ba86-2ca8a2267b07)


### 4. Login
![Login]![SignUp](https://github.com/user-attachments/assets/85840849-af6e-4cf2-841f-891cb69cfb35)


### 4. Database with Encripted Email id of patients.
![Database](https://github.com/user-attachments/assets/0054e942-5393-45c7-946c-775d947678f3)



## Project Overview

The Medical Dashboard Application provides a secure, responsive platform for managing and visualizing medical data. It is designed to handle patient data efficiently and securely, while offering users an intuitive and interactive interface to monitor and analyze medical information.

## Architecture

The application follows a typical client-server architecture, where:

- **Front-End:** Built using React (with Vite as the build tool), TailwindCSS for styling, and ApexCharts for data visualization.
- **Back-End:** Developed using Java with Spring Boot, connected to a PostgreSQL database for persistent storage. The back-end includes RESTful API endpoints for data management, authentication, and security features.
- **Security:** Sensitive data is encrypted, and access is controlled using Role-Based Access Control (RBAC) implemented through Spring Security.
- **Caching and Performance:** The application uses Ecache to optimize database query performance and reduce load on the server.

## Technologies Used

**Frontend:**

- React (with Vite)
- JavaScript, HTML, CSS
- TailwindCSS
- ApexCharts
- Hero Icons

**Backend:**

- Java, Spring Boot
- PostgreSQL
- Spring Security
- Lombok
- SLF4J (for logging)
- Ecache (for caching)

## Setup Instructions

### Frontend (React using Vite)

1. Clone the Repository:

    ```bash
    git clone https://github.com/SUBHUAM/BioDesignInnovationLabs.git
    cd your-repository/frontend
    ```

2. Install Dependencies:

    ```bash
    npm install
    ```

3. Run the Development Server:

    ```bash
    npm run dev
    ```

4. Build for Production:

    ```bash
    npm run build
    ```

### Backend (Spring Boot)

1. Clone the Repository:

    ```bash
    git clone https://github.com/SUBHUAM/BioDesignInnovationLabs.git
    cd your-repository/backend
    ```

2. Update Application Properties: Modify `src/main/resources/application.properties` to configure the PostgreSQL database connection and security settings.

3. Run the Application:

    ```bash
    ./mvnw spring-boot:run
    ```

4. API Endpoints:
    - CRUD endpoints for managing patient data
    - Secure endpoints for handling sensitive data (e.g., `api/patients/view`, `api/patients/update/{id}}`)

## Assumptions Made

- The application is expected to handle user roles such as Admin, Doctor.
- Performance optimization was a priority, especially with large patient datasets.

