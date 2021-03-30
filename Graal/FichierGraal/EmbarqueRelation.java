package fr.Graal.testJar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.core.term.DefaultTermFactory;
import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;

public class EmbarqueRelation {
	//Création du prédicat de la relation
	public Predicate AEmbarque = new Predicate("AEmbarque", 3);
	
	//Création de la db 
	public ArrayList<ArrayList<Term>> dbAEmbarqueRelation = new ArrayList<ArrayList<Term>>();
	
	//Création de la requête pour le mapping
	public SQLQuery AEmbarqueQueryFirstClass = new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS PRENOM,substr(NAME, instr(NAME, ',') + 2) AS NOM,HOME_DEST FROM TITANICFIRSTCLASS");
	public SQLQuery	AEmbarqueQuerySecondClass = new SQLQuery("SELECT substr(NAME,1, instr(NAME, ',') - 1) AS PRENOM,substr(NAME, instr(NAME, ',') + 2) AS NOM,HOME_DEST FROM TITANICSECONDCLASS");
	
	//Méthode d'éxecution de la requête de la relation/table
		public static ArrayList<ArrayList<Term>> storeQuery(SqliteDriver base,SQLQuery query, ArrayList<ArrayList<Term>> storeList ) throws SQLException {
			ResultSet res = base.createStatement().executeQuery(query.toString());
			int nbColonnes = res.getMetaData().getColumnCount();
			
			while(res.next()) {
					String homeSQL = res.getString("HOME_DEST");
					String prenomSQL = res.getString("PRENOM");
					String nomSQL = res.getString("NOM");
					System.out.println(prenomSQL);
					System.out.println(nomSQL);
					
				/*	String PrimaryKey[] = nomSQL.split(",");
					String nom = PrimaryKey[0];
					String prenom = PrimaryKey[1];*/
					

					ArrayList<Term> temp = mainExemple.createTermList();
					temp.add(DefaultTermFactory.instance().createLiteral(nomSQL));
					temp.add(DefaultTermFactory.instance().createLiteral(prenomSQL));
					temp.add(DefaultTermFactory.instance().createLiteral(homeSQL));
					storeList.add(temp);
				
			}
			return storeList;
		}


}
