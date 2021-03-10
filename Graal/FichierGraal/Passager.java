package com.example.graal_TER;

import java.util.ArrayList;

import fr.lirmm.graphik.graal.api.core.Atom;
import fr.lirmm.graphik.graal.api.core.Predicate;
import fr.lirmm.graphik.graal.api.core.Term;
import fr.lirmm.graphik.graal.core.atomset.graph.DefaultInMemoryGraphStore;
import fr.lirmm.graphik.graal.core.factory.DefaultAtomFactory;
import fr.lirmm.graphik.graal.core.term.DefaultTermFactory;

// pclass,survived,name,sex,age,sibsp,parch,ticket,fare,cabin,embarked,boat,body,home.dest

public class Passager {
	// Création de la relation passager
	public static void main(String args[]) {

		// création du prédicat de la relation passager
		Predicate passager = new Predicate("Passager", 4);
		Predicate VoyageTitanic = new Predicate("VoyageTitanic", 8);
		Predicate Possede = new Predicate("Possede", 3);
		Predicate APourCabine = new Predicate("APourCabine", 3);
		Predicate APourClasse = new Predicate("APourClasse", 3);
		Predicate AEmbarqueA = new Predicate("AEmbarqueA", 3);

		// ---- DEBUT Passager 1 ---- //
		// 1,1,"Allison, Master. Hudson Trevor",male,0.9167,1,2,113781,151.55,C22
		// C26,S,11,?,"Montreal, PQ / Chesterville, ON"

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

		ArrayList<Term> p1Possede = new ArrayList<Term>();
		p1Possede.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p1Possede.add(DefaultTermFactory.instance().createLiteral("Master. Hudson Trevor"));
		p1Possede.add(DefaultTermFactory.instance().createLiteral(113781));

		ArrayList<Term> p1Cabine = new ArrayList<Term>();
		p1Cabine.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p1Cabine.add(DefaultTermFactory.instance().createLiteral("Master. Hudson Trevor"));
		p1Cabine.add(DefaultTermFactory.instance().createLiteral("C22, C26"));

		ArrayList<Term> p1Classe = new ArrayList<Term>();
		p1Classe.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p1Classe.add(DefaultTermFactory.instance().createLiteral("Master. Hudson Trevor"));
		p1Classe.add(DefaultTermFactory.instance().createLiteral(1));

		ArrayList<Term> p1Embarque = new ArrayList<Term>();
		p1Embarque.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p1Embarque.add(DefaultTermFactory.instance().createLiteral("Master. Hudson Trevor"));
		p1Embarque.add(DefaultTermFactory.instance().createLiteral("Montreal, PQ"));

		// ---- FIN Passager 1 ---- //

		// ---- DEBUT Passager 2 ---- //
		// 1,0,"Allison, Miss. Helen Loraine",female,2,1,2,113781,151.55,C22
		// C26,S,?,?,"Montreal, PQ / Chesterville, ON"

		ArrayList<Term> p2Passager = new ArrayList<Term>();
		p2Passager.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p2Passager.add(DefaultTermFactory.instance().createLiteral("Miss. Helen Loraine"));
		p2Passager.add(DefaultTermFactory.instance().createVariable(2));
		p2Passager.add(DefaultTermFactory.instance().createLiteral("female"));

		ArrayList<Term> p2VoyageTitanic = new ArrayList<Term>();
		p2VoyageTitanic.add(DefaultTermFactory.instance().createLiteral("113781"));
		p2VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(2));
		p2VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(1));
		p2VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(151.55));
		p2VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(0));
		p2VoyageTitanic.add(DefaultTermFactory.instance().createLiteral("?"));
		p2VoyageTitanic.add(DefaultTermFactory.instance().createLiteral("?"));
		p2VoyageTitanic.add(DefaultTermFactory.instance().createLiteral("Chesterville, ON"));

		ArrayList<Term> p2Possede = new ArrayList<Term>();
		p2Possede.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p2Possede.add(DefaultTermFactory.instance().createLiteral("Miss. Helen Loraine"));
		p2Possede.add(DefaultTermFactory.instance().createLiteral(113781));

		ArrayList<Term> p2Cabine = new ArrayList<Term>();
		p2Cabine.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p2Cabine.add(DefaultTermFactory.instance().createLiteral("Miss. Helen Loraine"));
		p2Cabine.add(DefaultTermFactory.instance().createLiteral("C22, C26"));

		ArrayList<Term> p2Classe = new ArrayList<Term>();
		p2Classe.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p2Classe.add(DefaultTermFactory.instance().createLiteral("Miss. Helen Loraine"));
		p2Classe.add(DefaultTermFactory.instance().createLiteral(1));

		ArrayList<Term> p2Embarque = new ArrayList<Term>();
		p2Embarque.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p2Embarque.add(DefaultTermFactory.instance().createLiteral("Miss. Helen Loraine"));
		p2Embarque.add(DefaultTermFactory.instance().createLiteral("Montreal, PQ"));

		// ---- FIN Passager 2 ---- //

		// ---- DEBUT Passager 3 ---- //
		// 1,0,"Allison, Mr. Hudson Joshua Creighton",male,30,1,2,113781,151.55,C22
		// C26,S,?,135,"Montreal, PQ / Chesterville, ON"

		ArrayList<Term> p3Passager = new ArrayList<Term>();
		p3Passager.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p3Passager.add(DefaultTermFactory.instance().createLiteral("Mr. Hudson Joshua Creighton"));
		p3Passager.add(DefaultTermFactory.instance().createVariable(30));
		p3Passager.add(DefaultTermFactory.instance().createLiteral("male"));

		ArrayList<Term> p3VoyageTitanic = new ArrayList<Term>();
		p3VoyageTitanic.add(DefaultTermFactory.instance().createLiteral("113781"));
		p3VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(1));
		p3VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(2));
		p3VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(151.55));
		p3VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(0));
		p3VoyageTitanic.add(DefaultTermFactory.instance().createLiteral(136));
		p3VoyageTitanic.add(DefaultTermFactory.instance().createLiteral("?"));
		p3VoyageTitanic.add(DefaultTermFactory.instance().createLiteral("Chesterville, ON"));

		ArrayList<Term> p3Possede = new ArrayList<Term>();
		p3Possede.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p3Possede.add(DefaultTermFactory.instance().createLiteral("Mr. Hudson Joshua Creighton"));
		p3Possede.add(DefaultTermFactory.instance().createLiteral(113781));

		ArrayList<Term> p3Cabine = new ArrayList<Term>();
		p3Cabine.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p3Cabine.add(DefaultTermFactory.instance().createLiteral("Mr. Hudson Joshua Creighton"));
		p3Cabine.add(DefaultTermFactory.instance().createLiteral("C22, C26"));

		ArrayList<Term> p3Classe = new ArrayList<Term>();
		p3Classe.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p3Classe.add(DefaultTermFactory.instance().createLiteral("Mr. Hudson Joshua Creighton"));
		p3Classe.add(DefaultTermFactory.instance().createLiteral(1));

		ArrayList<Term> p3Embarque = new ArrayList<Term>();
		p3Embarque.add(DefaultTermFactory.instance().createLiteral("Allison"));
		p3Embarque.add(DefaultTermFactory.instance().createLiteral("Mr. Hudson Joshua Creighton"));
		p3Embarque.add(DefaultTermFactory.instance().createLiteral("Montreal, PQ"));

		// ---- FIN Passager 3 ---- //

		// ---- DEBUT Gestion Passager ---- //

		ArrayList<ArrayList<Term>> dbPassager = new ArrayList<ArrayList<Term>>();
		dbPassager.add(p1Passager);
		dbPassager.add(p2Passager);
		dbPassager.add(p3Passager);

		// liste d'atome contenant tout les passager
		ArrayList<Atom> passagerList = new ArrayList<Atom>();

		// insertion des données
		for (int i = 0; i < dbPassager.size(); i++) {

			passagerList.add(DefaultAtomFactory.instance().create(passager, dbPassager.get(i)));

		}

		// ---- FIN Gestion Passager ---- //

		// ---- DEBUT Gestion VoyageTitanic ---- //

		ArrayList<ArrayList<Term>> dbVoyageTitanic = new ArrayList<ArrayList<Term>>();
		dbVoyageTitanic.add(p1VoyageTitanic);
		dbVoyageTitanic.add(p2VoyageTitanic);
		dbVoyageTitanic.add(p3VoyageTitanic);

		ArrayList<Atom> VoyageTitanicList = new ArrayList<Atom>();

		for (int i = 0; i < dbVoyageTitanic.size(); i++) {

			VoyageTitanicList.add(DefaultAtomFactory.instance().create(VoyageTitanic, dbVoyageTitanic.get(i)));

		}

		// ---- FIN Gestion VoyageTitanic ---- //

		// ---- DEBUT Association Possede ---- //

		ArrayList<ArrayList<Term>> dbPossede = new ArrayList<ArrayList<Term>>();
		dbPossede.add(p1Possede);
		dbPossede.add(p2Possede);
		dbPossede.add(p3Possede);

		// liste d'atome contenant tout les passager
		ArrayList<Atom> possedeList = new ArrayList<Atom>();

		// insertion des données
		for (int i = 0; i < dbPossede.size(); i++) {

			possedeList.add(DefaultAtomFactory.instance().create(Possede, dbPossede.get(i)));

		}

		// ---- FIN Association Possede ---- //

		// ---- DEBUT Association APourCabine ---- //

		ArrayList<ArrayList<Term>> dbAPourCabine = new ArrayList<ArrayList<Term>>();
		dbAPourCabine.add(p1Cabine);
		dbAPourCabine.add(p2Cabine);
		dbAPourCabine.add(p3Cabine);

		// liste d'atome contenant tout les passager
		ArrayList<Atom> CabineList = new ArrayList<Atom>();

		// insertion des données
		for (int i = 0; i < dbAPourCabine.size(); i++) {

			CabineList.add(DefaultAtomFactory.instance().create(APourCabine, dbAPourCabine.get(i)));

		}

		// ---- FIN Association APourCabine ---- //

		// ---- DEBUT Association APourClasse ---- //

		ArrayList<ArrayList<Term>> dbAPourClasse = new ArrayList<ArrayList<Term>>();
		dbAPourClasse.add(p1Classe);
		dbAPourClasse.add(p2Classe);
		dbAPourClasse.add(p3Classe);

		// liste d'atome contenant tout les passager
		ArrayList<Atom> ClasseList = new ArrayList<Atom>();

		// insertion des données
		for (int i = 0; i < dbAPourClasse.size(); i++) {

			ClasseList.add(DefaultAtomFactory.instance().create(APourClasse, dbAPourClasse.get(i)));

		}

		// ---- FIN Association APourClasse ---- //

		// ---- DEBUT Association AEmbarqueA ---- //

		ArrayList<ArrayList<Term>> dbAEmbarqueA = new ArrayList<ArrayList<Term>>();
		dbAEmbarqueA.add(p1Embarque);
		dbAEmbarqueA.add(p2Embarque);
		dbAEmbarqueA.add(p3Embarque);

		// liste d'atome contenant tout les passager
		ArrayList<Atom> EmbarqueList = new ArrayList<Atom>();

		// insertion des données
		for (int i = 0; i < dbAEmbarqueA.size(); i++) {

			EmbarqueList.add(DefaultAtomFactory.instance().create(AEmbarqueA, dbAEmbarqueA.get(i)));

		}

		// ---- FIN Association AEmbarqueA ---- //

		// Creation du graphe
		DefaultInMemoryGraphStore graphBilan = new DefaultInMemoryGraphStore();
		for (int i = 0; i < dbPassager.size(); i++) {
			graphBilan.add(passagerList.get(i));
			graphBilan.add(VoyageTitanicList.get(i));
			graphBilan.add(possedeList.get(i));
			graphBilan.add(CabineList.get(i));
			graphBilan.add(ClasseList.get(i));
			graphBilan.add(EmbarqueList.get(i));
		}

		System.out.println(graphBilan.toString());

	}

}