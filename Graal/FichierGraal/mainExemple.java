package fr.Graal.testJar;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.lirmm.graphik.graal.api.core.Atom;
import fr.lirmm.graphik.graal.api.core.ConjunctiveQuery;
import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Substitution;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.api.kb.KnowledgeBase;
import fr.lirmm.graphik.graal.api.kb.KnowledgeBaseException;
import fr.lirmm.graphik.graal.core.AbstractSubstitution;
import fr.lirmm.graphik.graal.core.atomset.graph.DefaultInMemoryGraphStore;
import fr.lirmm.graphik.graal.core.factory.DefaultAtomFactory;
import fr.lirmm.graphik.graal.io.dlp.DlgpParser;
import fr.lirmm.graphik.graal.io.dlp.DlgpWriter;
import fr.lirmm.graphik.graal.kb.KBBuilder;
import fr.lirmm.graphik.graal.kb.KBBuilderException;
import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.DBColumn;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;
import fr.lirmm.graphik.util.stream.CloseableIterator;
import py4j.GatewayServer;



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
		
	//Méthode d'écriture dans un fichier
		public static void writeGraph(DefaultInMemoryGraphStore graph) throws IOException {
			try {
			
			File file = new File("D:\\out.txt");
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < graph.size(); i++) {
				bw.write(graph.toString() + "\n");
			}
			bw.close();
			
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		//Méthode d'une requête 
		public static void writeQuery(CloseableIterator<?> result, String txt) throws IOException {
			try {
					
				File file = new File("C:\\Users\\beaug\\Desktop\\M1S2\\TER\\Python\\" + txt + ".csv");
					
				if(!file.exists()) {
						file.createNewFile();
					}
					
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					
					while(result.hasNext()) {
						bw.write(result.next().toString() + "\n");
					}
					bw.close();
					
					
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
		
		

	

public static void main(String args[]) throws SQLException, IOException, KBBuilderException, KnowledgeBaseException {
	
		
	
	
		System.out.println("Récupération des cacas deux classes et affichage des colonnes pour vérification  :");
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
		
		/// VERSION 1 MAPPING ///
		
		// ---- DEBUT GESTION RELATION CABINE  ---- //
		

	/*	//Création de l'objet (prédicat, requête, db)
		CabineRelation cabineRelationFirstClass = new CabineRelation();
		CabineRelation cabineRelationSecondClass = new CabineRelation();
		
		//Execution de la requête/mapping et stockage
		CabineRelation.storeQuery(testBase,cabineRelationFirstClass.APourCabineQueryFirstClass,cabineRelationFirstClass.dbCabineRelation);
		CabineRelation.storeQuery(testBase,cabineRelationSecondClass.APourCabineQuerySecondClass,cabineRelationSecondClass.dbCabineRelation);
		
		//Insertion dans la liste d'Atom final
		insertionData(passagerList,cabineRelationFirstClass.APourCabine,cabineRelationFirstClass.dbCabineRelation);
		insertionData(passagerList,cabineRelationSecondClass.APourCabine,cabineRelationSecondClass.dbCabineRelation);
		
		// ---- FIN GESTION RELATION CABINE  ---- //
		
		
		// ---- DEBUT GESTION RELATION PASSAGER ---- //
		
		PassagerRelation PassengerFirstClass = new PassagerRelation();
		PassagerRelation PassengerSecondClass = new PassagerRelation();
		
		PassagerRelation.storeQuery(testBase, PassengerFirstClass.PassengerQueryFirstClass,PassengerFirstClass.dbPassagerRelation);
		PassagerRelation.storeQuery(testBase, PassengerSecondClass.PassengerQuerySecondClass,PassengerSecondClass.dbPassagerRelation);
		
		insertionData(passagerList,PassengerFirstClass.Passager,PassengerFirstClass.dbPassagerRelation);
		insertionData(passagerList,PassengerSecondClass.Passager,PassengerSecondClass.dbPassagerRelation);
		

		// ---- FIN GESTION RELATION PASSAGER  ---- //
		
		// ---- DEBUT GESTION RELATION CLASSE ---- //
		ClasseRelation ClasseRelationFirstClass = new ClasseRelation();
		ClasseRelation ClasseRelationSecondClass = new ClasseRelation();
		

		ClasseRelation.storeQuery(testBase, ClasseRelationFirstClass.APourClasseQueryFirstClass,ClasseRelationFirstClass.dbClasseRelation);
		ClasseRelation.storeQuery(testBase, ClasseRelationSecondClass.APourClasseQuerySecondClass,ClasseRelationSecondClass.dbClasseRelation);
		

		insertionData(passagerList,ClasseRelationFirstClass.APourClasse,ClasseRelationFirstClass.dbClasseRelation);
		insertionData(passagerList,ClasseRelationSecondClass.APourClasse,ClasseRelationSecondClass.dbClasseRelation);
		
		// ---- FIN GESTION RELATION CLASSE  ---- //
		
		// ---- DEBUT GESTION RELATION VOYAGETITANIC ---- //
		VoyageTitanicRelation VoyageTitanicFirstClass = new VoyageTitanicRelation();
		VoyageTitanicRelation VoyageTitanicSecondClass = new VoyageTitanicRelation();
		
		VoyageTitanicRelation.storeQuery(testBase, VoyageTitanicFirstClass.VoyageTitanicQueryFirstClass,VoyageTitanicFirstClass.dbVoyageTitanicRelation);
		VoyageTitanicRelation.storeQuery(testBase, VoyageTitanicSecondClass.VoyageTitanicQuerySecondClass,VoyageTitanicSecondClass.dbVoyageTitanicRelation);
		
		insertionData(passagerList,VoyageTitanicFirstClass.VoyageTitanic,VoyageTitanicFirstClass.dbVoyageTitanicRelation);
		insertionData(passagerList,VoyageTitanicSecondClass.VoyageTitanic,VoyageTitanicSecondClass.dbVoyageTitanicRelation);
		
		
		// ---- FIN GESTION RELATION VOYAGETITANIC  ---- //
		
		// ---- DEBUT GESTION RELATION AEMBARQUE ---- //
		
		EmbarqueRelation AEmbarqueFirstClass = new EmbarqueRelation();
		EmbarqueRelation AEmbarqueSecondClass = new EmbarqueRelation();
		
		EmbarqueRelation.storeQuery(testBase, AEmbarqueFirstClass.AEmbarqueQueryFirstClass,AEmbarqueFirstClass.dbAEmbarqueRelation);
		EmbarqueRelation.storeQuery(testBase, AEmbarqueSecondClass.AEmbarqueQuerySecondClass,AEmbarqueSecondClass.dbAEmbarqueRelation);
		
		insertionData(passagerList,AEmbarqueFirstClass.AEmbarque,AEmbarqueFirstClass.dbAEmbarqueRelation);
		insertionData(passagerList,AEmbarqueSecondClass.AEmbarque,AEmbarqueSecondClass.dbAEmbarqueRelation);
		
		*/
		// ---- FIN GESTION RELATION AEMBARQUE  ---- //
		
		///  FIN VERSION 1 MAPPING ///
		
		/// DEBUT VERSION 2 MAPPING ///
		passagerList.addAll(SQLMappingEvaluator.evaluate(testBase, new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS NOM,substr(NAME, instr(NAME, ',') + 2) AS PRENOM, CABIN FROM TITANICFIRSTCLASS"), new Predicate("cabineRelation",3)));
		passagerList.addAll(SQLMappingEvaluator.evaluate(testBase, new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS NOM,substr(NAME, instr(NAME, ',') + 2) AS PRENOM, CABIN FROM TITANICSECONDCLASS"), new Predicate("cabineRelation",3)));
		passagerList.addAll(SQLMappingEvaluator.evaluate(testBase, new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS NOM,substr(NAME, instr(NAME, ',') + 2) AS PRENOM,AGE,SEX FROM TITANICFIRSTCLASS"), new Predicate("passagerRelation",4)));
		passagerList.addAll(SQLMappingEvaluator.evaluate(testBase, new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS NOM,substr(NAME, instr(NAME, ',') + 2) AS PRENOM,AGE,SEX FROM TITANICSECONDCLASS"), new Predicate("passagerRelation",4)));
		
		passagerList.addAll(SQLMappingEvaluator.evaluate(testBase, new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS NOM,substr(NAME, instr(NAME, ',') + 2) AS PRENOM,TICKET,SIBSP,PARCH,TFARE,SURVIVED,BOAT,BODY FROM TITANICFIRSTCLASS"), new Predicate("voyageTitanic", 9)));
		passagerList.addAll(SQLMappingEvaluator.evaluate(testBase, new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS NOM,substr(NAME, instr(NAME, ',') + 2) AS PRENOM,TICKET,SIBSP,PARCH,TFARE,SURVIVED,BOAT,BODY FROM TITANICSECONDCLASS"), new Predicate("voyageTitanic", 9)));
		
		passagerList.addAll(SQLMappingEvaluator.evaluate(testBase, new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS NOM,substr(NAME, instr(NAME, ',') + 2) AS PRENOM,PCLASS FROM TITANICFIRSTCLASS"), new Predicate("aPourClasse", 3)));
		passagerList.addAll(SQLMappingEvaluator.evaluate(testBase, new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS NOM,substr(NAME, instr(NAME, ',') + 2) AS PRENOM,PCLASS FROM TITANICSECONDCLASS"), new Predicate("aPourClasse", 3)));
		
		passagerList.addAll(SQLMappingEvaluator.evaluate(testBase, new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS NOM,substr(NAME, instr(NAME, ',') + 2) AS PRENOM,HOME_DEST FROM TITANICFIRSTCLASS"), new Predicate("aEmbarque", 3)));
		passagerList.addAll(SQLMappingEvaluator.evaluate(testBase, new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS NOM,substr(NAME, instr(NAME, ',') + 2) AS PRENOM,HOME_DEST FROM TITANICSECONDCLASS"), new Predicate("aEmbarque", 3)));
	
		/// FIN VERSION 2 MAPPING ///
		
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
				
				System.out.println("Ecriture du graphe dans le fichier out :  ");
			//	writeGraph(graphBilan);
				System.out.println("Fin Ecriture du graphe dans le fichier out");
				
		// Création de la base de faits
				
		KBBuilder kbase = new KBBuilder();
		kbase.setStore(graphBilan);
		KnowledgeBase kb = kbase.build();
		
		
		
		System.out.println("Requête DLGP : ");
		
		ConjunctiveQuery query = DlgpParser.parseQuery("?(A,B,C,D,M,E,F,G,H,K) :- " 
		+ " passagerRelation(A,B,C,D),"
		+ " cabineRelation(A,B,M)"
		+ " aPourClasse(A,B,E),"
		+ " voyageTitanic(A,B,F,G,H,I,J,K,L).");

		ConjunctiveQuery check = DlgpParser.parseQuery("?(A,B,G) :- "
		+ " voyageTitanic(A,B,C,D,E,F,G,H,I).");
		
		
		System.out.println(query);	
		
		CloseableIterator<?> result = kb.query(query);
		CloseableIterator<?> checked = kb.query(check);

		System.out.println("Début écriture requête : ");
		writeQuery(result,"data");
		writeQuery(checked,"check");
		
		System.out.println("Fin écriture requête !");
		
		
	
}

}
