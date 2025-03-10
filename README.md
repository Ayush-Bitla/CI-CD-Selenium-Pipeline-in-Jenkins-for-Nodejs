# Running Selenium Test Cases Using Jenkins Pipeline

## Overview
This repository contains a **Jenkins pipeline script** to automate the execution of **Selenium test cases**. The pipeline fetches the code, compiles it, runs Selenium tests, and generates reports.

## Prerequisites
Before setting up the Jenkins pipeline, ensure the following dependencies are installed:

- **Jenkins** (latest version)
- **Maven** (for managing Java dependencies)
- **Git** (for fetching the repository)
- **Selenium WebDriver** (configured in the test project)
- **Java (JDK 8 or later)**

## Setting Up Jenkins Pipeline Job

### **Step 1: Create a New Pipeline Job**
1. Open **Jenkins Dashboard** → Click on **New Item**.
2. Enter a job name and select **Pipeline** → Click **OK**.
3. Scroll down to the **Pipeline** section and choose **Pipeline script**.

### **Step 2: Define the Jenkinsfile Pipeline Script**
Copy and paste the following pipeline script in the Pipeline section:

```groovy
pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/your-repo.git' // Replace with your repo URL
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Generate Report') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }
}
```

### **Step 3: Run the Pipeline**
1. Click **Save** and then **Build Now**.
2. The pipeline will:
   - Fetch the source code from GitHub.
   - Compile the project using Maven.
   - Run Selenium test cases.
   - Publish the test reports.
3. Monitor progress in the **Console Output**.

## **Project Structure**
```
📂 selenium-jenkins-pipeline/
 ├── 📂 src/
 │   ├── 📂 test/
 │   │   ├── 📂 java/
 │   │   │   ├── 📄 SampleTest.java  # Selenium test cases
 ├── 📄 pom.xml  # Maven dependencies
 ├── 📄 Jenkinsfile  # Jenkins pipeline script
```

## **Troubleshooting**
- **Jenkins is not recognizing 'mvn' command?**
  - Ensure **Maven** is installed and added to the system **PATH**.
- **Tests fail due to WebDriver issues?**
  - Check that the WebDriver **binary path** is configured in `src/test/resources/config.properties`.
- **Pipeline fails at the Git clone stage?**
  - Verify that Jenkins has the necessary **Git credentials**.
