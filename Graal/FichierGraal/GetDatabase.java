package fr.Graal.testJar;


import java.sql.SQLException;




import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;



public class GetDatabase {
	
	//A modifier en fonction de la position du fichier de db
	static String path = "C:\\Users\\beaug\\Desktop\\M1S2\\TER\\TestMavenJar\\testJar\\data\\Titanic.db";
	
	
		//Méthode de récupération de la BDD
		public static SqliteDriver createDatabase()  throws SQLException{
		
		SqliteDriver database  = new SqliteDriver(path);	
		
		return database;
	}

}
