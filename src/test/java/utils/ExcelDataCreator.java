package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ExcelDataCreator utility class for creating test data Excel files.
 * This is a one-time utility to generate the LoginTestData.xlsx file.
 * Run this class once to create the test data file.
 */
public class ExcelDataCreator {

    /**
     * Creates LoginTestData.xlsx with invalid login test data
     *
     * @throws IOException if error occurs during file creation
     */
    public static void createLoginTestData() throws IOException {
        // Create workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("InvalidLogins");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell usernameHeader = headerRow.createCell(0);
        usernameHeader.setCellValue("username");
        Cell passwordHeader = headerRow.createCell(1);
        passwordHeader.setCellValue("password");

        // Create data rows
        Object[][] testData = {
            {"invalid_user", "wrong_password"},
            {"admin", "123456"},
            {"test_user", "test123"},
            {"user123", "pass123"},
            {"", "secret_sauce"}
        };

        int rowNum = 1;
        for (Object[] data : testData) {
            Row row = sheet.createRow(rowNum++);
            
            Cell usernameCell = row.createCell(0);
            usernameCell.setCellValue((String) data[0]);
            
            Cell passwordCell = row.createCell(1);
            passwordCell.setCellValue((String) data[1]);
        }

        // Adjust column width
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 20 * 256);

        // Create directory if not exists
        File directory = new File("SauceDemo.xlsx");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Write file
        FileOutputStream fos = new FileOutputStream("SauceDemo.xlsx/LoginTestData.xlsx");
        workbook.write(fos);
        fos.close();
        workbook.close();

        System.out.println("LoginTestData.xlsx created successfully at SauceDemo.xlsx/LoginTestData.xlsx");
    }

    /**
     * Main method - Run this to create test data Excel file
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            createLoginTestData();
            System.out.println("Test data file created successfully!");
        } catch (IOException e) {
            System.out.println("Error creating test data file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

