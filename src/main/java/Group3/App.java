package Group3;
//defining imports
import java.sql.*;
import java.util.*;
import java.util.Scanner;

public class App
{
    public static void main(String[] args){
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect("localhost:33060");

        System.out.println("Enter the report you want to run");
        a.reportSelect();

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
    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
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
        if (cit == null)
        {
            System.out.println("No records");
            return;
        }

        System.out.println(
                cit.ID + " "
                        + cit.Name + " "
                        + cit.CountryCode + "\n"
                        + cit.District + "\n"
                        + cit.Population + "\n");
    }

    //Method for getting County
    public Country getCountry(String Code)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 "
                            + "FROM country "
                            + "WHERE Code = " + "'" + Code + "'";
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
        if (country == null)
        {
            System.out.println("No records");
            return;
        }

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

    // Get city Method for getting city details
    public CountryLanguage getLanguage(String CountryCode)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT CountryCode, Language, IsOfficial, Percentage "
                            + "FROM countrylanguage "
                            + "WHERE CountryCode = " + "'" + CountryCode + "'";
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
        if (cl == null)
        {
            System.out.println("No records");
            return;
        }

        System.out.println(
                cl.CountryCode + " "
                        + cl.Language + " "
                        + cl.IsOfficial + "\n"
                        + cl.Percentage + "\n");
    }

    /**
     * All the countries in the world organised by largest population to smallest.
     * @return CountryStatement
     */
    public ArrayList<Country> report1(){
        String strSelect =
                "SELECT Code, Name, Continent, Region, Population, Capital "
                        + "FROM country "
                        + "ORDER BY Population DESC";
        return CountryStatement(strSelect);
    }

    /**
     * All the countries in a continent organised by largest population to smallest.
     * @return CountryStatement
     */
    public ArrayList<Country> report2()
    {
        System.out.println("Enter the Continent you would like to search within");
        //Checks whether the region is within the database
        String input = checkContinent();

        String strSelect =
                "SELECT Code, Name, Continent, Region, Population, Capital "
                        + "FROM country "
                        + "WHERE Continent = " + "'" + input + "'"
                        + "ORDER BY Population DESC";
        return CountryStatement(strSelect);
    }

    /**
     * All the countries in a region organised by largest population to smallest.
     * @return CountryStatement
     */
    public ArrayList<Country> report3()
    {
        System.out.println("Enter the Region you would like to search within");
        //Checks whether the region is within the database
        String input = checkRegion();

        String strSelect =
                "SELECT Code, Name, Continent, Region, Population,  Capital "
                        + "FROM country "
                        + "WHERE Region = " + "'" + input + "'"
                        + "ORDER BY Population DESC";
        return CountryStatement(strSelect);
    }

    /**
     * The top N populated countries in the world where N is provided by the user.
     * @return CountryStatement
     */
    public ArrayList<Country> report4()
    {
        System.out.println("Enter the number of countries you would like to print");
        //Validates the input
        int numInput = validateIntInput();

        String strSelect =
                "SELECT Code, Name, Continent, Region, Population, Capital "
                        + "FROM country "
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;

        return CountryStatement(strSelect);
    }

    /**
     * The top N populated countries in a continent where N is provided by the user.
     * @return CountryStatement
     */
    public ArrayList<Country> report5()
    {
        System.out.println("Enter the number of countries you would like to print");
        //Validates the input
        int numInput = validateIntInput();

        System.out.println("Enter the continent you would like to search within");
        //Checks whether the region is within the database
        String input = checkContinent();

        String strSelect =
                "SELECT Code, Name, Continent, Region, Population, Capital "
                        + "FROM country "
                        + "WHERE Continent = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;

        return CountryStatement(strSelect);
    }

    /**
     * The top N populated countries in a region where N is provided by the user.
     * @return CountryStatement
     */
    public ArrayList<Country> report6()
    {
        System.out.println("Enter the number of countries you would like to print");
        //Validates the input
        int numInput = validateIntInput();

        System.out.println("Enter the region you would like to search within");
        //Checks whether the region is within the database
        String input = checkRegion();

        String strSelect =
                "SELECT Code, Name, Continent, Region, Population, Capital "
                        + "FROM country "
                        + "WHERE Region = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;

        return CountryStatement(strSelect);
    }

    /**
     * All the cities in the world organised by largest population to smallest.
     */
    public ArrayList<City> report7()
    {
        // Create string for SQL statement
        String strSelect =
                "SELECT Name, CountryCode, District, Population "
                        + "FROM city "
                        + "ORDER BY Population DESC";
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * All the cities in a continent organised by largest population to smallest.
     */
    public ArrayList<City> report8()
    {

        System.out.println("Enter the continent you would like to search within");
        String input = checkContinent();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.CountryCode = country.Code "
                        + "WHERE Continent = " + "'" + input + "'"
                        + "ORDER BY Population DESC ";
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * All the cities in a region organised by largest population to smallest.
     */
    public ArrayList<City> report9()
    {

        System.out.println("Enter the region you would like to search within");
        String input = checkRegion();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.CountryCode = country.Code "
                        + "WHERE Region = " + "'" + input + "'"
                        + "ORDER BY Population DESC ";
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * All the cities in a country organised by largest population to smallest.
     */
    public ArrayList<City> report10(){

        System.out.println("Enter the country you would like to search within");
        String input = checkCountry();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name, CountryCode, city.District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.CountryCode = country.Code "
                        + "WHERE country.Name = " + "'" + input + "'"
                        + "ORDER BY Population DESC";
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * All the cities in a district organised by largest population to smallest.
     */
    public ArrayList<City> report11() {

        System.out.println("Enter the district you would like to search within");
        String input = checkDistrict();

        // Create string for SQL statement
        String strSelect =
                "SELECT Name, CountryCode, District, Population "
                        + "FROM city "
                        + "WHERE District = " + "'" + input + "'"
                        + "ORDER BY Population DESC";
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * The top N populated cities in the world where N is provided by the user.
     */
    public ArrayList<City> report12(){

        System.out.println("Enter the number of cities you would like to print");
        int input = validateIntInput();

        // Create string for SQL statement
        String strSelect =
                "SELECT Name, CountryCode, District, Population "
                        + "FROM city "
                        + "ORDER BY Population DESC "
                        + "LIMIT " + input;
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * The top N populated cities in a continent where N is provided by the user.
     */

    public ArrayList<City> report13(){

        System.out.println("Enter the number of cities you would like to print");
        int numInput = validateIntInput();

        System.out.println("Enter the continent you would like to search within");
        String input = checkContinent();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.CountryCode = country.Code "
                        + "WHERE Continent = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * The top N populated cities in a region where N is provided by the user.
     */
    public ArrayList<City> report14()
    {

        System.out.println("Enter the number of cities you would like to print");
        int numInput = validateIntInput();

        System.out.println("Enter the region you would like to search within");
        String input = checkRegion();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.CountryCode = country.Code "
                        + "WHERE Region = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * The top N populated cities in a country where N is provided by the user.
     */
    public ArrayList<City> report15()
    {

        System.out.println("Enter the number of cities you would like to print");
        //Validates the input
        int numInput = validateIntInput();

        System.out.println("Enter the country you would like to search within");
        //Checks whether the country is within the database
        String input = checkCountry();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.CountryCode = country.Code "
                        + "WHERE country.Name = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * The top N populated cities in a district where N is provided by the user.
     * @return CityStatement
     */
    public ArrayList<City> report16()
    {
        System.out.println("Enter the number of cities you would like to print");
        //Validates the input
        int numInput = validateIntInput();

        System.out.println("Enter the District you would like to search within");
        //Checks whether the region is within the database
        String input = checkDistrict();

        // Create string for SQL statement
        String strSelect =
                "SELECT Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "WHERE District = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;

        return CityStatement(strSelect);
    }

    /**
     * All the capital cities in the world organised by largest population to smallest.
     */
    public ArrayList<City> report17()
    {

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "ORDER BY Population DESC";
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * All the capital cities in a continent organised by largest population to smallest.
     */
    public ArrayList<City> report18()
    {

        System.out.println("Enter the continent you would like to search within");
        //Checks whether the region is within the database
        String input = checkContinent();

        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "WHERE Continent = " + "'" + input + "'"
                        + "ORDER BY Population DESC ";

        return CityStatement(strSelect);
    }

    /**
     * All the capital cities in a region organised by largest to smallest.
     * @return CityStatement
     */
    public ArrayList<City> report19()
    {
        System.out.println("Enter the Region you would like to search within");
        //Checks whether the region is within the database
        String input = checkRegion();

        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "WHERE Region = " + "'" + input + "'"
                        + "ORDER BY Population DESC ";

        return CityStatement(strSelect);
    }

    /**
     * The top N populated capital cities in the world where N is provided by the user.
     */
    public ArrayList<City> report20()
    {

        System.out.println("Enter the number of capital cities you would like to print");
        int numInput = validateIntInput();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;
        // Execute SQL statement
        return CityStatement(strSelect);

    }

    /**
     * The top N populated capital cities in a continent where N is provided by the user.
     */
    public ArrayList<City> report21()
    {
        System.out.println("Enter the number of capital cities you would like to print");
        //Validates the input
        int numInput = validateIntInput();

        System.out.println("Enter the continent you would like to search within");
        //Checks whether the region is within the database
        String input = checkContinent();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "WHERE Continent = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * The top N populated capital cities in a region where N is provided by the user.
     */
    public ArrayList<City> report22()
    {

        System.out.println("Enter the number of capital cities you would like to print");
        //Validates the input
        int numInput = validateIntInput();

        System.out.println("Enter the region you would like to search within");
        //Checks whether the region is within the database
        String input = checkRegion();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "WHERE Region = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * Report on a number of people living in cities and not living in cities in continents
     */
    public String report23()
    {
        System.out.println("Enter the continent you would like to search within");
        //Checks whether the region is within the database
        String input = checkContinent();

        String str1 =
                "SELECT Sum(city.Population) "
                        + "FROM city "
                        + "JOIN country ON city.CountryCode = country.Code "
                        + "WHERE Continent = " + "'" + input + "'";


        String str2 =
                "SELECT Sum(Population) "
                        + "FROM country "
                        + "WHERE Continent = " + "'" + input + "'";

        return DifferenceStatement(str1, str2);
    }

    /**
     * Report on a number of people living in cities and not living in cities in Regions
     */
    public String report24()
    {
        System.out.println("Enter the Region you would like to search within");
        //Checks whether the region is within the database
        String input = checkRegion();

        String str1 =
                "SELECT Sum(city.Population) "
                        + "FROM city "
                        + "JOIN country ON city.CountryCode = country.Code "
                        + "WHERE Region = " + "'" + input + "'";


        String str2 =
                "SELECT Sum(Population) "
                        + "FROM country "
                        + "WHERE Region = " + "'" + input + "'";

        return DifferenceStatement(str1, str2);
    }

    /**
     * Report on a number of people living in cities and not living in cities in Countries
     */
    public String report25()
    {
        System.out.println("Enter the country you would like to search within");
        //Checks whether the country is within the database
        String input = checkCountry();

        String str1 =
                "SELECT Sum(city.Population) "
                        + "FROM city "
                        + "JOIN country ON city.CountryCode = country.Code "
                        + "WHERE country.Name = " + "'" + input + "'";


        String str2 =
                "SELECT Population "
                        + "FROM country "
                        + "WHERE Name = " + "'" + input + "'";

        return DifferenceStatement(str1, str2);
    }

    /**
     * Produce population of world
     */

    public String report26(){
        String strSelect =
                "SELECT SUM(Population) "
                        + "FROM country ";

        return PopulationStatement(strSelect);
    }

    /**
     * Produce Population of continent
     */
    public String report27(){
        System.out.println("Enter the continent you would like to search within");
        //Checks whether the region is within the database
        String input = checkContinent();

        String strSelect =
                "SELECT SUM(Population) "
                        + "FROM country "
                        + "WHERE Continent = " + "'" + input + "'";

        return PopulationStatement(strSelect);
    }

    /**
     * Produce Population of regions
     */
    public String report28(){
        System.out.println("Enter the Region you would like to search within");
        //Checks whether the region is within the database
        String input = checkRegion();

        String strSelect =
                "SELECT SUM(Population) "
                        + "FROM country "
                        + "WHERE Region = " + "'" + input + "'";

        return PopulationStatement(strSelect);
    }

    /**
     * Produce Population of Countries
     */
    public String report29(){
        System.out.println("Enter the country you would like to search within");
        //Checks whether the country is within the database
        String input = checkCountry();

        String strSelect =
                "SELECT SUM(Population) "
                        + "FROM country "
                        + "WHERE Name = " + "'" + input + "'";

        return PopulationStatement(strSelect);
    }

    /**
     * Produce Population on District
     */
    public String report30(){
        System.out.println("Enter the District you would like to search within");
        //Checks whether the region is within the database
        String input = checkDistrict();

        String strSelect =
                "SELECT SUM(Population) "
                        + "FROM city "
                        + "WHERE District = " + "'" + input + "'";

        return PopulationStatement(strSelect);
    }

    /**
     * Produce Population on City
     */
    public String report31(){
        System.out.println("Enter the City you would like to search within");
        //Checks whether the region is within the database
        String input = checkCity();

        String strSelect =
                "SELECT SUM(Population) "
                        + "FROM city "
                        + "WHERE Name = " + "'" + input + "'";

        return PopulationStatement(strSelect);
    }

    /**
     * Produce a report on most spoken languages
     */
    public String report32(int i)
    {

        String[] input = {"Chinese", "Hindi", "Spanish", "English", "Arabic"};

        String str =
                "SELECT SUM(Population) "
                        + "FROM country ";

        String strSelect =
                "SELECT SUM(Population) "
                        + "FROM country "
                        + "JOIN countrylanguage ON country.Code = countrylanguage.CountryCode "
                        + "WHERE Language = " + "'" + input[i] + "'";

        return LanguageStatement(input[i], strSelect, str);
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
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
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
     * Getting Population details
     */
    public String PopulationStatement(String Query){
        String Population = "";
        String Test = "test";
        int NextPop = 0;
        try {
            Statement stmt = con.createStatement();

            ResultSet rset = stmt.executeQuery(Query);

            while (rset.next())
            {
                Population = rset.getString(1);
            }
            return Population;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return Test;
        }
    }

    public String LanguageStatement(String input, String Query, String Query2){
        float number = 0;
        int outputNum;
        String Statement;
        float Calculation;
        float TotalPopulation = 0;
        try {
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();

            ResultSet rset = stmt.executeQuery(Query);
            ResultSet rset2 = stmt2.executeQuery(Query2);

            while (rset.next())
            {
                number = rset.getFloat(1);
            }
            while (rset2.next())
            {
                TotalPopulation = rset2.getFloat(1);
            }
            Calculation = number / TotalPopulation;
            Calculation = Calculation * 100;
            outputNum = (int)number;

            Statement = ("The number of people that speak " + input + " is " + outputNum + " And that is " + Calculation + " percentage of the world population");
            return Statement;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Langauge details");
            return null;
        }
    }


    /**
     * Getting the Details of people living in city and not in city
     */
    public String DifferenceStatement(String Query1, String Query2){
        long CityIn = 0;
        long Total = 0;
        long CityOut;
        String Difference;
        //DifferenceArray = new String[195];


        try {
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();

            ResultSet rset = stmt.executeQuery(Query1);
            ResultSet rset2 = stmt2.executeQuery(Query2);

            while (rset.next())
            {
                CityIn = rset.getLong(1);
            }

            while(rset2.next())
            {
                Total = rset2.getLong(1);
            }
            CityOut = Total - CityIn;
            Difference = ("Population in Cities " + CityIn + " " + "Population not in Cities " + CityOut);

            //Difference = (Total + " " + CityIn);
            return Difference;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population Difference details");
            return null;
        }
    }

    /**
     * Selects which report to run
     */
    public void reportSelect()
    {
        int ReportNum;
        int i;
        ArrayList<City> cities;
        ArrayList<Country> countries;
        String Language;
        String Difference;
        String[] DifferencePop;
        DifferencePop = new String[195];

        String Population;

        while (true)
        {
            ReportNum = validateIntInput();

            if(ReportNum >0 && ReportNum <63) {
                break;
            }else{
                System.out.println("Please enter a valid Report Number");
            }
        }

        switch (ReportNum)
        {
            case 1:
                countries = report1();
                displayCountries(countries);
                break;
            case 2:
                countries = report2();
                displayCountries(countries);
                break;
            case 3:
                countries = report3();
                displayCountries(countries);
                break;
            case 4:
                countries = report4();
                displayCountries(countries);
                break;
            case 5:
                countries = report5();
                displayCountries(countries);
                break;
            case 6:
                countries = report6();
                displayCountries(countries);
                break;
            case 7:
                cities = report7();
                displayCities(cities);
                break;
            case 8:
                cities = report8();
                displayCities(cities);
                break;
            case 9:
                cities = report9();
                displayCities(cities);
                break;
            case 10:
                cities = report10();
                displayCities(cities);
                break;
            case 11:
                cities = report11();
                displayCities(cities);
                break;
            case 12:
                cities = report12();
                displayCities(cities);
                break;
            case 13:
                cities = report13();
                displayCities(cities);
                break;
            case 14:
                cities = report14();
                displayCities(cities);
                break;
            case 15:
                cities = report15();
                displayCities(cities);
                break;
            case 16:
                cities = report16();
                displayCities(cities);
                break;
            case 17:
                cities = report17();
                displayCities(cities);
                break;
            case 18:
                cities = report18();
                displayCities(cities);
                break;
            case 19:
                cities = report19();
                displayCities(cities);
                break;
            case 20:
                cities = report20();
                displayCities(cities);
                break;
            case 21:
                cities = report21();
                displayCities(cities);
                break;
            case 22:
                cities = report22();
                displayCities(cities);
                break;
            case 23:
                Difference = report23();
                displaypopDif(Difference);
                break;
            case 24:
                Difference = report24();
                displaypopDif(Difference);
                break;
            case 25:
                Difference = report25();
                displaypopDif(Difference);
                break;
            case 26:
                Population = report26();
                displayPopulation(Population);
                break;
            case 27:
                Population = report27();
                displayPopulation(Population);
                break;
            case 28:
                Population = report28();
                displayPopulation(Population);
                break;
            case 29:
                Population = report29();
                displayPopulation(Population);
                break;
            case 30:
                Population = report30();
                displayPopulation(Population);
                break;
            case 31:
                Population = report31();
                displayPopulation(Population);
                break;
            case 32:
                for(i = 0; i < 5; i++) {
                    Language = report32(i);
                    displayLanguageReport(Language);
                }
                break;
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
     * Ensures that the given country exists within the database
     * @return input
     */
    public String checkCountry()
    {
        String input = validateStringInput();

        //Checks whether the country code is within the database
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name "
                            + "FROM country "
                            + "WHERE Name = " + "'" + input + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //If no records are returned then you'll be asked to enter another country code and that will be validated
            while(!rset.next())
            {
                System.out.println("Country does not exist. Try again");
                input = validateStringInput();

                // Create string for SQL statement
                strSelect =
                        "SELECT Name "
                                + "FROM country "
                                + "WHERE Name = " + "'" + input + "'";
                // Execute SQL statement
                rset = stmt.executeQuery(strSelect);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
        return input;
    }

    /**
     * Ensures that the given district exists within the database
     * @return input
     */
    public String checkDistrict()
    {
        String input = validateStringInput();

        //Checks whether the district is within the database
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT District "
                            + "FROM city "
                            + "WHERE District = " + "'" + input + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //If no records are returned then you'll be asked to enter another district and that will be validated
            while(!rset.next())
            {
                System.out.println("District does not exist. Try again");
                input = validateStringInput();

                // Create string for SQL statement
                strSelect =
                        "SELECT District "
                                + "FROM city "
                                + "WHERE District = " + "'" + input + "'";
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
     * Ensures that the given continent exists within the database
     * @return input
     */
    public String checkContinent()
    {
        String input = validateStringInput();

        //Checks whether the continent is within the database
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Continent "
                            + "FROM country "
                            + "WHERE Continent = " + "'" + input + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //If no records are returned then you'll be asked to enter another continent and that will be validated
            while(!rset.next())
            {
                System.out.println("Continent does not exist. Try again");
                input = validateStringInput();

                // Create string for SQL statement
                strSelect =
                        "SELECT Continent "
                                + "FROM country "
                                + "WHERE Continent = " + "'" + input + "'";
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
     * Ensures that the given city exists within the database
     * @return input
     */
    public String checkCity()
    {
        String input = validateStringInput();

        //Checks whether the continent is within the database
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name "
                            + "FROM city "
                            + "WHERE Name = " + "'" + input + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //If no records are returned then you'll be asked to enter another continent and that will be validated
            while(!rset.next())
            {
                System.out.println("City does not exist. Try again");
                input = validateStringInput();

                // Create string for SQL statement
                strSelect =
                        "SELECT Name "
                                + "FROM city "
                                + "WHERE Name = " + "'" + input + "'";
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
    /*
    public String checkLanguage()
    {
        String input = validateStringInput();

        //Checks whether the Language is within the database
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Language "
                            + "FROM countrylanguage "
                            + "WHERE Language = " + "'" + input + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //If no records are returned then you'll be asked to enter another Language and that will be validated
            while(!rset.next())
            {
                System.out.println("language does not exist. Try again");
                input = validateStringInput();

                // Create string for SQL statement
                strSelect =
                        "SELECT Language "
                                + "FROM countrylanguage "
                                + "WHERE Language = " + "'" + input + "'";
                // Execute SQL statement
                rset = stmt.executeQuery(strSelect);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Language details");
            return null;
        }

        return input;
    }
     */

    /**
     * Displays the output of the query
     * @param cities
     */
    public void displayCities(ArrayList<City> cities)
    {
        if(cities == null)
        {
            System.out.println("No cities");
            return;
        }

        // Print header
        System.out.printf("%-10s %-15s %-20s %-25s%n","Name", "Country Code", "District", "Population");
        //Loop through every city in the list
        for (City cit: cities)
        {
            if(cit == null)
                continue;
            String cit_string = String.format("%-10s %-15s %-20s %-25s",
                    cit.Name, cit.CountryCode, cit.District, cit.Population);
            System.out.println(cit_string);
        }
    }

    public void displayCountries(ArrayList<Country> countries)
    {
        if(countries == null)
        {
            System.out.println("No countries");
            return;
        }

        //System.out.println(String.format("%-10s %-55 %-15 %-30 %-15 %-10 %-15 %-10 %-15 %-15 %-45"))
        System.out.printf("%-10s %-15s %-20s %-25s %-30s %-35s%n", "Code", "Name", "Continent", "Region", "Population", "Capital");

        for (Country cou: countries){
            if(cou == null){
                continue;
            }
            String cou_String = String.format("%-10s %-15s %-20s %-25s %-30s %-35s",
                    cou.Code, cou.Name, cou.Continent, cou.Region, cou.Population, cou.Capital);
            System.out.println(cou_String);
        }
    }

    public void displayPopulation(String Population){
        if(Population == null){
            System.out.println("Error no data");
            return;
        }
        System.out.println("Population: " + Population);
    }

    public void displayLanguageReport(String Language){
        if(Language == null){
            System.out.println("Error No data");
            return;
        }
        System.out.println(Language);
    }


    public void displaypopDif(String PopDif){
        if(PopDif == null){
            System.out.println("Error No data");
            return;
        }
        System.out.println("Population: " + PopDif);
    }
}