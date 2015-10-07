import javax.tools.FileObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoURI;


public class addWatcheShows {

	public static void main(String[] args) {
		String uriString = "mongodb://namila:123abc@hyphendbserver.cloudapp.net:27017/testingProduser";
		MongoURI uri = new MongoURI(uriString);
		
		try {
			DB db = uri.connectDB();
			System.out.println("connected");
			DBCollection collection = db.getCollection("TvShows");
				
			System.out.println("getDB");
			
			BasicDBObject searhObject = new BasicDBObject();
			BasicDBObject query = new BasicDBObject();
			query.put("tvShowId", true);
			query.put("_id", false);
			
			
			DBCursor cursor = collection.find(searhObject,query);
			
			System.out.println("cursor done");
			
			while(cursor.hasNext()) {
				BasicDBObject queryObject = (BasicDBObject) cursor.next();
				//System.out.println(queryObject);
				String tvShowIdString = (String) cursor.next().toString();
				//System.out.println(tvShowIdString);	
				String splitting [] = tvShowIdString.split(" ");
				String Id = splitting[3].replaceAll("[^a-zA-Z0-9]+", " ");
				
					System.out.println(Id);
				
			}
			
		}catch(Exception e){
			
		}
	}

}
