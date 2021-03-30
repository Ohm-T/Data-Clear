package fr.Graal.testJar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.lirmm.graphik.graal.api.core.Atom;
import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.core.factory.DefaultAtomFactory;
import fr.lirmm.graphik.graal.core.term.DefaultTermFactory;
import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;

public class CabineRelation {
	
	
	
	//Création du prédicat de la relation
	public Predicate APourCabine = new Predicate("APourCabine", 3);
	
	//Création de la db 
	ArrayList<ArrayList<Term>> dbCabineRelation = new ArrayList<ArrayList<Term>>();
	
	
	//Création de la requête pour le mapping
	public SQLQuery APourCabineQueryFirstClass = new SQLQuery("SELECT NAME,CABIN FROM TITANICFIRSTCLASS");
	// 1 classe par requête
	public SQLQuery APourCabineQuerySecondClass = new SQLQuery("SELECT NAME,CABIN FROM TITANICSECONDCLASS");
	
	
	//Méthode d'éxecution de la requête de la relation/table
	public static ArrayList<ArrayList<Term>> storeQuery(SqliteDriver base,SQLQuery query, ArrayList<ArrayList<Term>> storeList ) throws SQLException {
		ResultSet res = base.createStatement().executeQuery(query.toString());
		
		while(res.next()) {
			//
				System.out.println(res.getString(1));
				String cabinSQL = res.getString("CABIN"); 
				String nomSQL = res.getString("NAME");
				
			//	
				// 
				String PrimaryKey[] = nomSQL.split(",");
				String nom = PrimaryKey[0];
				String prenom = PrimaryKey[1];
				//

				ArrayList<Term> temp = mainExemple.createTermList();
				// si null, créer une variable à la place.
				temp.add(DefaultTermFactory.instance().createLiteral(nom));
				temp.add(DefaultTermFactory.instance().createLiteral(prenom));
				temp.add(DefaultTermFactory.instance().createLiteral(cabinSQL));
				storeList.add(temp);
			
		}
		return storeList;
	}
	
	
	
	
}
