package SQLITEToSQLITE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import csvToSQLITE.SQLiteJDBC;

//https://stackoverflow.com/questions/11155886/split-firstname-and-lastname-in-sqlite
public class SQLITEToSQLITE {
	
	static String PATH1 = "/home/hayaat/Desktop/Master/M1/TER/BDD/CSV/JAVA/SqlLite/Titanic.db";	
	static String dataPath = "/home/hayaat/Desktop/Master/M1/TER/BDD/CSV/JAVA/SqlLite/out/";
	
	/********************************************
	 * 
	 * CREATION DES OUTILS POUR LES REQUETES SQL
	 * 
	 ********************************************/
	
    Connection c = null;
    Statement stmt = null;
    
	/********************************
	 * 
	 * CONNEXION A LA DB
	 * 
	 ********************************/
    
    private void openConnectionToOldDb() throws SQLException
    {
        if (c == null || c.isClosed())
        {
        	c = DriverManager.getConnection("jdbc:sqlite:Titanic.db");
            //c.setAutoCommit(false);
            System.out.println("Connexion successfull");
        }
    }
    
    private void openConnectionToNewDb() throws SQLException
    {
        if (c == null || c.isClosed())
        {
        	c = DriverManager.getConnection("jdbc:sqlite:TitanicMapped.db");
            //c.setAutoCommit(false);
            System.out.println("Connexion successfull");
        }
    }
    
	/********************************
	 * 
	 * DECONNEXION A LA DB
	 * 
	 ********************************/

    // Closes connection to database
    private void closeConnection() throws SQLException
    {
        c.close();
    }
    
	/********************************
	 * 
	 * MISE EN PLACE DU LIENS ENTRE LES DB DEPUIS LA DB EXPEDITEUR
	 * 
	 ********************************/
    
    private void attachTo() throws SQLException{
    	String attach = "ATTACH DATABASE 'TitanicMapped.db' AS TitanicMapped";
    	
    	stmt = c.createStatement();
        stmt.executeUpdate(attach);
        stmt.close();
        
        System.out.println("Attach succesfull");
    }
    
	
	/*******************************************************
	 * 
	 * CREATION DES TABLEAUX DANS LA DB DESTINATAIRE
	 * 
	 ********************************************************/
    
