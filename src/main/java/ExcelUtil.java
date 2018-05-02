import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtil {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFCell cell;
    private XSSFRow row;

    public void setFile(String filePath, int sheetIndex){

        //read file
        try {
            FileInputStream excelFile = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheetAt(sheetIndex);

        } catch (Exception e) {
            System.out.println("File couldn't not be found or corrupted!");;
            System.out.println(e);
        }
    }

    public String getCellData(int row, int column){

        cell = sheet.getRow(row).getCell(column);

        String cellData = cell.getStringCellValue();

        return cellData;

    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public XSSFCell getCell() {
        return cell;
    }

    public XSSFRow getRow() {
        return row;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public void setCell(XSSFCell cell) {
        this.cell = cell;
    }

    public void setRow(XSSFRow row) {
        this.row = row;
    }
}
