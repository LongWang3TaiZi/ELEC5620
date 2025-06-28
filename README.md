# Intelligent Financial Management System

- This project develops a software that can be used by customers, banks, and (technical team) administrators. For customers, it aims to help users manage and analyze their assets, get financial advice, and solve financial confusion. For banks, it aims to help them develop better financial products and match more suitable customers.
- The Backend of an Intelligent Financial Management system built with Spring Boot and Gradle. This system supports user authentication, asset analysis, financial product management, and intelligent chat services.
- This project is a front-end development project based on 'vue3-Element'.

## Features

- **User Authentication**: Secure login and registration for users.
- **Asset Analysis**: Users can generate asset analysis reports by using openAI api.
- **Financial Product**: Bank can manage financial products and customer can browse products.
- **Intelligent Chat**: Users can chat with the system to get financial advice.


## Project Back-End Structure
```
backend/
├── src
│   ├── main
│   │   ├── java/com/elec5620/intelligentfinancial
│   │   │   ├── IntelligentFinancialApplication.java   # Main application class
│   │   │   ├── config/                 # Application configuration
│   │   │   ├── controller/             # REST controllers for handling HTTP requests
│   │   │   ├── dto/                    # Data Transfer Objects (DTOs) for API
│   │   │   ├── model/                  # Entity models
│   │   │   ├── repository/             # Data access layer interfaces
│   │   │   ├── service/                # Business logic layer
│   │   │   ├── utils/                  # Utility functions and helpers
│   │   ├── resources/
│   │       └── application.properties  # Configuration file
│   └── test
│       └── java/com/elec5620/intelligentfinancial
│           └── IntelligentFinancialApplicationTests.java  # Test cases
├── build.gradle                        # Gradle build file
├── settings.gradle                     # Gradle settings file
```

## Back-End

### Installation

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

### Usage

- Access the application at `http://localhost:8080` (default port).

### Configuration

Edit `src/main/resources/application.properties` to configure the database, server port, and external service integrations (Bank, Delivery, Email).

## Front-End

### Installation and Usage

- We recommend you install ‘vscode’ app to run this project.
- DOWNLOADS: https://code.visualstudio.com/Download
- You can choose the corresponding version (Windows, macOS or Linux) to download according to your operating system.

### Quick Start

- You need to create a new terminal in vscode, cd to the corresponding folder, and then enter the following code to quick start.

1. **Install Dependencies**:
  ```bash
  npm install
  ```

2. **Start the service**:
  ```bash
  npm start
  ```

##### Dependencies and Versions
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