	public void createTables() throws SQLException {
		
		try {
		    String billetVoyageTitanic = "CREATE TABLE BILLETVOYAGETITANIC " +
		            "( " +
		            " TICKET      VARCHAR(20) ,"+
		            " SIBSP       INT,"+
		            " PARCH       INT,"+
		            " TFARE       FLOAT,"+
		            " SURVIVED    BOOLEAN," + 
		            " BODY        VARCHAR(20),"+//or NULL
		            " BOAT        VARCHAR(20),"+//or NULL
		            " HOME        VARCHAR(20), " + 
		            " DEST        VARCHAR(20) ) ;";
		    
		    String passager = "CREATE TABLE PASSAGER " +
		            " ( " +
		            " FIRSTNAME        CHAR(60),"+ 
		    		" LASTNAME   CHAR(40),"+ 
		            " AGE         REAL,"+ //or NULL
		            " SEX         CHAR(1)"+
		            " ,CONSTRAINT IDPASSAGER PRIMARY KEY (FIRSTNAME, LASTNAME)"+
		             " ) ;" ;
		    
		    String classe = "CREATE TABLE CLASSE " +
		            "( " +
		            " PCLASS REAL NOT NULL PRIMARY KEY) ;";
		    
		    String cabine = "CREATE TABLE CABINE " +
		            "( " +
		            " CABIN VARCHAR(20) NOT NULL PRIMARY KEY) ;";
		    
		    String pontEmbarcation = "CREATE TABLE PONTEMBARCATION " +
		            "( " +
		            " EMBARKED CHAR(1) NOT NULL PRIMARY KEY) ;";
		    
		    
		    String aPourClasse = "CREATE TABLE APOURCLASSE " +
		            "( " +
		            " PCLASS REAL," +
		            " TICKET      VARCHAR(20)," +
		            //" CONSTRAINT PK_APOURCLASSE PRIMARY KEY (PCLASS , TICKET)," +
		            " FOREIGN KEY (PCLASS) REFERENCES CLASSE (PCLASS)" +
		            " ON DELETE CASCADE ," +
		            " FOREIGN KEY (TICKET) REFERENCES BILLETVOYAGETITANIC (TICKET)" +
		            " ON DELETE CASCADE ) ;" ;
		    
		    String possede = "CREATE TABLE POSSEDE " +
		            "( " +
		    		" IDPASSAGER  VARCHAR(60),"+
		            " TICKET      VARCHAR(20)," +
		            " CONSTRAINT PK_POSSEDE PRIMARY KEY (IDPASSAGER , TICKET)," +
		            " FOREIGN KEY (IDPASSAGER) REFERENCES PASSAGER (IDPASSAGER)" +
		            " ON DELETE CASCADE ," +
		            " FOREIGN KEY (TICKET) REFERENCES BILLETVOYAGETITANIC (TICKET)" +
		            " ON DELETE CASCADE ) ;" ;
		    
		    String aEmbarqueA = "CREATE TABLE AEMBARQUEA " +
		            "( " +
		    		" EMBARKED CHAR(1) ,"+
		            " TICKET      VARCHAR(20)," +
		            //" CONSTRAINT PK_AEMBARQUEA PRIMARY KEY  (EMBARKED , TICKET)," +
		            " FOREIGN KEY (EMBARKED) REFERENCES PONTEMBARCATION (EMBARKED)" +
		            " ON DELETE CASCADE ," +
		            " FOREIGN KEY (TICKET) REFERENCES BILLETVOYAGETITANIC (TICKET)" +
		            " ON DELETE CASCADE ) ;" ;
		    
		    String aPourCabine = "CREATE TABLE APOURCABINE " + //CHQ A ENTRE 0,N CABINES
		            "( " +
		    		" CABIN VARCHAR(20) ,"+
		            " TICKET      VARCHAR(20)," +
		            //" CONSTRAINT PK_APOURCABINE PRIMARY KEY(CABIN , TICKET)," +
		            " FOREIGN KEY (CABIN) REFERENCES CABINE (CABIN)" +
		            " ON DELETE CASCADE ," +
		            " FOREIGN KEY (TICKET) REFERENCES BILLETVOYAGETITANIC (TICKET)" +
		            " ON DELETE CASCADE ) ;" ;
		    
		    String drop = "DROP TABLE IF EXISTS APOURCABINE ; "+
		    		"DROP TABLE IF EXISTS AEMBARQUEA ; "+
		    		"DROP TABLE IF EXISTS POSSEDE ; "+
		    		"DROP TABLE IF EXISTS APOURCLASSE ; "+
		    		"DROP TABLE IF EXISTS PONTEMBARCATION ; "+
		    		"DROP TABLE IF EXISTS CLASSE ; "+
		    		"DROP TABLE IF EXISTS CABINE ; "+
		    		"DROP TABLE IF EXISTS PASSAGER ; "+
		    		"DROP TABLE IF EXISTS BILLETVOYAGETITANIC ; ";
		    

	        stmt = c.createStatement();
	        stmt.executeUpdate(drop);
	        System.out.println("DROPPED");
	        
	        stmt.executeUpdate(billetVoyageTitanic);
	        System.out.println("BILLETVOYAGETITANIC created");
	        stmt.executeUpdate(passager);
	        System.out.println("PASSAGER created");
	        stmt.executeUpdate(classe);
	        System.out.println("CLASSE created");
	        stmt.executeUpdate(cabine);
	        System.out.println("CABINE created");
	        stmt.executeUpdate(pontEmbarcation);
	        System.out.println("PONTEMBARCATION created");
	        System.out.println("");
	        
	        stmt.executeUpdate(possede);
	        System.out.println("POSSEDE created");
	        stmt.executeUpdate(aPourClasse);
	        System.out.println("APOURCLASSE created");
	        stmt.executeUpdate(aEmbarqueA);
	        System.out.println("AEMBARQUERA created");
	        stmt.executeUpdate(aPourCabine);
	        System.out.println("APOURCABINE created");
	        System.out.println("");
	        
	        stmt.close();
	        
	        System.out.println("Tables successfully created");
	        
	        
		    } 
			catch(SQLException e)
			{
				System.out.println(e.getMessage());

			}
		}  
	
