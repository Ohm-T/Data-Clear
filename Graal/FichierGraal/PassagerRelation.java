package com.example.graal_TER;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.core.term.DefaultTermFactory;
import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;

public class PassagerRelation {
	
	//Création du prédicat de la relation
	public Predicate passager = new Predicate("Passager", 4);
	
	//Création de la db 
	public ArrayList<ArrayList<Term>> dbPassagerRelation = new ArrayList<ArrayList<Term>>();
	
	//Création de la requête pour le mapping
	public SQLQuery PassengerQueryFirstClass = new SQLQuery("SELECT NAME,AGE,SEX FROM TITANICFIRSTCLASS");
	public SQLQuery PassengerQuerySecondClass = new SQLQuery("SELECT NAME,AGE,SEX FROM TITANICSECONDCLASS");
	
	//Méthode d'éxecution de la requête de la relation/table
		public static ArrayList<ArrayList<Term>> storeQuery(SqliteDriver base,SQLQuery query, ArrayList<ArrayList<Term>> storeList ) throws SQLException {
			ResultSet res = base.createStatement().executeQuery(query.toString());
			int nbColonnes = res.getMetaData().getColumnCount();
			
			while(res.next()) {
				for(int i = 1; i<= nbColonnes; i++) {
					String ageSQL = res.getString("AGE");
					String sexSQL = res.getString("SEX");
					String nomSQL = res.getString("NAME");
					
					
					String PrimaryKey[] = nomSQL.split(",");
					String nom = PrimaryKey[0];
					String prenom = PrimaryKey[1];
					

					ArrayList<Term> temp = mainExemple.createTermList();
					temp.add(DefaultTermFactory.instance().createLiteral(nom));
					temp.add(DefaultTermFactory.instance().createLiteral(prenom));
					temp.add(DefaultTermFactory.instance().createLiteral(ageSQL));
					temp.add(DefaultTermFactory.instance().createLiteral(sexSQL));
					storeList.add(temp);
				}
			}
			return storeList;
		}

}
