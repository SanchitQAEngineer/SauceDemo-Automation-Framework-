@echo off
REM Quick Fix for LoginTest.java Compilation Errors
REM Run this to resolve TestNG dependency issues

echo ============================================
echo Quick Fix for Compilation Errors
echo ============================================
echo.

echo This script will install Maven dependencies to resolve
echo the "Cannot resolve symbol" errors in LoginTest.java
echo.

echo Checking prerequisites...

where java >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Java not found!
    echo Please install Java JDK 11+ first.
    echo Download: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Maven not found!
    echo Please install Maven first.
    echo Download: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

echo ✓ Prerequisites OK
echo.

echo Installing Maven dependencies...
echo (This will download TestNG, Selenium, and other libraries)
echo.

mvn clean install -DskipTests

if %errorlevel% neq 0 (
    echo.
    echo ERROR: Dependency installation failed!
    echo Check your internet connection and try again.
    echo.
    echo If the problem persists:
    echo 1. Delete .m2/repository folder
    echo 2. Run: mvn clean install -U (force update)
    echo 3. Check firewall/proxy settings
    pause
    exit /b 1
)

echo.
echo ✓ Dependencies installed successfully!
echo.

echo Generating test data...
mvn exec:java -Dexec.mainClass="utils.ExcelDataCreator"

echo.
echo ============================================
echo Fix Complete!
echo ============================================
echo.
echo The red lines in LoginTest.java should now be resolved.
echo.
echo Next steps:
echo 1. Refresh your IDE project (File -> Invalidate Caches)
echo 2. The TestNG imports should now be resolved
echo 3. Run a test to verify: mvn test -Dtest=LoginTest#testValidLogin
echo.
pause
