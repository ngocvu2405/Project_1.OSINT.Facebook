package stat;

import org.knowm.xchart.*;

import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class JsonFileToExcelConverter {

    public static void toExcel() throws IOException {

        // Read the JSON data from the file
        String jsonString = new String(Files.readAllBytes(Paths.get("user.json")));

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
        String jsonString2 = new String(Files.readAllBytes(Paths.get("group.json")));
        System.out.println(jsonString2);
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
        
        // Count variables for chart data
        int adminTrueCount = 0;
        int adminFalseCount = 0;
        int descriptionHaveCount =0;
        int descriptionNotHaveCount =0;
        // Loop through each record in the array and count the number of groups with admin values 'true' and 'false'
        for (int i = 0; i < recordsArray2.length(); i++) {
            JSONObject record = recordsArray2.getJSONObject(i);
            JSONObject fields = record.getJSONObject("fields");
            boolean administrator;
            if (fields.has("administrator")) {
                administrator = fields.getBoolean("administrator");
            } else {
                administrator = false;
            }
            if (administrator) {
                adminTrueCount++;
            } else {
                adminFalseCount++;
            }
            if (fields.has("description")) {
                descriptionHaveCount++;
            } else {
                descriptionNotHaveCount++;
            }
        }

        // Create a bar chart of number groups of value of admin true and false
        CategoryChart adminBarChart = new CategoryChartBuilder()
                .width(690)
                .height(860)
                .title("Number of Groups by Admin Value")
                .xAxisTitle("Admin Value")
                .yAxisTitle("Number of Groups")
                .theme(ChartTheme.XChart)
                .build();
        
        // Customize Chart
        adminBarChart.getStyler().setLegendPosition(LegendPosition.InsideNW);

        adminBarChart.addSeries("Groups", Arrays.asList("True", "False"), Arrays.asList(adminTrueCount, adminFalseCount));

        // Create a bar chart number groups has description and not
        CategoryChart descriptionBarChart = new CategoryChartBuilder()
                .width(690)
                .height(860)
                .title("Number of Groups by Description Value")
                .xAxisTitle("Description Value")
                .yAxisTitle("Number of Groups")
                .theme(ChartTheme.XChart)
                .build();
        
        // Customize Chart
        descriptionBarChart.getStyler().setLegendPosition(LegendPosition.InsideNW);

        descriptionBarChart.addSeries("Groups", Arrays.asList("Have", "Not have"), Arrays.asList(descriptionHaveCount, descriptionNotHaveCount));

        // Create a pie chart
        PieChart adminPieChart = new PieChartBuilder()
                .width(800)
                .height(600)
                .title("Distribution of Groups by Admin Value")
                .theme(ChartTheme.XChart)
                .build();

        adminPieChart.addSeries("True", adminTrueCount);
        adminPieChart.addSeries("False", adminFalseCount);

//        String path = "/final/src/RESULT";
//        File file = new File(path);
//        file.getParentFile().mkdirs();
        // Save the charts to PNG files
        BitmapEncoder.saveBitmap(adminBarChart, "RESULT/adminBarChart.png", BitmapEncoder.BitmapFormat.PNG);
        BitmapEncoder.saveBitmap(adminPieChart, "RESULT/adminPieChart.png", BitmapEncoder.BitmapFormat.PNG);
        BitmapEncoder.saveBitmap(descriptionBarChart, "RESULT/descriptionBarChart.png", BitmapEncoder.BitmapFormat.PNG);
        
        // Write the Excel file to disk
        FileOutputStream outputStream = new FileOutputStream(new File("RESULT/Airtable_Base_Data.xlsx"));

        workbook.write(outputStream);
        workbook.close();
    }

}