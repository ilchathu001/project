import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoURI;


public class rating {

	public static void main(String[] args) {

		String uriString = "mongodb://namila:123abc@hyphendbserver.cloudapp.net:27017/testingProduser";
		MongoURI uri = new MongoURI(uriString);
		
		try {
			DB db = uri.connectDB();
			System.out.println("connected");
			DBCollection collection = db.getCollection("TvShows");
				
			System.out.println("getDB");
			
			BasicDBObject query = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject("runtime",true).append("_id",false);
			
			DBCursor cursor = collection.find(query,fields);
			
			while(cursor.hasNext()) {
				DBObject queryObject = cursor.next();
				
				String runtimeString = (String) queryObject.get("runtime");
				System.out.println(runtimeString);
				int runtimeInt = Integer.parseInt(runtimeString);
				
				ArrayList<Integer> slotList = new ArrayList<Integer>();
				slotList.add(runtimeInt);
				
				for (int i = 1; i <= runtimeInt; i++) {
					System.out.println("Slot number :" +i);
				}
				
			   //System.out.println(queryObject.toString());
				
			    
			}
			
		} catch (Exception e) {

		}
		
	}

}