	/*****************************************************************************
	 * 
	 * MISE EN PLACE DES FONCTIONS D'INSERTION DE DONNÉS DANS LA DB DESTINATAIRE
	 * 
	 *****************************************************************************/

    private void billet() throws SQLException{
    	
    	stmt = c.createStatement();
    	
    	String billet = "INSERT INTO TitanicMapped.BILLETVOYAGETITANIC"
    			+ " (TICKET, SIBSP , PARCH , TFARE , SURVIVED , BODY , BOAT, HOME, DEST) "
    			+ " SELECT TICKET, SIBSP , PARCH , TFARE , SURVIVED ,"
    			+ " BODY , BOAT , "
    			+ " (SELECT substr(HOME_DEST,1, instr(HOME_DEST, '/') - 1)) , "
    			+ " (SELECT substr(HOME_DEST, instr(HOME_DEST, '/' ) + 1)) "
    			+ " FROM TITANICFIRSTCLASS ;";

        stmt.executeUpdate(billet);
        
    	billet = "INSERT INTO TitanicMapped.BILLETVOYAGETITANIC"
    			+ " (TICKET, SIBSP , PARCH , TFARE , SURVIVED , BODY , BOAT, HOME, DEST) "
    			+ " SELECT TICKET, SIBSP , PARCH , TFARE , SURVIVED ,"
    			+ " BODY , BOAT , "
    			+ " (SELECT substr(HOME_DEST,1, instr(HOME_DEST, '/') - 1)) , "
    			+ " (SELECT substr(HOME_DEST, instr(HOME_DEST, '/' ) + 1)) "
    			+ " FROM TITANICSECONDCLASS ;";

        stmt.executeUpdate(billet);       
        stmt.close();
        
        System.out.println("Insertion of TITANIC to BILLETVOYAGETITANIC");
    }
    
    private void passager() throws SQLException{
    	System.out.println("START PASSAGER");
    	
    	String pass = "INSERT INTO PASSAGER"
    			+ "(LASTNAME , FIRSTNAME , AGE , SEX) "
    			+" SELECT  "
    			+ " (SELECT substr(NAME,1, instr(NAME, ',') - 1)) , "
    			+ " (SELECT substr(NAME, instr(NAME, ',') + 2)) ,  "
    			+ " AGE , SEX FROM TITANICFIRSTCLASS;";
    	
    	stmt = c.createStatement();
        stmt.executeUpdate(pass);
        
    	pass = "INSERT INTO PASSAGER"
    			+ "(LASTNAME , FIRSTNAME , AGE , SEX) "
    			+" SELECT  "
    			+ " (SELECT substr(NAME,1, instr(NAME, ',') - 1)) , "
    			+ " (SELECT substr(NAME, instr(NAME, ',') + 2)) ,  "
    			+ " AGE , SEX FROM TITANICSECONDCLASS;";
    	
        stmt.executeUpdate(pass);     
        stmt.close();
        
        System.out.println("Insertion of TITANIC to PASSAGER");
    }
    
