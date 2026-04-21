@echo off
REM Selenium Automation Framework - Complete Setup Script
REM This script installs dependencies and sets up the framework

echo ============================================
echo Selenium Automation Framework Setup
echo ============================================
echo.

echo Checking for Java...
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Java not found!
    echo.
    echo Please install Java JDK 11 or higher:
    echo 1. Download from: https://www.oracle.com/java/technologies/downloads/
    echo 2. Choose "Windows x64 Installer"
    echo 3. Install and add to PATH
    echo 4. Restart command prompt and run this script again
    echo.
    pause
    exit /b 1
) else (
    echo ✓ Java found
)

echo.
echo Checking for Maven...
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Maven not found!
    echo.
    echo Please install Maven:
    echo 1. Download from: https://maven.apache.org/download.cgi
    echo 2. Extract to C:\Maven (or any folder)
    echo 3. Add bin folder to PATH (e.g., C:\Maven\bin)
    echo 4. Restart command prompt and run this script again
    echo.
    pause
    exit /b 1
) else (
    echo ✓ Maven found
)

echo.
echo Installing Maven dependencies...
echo This may take 5-10 minutes on first run...
mvn clean install -DskipTests
if %errorlevel% neq 0 (
    echo ERROR: Failed to install dependencies!
    echo Check your internet connection and try again.
    pause
    exit /b 1
)

echo.
echo Generating test data Excel file...
mvn exec:java -Dexec.mainClass="utils.ExcelDataCreator"
if %errorlevel% neq 0 (
    echo WARNING: Could not generate test data, but framework should work
)

echo.
echo ============================================
echo Setup Complete!
echo ============================================
echo.
echo Your framework is now ready!
echo.
echo Next steps:
echo 1. Open the project in your IDE (IntelliJ IDEA recommended)
echo 2. Run tests using: mvn clean test
echo 3. View reports in: reports/ folder
echo 4. Check screenshots in: screenshots/ folder
echo.
echo Alternative test execution:
echo - Use PowerShell script: .\run-tests.ps1 -Action all
echo - Run specific tests: mvn test -Dtest=LoginTest
echo.
echo Documentation:
echo - Start with: 00_START_HERE.md
echo - Complete docs: README.md
echo - Setup guide: SETUP_GUIDE.md
echo.
pause
