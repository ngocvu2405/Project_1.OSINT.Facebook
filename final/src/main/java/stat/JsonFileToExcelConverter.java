package stat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileToExcelConverter {

    public static void toExcel() {
      try {
        // Read the JSON data from the file
        String jsonString = new String(Files.readAllBytes(Paths.get("ExportJson/user.json")));

        // Create a new Excel workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a new sheet for User
        Sheet sheet = workbook.createSheet("User Data");

        // Create a header row for the Excel sheet
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("User ID");
        headerRow.createCell(1).setCellValue("Full Name");

        // Get the "records" array from the JSON object
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray recordsArray = jsonObject.getJSONArray("records");

        // Loop through each record in the array and add a new row to the Excel sheet
        for (int i = 0; i < recordsArray.length(); i++) {
            JSONObject record = recordsArray.getJSONObject(i);
            JSONObject fields = record.getJSONObject("fields");
            String userId = fields.getString("userId");
            String fullName = fields.getString("fullname");
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(userId);
            row.createCell(1).setCellValue(fullName);
        }

        // Auto-size the columns in the Excel sheet
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }
        
        
        // Create a new sheet for Group
        String jsonString2 = new String(Files.readAllBytes(Paths.get("ExportJson/group.json")));
        Sheet sheet2 = workbook.createSheet("Group");

        // Create a header row for the Excel sheet
        Row headerRow2 = sheet2.createRow(0);
        headerRow2.createCell(0).setCellValue("Group ID");
        headerRow2.createCell(1).setCellValue("Name");
        headerRow2.createCell(2).setCellValue("Description");
        headerRow2.createCell(3).setCellValue("Administrator");


        // Get the "records" array from the JSON object
        JSONObject jsonObject2 = new JSONObject(jsonString2);
        JSONArray recordsArray2 = jsonObject2.getJSONArray("records");

        // Loop through each record in the array and add a new row to the Excel sheet
        for (int i = 0; i < recordsArray2.length(); i++) {
            JSONObject record = recordsArray2.getJSONObject(i);
            JSONObject fields = record.getJSONObject("fields");
            String id = fields.getString("id");
            String name = fields.getString("name");
            String description;
            if(fields.has("description")) {
            	description = fields.getString("description");
            }
            else {
            	description = "Nothing to describe";
            }
           
            Boolean administrator;
            if (fields.has("administrator")) {
            	administrator = fields.getBoolean("administrator");
            }
            else {
            	administrator = false;
            }
            Row row2 = sheet2.createRow(i + 1);
            row2.createCell(0).setCellValue(id);
            row2.createCell(1).setCellValue(name);
            row2.createCell(2).setCellValue(description);
            row2.createCell(3).setCellValue(administrator);

        }

        // Auto-size the columns in the Excel sheet
        for (int i = 0; i < headerRow2.getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Loop through each record in the array and add a new row to the Excel sheet
        for (int i = 0; i < recordsArray2.length(); i++) {
            JSONObject record = recordsArray2.getJSONObject(i);
            JSONObject fields = record.getJSONObject("fields");
            String id = fields.getString("id");
            String name = fields.getString("name");
            String description;
            if(fields.has("description")) {
                description = fields.getString("description");
            }
            else {
                description = "Nothing to describe";
            }

            Boolean administrator;
            if (fields.has("administrator")) {
                administrator = fields.getBoolean("administrator");
            }
            else {
                administrator = false;
            }
            Row row2 = sheet2.createRow(i + 1);
            row2.createCell(0).setCellValue(id);
            row2.createCell(1).setCellValue(name);
            row2.createCell(2).setCellValue(description);
            row2.createCell(3).setCellValue(administrator);

        }

        // Auto-size the columns in the Excel sheet
        for (int i = 0; i < headerRow2.getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Write the Excel file to disk
        FileOutputStream outputStream = new FileOutputStream(new File("RESULT/Airtable_Base_Data.xlsx"));

        workbook.write(outputStream);
        workbook.close();
      } 
      catch(IOException e)
      {
          System.out.print("Cannot write to Excel");
      }
    }
}