package hust.soict.sec;
import hust.soict.sec.exportToExcel.ExportToExcel;
import hust.soict.sec.syncToAirtable.SyncToAirtable;
import hust.soict.sec.scoutFacebook.FBobjects.ScoutFacebook;
import hust.soict.sec.scoutFacebook.post.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.*;

public class Proj1 {
    public static void main(String[] args) {
        // Scout Facebook posts
//        List<Feed> postFeeds = ScoutFacebook.scoutPosts();

//        // Export to Excel
//        ExportToExcel.export(postFeeds);
//
//        // Sync to Airtable
//        SyncToAirtable.sync(postFeeds);
    	
    	fetchData();
    	
    }
}
