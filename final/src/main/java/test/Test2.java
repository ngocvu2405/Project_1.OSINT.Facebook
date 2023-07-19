<<<<<<< HEAD
package test;
import java.io.IOException;

import airtable.*;
import stat.JsonFileToExcelConverter;
public class Test2 {

	public static void main(String[] args) {
		RecordUser user = new RecordUser();
		user.reformatData();
		RecordGroup group = new RecordGroup();
		group.reformatData();
		
		try {
			String responseUser = GetRequest.getFromAirtable("appfpkYiYDZtMWJhA", "tblexw8RrU1S7drHh", "patJOGkmzGUONSJVC.1e139f03d8fc3789fa64c266896a4a32fd875c90d0c83e895e28e49a44ed89b7");
			GetRequest.toJsonFile(responseUser , "user.json");
			String responseGroup = GetRequest.getFromAirtable("appfpkYiYDZtMWJhA", "tblhmlceroOgnh6Ed", "patJOGkmzGUONSJVC.1e139f03d8fc3789fa64c266896a4a32fd875c90d0c83e895e28e49a44ed89b7");
			GetRequest.toJsonFile(responseGroup , "group.json");
			JsonFileToExcelConverter.toExcel();
			System.out.println("All the thing is done! Check data statistic result in the folder RESULT");
		} catch (IOException e) {
			System.out.println("Something wrong happening");
		}
		
	}

}
=======
package test;
import java.io.IOException;

import airtable.*;
import stat.JsonFileToExcelConverter;
public class Test2 {

	public static void main(String[] args) {
		RecordUser user = new RecordUser();
		user.reformatData();
		RecordGroup group = new RecordGroup();
		group.reformatData();
		
		try {
			String responseUser = GetRequest.getFromAirtable("appfpkYiYDZtMWJhA", "tblexw8RrU1S7drHh", "patJOGkmzGUONSJVC.1e139f03d8fc3789fa64c266896a4a32fd875c90d0c83e895e28e49a44ed89b7");
			GetRequest.toJsonFile(responseUser , "user.json");
			String responseGroup = GetRequest.getFromAirtable("appfpkYiYDZtMWJhA", "tblhmlceroOgnh6Ed", "patJOGkmzGUONSJVC.1e139f03d8fc3789fa64c266896a4a32fd875c90d0c83e895e28e49a44ed89b7");
			GetRequest.toJsonFile(responseGroup , "group.json");
			JsonFileToExcelConverter.toExcel();
			System.out.println("All the thing is done! Check data statistic result in the folder RESULT");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("All the thinng not done! Some errors");
		}
		
	}

}
>>>>>>> 03b99985bb22b10552bf9ce159b1fdfbce4e1a3c
