import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;
import com.mongodb.util.JSON;

import java.util.Locale;

public class users {

	public static void main(String[] args) {

		String uriString = "mongodb://namila:123abc@hyphendbserver.cloudapp.net:27017/testingProduser";
		MongoURI uri = new MongoURI(uriString);
		try {

			DB db = uri.connectDB();
			
			DBCollection collection = db.getCollection("usersPlusTvShowId");
			
			System.out.println("connected");
			
			for (int i = 1751; i < 5000; i++) {
				BasicDBObject document = new BasicDBObject();
				document.put("userID", i);
				document.put("location",getListOfCountries(Locale.ENGLISH) );
				
				
				
				List<BasicDBObject> tvShowIdList = new ArrayList<>();
				int r = (int) (Math.random() * (10 - 3)) + 3;
				for (int j = 3; j <=r; j++) {
					tvShowIdList.add(new BasicDBObject("tvShowId",getTvShowId()));
										
				}
				document.put("tvShowId", tvShowIdList);
				
				collection.insert(document);
			}
			
			
		}catch(Exception e){
			System.out.println("error");
			
		}
		
	}
	 public static String getListOfCountries(Locale locale) {

			String[] locales = Locale.getISOCountries();
						
			Random ran = new Random();
			Locale obj = new Locale("",locales[ran.nextInt(locales.length)]);
			return obj.getDisplayCountry(locale);
		    }
	 
	 
	 public static String getTvShowId() throws MongoException, UnknownHostException {
		 String outputTvShowId ="";
		 
		 
			 String uriString = "mongodb://namila:123abc@hyphendbserver.cloudapp.net:27017/testingProduser";
			 MongoURI uri = new MongoURI(uriString);
			 DB db = uri.connectDB();
			 DBCollection collection = db.getCollection("TvShows");
					
				//System.out.println("getDB");
				
				BasicDBObject searhObject = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.put("tvShowId", true);
				query.put("_id", false);
				
				
				DBCursor cursor = collection.find(searhObject,query);
				
				//System.out.println("cursor done");
				
				ArrayList<String> tvShowIdList = new ArrayList<String>();
				
				
				while(cursor.hasNext()) {
					//BasicDBObject queryObject = (BasicDBObject) cursor.next();
					//System.out.println(queryObject);
					String tvShowIdString = (String) cursor.next().toString();
					//System.out.println(tvShowIdString);	
					String splitting [] = tvShowIdString.split(" ");
					String Id = splitting[3].replaceAll("[^a-zA-Z0-9]+", " ");
					
					tvShowIdList.add(Id);
					
				
						Random ran = new Random();
						int index = ran.nextInt(tvShowIdList.size());
						outputTvShowId = tvShowIdList.get(index); 
						System.out.println("Added index:" + index);
				
    	}
				return outputTvShowId;
	 }
}
