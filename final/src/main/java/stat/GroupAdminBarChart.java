package stat;

import org.knowm.xchart.*;

import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class GroupAdminBarChart {

    public static void getGroupAdminBarChart() {
     try {
        // Create a new sheet for Group
        String jsonString2 = new String(Files.readAllBytes(Paths.get("group.json")));
        
        JSONObject jsonObject2 = new JSONObject(jsonString2);
        JSONArray recordsArray2 = jsonObject2.getJSONArray("records");

        // Count variables for chart data
        int adminTrueCount = 0;
        int adminFalseCount = 0;

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
        
        // Customize Bar Chart
        adminBarChart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        adminBarChart.addSeries("Groups", Arrays.asList("True", "False"), Arrays.asList(adminTrueCount, adminFalseCount));

        // Save the charts to PNG files
        BitmapEncoder.saveBitmap(adminBarChart, "RESULT/adminBarChart.png", BitmapEncoder.BitmapFormat.PNG);
     }
     catch(IOException e)
     {
         System.out.print("Cannot create GroupAdminBarChart");
     }
    }
}
