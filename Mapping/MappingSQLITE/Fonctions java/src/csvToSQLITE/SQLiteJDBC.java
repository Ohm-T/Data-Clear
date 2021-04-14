package csvToSQLITE;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

//import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

//https://codereview.stackexchange.com/questions/222491/sqlite-in-memory-database-that-inserts-data-read-from-a-csv-file
//https://www.tutorialspoint.com/sqlite/sqlite_java.htm
//http://tutorials.jenkov.com/java-nio/path.html

public class SQLiteJDBC {
    private final  String badDataPath = "/home/hayaat/Desktop/Master/M1/TER/BDD/CSV/JAVA/SqlLite/out/";
    private final  String badDataExt = ".csv";
    private final  DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
    //If any of the columns in a row of the CSV file are blank, it should be written into a bad-data-<timestamp>.csv file and not inserted into the database.
    private final  String badDataFilename = badDataPath + df.format(new Date()) + badDataExt;
   
    static String PATH1 = "/home/hayaat/Desktop/Master/M1/TER/BDD/CSV/JAVA/SqlLite/data/firstclass_passengers.csv";	     
    static String PATH2 = "/home/hayaat/Desktop/Master/M1/TER/BDD/CSV/JAVA/SqlLite/data/secondclass_passengers.csv";
    
    Connection c = null;
    Statement stmt = null;
    
    
    /**************************************
     * 
     * CONNEXIONS ET DÉCONNEXION A LA DB
     * 
     **************************************/
    
    private void openConnection() throws SQLException
    {
        if (c == null || c.isClosed())
        {
        	c = DriverManager.getConnection("jdbc:sqlite:Titanic.db");
            //c.setAutoCommit(false);
            System.out.println("Connexion successfull");
        }
    }

    // Closes connection to database
    private void closeConnection() throws SQLException
    {
        c.close();
    }
    
    /***********************************
     * 
     * CREATION DES TABLEAUX DANS LA DB
     * 
     ***********************************/
	
	public void createTables() {
		
		try {
			Statement stmt = c.createStatement();
	         //Class.forName("org.sqlite.JDBC");
	         stmt = c.createStatement();		 	         
	         String delete = "DROP TABLE IF EXISTS TITANICFIRSTCLASS ;";	         
	         stmt.executeUpdate(delete);
	         
	         System.out.println("TITANICFIRSTCLASS deleted");
	         
	         delete = "DROP TABLE IF EXISTS TITANICSECONDCLASS ;";	         
	         stmt.executeUpdate(delete);
	         
	         System.out.println("TITANICSECONDCLASS deleted");
			
	        String class1 = "CREATE TABLE TITANICFIRSTCLASS " +
                     "( " +
                     " PCLASS      INT, " + 
                     " SURVIVED    BOOLEAN," + 
                     " NAME        CHAR(100), " + 
                     " SEX         CHAR(1),"+
                     " AGE         REAL,"+ //or Uknown
                     " SIBSP       INT,"+
                     " PARCH       INT,"+
                     " TICKET      VARCHAR(20),"+
                     " TFARE       FLOAT,"+
                     " CABIN       VARCHAR(20),"+//or Uknown
                     " EMBARKED    CHAR(1),"+
                     " BOAT        VARCHAR(20),"+//or Uknown
                     " BODY        VARCHAR(20),"+//or Uknown
                     " HOME_DEST   VARCHAR(40) );";
	        stmt.executeUpdate(class1);
	        
	        System.out.println("TITANICFIRSTCLASS created");
	        
	        String class2 = "CREATE TABLE TITANICSECONDCLASS " +
                    "( " +
                    " PCLASS      INT, " + 
                    " SURVIVED    BOOLEAN," + 
                    " NAME        CHAR(40), " + 
                    " SEX         CHAR(1),"+
                    " AGE         REAL,"+ //or ?
                    " SIBSP       REAL,"+
                    " PARCH       REAL,"+
                    " TICKET      VARCHAR(20),"+
                    " TFARE       FLOAT,"+
                    " CABIN       VARCHAR(20),"+//or ?
                    " EMBARKED    CHAR(1),"+
                    " BOAT        VARCHAR(20),"+//or ?
                    " BODY        VARCHAR(20),"+//or ?
                    " HOME_DEST   VARCHAR(40) );";
	        stmt.executeUpdate(class2);
	        
	        System.out.println("TITANICSECONDCLASS created");
	        
	        stmt.close();
	        
	        System.out.println("Tables successfully created");
	        
		} 
		catch(SQLException e)
		{
			System.out.println(e.getMessage());

		}
	}
	
