package com.example.graal_TER;


import java.sql.SQLException;
import java.util.List;


import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.DBColumn;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;


public class GetDatabase {
	
	static String path = "C:\\Users\\beaug\\Desktop\\M1S2\\TER\\Graal\\graal_TER\\data\\Titanic.db";
	
	
		//Méthode de récupération de la BDD
		public static SqliteDriver createDatabase()  throws SQLException{
		
		SqliteDriver database  = new SqliteDriver(path);	
		
		return database;
	}
	
	public static void main(String args[]) throws SQLException {
		
		SqliteDriver testBase;
		
		// Requête ?
		SQLQuery test = new SQLQuery("SELECT AGE FROM TITANICFIRSTCLASS");
		
		
		//Liste des colonnes et affichage
		testBase = createDatabase();
		
		List<DBColumn> res;
		
		res = testBase.getColumns("TITANICFIRSTCLASS");
		
		for( DBColumn value : res) {
			System.out.println(value.toString());
		}
		
		System.out.println(testBase.getTable("AGE"));
		
		

	
	}

}
