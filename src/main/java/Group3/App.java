package Group3;
//defining imports
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
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
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            //Send error message
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        //for loop to try connecting to database 10 times
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                //Displaying Error message
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                //Displaying Error message
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                // Displaying error message when disconnecting error
                System.out.println("Error closing connection to database");
            }
        }
    }

    // Get city Method for getting city details
    public City getCity(int ID)
    {
        try
        {
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
            if (rset.next())
            {
                City cit = new City();
                cit.ID = rset.getInt("ID");
                cit.Name = rset.getString("Name");
                cit.CountryCode = rset.getString("CountryCode");
                cit.District = rset.getString("District");
                cit.Population = rset.getInt("Population");
                return cit;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    //Method for displaying city details
    public void displayCity(City cit)
    {
        if (cit != null)
        {
            System.out.println(
                    cit.ID + " "
                            + cit.Name + " "
                            + cit.CountryCode + "\n"
                            + cit.District + "\n"
                            + cit.Population + "\n");
        }
    }

    //Method for getting County
    public Country getCountry(int Capital)
    {
        try
        {
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
            if (rset.next())
            {
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
            }
            else
                return null;
        }
        catch (Exception e)
        {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    //Method for displaying country details
    public void displayCountry(Country country)
    {
        if (country != null)
        {
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
    public CountryLanguage getLanguage(double Percentage)
    {
        try
        {
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
            if (rset.next())
            {
                CountryLanguage cl = new CountryLanguage();
                cl.CountryCode = rset.getString("CountryCode");
                cl.Language = rset.getString("Language");
                cl.IsOfficial = rset.getString("IsOfficial");
                cl.Percentage = rset.getDouble("Percentage");
                return cl;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            // Displaying error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get country language details");
            return null;
        }
    }

    //Method for displaying countrylanguage details
    public void displayLanguage(CountryLanguage cl)
    {
        if (cl != null)
        {
            System.out.println(
                    cl.CountryCode + " "
                            + cl.Language + " "
                            + cl.IsOfficial + "\n"
                            + cl.Percentage + "\n");
        }
    }

    /**
     * produce a report on all the cities in a region from largest to smallest
     * @return
     */

    public ArrayList<City> report9(){
        System.out.println("Enter the Region you would like to search within");
        //Checks whether the region is within the database
        String input = checkRegion();

        String strSelect =
                "SELECT ID, city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "WHERE Region = " + "'" + input + "'"
                        + "ORDER BY Population DESC ";

        return CityStatement(strSelect);
    }

    /**
     * produce a report on the top n populated countries in a region
     * @return
     */

    public ArrayList<Country> report6(){
        System.out.println("Enter the number of countries you would like to print");
        //Validates the input
        int numInput = validateIntInput();

        System.out.println("Enter the region you would like to search within");
        //Checks whether the region is within the database
        String input = checkRegion();

        String strSelect =
                "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 "
                        + "FROM country "
                        + "WHERE Region = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;

        return CountryStatement(strSelect);
    }

    /**
     * Ensures that the input only contains numbers
     * @return scan.nextInt()
     */
    public int validateIntInput()
    {
        Scanner scan = new Scanner(System.in);

        while(!scan.hasNextInt())
        {
            System.out.println("Please enter a number");
            scan.next();
        }
        return Integer.parseInt(scan.nextLine());
    }

    /**
     * Executes the given query then adds the info to cityList
     * @return cityList
     */
    public ArrayList<City> CityStatement(String Query){
        ArrayList<City> cityList = new ArrayList<>();
        try{
            Statement stmt = con.createStatement();

            ResultSet rset = stmt.executeQuery(Query);

            // Return new CityList if valid.
            while (rset.next())
            {
                City tempCity = new City();
                tempCity.ID = rset.getInt("ID");
                tempCity.Name = rset.getString("Name");
                tempCity.CountryCode = rset.getString("CountryCode");
                tempCity.District = rset.getString("District");
                tempCity.Population = rset.getInt("Population");
                cityList.add(tempCity);
            }
            return cityList;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * Getting Country details
     */
    public ArrayList<Country> CountryStatement(String Query){
        ArrayList<Country> CountryList = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            ResultSet rset = stmt.executeQuery(Query);

            while (rset.next())
            {
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
                CountryList.add(country);
            }
            return CountryList;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Ensures that the given region exists within the database
     * @return input
     */
    public String checkRegion()
    {
        String input = validateStringInput();

        //Checks whether the region is within the database
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Region "
                            + "FROM country "
                            + "WHERE Region = " + "'" + input + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //If no records are returned then you'll be asked to enter another region and that will be validated
            while(!rset.next())
            {
                System.out.println("Region does not exist. Try again");
                input = validateStringInput();

                // Create string for SQL statement
                strSelect =
                        "SELECT Region "
                                + "FROM country "
                                + "WHERE Region = " + "'" + input + "'";
                // Execute SQL statement
                rset = stmt.executeQuery(strSelect);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
        return input;
    }

    /**
     * Ensures that there are only letters in the input
     * @return input
     */
    public String validateStringInput()
    {
        Scanner scan = new Scanner(System.in);
        String input;

        input = scan.nextLine();

        while (true)
        {
            if(input.matches("[a-zA-Z_ ]+"))
                break;
            else {
                System.out.println("Please enter a valid input");
                input = scan.nextLine();
            }
        }
        return input;
    }




}