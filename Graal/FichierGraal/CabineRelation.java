package com.example.graal_TER;

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
	public Predicate APourCabine = new Predicate("APourCabine", 2);
	
	//Création de la db 
	ArrayList<ArrayList<Term>> dbCabineRelation = new ArrayList<ArrayList<Term>>();
	
	
	//Création de la requête pour le mapping
	public SQLQuery APourCabineQuery = new SQLQuery("SELECT NAME,CABIN  FROM TITANICFIRSTCLASS");
	
	
	
	//Méthode d'éxecution de la requête de la relation/table
	public static ArrayList<ArrayList<Term>> storeQuery(SqliteDriver base,SQLQuery query, ArrayList<ArrayList<Term>> storeList ) throws SQLException {
		ResultSet res = base.createStatement().executeQuery(query.toString());
		int nbColonnes = res.getMetaData().getColumnCount();
		
		while(res.next()) {
			for(int i = 1; i<= nbColonnes; i++) {
				String cabinSQL = res.getString("CABIN"); 
				String nomSQL = res.getString("NAME");
				ArrayList<Term> temp = mainExemple.createTermList();
				temp.add(DefaultTermFactory.instance().createLiteral(nomSQL));
				temp.add(DefaultTermFactory.instance().createLiteral(cabinSQL));
				storeList.add(temp);
			}
		}
		return storeList;
	}
	
	
	//Méthode d'insertion des données d'une relation
	public ArrayList<Atom> insertionData(ArrayList<Atom> list) {
		for (int i = 0; i < this.dbCabineRelation.size(); i++) {
			list.add(DefaultAtomFactory.instance().create(APourCabine, dbCabineRelation.get(i)));
			
			}
		return list;
	}
	
	
}
