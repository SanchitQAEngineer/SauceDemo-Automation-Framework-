package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    /**
     * Reads Excel data into a List of Maps.
     * Each row becomes a Map<Header, Value>.
     *
     * @param filePath       Path to the Excel file
     * @param sheetName      Name of the sheet to read
     * @param headerRowIndex Index of the header row (0-based)
     * @return List of row data as maps
     */
    public static List<Map<String, String>> getTestData(String filePath, String sheetName, int headerRowIndex) {
        List<Map<String, String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(headerRowIndex);

            int colCount = headerRow.getPhysicalNumberOfCells();
            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = headerRowIndex + 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < colCount; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell cell = row.getCell(j);

                    String header = (headerCell == null) ? "Column" + j : headerCell.toString();
                    String value = (cell == null) ? "" : cell.toString();

                    rowData.put(header.trim(), value.trim());
                }
                data.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
