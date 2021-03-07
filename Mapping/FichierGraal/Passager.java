package com.example.graal_TER;

import java.util.ArrayList;
import java.util.List;

import fr.lirmm.graphik.graal.api.core.Atom;
import fr.lirmm.graphik.graal.api.core.Constant;
import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.api.core.Variable;
import fr.lirmm.graphik.graal.core.DefaultAtom;
import fr.lirmm.graphik.graal.core.factory.DefaultAtomFactory;
import fr.lirmm.graphik.graal.core.term.DefaultTermFactory;


// pclass,survived,name,sex,age,sibsp,parch,ticket,fare,cabin,embarked,boat,body,home.dest
// 1,1,"Allison, Master. Hudson Trevor",male,0.9167,1,2,113781,151.55,C22 C26,S,11,?,"Montreal, PQ / Chesterville, ON"
// 1,0,"Allison, Miss. Helen Loraine",female,2,1,2,113781,151.55,C22 C26,S,?,?,"Montreal, PQ / Chesterville, ON"
// 1,0,"Allison, Mr. Hudson Joshua Creighton",male,30,1,2,113781,151.55,C22 C26,S,?,135,"Montreal, PQ / Chesterville, ON"

public class Passager
{
	// Création de la relation passager
	public static void main(String args[]) {
		
	
	//création du prédicat de la relation
	String personne = "passager";
	Predicate passager = new Predicate(personne,4);
	
	int x = 30;
	//création des Terms de la relation passager
	Term nom = DefaultTermFactory.instance().createLiteral("Allison");
	Term prenom = DefaultTermFactory.instance().createLiteral("Hudson Joshua Creighton");
	Term age = DefaultTermFactory.instance().createVariable(x);
	Term sexe = DefaultTermFactory.instance().createLiteral("homme");
	
	
	
	
	
	
	List<Term> attributs = new ArrayList<Term>();
	attributs.add(nom);
	attributs.add(prenom);
	attributs.add(age);
	attributs.add(sexe);
	
	
	Atom passagerAtom = DefaultAtomFactory.instance().create(passager,attributs);
	
	System.out.println(passagerAtom);

	
	
	}
	
	
		
    
 }
