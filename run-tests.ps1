# Selenium Automation Framework - PowerShell Test Runner
# Usage: .\run-tests.ps1 -Action <action>

param(
    [Parameter(Mandatory=$false)]
    [ValidateSet("all", "login", "cart", "logout", "compile", "clean", "report")]
    [string]$Action = "all",

    [Parameter(Mandatory=$false)]
    [ValidateSet("chrome", "edge")]
    [string]$Browser = "chrome"
)

# Colors for output
$Success = "Green"
$Error = "Red"
$Info = "Cyan"
$Warning = "Yellow"

function Print-Header {
    param([string]$Message)
    Write-Host "`n======================================" -ForegroundColor $Info
    Write-Host $Message -ForegroundColor $Info
    Write-Host "======================================`n" -ForegroundColor $Info
}

function Print-Success {
    param([string]$Message)
    Write-Host "✓ $Message" -ForegroundColor $Success
}

function Print-Error {
    param([string]$Message)
    Write-Host "✗ $Message" -ForegroundColor $Error
}

function Print-Info {
    param([string]$Message)
    Write-Host "ℹ $Message" -ForegroundColor $Info
}

function Check-Maven {
    Print-Info "Checking Maven installation..."
    $mvnVersion = mvn -version 2>&1
    if ($LASTEXITCODE -eq 0) {
        Print-Success "Maven found"
        return $true
    } else {
        Print-Error "Maven not found. Please install Maven and add it to PATH"
        return $false
    }
}

function Check-Java {
    Print-Info "Checking Java installation..."
    $javaVersion = java -version 2>&1
    if ($LASTEXITCODE -eq 0) {
        Print-Success "Java found"
        return $true
    } else {
        Print-Error "Java not found. Please install Java and add it to PATH"
        return $false
    }
}

function Run-AllTests {
    Print-Header "Running All Tests"
    mvn clean test
    if ($LASTEXITCODE -eq 0) {
        Print-Success "All tests completed"
    } else {
        Print-Error "Tests failed"
    }
}

function Run-LoginTests {
    Print-Header "Running Login Tests"
    mvn clean test -Dtest=LoginTest
    if ($LASTEXITCODE -eq 0) {
        Print-Success "Login tests completed"
    } else {
        Print-Error "Login tests failed"
    }
}

function Run-CartTests {
    Print-Header "Running Cart Tests"
    mvn clean test -Dtest=CartTest
    if ($LASTEXITCODE -eq 0) {
        Print-Success "Cart tests completed"
    } else {
        Print-Error "Cart tests failed"
    }
}

function Run-LogoutTests {
    Print-Header "Running Logout Tests"
    mvn clean test -Dtest=LogoutTest
    if ($LASTEXITCODE -eq 0) {
        Print-Success "Logout tests completed"
    } else {
        Print-Error "Logout tests failed"
    }
}

function Clean-Project {
    Print-Header "Cleaning Project"
    mvn clean
    if ($LASTEXITCODE -eq 0) {
        Print-Success "Project cleaned"
    } else {
        Print-Error "Clean failed"
    }
}

function Compile-Project {
    Print-Header "Compiling Project"
    mvn compile -DskipTests
    if ($LASTEXITCODE -eq 0) {
        Print-Success "Project compiled"
    } else {
        Print-Error "Compilation failed"
    }
}

function Open-Report {
    Print-Header "Opening Test Report"
    $reportPath = Get-ChildItem -Path "reports" -Filter "ExtentReport_*.html" |
                  Sort-Object LastWriteTime -Descending |
                  Select-Object -First 1

    if ($reportPath) {
        Print-Success "Opening report: $($reportPath.Name)"
        & $reportPath.FullName
    } else {
        Print-Error "No test report found. Run tests first."
    }
}

function Show-Menu {
    Print-Header "Selenium Automation Framework - Test Runner"
    Write-Host "Usage: .\run-tests.ps1 -Action <action>`n"
    Write-Host "Available Actions:" -ForegroundColor $Info
    Write-Host "  all        - Run all tests"
    Write-Host "  login      - Run login tests only"
    Write-Host "  cart       - Run cart tests only"
    Write-Host "  logout     - Run logout tests only"
    Write-Host "  compile    - Compile project only"
    Write-Host "  clean      - Clean build artifacts"
    Write-Host "  report     - Open latest test report`n"
    Write-Host "Browser Options: chrome (default), edge`n"
    Write-Host "Example:"
    Write-Host "  .\run-tests.ps1 -Action all -Browser chrome"
}

# Main execution
function Main {
    Print-Header "Selenium Automation Framework"

    # Show menu if no action provided
    if ($Action -eq "") {
        Show-Menu
        return
    }

    # Check prerequisites
    if (-not (Check-Java)) {
        Print-Error "Please install Java first"
        return
    }

    if (-not (Check-Maven)) {
        Print-Error "Please install Maven first"
        return
    }

    Print-Info "Selected Action: $Action"
    Print-Info "Browser: $Browser`n"

    # Execute action
    switch ($Action) {
        "all" { Run-AllTests }
        "login" { Run-LoginTests }
        "cart" { Run-CartTests }
        "logout" { Run-LogoutTests }
        "compile" { Compile-Project }
        "clean" { Clean-Project }
        "report" { Open-Report }
        default { Show-Menu }
    }

    Print-Header "Execution Complete"
    Print-Info "Check 'reports' folder for HTML report"
    Print-Info "Check 'screenshots' folder for failure screenshots"
}

# Run main
Main