    private void possede() throws SQLException{
    	System.out.println("START POSSEDE");
    	stmt = c.createStatement();
    	
    	String poss = "INSERT INTO POSSEDE"
    			+ "(IDPASSAGER,TICKET) "
    			+" SELECT  "
    			+" (SELECT (substr(NAME, instr(NAME, ',') + 2)) || (substr(NAME,1, instr(NAME, ',') - 1)) ) , "    			
    			+ " TICKET FROM TITANICFIRSTCLASS;";
    	
        stmt.executeUpdate(poss);
        
    	poss = "INSERT INTO POSSEDE"
    			+ "(IDPASSAGER,TICKET) "
    			+" SELECT  "
    			+" (SELECT (substr(NAME, instr(NAME, ',') + 2)) || (substr(NAME,1, instr(NAME, ',') - 1)) ) , "    			
    			+ " TICKET FROM TITANICSECONDCLASS;";
    	
        stmt.executeUpdate(poss);
        stmt.close();
        
        System.out.println("Insertion of TITANIC to PASSAGER");
    }
    
    private void aPourCabine() throws SQLException{
    	System.out.println("START APOURCABINE");
    	stmt = c.createStatement();
        
    	String poss = "INSERT INTO APOURCABINE"
    			+ "(CABIN,TICKET) "
    			+" SELECT  "
    			+" CABIN , "    			
    			+ " TICKET FROM TITANICFIRSTCLASS WHERE LENGTH(CABIN) = 2 ;";
    	stmt.executeUpdate(poss);
    	
    	poss = "INSERT INTO APOURCABINE"
    			+ "(CABIN,TICKET) "
    			+" SELECT  "
    			+" CABIN , "    			
    			+ " TICKET FROM TITANICFIRSTCLASS WHERE LENGTH(CABIN) = 3;";
    	stmt.executeUpdate(poss);
    	
    	poss = "INSERT INTO APOURCABINE"
    			+ "(CABIN,TICKET) "
    			+" SELECT  "
    			+" CABIN , "    			
    			+ " TICKET FROM TITANICFIRSTCLASS WHERE LENGTH(CABIN) = 4 AND CABIN NOT LIKE 'null' ;";
    	stmt.executeUpdate(poss);
    	
    	poss = "INSERT INTO APOURCABINE"
    			+ "(CABIN,TICKET) "
    			+" SELECT  "
    			+" CABIN , "    			
    			+ " TICKET FROM TITANICFIRSTCLASS WHERE LENGTH(CABIN) > 4;";
    	stmt.executeUpdate(poss);
    	
    	/* instr(CABIN, ' ') )    CABIN NOT LIKE 'null' */
    	
    	poss = "INSERT INTO APOURCABINE"
    			+ "(CABIN,TICKET) "
    			+" SELECT  "
    			+" CABIN , "    			
    			+ " TICKET FROM TITANICSECONDCLASS WHERE LENGTH(CABIN) = 2 ;";
    	stmt.executeUpdate(poss);
    	
    	poss = "INSERT INTO APOURCABINE"
    			+ "(CABIN,TICKET) "
    			+" SELECT  "
    			+" CABIN , "    			
    			+ " TICKET FROM TITANICSECONDCLASS WHERE LENGTH(CABIN) = 3;";
    	stmt.executeUpdate(poss);
    	
    	poss = "INSERT INTO APOURCABINE"
    			+ "(CABIN,TICKET) "
    			+" SELECT  "
    			+" CABIN , "    			
    			+ " TICKET FROM TITANICSECONDCLASS WHERE LENGTH(CABIN) = 4 AND CABIN NOT LIKE 'null' ;";
    	stmt.executeUpdate(poss);
    	
    	poss = "INSERT INTO APOURCABINE"
    			+ "(CABIN,TICKET) "
    			+" SELECT  "
    			+" CABIN , "    			
    			+ " TICKET FROM TITANICFIRSTCLASS WHERE LENGTH(CABIN) > 4;";
    	stmt.executeUpdate(poss);

    	
        stmt.close();
        
        System.out.println("Insertion of TITANIC to APOURCABINE");
    }
    
