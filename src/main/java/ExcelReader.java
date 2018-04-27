import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by rajeevkumarsingh on 18/12/17.
 */

public class ExcelReader {
    public static final String SAMPLE_XLS_FILE_PATH = "./sample-xls-file.xls";
    public static final String SAMPLE_XLSX_FILE_PATH = "./sample-xlsx-file.xlsx";

    public static void main(String[] args) throws IOException, InvalidFormatException {
    	Workbook workbook = null;
    	try{
        // Creating a Workbook from an Excel file (.xls or .xlsx)
    		workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
    	}catch(Exception e){
    		System.out.println("Error: no se encontro la planilla de calculo\n Posible problema: renombraste el archivo");
    		e.printStackTrace();
    	};
        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */     

        // 3. Or you can use a Java 8 forEach wih lambda
        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });

        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
        */

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n From the sheet: " + sheet.getSheetName());
        sheet.forEach(row -> {
            row.forEach(cell -> {
                printCellValue(cell);
            });
            System.out.println();
        });

        // Closing the workbook
        workbook.close();
        
        // Getting the Sheet at index zero
        Sheet sheet1 = workbook.getSheetAt(1);         

        // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n From the sheet: " + sheet1.getSheetName());
        sheet1.forEach(row -> {
            row.forEach(cell -> {
                printCellValue(cell);
            });
            System.out.println();
        });

        // Closing the workbook
        workbook.close();
        
        // Getting the Sheet at index zero
        Sheet sheet2 = workbook.getSheetAt(2);         

        // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n From the sheet: " + sheet2.getSheetName());
        sheet2.forEach(row -> {
            row.forEach(cell -> {
                printCellValue(cell);
            });
            System.out.println();
        });

        // Closing the workbook
        workbook.close();
    }

    private static void printCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case STRING:
                System.out.print(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.print(cell.getDateCellValue());
                } else {
                    System.out.print(cell.getNumericCellValue());
                }
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;
            default:
                System.out.print("");
        }

        System.out.print("\t");
    }
}
