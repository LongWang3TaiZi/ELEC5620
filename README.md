# Intelligent Financial Management System

- This project is an intelligent financial management system designed for customers, banks and administrators. It aims to enhance the financial service experience through intelligent means. The system adopts a front-end and back-end separation architecture. The back-end is based on Spring Boot, and the front-end is based on Vue3 + Element Plus. It supports functions such as user asset management, intelligent analysis, financial product management and intelligent question answering.

## Features

- **Intelligent Asset Analysis**: Integrated with OpenAI API, it automatically generates personalized asset analysis reports and suggestions.
- **Multi-Role Support**: Supports three types of users: customers, banks, and administrators, with clear permissions and rich functions.
- **Financial Product Management**: Banks can release products, and customers can browse, collect, and recommend products.
- **Intelligent Q&A Bot**: Built-in intelligent chatbot provides real-time financial consultation.
- **Front-End and Back-End Separation**: RESTful API design, easy to maintain and expand.
- **Responsive Front-End**: Based on Vue3 + Element Plus, with a beautiful interface and friendly interaction.
- **Multi-Language Support**: Built-in Chinese and English switching to meet the needs of different users.
- **Comprehensive Unit Testing**: Ensures system stability and reliability.

## Technology Stack 
- **Backend**: Spring Boot, Java, Gradle, JPA, OpenAI API
- **Frontend**: Vue3, Element Plus, Pinia, Vue Router, Vite
- **Database**: Supports multiple database configurations (see `application.properties` for details)
- **Others**: JWT authentication, modular development, unit testing

## Project Back-End Structure
```
Backend/
├── controller/ # REST API controller
├── service/    # Business logic layer
├── repository/ # Data access layer
├── model/      # Entity class
├── dto/        # Data transfer object
├── utils/      # Utility class
└── resources/  # Configuration files 

Frontend/
├── src/
├── api/        # Front-end API encapsulation
├── components/ # Common Components
├── views/      # Page Views
├── router/     # Route Configuration
├── pinia/      # State Management
└── assets/     # Static Resources
```
## Main Functions 

### User End

  - Registration, login, personal information management
  - Asset entry and analysis, generation of asset reports
  - Browse, collect, recommend financial products
  - Intelligent question answering and financial consultation 

### Bank End

  - Financial product release and management
  - Customer management and recommendation 

### Administrator End

  - User and Permission Management
  - System Data Statistics and Maintenance 

## Quick Start

### Back-End

1. **Build the Project**:
   Make sure you have Gradle installed. Run the following command to build:
    ```bash
    ./gradlew build
    ```

2. **Run the Application**:
   Start the application using:
    ```bash
    ./gradlew bootRun
    ```

3. **Usage**:

  - Access the application at `http://localhost:8080` (default port).

4. **Configuration**:

  - Edit `src/main/resources/application.properties` to configure the database, server port, and external service integrations (Bank, Delivery, Email).

### Front-End

- We recommend you install ‘vscode’ app to run this project.
- DOWNLOADS: https://code.visualstudio.com/Download
- You can choose the corresponding version (Windows, macOS or Linux) to download according to your operating system.
- You need to create a new terminal in vscode, cd to the corresponding folder, and then enter the following code to quick start.

1. **Install Dependencies**:
    ```bash
    npm install
    ```

2. **Start the service**:
    ```bash
    npm start
    ```

3. **Dependencies and Versions**:

    | Dependencies                                 | Versions|
    | -------------------------------------------- | ------- |
    | axios                                        | ^0.21.1 |
    | pinia                                        | ^2.0.14 |
    | vue                                          | ^3.2.33 |
    | vue-router                                   | ^4.0.5  |
    | vuex                                         | ^4.0.0  |
    | @ehutch79/vite-eslint                        | 0.0.1   |
    | @vitejs/plugin-vue                           | ^1.2.3  |
    | @vue/compiler-sfc                            | ^3.1.2  |
    | @vue/eslint-config-prettier                  | ^6.0.0  |
    | autoprefixer                                 | ^10.2.5 |
    | babel-eslint                                 | ^10.1.0 |
    | crypto-js                                    | ^4.0.0  |
    | element-plus                                 | ^2.2.13 |
    | eslint                                       | ^6.7.2  |
    | eslint-plugin-prettier                       | ^3.1.3  |
    | eslint-plugin-vue                            | ^7.0.0-0|
    | husky                                        | ^1.3.1  |
    | lint-staged                                  | ^9.5.0  |
    | prettier                                     | ^1.19.1 |
    | sass                                         | ^1.41.1 |
    | vite                                         | ^2.9.18 |
    | vite-plugin-mock                             | ^2.3.0  |
    | vite-plugin-svg-icons                        | ^0.4.0  |
    | vue-i18n                                     | ^9.0.0  |
