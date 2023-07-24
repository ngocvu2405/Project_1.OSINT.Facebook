package test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.json.JSONObject;

import airtable.*;
import stat.*;
public class Test2 {
	public static final String TEXT_RED = "\u001B[31m";
	public static final String TEXT_GREEN = "\u001B[32m";
	public static final String TEXT_RESET = "\u001B[0m";
	public static final String TEXT_CYAN = "\u001B[36m";
	public static final String TEXT_YELLOW = "\u001B[33m";
	public static final String TEXT_BOLD = "\u001b[1m";
	public static final String TEXT_ITALIC = "\u001b[3m";
	public static final String TEXT_UNDERLINE = "\u001b[4m";
	public static void main(String[] args) throws IOException {
		final String API_INFO = new String(Files.readAllBytes(Paths.get("src/main/java/airtable/validatedAPI.json")));
	    JSONObject jsonObject = new JSONObject(API_INFO);
	    final String TOKEN_AIRTABLE = jsonObject.getString("APIToken");
	    final String BASE_AIRTABLE_ID = jsonObject.getString("baseID");
	    final String TABLE_USER = jsonObject.getString("userTable");
	    final String TABLE_GROUP = jsonObject.getString("groupTable");


		String general = "----------------------------------------------\n" +
				TEXT_BOLD +TEXT_GREEN+ "               APPLICATION\n\n" + TEXT_RESET +
                TEXT_YELLOW+TEXT_BOLD +"How can I help you? Please choose an option:\n" + TEXT_RESET +
                TEXT_ITALIC +TEXT_BOLD +TEXT_CYAN+"--FB API--\n" + TEXT_RESET +
                "1. Getting users data\n" +
                "2. Getting groups data\n" +
                "Note: After getting information from FB API, data will be auto synced to Airtable\n" +
                TEXT_ITALIC +TEXT_BOLD +TEXT_CYAN+"--Airtable--\n" + TEXT_RESET +
                "3. Getting users data to 'user.json' file\n" +
                "4. Getting groups data to 'group.json' file\n" +
                "5. Getting only read link Airtable base \n" +
                TEXT_ITALIC +TEXT_BOLD +TEXT_CYAN+"--Statistic data to Excel and Charts--\n" +TEXT_RESET +
                "6. Create Excel file for data in json files got from Airtable\n" + 
                "7. Create bar chart GroupAdminBarChart\n" +
                "8. Create bar chart GroupDescriptionsBarChart\n" +
                "9. Create pie chart GroupAdminPieChart\n" +  
                "----------------------------------------------\n" +
                "10. Help \n" +
                "11. Exit";
       while (true)
       {
        	System.out.println(general);
            Scanner input = new Scanner(System.in);
            String option = input.nextLine();

            if (option.equals("1"))
            {
            	System.out.println("--1. Getting User Data--");
            	RecordUser user = new RecordUser();
				user.reformatData();
			}
           	else if (option.equals("2"))
            {
            	System.out.println("--2. Getting Group Data--");
            	RecordGroup group = new RecordGroup();
				group.reformatData();
			}
            else if (option.equals("3"))
                {
                	System.out.println("--3. Getting users data to 'user.json' file--");
                	String responseUser;
					try {
						responseUser = GetRequest.getFromAirtable(BASE_AIRTABLE_ID, TABLE_USER, TOKEN_AIRTABLE);
						GetRequest.toJsonFile(responseUser , "user.json");
	    				System.out.println(TEXT_CYAN +"Succeed getting users data to 'user.json' file in folder ExportJson!" + TEXT_RESET);
					} catch (Exception e) {
						System.out.println(TEXT_RED+ "The API Token of Airtable supplied by admin is over. Contact admin to update the tool"+ TEXT_RESET);
					}
                }
                else if (option.equals("4"))
                {
                	System.out.println("--4. Getting users data to 'group.json' file--");
                	String responseGroup;
					try {
						responseGroup = GetRequest.getFromAirtable(BASE_AIRTABLE_ID, TABLE_GROUP, TOKEN_AIRTABLE);
	    				GetRequest.toJsonFile(responseGroup , "group.json");
	    				System.out.println(TEXT_CYAN + "Succeed getting users data to 'group.json' file in folder ExportJson!" + TEXT_RESET);
					} catch (Exception e) {
						System.out.println(TEXT_RED+ "The API Token of Airtable supplied by admin is over. Contact admin to update the tool"+ TEXT_RESET);
					}
                }
                else if (option.equals("5"))
                {
                	System.out.println("--5. Getting only read link Airtable base--");
                	System.out.println(TEXT_CYAN+ "Link of read-only Airtable Base: https://airtable.com/appfpkYiYDZtMWJhA/shrpzLV4umIzKqPkt" +TEXT_RESET);
                }
            else if (option.equals("6"))
            {
            	System.out.println("--6. Create Excel file for data got from Airtable--");
            	JsonFileToExcelConverter.toExcel();
            	System.out.println(TEXT_CYAN + "Check file Airtable_Base_Data.xlsx in the folder RESULT" + TEXT_RESET);
            }
            else if (option.equals("7"))
            {
            	System.out.println("--7. Create bar chart GroupAdminBarChart--");
            	GroupAdminBarChart.getGroupAdminBarChart();
            	System.out.println(TEXT_CYAN + "Check file adminBarChart.png in the folder RESULT" + TEXT_RESET);

            }
            else if (option.equals("8"))
            {
            	System.out.println("--8. Create bar chart GroupDescriptionsBarChart--");
            	DescriptionsChart.getDescriptionBarChart();
            	System.out.println(TEXT_CYAN + "Check file descriptionBarChart.png in the folder RESULT" + TEXT_RESET);

            }
            else if (option.equals("9"))
            {
            	System.out.println("--9. Create pie chart GroupAdminPieChart--");
            	GroupAdminPieChart.getGroupAdminPieChart();
            	System.out.println(TEXT_CYAN + "Check file adminPieChart.png in the folder RESULT" + TEXT_RESET);

            }
            else if (option.equals("10"))
            {
            	System.out.println(TEXT_YELLOW +"ENTER AN INTEGER FROM 1-9 TO USE ONE OF THESE FUNCTION:"+ TEXT_RESET);
            	System.out.println(general);
            }
            else if (option.equals("11"))
            {
                System.out.println(TEXT_YELLOW + "GOOD BYE!" + TEXT_RESET);
                System.exit(0);
            }
            else {
            	System.out.println(TEXT_RED + TEXT_BOLD+TEXT_UNDERLINE + "!!!WARNING: YOUR SYNTAX IS WRONG, PLEASE READ THE INSTRUCTION FOLLOWING!!!" +TEXT_RESET);
            	System.out.println("ENTER AN INTEGER FROM 1-9 TO USE ONE OF THESE FUNCTION:");
            	System.out.println(general);
            }
            System.out.println("Do you want to continue using application? \n"
            + TEXT_GREEN+ "\t Y/y/YES to continue\n" +TEXT_RESET
            + TEXT_RED + "\t Any other key to exit" +TEXT_RESET);
        
            String choosing = input.nextLine();
            if (choosing.equals("Y") || choosing.equals("y") || choosing.equals("Yes") || choosing.equals("yes"))
            {
                continue;
            }
            else
            {
                System.out.println(TEXT_YELLOW + "GOOD BYE!" + TEXT_RESET);
            	input.close();
                System.exit(0);
            }
       }
   }

}

