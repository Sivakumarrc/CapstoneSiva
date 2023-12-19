package Resources;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataReader {

    public static Object[][] readTestData(String filePath, String sheetName) {
        try (FileInputStream file = new FileInputStream(new File(filePath))) {
            Workbook workbook = WorkbookFactory.create(file);

            // Debug: Print sheet names
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                System.out.println("Sheet " + i + ": " + workbook.getSheetName(i));
            }

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet != null) {
                int rowCount = sheet.getPhysicalNumberOfRows();
                int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

                Object[][] data = new Object[rowCount - 1][colCount];

                for (int i = 1; i < rowCount; i++) {
                    for (int j = 0; j < colCount; j++) {
                        Cell cell = sheet.getRow(i).getCell(j);
                        data[i - 1][j] = cell == null ? "" : cell.toString();
                    }
                }

                // Debug: Print data
                for (Object[] row : data) {
                    for (Object cellValue : row) {
                        System.out.print(cellValue + "\t");
                    }
                    System.out.println();
                }

                return data;
            } else {
                System.out.println("Sheet not found: " + sheetName);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


