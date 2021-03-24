package Group3;
//defining imports
import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Get city
        City cit = a.getCity(2);
        // Display results
        a.displayCity(cit);

        // Get country
        Country country = a.getCountry(1);
        //Display Details
        a.displayCountry(country);

        //Get country Language
        CountryLanguage cl = a.getLanguage(0.1);
        //Display Details
        a.displayLanguage(cl);
        
        //Displaying report 24
	ArrayList<country> report24 = a.getReport24();
        a.printCountries(report24);
        
        //Displaying report 25
        ArrayList<Country> report25 = a.getReport25("Africa");
        a.printCountries(report25);

        //Displaying report 26
        String str = "Southern Europe";
        char[] ch = str.toCharArray();
        Country report26 = a.getReport26(ch);
        a.displayCountry(report26);

        //Displaying report 28
        Country report28 = a.getReport28("Africa", 5);
        a.displayCountry(report28);
        
        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //Send error message
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        //for loop to try connecting to database 10 times
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                //Displaying Error message
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                //Displaying Error message
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                // Displaying error message when disconnecting error
                System.out.println("Error closing connection to database");
            }
        }
    }

    // Get city Method for getting city details
    public City getCity(int ID) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE ID = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            if (rset.next()) {
                City cit = new City();
                cit.ID = rset.getInt("ID");
                cit.Name = rset.getString("Name");
                cit.CountryCode = rset.getString("CountryCode");
                cit.District = rset.getString("District");
                cit.Population = rset.getInt("Population");
                return cit;
            } else
                return null;
        } catch (Exception e) {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    //Method for displaying city details
    public void displayCity(City cit) {
        if (cit != null) {
            System.out.println(
                    cit.ID + " "
                            + cit.Name + " "
                            + cit.CountryCode + "\n"
                            + cit.District + "\n"
                            + cit.Population + "\n");
        }
    }

    //Method for getting County
    public Country getCountry(int Capital) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 "
                            + "FROM country "
                            + "WHERE Capital = " + Capital;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.SurfaceArea = rset.getFloat("SurfaceArea");
                country.IndepYear = rset.getInt("IndepYear");
                country.Population = rset.getInt("Population");
                country.LifeExpectancy = rset.getFloat("LifeExpectancy");
                country.GNP = rset.getFloat("GNP");
                country.OldGNP = rset.getFloat("GNPOld");
                country.LocalName = rset.getString("LocalName");
                country.GovernmentForm = rset.getString("GovernmentForm");
                country.HeadOfState = rset.getString("HeadOfState");
                country.Capital = rset.getInt("Capital");
                country.Code2 = rset.getString("Code2");
                return country;
            } else
                return null;
        } catch (Exception e) {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    //Method for displaying country details
    public void displayCountry(Country country) {
        if (country != null) {
            System.out.println(
                    country.Code + " "
                            + country.Name + " "
                            + country.Continent + "\n"
                            + country.Region + "\n"
                            + country.SurfaceArea + "\n"
                            + country.IndepYear + "\n"
                            + country.Population + "\n"
                            + country.LifeExpectancy + "\n"
                            + country.GNP + "\n"
                            + country.OldGNP + "\n"
                            + country.LocalName + "\n"
                            + country.GovernmentForm + "\n"
                            + country.HeadOfState + "\n"
                            + country.Capital + "\n"
                            + country.Code2 + "\n");
        }
    }

    // Get city Method for getting city details
    public CountryLanguage getLanguage(double Percentage) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT CountryCode, Language, IsOfficial, Percentage "
                            + "FROM countrylanguage "
                            + "WHERE Percentage = " + Percentage;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            if (rset.next()) {
                CountryLanguage cl = new CountryLanguage();
                cl.CountryCode = rset.getString("CountryCode");
                cl.Language = rset.getString("Language");
                cl.IsOfficial = rset.getString("IsOfficial");
                cl.Percentage = rset.getDouble("Percentage");
                return cl;
            } else
                return null;
        } catch (Exception e) {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get country language details");
            return null;
        }
    }

    //Method for displaying countrylanguage details
    public void displayLanguage(CountryLanguage cl) {
        if (cl != null) {
            System.out.println(
                    cl.CountryCode + " "
                            + cl.Language + " "
                            + cl.IsOfficial + "\n"
                            + cl.Percentage + "\n");
        }
    }

    //Method for report 25
    public Country getReport26(char[] region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population, Region"
                            + "FROM country "
                            + "WHERE Region = " + region;
            //+ "ORDER BY Population ASC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Population = rset.getInt("Region");
                return country;
            } else
                return null;
        } catch (Exception e) {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get report26 details");
            return null;
        }
    }


    public ArrayList<Country> getReport25(String continent) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 "
                            + "FROM country "
                            + "WHERE Continent = " + "'" + continent + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next()) {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.SurfaceArea = rset.getFloat("SurfaceArea");
                country.IndepYear = rset.getInt("IndepYear");
                country.Population = rset.getInt("Population");
                country.LifeExpectancy = rset.getFloat("LifeExpectancy");
                country.GNP = rset.getFloat("GNP");
                country.OldGNP = rset.getFloat("GNPOld");
                country.LocalName = rset.getString("LocalName");
                country.GovernmentForm = rset.getString("GovernmentForm");
                country.HeadOfState = rset.getString("HeadOfState");
                country.Capital = rset.getInt("Capital");
                country.Code2 = rset.getString("Code2");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get report25 details");
            return null;
        }
    }



    /*
    public ArrayList<Country> getReport25(char[] continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population, Continent"
                            + "FROM country ";
                            //+ "WHERE Continent = " + continent
                            //+ "ORDER BY Population ASC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<Country>();
            // Return new City if valid.
            // Check one is returned
            while (rset.next())
            {
                Country country = new Country();
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Population = rset.getInt("Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get report25 details");
            return null;
        }
    }
     */

    public void printCountries(ArrayList<Country> countries) {
        if (countries == null) {
            System.out.println("No countries");
            return;
        }
        System.out.println(String.format("%s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s", "Code", "Name", "Continent", "Region", "SurfaceArea", "IndepYear", "Population", "LifeExpectancy", "GNP", "GNPOld", "LocalName", "GovernmentForm", "HeadOfState", "Capital", "Code2"));
        for (Country country : countries) {
            if (country == null)
                continue;
            String countryString = String.format("%s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s", country.Code, country.Name, country.Continent, country.Region, country.SurfaceArea, country.IndepYear, country.Population, country.LifeExpectancy, country.GNP, country.OldGNP, country.LocalName, country.GovernmentForm, country.HeadOfState, country.Capital, country.Code2);
            System.out.println(countryString);
        }
    }


    public Country getReport28(String continent, int num) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population, Continent"
                            + "FROM country "
                            + "WHERE Continent = " + continent
                            + "ORDER BY Population ASC"
                            + "LIMIT " + num;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Population = rset.getInt("Population");
                return country;
            } else
                return null;
        } catch (Exception e) {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get report28 details");
            return null;
        }
    }


    public ArrayList<Country> getReport29(String region, int n) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital"
                            + "FROM country "
                            + "WHERE Region = " + region
                            + "ORDER BY Population DESC"
                            + "LIMIT " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Create array list to store countries
            ArrayList<Country> countries = new ArrayList<>();
            // Return new Country if valid.
            // Check one is returned
            while (rset.next()) {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get report29 details");
            return null;
        }
    }


    public ArrayList<City> getReport32(String region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population"
                            + "FROM city "
                            + "WHERE Region = " + region
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Create array list to store countries
            ArrayList<City> cities = new ArrayList<>();
            // Return new city if valid.
            // Check one is returned
            while (rset.next()) {
                City city = new City();
                city.Name = rset.getString("Name");
                city.Population = rset.getInt("Population");
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get report32 details");
            return null;
        }
    }

    public ArrayList<Country> getReport24(String country)
    {
	    try
	    {
		    //create an SQL statement
		    Staetment stmt = con.createStatement();
		    // Create string for SQL statement
		    String strSelect =
			    //Selecting the name and ordering by the
			    //population from largest to smallest
			    "SELECT Name, Population"
			    	+ "FROM country"
			    	+ "ORDER BY Population DESC";
	    // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
	    //Creating an array list for the countries
	    ArrayList<Country> countries = new ArrayList<>();
		while (rset.next()) {
                	Country country = new Country();
                	country.Name = rset.getString("Name");
               		 country.Population = rset.getInt("Population");
                	countries.add(country);
           	 }
	   	 }
	   	 return countries;
	    catch (Exception e)
	    {
	        // Displaying error message
	        System.out.println(e.getMessage());
	        System.out.println("Failed to get report24 details");
	        return null;
	    }
    }
    
}
