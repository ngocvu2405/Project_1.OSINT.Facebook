package test;
import java.io.IOException;

import airtable.GetRequest;
import stat.JsonFileToExcelConverter;
public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String responseUser = GetRequest.getFromAirtable("appfpkYiYDZtMWJhA", "tblexw8RrU1S7drHh", "patJOGkmzGUONSJVC.1e139f03d8fc3789fa64c266896a4a32fd875c90d0c83e895e28e49a44ed89b7");
			GetRequest.toJsonFile(responseUser , "user.json");
			String responseGroup = GetRequest.getFromAirtable("appfpkYiYDZtMWJhA", "tblhmlceroOgnh6Ed", "patJOGkmzGUONSJVC.1e139f03d8fc3789fa64c266896a4a32fd875c90d0c83e895e28e49a44ed89b7");
			GetRequest.toJsonFile(responseGroup , "group.json");
			JsonFileToExcelConverter.toExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
