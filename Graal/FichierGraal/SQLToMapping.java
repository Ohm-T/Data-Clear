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

public class SQLToMapping {
	
	
	public static ArrayList<Atom> SQLtoGraal(SqliteDriver database,SQLQuery query, Predicate p,ArrayList<ArrayList<Term>> storeList,ArrayList<Atom> atomList) throws SQLException {
		ResultSet res = database.createStatement().executeQuery(query.toString());
		int cpt = 0;
		
		
		try {
		
		
		//Vérification de la cohérence de la requête et du prédicat 
		ResultSetMetaData data = res.getMetaData();
		cpt = data.getColumnCount(); 
		if(cpt != p.getArity()) {
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
			storeList.add(temp);
		}
		
		
		//Insertion des données dans la liste final
		for (int i = 0; i < storeList.size(); i++) {
			atomList.add(DefaultAtomFactory.instance().create(p, storeList.get(i)));
			
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		

		
		
		return atomList;
		
	}

}
