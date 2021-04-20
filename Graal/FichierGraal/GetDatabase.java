package fr.Graal.testJar;


import java.sql.SQLException;




import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;



public class GetDatabase {
	
	//A modifier en fonction de la position du fichier de db
	static String path = "C:\\Users\\beaug\\Desktop\\M1S2\\TER\\TestMavenJar\\testJar\\data\\Titanic.db";
	
	
		//Méthode de récupération de la BDD
		public static SqliteDriver createDatabase()  throws SQLException{
		
		SqliteDriver database  = new SqliteDriver(path);	
		
		return database;
		
	}
		public static MongoClient createMongoBase() {
			
			MongoClient mongobase = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
			return mongobase;
					
		}

}
