package com.example.graal_TER;

import java.util.ArrayList;
import java.util.List;

import fr.lirmm.graphik.graal.api.core.Atom;
import fr.lirmm.graphik.graal.api.core.Constant;
import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.api.core.Variable;
import fr.lirmm.graphik.graal.core.DefaultAtom;
import fr.lirmm.graphik.graal.core.atomset.LinkedListAtomSet;
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
		
	
	//création du prédicat de la relation passager
	Predicate passager = new Predicate("Passager",4);
	Predicate VoyageTitanic = new Predicate("VoyageTitanic",8);
	
	
	
	// ---- DEBUT Passager 1 ---- //
	
	ArrayList<Term> p1Passager = new ArrayList<Term>();
	p1Passager.add(DefaultTermFactory.instance().createLiteral("Allison"));
	p1Passager.add(DefaultTermFactory.instance().createLiteral("Master. Hudson Trevor"));
	p1Passager.add(DefaultTermFactory.instance().createVariable(0.9167));
	p1Passager.add(DefaultTermFactory.instance().createLiteral("male"));
	
	
	ArrayList<Term> p1VoyageTitanic = new ArrayList<Term>();
	p1VoyageTitanic.add(DefaultTermFactory.instance().createLiteral("113781"));
	p1VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(1));
	p1VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(2));
	p1VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(151.55));
	p1VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(1));
	p1VoyageTitanic.add(DefaultTermFactory.instance().createLiteral("?"));
	p1VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(11));
	p1VoyageTitanic.add(DefaultTermFactory.instance().createLiteral("Chesterville, ON"));
	 
	// ---- FIN Passager 1 ---- //
	
	 ArrayList<Term> p2 = new ArrayList<Term>();
	 p2.add(DefaultTermFactory.instance().createLiteral("Allison"));
	 p2.add(DefaultTermFactory.instance().createLiteral("Miss. Helen Loraine"));
	 p2.add(DefaultTermFactory.instance().createVariable(2));
	 p2.add(DefaultTermFactory.instance().createLiteral("female"));

	 
	 ArrayList<Term> p3 = new ArrayList<Term>();
	 p3.add(DefaultTermFactory.instance().createLiteral("Allison"));
	 p3.add(DefaultTermFactory.instance().createLiteral("Hudson Joshua Creighton"));
	 p3.add(DefaultTermFactory.instance().createVariable(30));
	 p3.add(DefaultTermFactory.instance().createLiteral("male"));
	 
	 
	 // ---- DEBUT Gestion Passager ---- //
	 
	 ArrayList<ArrayList<Term>> dbPassager = new ArrayList<ArrayList<Term>>();
	 dbPassager.add(p1Passager);
	 dbPassager.add(p2);
	 dbPassager.add(p3);
	 
	//liste d'atome contenant tout les passager
		 ArrayList<Atom> passagerList = new ArrayList<Atom>();

		//insertion des données
		 for(int i = 0; i<dbPassager.size(); i++){
			 
		 	passagerList.add( DefaultAtomFactory.instance().create(passager,dbPassager.get(i)));
		 	
		 }
		 
		 
		 //LinkedList ajout des Atom
		 LinkedListAtomSet passagerLLAtomSet = new LinkedListAtomSet() ;
		 passagerLLAtomSet.add(passagerList.get(0));
		 passagerLLAtomSet.add(passagerList.get(1));
		 passagerLLAtomSet.add(passagerList.get(2));
		
	 
	// ---- FIN Gestion Passager ---- //
		 
	
	// ---- DEBUT Gestion VoyageTitanic ---- //
		 
	 ArrayList<ArrayList<Term>> dbVoyageTitanic = new ArrayList<ArrayList<Term>>();
	 dbVoyageTitanic.add(p1VoyageTitanic);
	 
	 ArrayList<Atom> VoyageTitanicList = new ArrayList<Atom>();
	
	 
	 
	 for(int i = 0; i<dbVoyageTitanic.size(); i++){
		 
		 VoyageTitanicList.add(DefaultAtomFactory.instance().create(VoyageTitanic,dbVoyageTitanic.get(i)));
	 	
	 }
	 
	 //LinkedList ajout des Atom
	 LinkedListAtomSet VoyageTitanicLLAtomSet = new LinkedListAtomSet() ;
	 VoyageTitanicLLAtomSet.add(VoyageTitanicList.get(0));
	 
	 
	// ---- FIN Gestion VoyageTitanic ---- //
	 
	
	System.out.println(passagerLLAtomSet);
	System.out.println(VoyageTitanicLLAtomSet);

	
	
	}
	
	
		
    
 }