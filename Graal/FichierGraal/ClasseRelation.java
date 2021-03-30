package fr.Graal.testJar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.core.term.DefaultTermFactory;
import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;

public class ClasseRelation {
	

	//Création du prédicat de la relation
	public Predicate APourClasse = new Predicate("APourClasse", 3);
	
	//Création de la db 
	ArrayList<ArrayList<Term>> dbClasseRelation = new ArrayList<ArrayList<Term>>();
	
	
	//Création de la requête pour le mapping
	public SQLQuery APourClasseQueryFirstClass = new SQLQuery("SELECT NAME,PCLASS FROM TITANICFIRSTCLASS");
	public SQLQuery APourClasseQuerySecondClass = new SQLQuery("SELECT NAME,PCLASS FROM TITANICSECONDCLASS");
	
	
	//Méthode d'éxecution de la requête de la relation/table
	public static ArrayList<ArrayList<Term>> storeQuery(SqliteDriver base,SQLQuery query, ArrayList<ArrayList<Term>> storeList ) throws SQLException {
		ResultSet res = base.createStatement().executeQuery(query.toString());
		int nbColonnes = res.getMetaData().getColumnCount();
		
		while(res.next()) {
				String classSQL = res.getString("PCLASS"); 
				String nomSQL = res.getString("NAME");

				String PrimaryKey[] = nomSQL.split(",");
				String nom = PrimaryKey[0];
				String prenom = PrimaryKey[1];
				

				ArrayList<Term> temp = mainExemple.createTermList();
				temp.add(DefaultTermFactory.instance().createLiteral(nom));
				temp.add(DefaultTermFactory.instance().createLiteral(prenom));
				temp.add(DefaultTermFactory.instance().createLiteral(classSQL));
				storeList.add(temp);
		}
		return storeList;
	}

}
