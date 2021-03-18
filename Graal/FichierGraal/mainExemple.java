package com.example.graal_TER;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.lirmm.graphik.graal.api.core.Atom;
import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.core.atomset.graph.DefaultInMemoryGraphStore;
import fr.lirmm.graphik.graal.core.factory.DefaultAtomFactory;
import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.DBColumn;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;

public class mainExemple {
	
	//Méthode pour créer une liste de Term pour le mapping
	public static ArrayList<Term> createTermList() {
		return new ArrayList<Term>();
		
	}
	
	
public static void main(String args[]) throws SQLException {
	
	
		System.out.println("Récupération de la database et affichage de ses colonnes pour vérification  :");
		System.out.println(" ");
		
		
		//Création du driver et récupération de la base de donnée
		SqliteDriver testBase;
		testBase = GetDatabase.createDatabase();
		
		
		//Affichage des colonnes de la database
		List<DBColumn> res;
		res = testBase.getColumns("TITANICFIRSTCLASS");
		
		for( DBColumn value : res) {
			System.out.println(value.toString());
		}
		
		// liste d'atome contenant tout les passager
		ArrayList<Atom> passagerList = new ArrayList<Atom>();
		
		
		
		// ---- DEBUT GESTION RELATION CABINE EXEMPLE ---- //
		
		//Création de l'objet (prédicat, requête, db)
		CabineRelation cabineRelation = new CabineRelation();
		
		//Execution de la requête/mapping et stockage
		CabineRelation.storeQuery(testBase,cabineRelation.APourCabineQuery,cabineRelation.dbCabineRelation);
		
		//Insertion dans la liste d'Atom final
		cabineRelation.insertionData(passagerList);
		
		
		// ---- FIN GESTION RELATION CABINE EXEMPLE ---- //
		
		System.out.println("Affichage du graphe résultat contenant les différents prédicats créer et remplie avec les données de la database  :");
		System.out.println(" ");
		// Creation du graphe
				DefaultInMemoryGraphStore graphBilan = new DefaultInMemoryGraphStore();
				for (int i = 0; i < cabineRelation.dbCabineRelation.size(); i++) {
					graphBilan.add(passagerList.get(i));
				}
				
				System.out.println(graphBilan.toString());
	}

}
