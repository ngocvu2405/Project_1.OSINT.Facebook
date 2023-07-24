package stat;

import org.knowm.xchart.*;

import org.knowm.xchart.style.Styler.ChartTheme;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GroupAdminPieChart {

    public static void getGroupAdminPieChart() {
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

        // Create a pie chart
        PieChart adminPieChart = new PieChartBuilder()
                .width(800)
                .height(600)
                .title("Distribution of Groups by Admin Value")
                .theme(ChartTheme.XChart)
                .build();
        
        adminPieChart.addSeries("True", adminTrueCount);
        adminPieChart.addSeries("False", adminFalseCount);

        // Save the charts to PNG files
        BitmapEncoder.saveBitmap(adminPieChart, "RESULT/adminPieChart.png", BitmapEncoder.BitmapFormat.PNG);
      }
      catch(IOException e)
      {
          System.out.print("Cannot create GroupAdminPieChart");
      }
     }
}