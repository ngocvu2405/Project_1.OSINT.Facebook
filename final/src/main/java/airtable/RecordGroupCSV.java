package airtable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import facebook.GetData;
import facebook.user.Group;
public class RecordGroupCSV {

    public static void main(String[] args) {
        Group userGr = new Group("me?fields=groups%7Bid%2Cname%2Cdescription%2Cadministrator%2Cmember_count%7D&access_token=");
        String order = userGr.order;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Facebook access token: ");
        String accessToken = scanner.nextLine();
        Group.setAccessToken(accessToken);
        try {
            String res = GetData.getData(accessToken, order);
            System.out.println(res);

            JSONObject jsonObject = new JSONObject(res);
            JSONArray jsonArray = jsonObject.getJSONObject("groups").getJSONArray("data");

            StringBuilder csvData = new StringBuilder();
            csvData.append("Group ID,Name,Description,Administrator,Member Count");
            csvData.append("\n");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject groupObject = jsonArray.getJSONObject(i);

                String groupId = groupObject.getString("id");
                String groupName = groupObject.getString("name");
                String description = groupObject.isNull("description") ? "" : groupObject.getString("description");
                boolean isAdmin = groupObject.getBoolean("administrator");
                int memberCount = groupObject.isNull("member_count") ? 0 : groupObject.getInt("member_count");

                csvData.append(groupId).append(",");
                csvData.append(groupName).append(",");
                csvData.append(description).append(",");
                csvData.append(isAdmin).append(",");
                csvData.append(memberCount);
                csvData.append("\n");
            }

            String csvFilePath = "group_data.csv";
            FileWriter csvWriter = new FileWriter(csvFilePath);
            csvWriter.write(csvData.toString());
            csvWriter.flush();
            csvWriter.close();

            System.out.println("CSV file created successfully: " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}

