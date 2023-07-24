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

public class DescriptionsChart {

    public static void getDescriptionBarChart() {
      try {
        // Create a new sheet for Group
        String jsonString2 = new String(Files.readAllBytes(Paths.get("group.json")));
        
        JSONObject jsonObject2 = new JSONObject(jsonString2);
        JSONArray recordsArray2 = jsonObject2.getJSONArray("records");

        // Count variables for chart data
        int descriptionHaveCount =0;
        int descriptionNotHaveCount =0;
        // Loop through each record in the array and count the number of groups with admin values 'true' and 'false'
        for (int i = 0; i < recordsArray2.length(); i++) {
            JSONObject record = recordsArray2.getJSONObject(i);
            JSONObject fields = record.getJSONObject("fields");
            if (fields.has("description")) {
                descriptionHaveCount++;
            } else {
                descriptionNotHaveCount++;
            }
        }
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
        

        // Save the charts to PNG files
        BitmapEncoder.saveBitmap(descriptionBarChart, "RESULT/descriptionBarChart.png", BitmapEncoder.BitmapFormat.PNG);
      }
      catch(IOException e)
      {
          System.out.print("Cannot create DescriptionsChart");
      }
    }

}