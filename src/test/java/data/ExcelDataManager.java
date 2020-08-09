package data;

import config.Config;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataManager {

    private Workbook workbook;

    public ExcelDataManager() throws IOException {
        Path path = Paths.get(Config.getProperty("datafile.location"),Config.getProperty("datafile.name"));
        InputStream inputStream = new FileInputStream(path.toFile());
        workbook = new XSSFWorkbook(inputStream);

    }
    public List<Object[]> getDataFromSheet(String sheetName){
        Sheet sheet = workbook.getSheet(sheetName);
        Iterator<Row> rowIterator = sheet.rowIterator();
        //Skip the header
        rowIterator.next();

        List<Object[]> data = new ArrayList<>();

        while (rowIterator.hasNext()){
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();
            List<Object> cellData = new ArrayList<>();

            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();

                if (cell.getCellTypeEnum() == CellType.STRING){
                    cellData.add(cell.getStringCellValue());
                }
                if (cell.getCellTypeEnum() == CellType.NUMERIC){
                    cellData.add(cell.getNumericCellValue());
                }
                if (cell.getCellTypeEnum() == CellType.BOOLEAN){
                    cellData.add(cell.getBooleanCellValue());
                }
            }
            data.add(cellData.toArray());
        }
        return data;
    }

    @DataProvider
    public Object [] [] contactProvider(){
        Object [] [] data = {
                { "hitesh@gmail.com","sbe","hitesh","chavda"},
                {"atina@gmail.com","pragra","Atin","Singh"},

        };
        return data;
    }
    @DataProvider
    public Iterator<Object[]> contactProvider2(){
//        List<Object[]> data = new ArrayList<>();
//        data.add(new Object[] { "hitesh@gmail.com","sbe","hitesh","chavda"});
//        data.add(new Object[] {"atina@gmail.com","pragra","Atin","Singh"});
        return getDataFromSheet("Contact").iterator();
        }

     @DataProvider
    public Iterator<Object[]> dummy(){
//        List<Object[]> data = new ArrayList<>();
//        data.add(new Object[] { "hitesh@gmail.com","sbe","hitesh","chavda"});
//        data.add(new Object[] {"atina@gmail.com","pragra","Atin","Singh"});
        return getDataFromSheet("Account").iterator();
    }
}
