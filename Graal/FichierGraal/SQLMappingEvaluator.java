package fr.Graal.testJar;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;



import fr.lirmm.graphik.graal.api.core.Atom;
import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.core.factory.DefaultAtomFactory;
import fr.lirmm.graphik.graal.core.term.DefaultTermFactory;
import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;

public class SQLMappingEvaluator { 
	
	//SQlMappingEvaluater
	public static ArrayList<Atom> evaluate(SqliteDriver database,SQLQuery query, Predicate p) throws SQLException {
		ResultSet res = database.createStatement().executeQuery(query.toString());
		ArrayList<Atom> tempAtomList = new ArrayList<Atom>();

		try {
		
		
		//Vérification de la cohérence de la requête et du prédicat 

		if(res.getMetaData().getColumnCount() != p.getArity()) {
			System.out.println("Le nombre d'arguments dans la requête est différent de la taille du prédicat");
			return null;
		}
		

		//Création des Term pour chaque relation
		while(res.next()) {
			ArrayList<Term> temp = mainExemple.createTermList();

			//Tant qu'il y a des données à mettre dans le prédicat
			for(int i = 1; i <= p.getArity(); i++) {
				String tempString = res.getString(i);
				if(tempString != "null")  {					
					temp.add(DefaultTermFactory.instance().createLiteral(tempString));
				}
				else {
					temp.add(DefaultTermFactory.instance().createVariable(tempString));
				}
				
			}
			tempAtomList.add(DefaultAtomFactory.instance().create(p, temp));
		}

		
		} catch(Exception e) {
			e.printStackTrace();
		}
		

		
		
		return tempAtomList;
		
	}

}
