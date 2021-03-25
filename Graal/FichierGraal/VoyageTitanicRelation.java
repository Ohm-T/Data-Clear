package com.example.graal_TER;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.core.term.DefaultTermFactory;
import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;

public class VoyageTitanicRelation {
	
	//Création du prédicat de la relation
		public Predicate VoyageTitanic = new Predicate("VoyageTitanic", 9);
		
		//Création de la db 
		ArrayList<ArrayList<Term>> dbVoyageTitanicRelation = new ArrayList<ArrayList<Term>>();
		
		
		//Création de la requête pour le mapping
		public SQLQuery VoyageTitanicQueryFirstClass = new SQLQuery("SELECT NAME,TICKET,SIBSP,PARCH,TFARE,SURVIVED,BOAT,BODY FROM TITANICFIRSTCLASS");
		public SQLQuery VoyageTitanicQuerySecondClass = new SQLQuery("SELECT NAME,TICKET,SIBSP,PARCH,TFARE,SURVIVED,BOAT,BODY FROM TITANICSECONDCLASS");
		
		
		//Méthode d'éxecution de la requête de la relation/table
		public static ArrayList<ArrayList<Term>> storeQuery(SqliteDriver base,SQLQuery query, ArrayList<ArrayList<Term>> storeList ) throws SQLException {
			ResultSet res = base.createStatement().executeQuery(query.toString());
			int nbColonnes = res.getMetaData().getColumnCount();
			
			while(res.next()) {
					String nomSQL = res.getString("NAME");
					String ticketSQL = res.getString("TICKET"); 
					String sibspSQL = res.getString("SIBSP");
					String parchSQL = res.getString("PARCH");
					String tfareSQL = res.getString("TFARE");
					String survivedSQL = res.getString("SURVIVED"); 
					String bodySQL = res.getString("BODY");
					String boatSQL = res.getString("BOAT");
					
					
					
					
					String PrimaryKey[] = nomSQL.split(",");
					String nom = PrimaryKey[0];
					String prenom = PrimaryKey[1];
					

					ArrayList<Term> temp = mainExemple.createTermList();
					temp.add(DefaultTermFactory.instance().createLiteral(nom));
					temp.add(DefaultTermFactory.instance().createLiteral(prenom));
					temp.add(DefaultTermFactory.instance().createLiteral(ticketSQL));
					temp.add(DefaultTermFactory.instance().createLiteral(sibspSQL));
					temp.add(DefaultTermFactory.instance().createLiteral(parchSQL));
					temp.add(DefaultTermFactory.instance().createLiteral(tfareSQL));
					temp.add(DefaultTermFactory.instance().createLiteral(survivedSQL));
					temp.add(DefaultTermFactory.instance().createLiteral(bodySQL));
					temp.add(DefaultTermFactory.instance().createLiteral(boatSQL));;
					storeList.add(temp);
				
			}
			return storeList;
		}

}
