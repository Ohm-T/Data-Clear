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
	
	//Méthode d'insertion des données d'une relation
		public static ArrayList<Atom> insertionData(ArrayList<Atom> list,Predicate p,ArrayList<ArrayList<Term>> db) {
			for (int i = 0; i < db.size(); i++) {
				list.add(DefaultAtomFactory.instance().create(p, db.get(i)));
				
				}
			return list;
		}
	

public static void main(String args[]) throws SQLException {
	
	
		System.out.println("Récupération des deux classes et affichage des colonnes pour vérification  :");
		System.out.println(" ");
		
		
		//Création du driver et récupération de la base de donnée
		SqliteDriver testBase;
		testBase = GetDatabase.createDatabase();
		
		
		//Affichage des colonnes de la poremière classe
		List<DBColumn> firstClass;
		List<DBColumn> secondClass;
		firstClass = testBase.getColumns("TITANICFIRSTCLASS");
		secondClass = testBase.getColumns("TITANICSECONDCLASS");
		
		for( DBColumn value : firstClass) {
			System.out.println(value.toString());
		}
		
		System.out.println(" ");
		
		for( DBColumn value : secondClass) {
			System.out.println(value.toString());
		}
		
		// liste d'atome contenant tout les passager
		ArrayList<Atom> passagerList = new ArrayList<Atom>();
		
		
		
		// ---- DEBUT GESTION RELATION CABINE EXEMPLE ---- //
		
		//Création de l'objet (prédicat, requête, db)
		CabineRelation cabineRelationFirstClass = new CabineRelation();
		CabineRelation cabineRelationSecondClass = new CabineRelation();
		
		//Execution de la requête/mapping et stockage
		CabineRelation.storeQuery(testBase,cabineRelationFirstClass.APourCabineQueryFirstClass,cabineRelationFirstClass.dbCabineRelation);
		CabineRelation.storeQuery(testBase,cabineRelationSecondClass.APourCabineQuerySecondClass,cabineRelationSecondClass.dbCabineRelation);
		
		//Insertion dans la liste d'Atom final
		System.out.println(passagerList.size());
		insertionData(passagerList,cabineRelationFirstClass.APourCabine,cabineRelationFirstClass.dbCabineRelation);
		System.out.println(passagerList.size());
		insertionData(passagerList,cabineRelationSecondClass.APourCabine,cabineRelationSecondClass.dbCabineRelation);
		System.out.println(passagerList.size());
		
		// ---- FIN GESTION RELATION CABINE EXEMPLE ---- //
		
		System.out.println("Affichage du graphe résultat contenant les différents prédicats créer et remplie avec les données de la database  :");
		System.out.println(" ");
		// Creation du graphe
				DefaultInMemoryGraphStore graphBilan = new DefaultInMemoryGraphStore();
				for (int i = 0; i < passagerList.size(); i++) {
					graphBilan.add(passagerList.get(i));
				}
				System.out.println("Vérification : ");
				
				System.out.println("Taille de la liste de passager :  ");
				System.out.println(passagerList.size());
				System.out.println("Taille du graphe : ");
				System.out.println(graphBilan.size());
				
				System.out.println(graphBilan);
	}

}
