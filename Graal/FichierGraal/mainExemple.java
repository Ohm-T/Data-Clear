package com.example.graal_TER;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.lirmm.graphik.graal.api.core.Atom;
import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.core.atomset.graph.DefaultInMemoryGraphStore;
import fr.lirmm.graphik.graal.core.factory.DefaultAtomFactory;
import fr.lirmm.graphik.graal.core.term.DefaultTermFactory;
import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.DBColumn;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;

public class mainExemple {
	
public static void main(String args[]) throws SQLException {
	
	
		System.out.println("Récupération de la database et affichage de ses colonnes pour vérification  :");
		System.out.println(" ");
		
		SqliteDriver testBase;
		testBase = GetDatabase.createDatabase();
		List<DBColumn> res;
		res = testBase.getColumns("TITANICFIRSTCLASS");
		
		for( DBColumn value : res) {
			System.out.println(value.toString());
		}
		
		Predicate age = new Predicate("Age", 2);
		ArrayList<Term> agePassager = new ArrayList<Term>();
		ArrayList<ArrayList<Term>> dbAgePassager = new ArrayList<ArrayList<Term>>();
		
		// Création d'une requête (pour s'en rappeler)
		SQLQuery test = new SQLQuery("SELECT AGE, NAME FROM TITANICFIRSTCLASS");
		
		
		// Execution d'un requête
		ResultSet testQ = testBase.createStatement().executeQuery(test.toString());
		
		//Récupérer le nombre de colonnes (pour itérer dessus)
		int nbColonnes = testQ.getMetaData().getColumnCount();
		
		while(testQ.next()) {
			for(int i = 1; i<= nbColonnes; i++) {
				String ageSQL = testQ.getString("AGE"); 
				System.out.println(ageSQL);
				String nomSQL = testQ.getString("NAME");
				agePassager.add(DefaultTermFactory.instance().createLiteral(ageSQL));
				agePassager.add(DefaultTermFactory.instance().createLiteral(nomSQL));
				dbAgePassager.add(agePassager);
			}
		}
		
		

		
		// liste d'atome contenant tout les passager
		ArrayList<Atom> passagerList = new ArrayList<Atom>();
		
		// insertion des données
		for (int i = 0; i < dbAgePassager.size(); i++) {

		passagerList.add(DefaultAtomFactory.instance().create(age, dbAgePassager.get(i)));

		}
		
		// Creation du graphe
				DefaultInMemoryGraphStore graphBilan = new DefaultInMemoryGraphStore();
				for (int i = 0; i < dbAgePassager.size(); i++) {
					graphBilan.add(passagerList.get(i));
				}
				
				System.out.println(graphBilan.toString());
	}

}
