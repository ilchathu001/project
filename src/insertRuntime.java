import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoURI;


public class insertRuntime {

	public static void main(String[] args) {

		String uriString = "mongodb://namila:123abc@hyphendbserver.cloudapp.net:27017/testingProduser";
		MongoURI uri = new MongoURI(uriString);
		
		/*try {
			DB db = uri.connectDB();
			System.out.println("connected");
			DBCollection episodeCollection = db.getCollection("TvShowEpisodes");
			System.out.println("getDb");
			
			
			BasicDBObject query = new BasicDBObject();
			BasicDBObject seasonQuery = new BasicDBObject("seasonId",true).append("_id",false);
			
			DBCursor seaonCursor = episodeCollection.find(query,seasonQuery);
			System.out.println("cursor created");
			
			while(seaonCursor.hasNext()) {
				DBObject seasonObject = seaonCursor.next();
				System.out.println("DBObject created");
				String seasonIdString = (String) seasonObject.get("seasonId");
				//System.out.println("ijkk");
				System.out.println(seasonIdString);
				
			}
		}catch(Exception e){
			
		}*/
		
		
		
		try {
			DB db = uri.connectDB();
			System.out.println("connected");
			DBCollection collection = db.getCollection("TvShowEpisodes");
				
			System.out.println("getDB");
			
			BasicDBObject query = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject("seasonId",true).append("_id",false);
			
			
			DBCursor cursor = collection.find(query,fields);
			
			while(cursor.hasNext()) {
				DBObject queryObject = cursor.next();
				
				String runtimeString = (String) queryObject.get("seasonId");
				System.out.println(runtimeString);
				//int runtimeInt = Integer.parseInt(runtimeString);
				
				/*for (int i = 1; i <= runtimeInt; i++) {
					System.out.println("Slot number :" +i);
				}*/
				
			   //System.out.println(queryObject.toString());
				
			    
			}
			
		} catch (Exception e) {

		}
		
	}

}
