package fr.Graal.testJar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import fr.lirmm.graphik.graal.api.core.Atom;
import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.core.factory.DefaultAtomFactory;
import fr.lirmm.graphik.graal.core.term.DefaultTermFactory;
import fr.lirmm.graphik.graal.store.rdbms.driver.SqliteDriver;
import fr.lirmm.graphik.graal.store.rdbms.util.SQLQuery;

public class MongoDBMappingEvaluator {
	//SQlMappingEvaluater
		public static ArrayList<Atom> evaluate(MongoCollection<Document> coll,FindIterable<Document> query, Predicate p) throws SQLException {
			
			ArrayList<Atom> tempAtomList = new ArrayList<Atom>();

			try {
			
			for(Document x : query) {
				ArrayList<Term> temp = mainExemple.createTermList();
				// "Document{{lastname=Mahon, firstname=Mr. John, sex=male}}
				//Tant qu'il y a des données à mettre dans le prédicat
				String tempString = "";
				for(int i = 1; i <= p.getArity(); i++) {
					String tempS = x.toString();
					String[] pls = tempS.split("[a-zA-Z_0-9]*=");
					
					if(i >= pls.length) {
						tempString = "null";
					}
					else {
					
					tempString = pls[i];
					tempString = tempString.replace("}}", "");
					System.out.println(tempString);
					}
					if(tempString != "null")  {					
						temp.add(DefaultTermFactory.instance().createLiteral(tempString));
					}
					else {
						temp.add(DefaultTermFactory.instance().createVariable(tempString));
					}
					
				}
				tempAtomList.add(DefaultAtomFactory.instance().create(p, temp));
				
			}
			} catch(Exception e) {
				e.printStackTrace();
			}
			

			
			
			return tempAtomList;
			
		}

}
