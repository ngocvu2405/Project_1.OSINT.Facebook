package test;
import java.io.IOException;
import org.json.*;

import airtable.*;
public class Test2 {

	public static void main(String[] args) {
		RecordUser user = new RecordUser();
		// TODO Auto-generated method stub
		String data = user.reformatData();
		try {
			user.POSTRequest("appfpkYiYDZtMWJhA", "tblexw8RrU1S7drHh", "patJOGkmzGUONSJVC.1e139f03d8fc3789fa64c266896a4a32fd875c90d0c83e895e28e49a44ed89b7", data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
