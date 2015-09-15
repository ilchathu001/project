import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

import java.util.Locale;

public class users {

	public static void main(String[] args) {

		try {

			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("TestProduser");
			
			DBCollection collection = db.getCollection("users");
			
			BasicDBObject document = new BasicDBObject();
			
			for (int i = 100; i < 5000; i++) {
				document.put("userID", i);
				document.put("location",getListOfCountries(Locale.ENGLISH) );
			}
			
			collection.insert(document);
			
		}catch(Exception e){
			
		}
		
	}
	 public static String getListOfCountries(Locale locale) {

			String[] locales = Locale.getISOCountries();
						
			Random ran = new Random();
			Locale obj = new Locale("",locales[ran.nextInt(locales.length)]);
			return obj.getDisplayCountry(locale);
		    }

}