    private void aPourClasse() throws SQLException{
    	System.out.println("START APOURCLASSE");
    	stmt = c.createStatement();
    	
    	String poss = "INSERT INTO APOURCLASSE"
    			+ "(PCLASS,TICKET) "
    			+" SELECT  "
    			+" PCLASS , "    			
    			+ " TICKET FROM TITANICFIRSTCLASS;";
    	
        stmt.executeUpdate(poss);
        
    	poss = "INSERT INTO APOURCLASSE"
    			+ "(PCLASS,TICKET) "
    			+" SELECT  "
    			+" PCLASS , "    			
    			+ " TICKET FROM TITANICSECONDCLASS;";
    	
        stmt.executeUpdate(poss);
        stmt.close();
        
        System.out.println("Insertion of TITANIC to APOURCLASSE");
    }
    
    private void aEmbarqueA() throws SQLException{
    	System.out.println("START AEMBARQUEA");
    	stmt = c.createStatement();
    	
    	String poss = "INSERT INTO AEMBARQUEA"
    			+ "(EMBARKED,TICKET) "
    			+" SELECT  "
    			+" EMBARKED , "    			
    			+ " TICKET FROM TITANICFIRSTCLASS;";
    	
        stmt.executeUpdate(poss);
        
    	poss = "INSERT INTO AEMBARQUEA"
    			+ "(EMBARKED,TICKET) "
    			+" SELECT  "
    			+" EMBARKED , "    			
    			+ " TICKET FROM TITANICSECONDCLASS;";
    	
        stmt.executeUpdate(poss);
        stmt.close();
        
        System.out.println("Insertion of TITANIC to AEMBARQUEA");
    }
    
	/********************************
	 * 
	 * TEST D'AFFICHAGE DES DONNÉES D'UN TABLEAU DANS LA NOUVELLE DB
	 * 
	 ********************************/
    
    private void testDB(String tab)
    {
        try
        {
            final Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM "+ tab +" ;");

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            int nbRow=0;

            while(rs.next())
            {
                for (int i = 1; i <= numColumns; i++)
                {	
                	//montre une colonne
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
                nbRow++;
            }
            System.out.println();
            System.out.println("NOMBRE DE LIGNES TOTAL DE LA CLASSE "+ tab +" : " + nbRow);
        } catch (SQLException e)
        {
        	System.out.println("ERREUR");
        	System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
	/********************************
	 * 
	 * 				MAIN
	 * 
	 ********************************/
    

	  public static void main( String args[] ) throws SQLException {
	      SQLITEToSQLITE obj = new SQLITEToSQLITE();    
	      
	      try {
	    	  Class.forName("org.sqlite.JDBC");
	    	 //connexion/création à la base de donnée TitanicMapped.db
	         obj.openConnectionToNewDb();
	         
	         //Création des tables de la nouvelle base de données
	         obj.createTables();
	      
	         //Déconnexion de la nouvelle base de données
	         obj.closeConnection();
	     
	         //connexion à la base de donnée Titanic.db : les insertion ne peux se faire qu'à partir de celle ci
	         obj.openConnectionToOldDb();
	         
	         //création d'un liens entre Titanic.db et TitanicMapped.db pour effectué les insertions
	         obj.attachTo();
	         
	         //Insertion dans chaque tableau
	         
	         obj.billet(); //BILLETVOYAGETITANIC
	         
	         obj.passager(); //PASSAGER
	         
	         obj.possede(); //POSSEDE
	         
	         obj.aPourCabine(); //APOURCABINE
	         
	         obj.aPourClasse(); //APOURCLASSE
	         
	         obj.aEmbarqueA(); //AEMBARQUEA
	         
		     System.out.println("");
	         
	         obj.testDB("APOURCABINE");
	         
	         //Fin des insertions et donc déconnexion à la base de données
	         obj.closeConnection();
	         
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	   }
	
}
