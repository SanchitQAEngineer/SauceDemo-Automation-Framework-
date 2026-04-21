# Installation Guide - Selenium Automation Framework

This guide will help you set up the Selenium Automation Framework on your Windows machine.

## Prerequisites

Before setting up the framework, ensure you have:
- Windows 10 or later
- Administrator access (for installation)
- Internet connection (to download dependencies)
- At least 500MB free disk space

## Step-by-Step Installation

### Step 1: Install Java Development Kit (JDK) 11 or Higher

1. **Download JDK**
   - Go to: https://www.oracle.com/java/technologies/downloads/
   - Select "Java SE 11" or higher
   - Choose "Windows x64 Installer"
   - Click Download and accept license terms

2. **Install JDK**
   - Run the installer
   - Choose installation directory (default: `C:\Program Files\Java\jdk-11`)
   - Complete the installation

3. **Verify Java Installation**
   - Open Command Prompt
   - Run: `java -version`
   - You should see Java version information

4. **Set JAVA_HOME Environment Variable** (if not set automatically)
   - Right-click "This PC" or "My Computer"
   - Select "Properties"
   - Click "Advanced system settings"
   - Click "Environment Variables"
   - Click "New" under System variables
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-11` (or your JDK path)
   - Click OK and restart Command Prompt

### Step 2: Install Maven

1. **Download Maven**
   - Go to: https://maven.apache.org/download.cgi
   - Download "Binary zip archive" (e.g., apache-maven-3.9.x-bin.zip)

2. **Extract Maven**
   - Extract the downloaded zip file
   - Recommended location: `C:\Maven` or `C:\apache-maven-3.9.x`

3. **Add Maven to PATH**
   - Right-click "This PC" or "My Computer"
   - Select "Properties"
   - Click "Advanced system settings"
   - Click "Environment Variables"
   - Under System variables, find or create "Path"
   - Click Edit
   - Click New
   - Enter: `C:\Maven\bin` (or your Maven bin path)
   - Click OK repeatedly to close all dialogs
   - **Restart Command Prompt or PowerShell**

4. **Verify Maven Installation**
   - Open new Command Prompt or PowerShell
   - Run: `mvn -version`
   - You should see Maven version and Java path

### Step 3: Download Project

#### Option A: Using Git (Recommended)
```bash
git clone <repository-url>
cd AutomationFramework
```

#### Option B: Manual Download
1. Download the project as ZIP
2. Extract to desired location
3. Navigate to project folder in Command Prompt

### Step 4: Install Framework Dependencies

1. **Open Command Prompt or PowerShell**
   - Navigate to project directory:
   ```bash
   cd C:\Users\SANCHIT\IdeaProjects\AutomationFramework
   ```

2. **Install Maven Dependencies**
   ```bash
   mvn clean install
   ```
   - This will download all required dependencies
   - First run may take 5-10 minutes
   - Requires internet connection

3. **Wait for Completion**
   - You should see: `BUILD SUCCESS`
   - If you see errors, check:
     - Internet connection
     - Firewall settings
     - Java and Maven paths

### Step 5: Generate Test Data

1. **Create Test Data Excel File**
   ```bash
   mvn exec:java -Dexec.mainClass="utils.ExcelDataCreator"
   ```

2. **Verify File Creation**
   - Check folder: `test-data/`
   - Should contain: `LoginTestData.xlsx`
   - File size: ~15-20 KB

### Step 6: Verify Installation

1. **Compile Project**
   ```bash
   mvn clean compile
   ```
   - Should see: `BUILD SUCCESS`

2. **Check Project Structure**
   - Verify all folders exist:
     - `src/test/java/base/`
     - `src/test/java/pages/`
     - `src/test/java/tests/`
     - `src/test/java/utils/`
     - `src/test/java/listeners/`
     - `test-data/`

3. **Quick Test Run** (Optional)
   ```bash
   mvn test -Dtest=LoginTest#testValidLogin -DskipITs
   ```

## Troubleshooting Installation

### Issue 1: "mvn is not recognized"
**Solution:**
- Maven is not in PATH
- Run `mvn -version` to verify
- Follow Step 2 again to add Maven to PATH
- Restart Command Prompt after changing PATH

### Issue 2: "Java not found or wrong version"
**Solution:**
- Check Java version: `java -version`
- Ensure Java 11 or higher
- Set JAVA_HOME environment variable
- Restart Command Prompt

### Issue 3: "BUILD FAILURE" with dependency errors
**Solution:**
- Check internet connection
- Try: `mvn clean install -U` (force update)
- Check firewall/proxy settings
- Try: `mvn install -X` (debug mode)

### Issue 4: "test-data/LoginTestData.xlsx not found"
**Solution:**
- Run: `mvn exec:java -Dexec.mainClass="utils.ExcelDataCreator"`
- Or create manually following the structure in `test-data/README.txt`

### Issue 5: Port already in use
**Solution:**
- If running local server, change port in configuration
- Or stop the application using the port

## IDE Setup (Optional but Recommended)

### IntelliJ IDEA

1. **Open Project**
   - File → Open
   - Select project folder
   - Click OK

2. **Configure SDK**
   - File → Project Structure
   - Select Java 11 SDK
   - Apply and OK

3. **Maven Auto-Discovery**
   - Should recognize `pom.xml` automatically
   - Check Maven tool window

4. **Run Tests**
   - Right-click test class
   - Select "Run"

### Eclipse

1. **Import Project**
   - File → Import → Existing Maven Projects
   - Select project folder

2. **Configure JRE**
   - Right-click project → Properties
   - Java Compiler → Installed JREs
   - Select Java 11+

3. **Run Tests**
   - Right-click test class
   - Run As → TestNG Test

### Visual Studio Code

1. **Install Extensions**
   - Java Extension Pack
   - Maven for Java
   - TestNG Runner (optional)

2. **Open Folder**
   - File → Open Folder
   - Select project folder

3. **Run Tests**
   - Terminal → New Terminal
   - Run: `mvn test`

## Verify Complete Setup

Run this checklist to verify everything is installed:

- [ ] Java installed: `java -version` shows Java 11+
- [ ] Maven installed: `mvn -version` shows Maven 3.6+
- [ ] Project cloned/extracted
- [ ] Dependencies installed: `mvn install` succeeded
- [ ] Test data created: `test-data/LoginTestData.xlsx` exists
- [ ] Project compiles: `mvn compile` succeeds
- [ ] IDE recognized project (if using IDE)
- [ ] Can run sample test successfully

## First Test Run

After completing installation:

1. **Run All Tests**
   ```bash
   mvn clean test
   ```

2. **View Results**
   - Terminal will show test execution progress
   - Look for: `[INFO] BUILD SUCCESS`

3. **Check Reports**
   - Open: `reports/ExtentReport_<timestamp>.html` in web browser
   - View: Screenshots in `screenshots/` folder

4. **Troubleshoot Failures** (if any)
   - Check test logs in console output
   - Review screenshots for what happened
   - Check Extent Report for detailed failure info

## System Requirements Summary

| Component | Requirement | Recommended |
|-----------|-------------|------------|
| Java | JDK 11+ | JDK 17 or 21 |
| Maven | 3.6.0+ | 3.9.0+ |
| Memory | 2GB | 4GB+ |
| Disk Space | 500MB | 1GB+ |
| Browsers | Chrome 90+ | Latest |
| OS | Windows 10 | Windows 11 |

## Environment Variables Reference

After installation, your environment should have:

| Variable | Value | Example |
|----------|-------|---------|
| JAVA_HOME | JDK installation path | `C:\Program Files\Java\jdk-11` |
| Path | Contains Java and Maven bin | `...;C:\Program Files\Java\jdk-11\bin;C:\Maven\bin;...` |

## What's Installed

### Maven Dependencies
- **Selenium WebDriver**: Web automation library
- **TestNG**: Testing framework
- **Extent Reports**: Reporting library
- **Apache POI**: Excel reading/writing
- **WebDriverManager**: WebDriver management

### Project Components
- **Base Test Class**: Handles browser setup/teardown
- **Page Objects**: Encapsulates page elements and actions
- **Test Classes**: Contains test cases
- **Utilities**: Helper classes for common operations
- **Listeners**: TestNG listener for reporting
- **Configuration**: Framework settings and properties

## Next Steps

After successful installation:

1. **Read SETUP_GUIDE.md** for detailed framework usage
2. **Explore the code** to understand structure
3. **Create new tests** following existing patterns
4. **Configure testng.xml** for your test scenarios
5. **Set up CI/CD** for automated testing

## Getting Help

### Official Resources
- Maven: https://maven.apache.org/
- Java: https://docs.oracle.com/en/java/
- Selenium: https://www.selenium.dev/
- TestNG: https://testng.org/

### Common Issues Database
Check `SETUP_GUIDE.md` for more troubleshooting tips

### Community Support
- Stack Overflow
- GitHub Issues
- Test automation forums

## Installation Checklist

Print or save this checklist:

```
Installation Verification Checklist
====================================

□ Java 11 installed
□ Maven 3.6+ installed
□ JAVA_HOME variable set
□ Maven in PATH
□ Project downloaded
□ Maven dependencies installed
□ Test data Excel created
□ Project compiles successfully
□ First test runs successfully
□ Reports generated correctly
□ All tests pass (expected)
```

---

**Installation Guide Version:** 1.0
**Last Updated:** April 16, 2026
**Framework Version:** 1.0-SNAPSHOT

For more information, see README.md and SETUP_GUIDE.md