    /**************************************************
     * 
     * INSERTIONS DES DONNÉES DU CSV DANS LES TABLEAUX
     * 
     **************************************************/
	
	public void insertFromCSV(String PATH,String classname) {

		try {
			Reader reader = Files.newBufferedReader(Paths.get(PATH));
			
			System.out.println("path ok");	
			
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			
			System.out.println("reader ok");

			Writer writer = Files.newBufferedWriter(Paths.get(badDataFilename));
			
			System.out.println("writer ok");

			CSVWriter csvWriter = new CSVWriter(writer,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
			
			System.out.println("csvWriter ok");
        
			String[] header= {"PCLASS", "SURVIVED", "NAME", "SEX","AGE",
					"SIBSP", "PARCH", "TICKET", "TFARE", "CABIN",
        				"EMBARKED", "BOAT", "BODY", "HOME_DEST"};
			
			csvWriter.writeNext(header);
			
            PreparedStatement pstatement = c.prepareStatement(
            		"INSERT INTO "+ classname +"(PCLASS, SURVIVED, NAME, SEX,AGE,"
            				+ "SIBSP,PARCH, TICKET, TFARE, CABIN,"
            				+ "EMBARKED,BOAT,BODY, HOME_DEST) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			
			String[] nextRecord;
			int recordsFailed = 0;
			int recordsSuccessful = 0;
			
			//AJOUTE LIGNE PAR LIGNE LES DONNÉES
			while((nextRecord = csvReader.readNext()) != null) {
				
				//SURVIVED
				if(nextRecord[1].equals("No")) {
					nextRecord[1]="0";
				}
				if(nextRecord[1].equals("Yes")) {
					nextRecord[1]="1";
				}
				
				//SEX
				if(nextRecord[3].equals("F")) {
					nextRecord[3]="female";
				}
				if(nextRecord[3].equals("M")) {
					nextRecord[3]="male";
				}
				
				//AGE
				if(nextRecord[4].equals("?")|| nextRecord[4].equals("Unknown")) {
					nextRecord[4]="null";
				}
				
				//CABIN
				if(nextRecord[9].equals("?")|| nextRecord[9].equals("Unknown")) {
					nextRecord[9]="null";
				}
				
				//BOAT
				if(nextRecord[11].equals("?")|| nextRecord[11].equals("Unknown")) {
					nextRecord[11]="null";
				}
				
				//BODY
				if(nextRecord[12].equals("?")|| nextRecord[12].equals("Unknown")) {
					nextRecord[12]="null";
				}
				
				//HOME_DEST
				if(nextRecord[13].equals("?")|| nextRecord[13].equals("Unknown")) {
					nextRecord[13]="null";
				}
                if (!Arrays.asList(nextRecord).contains(""))
                {
					recordsSuccessful++;
                	for(int i=0;i<header.length;i++) {
                		pstatement.setString(i+1, nextRecord[i]);
                	}
				pstatement.executeUpdate();
                }
                
                else {
					recordsFailed++;
                    csvWriter.writeNext(nextRecord);                	
                }                
			}
			 csvWriter.close();
			 pstatement.close();
			 
			 System.out.println("ClassName : " + classname);
			 System.out.println("recordsSuccessful : "+ recordsSuccessful);
			 System.out.println("recordsFailed : " + recordsFailed);
		}
		
		catch (SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
    
    /***************************************
     * 
     * TEST DE LA DB
     * 
     ***************************************/
	
    
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

		
	/*Following Java programs shows how to connect to an existing database. 
	 * If the database does not exist, then it will be created and finally a database object will be returned.
	 */
	  public static void main( String args[] ) throws SQLException {
	      SQLiteJDBC obj = new SQLiteJDBC();    
	      
	      try {
	    	  Class.forName("org.sqlite.JDBC");
	    	 //connexion/création à la base de donnée test.db
	         obj.openConnection();
	         
	         //Création des tables
	         obj.createTables();
	         
	         //Remplissage firstclass_passengers.csv
	         obj.insertFromCSV(PATH1,"TITANICFIRSTCLASS");	
	         
	         //Remplissage secondclass_passengers.csv
	         obj.insertFromCSV(PATH2,"TITANICSECONDCLASS");
	         
	         obj.testDB("TITANICFIRSTCLASS");
	         obj.closeConnection();
	     
             //When the above program is compiled and executed, 
	         //it will create TITANICFIRSTCLASS/TITANICSECONDCLASS table in the db Titanic.db      

	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	   }
	}
