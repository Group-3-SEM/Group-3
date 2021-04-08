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

        while (true)
        {
            int ReportNum = a.validateIntInput();

            if(ReportNum >0 && ReportNum <40) {
                a.reportSelect(ReportNum);
                break;
            }else{
                System.out.println("Please enter a valid Report Number");
            }
        }


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
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
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
     * All the cities in the world organised by largest population to smallest.
     */
    public ArrayList<City> report7(){

        // Create string for SQL statement
        String strSelect =
                "SELECT ID, Name, CountryCode, District, Population "
                        + "FROM city "
                        + "ORDER BY Population DESC";
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * All the cities in a country organised by largest population to smallest.
     */
    public ArrayList<City> report10(){

        System.out.println("Enter the country code you would like to search within");
        String input = checkCountryCode();

        // Create string for SQL statement
        String strSelect =
                "SELECT ID, Name, CountryCode, District, Population "
                        + "FROM city "
                        + "WHERE CountryCode = " + "'" + input + "'"
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
                "SELECT ID, Name, CountryCode, District, Population "
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
                "SELECT ID, Name, CountryCode, District, Population "
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
                "SELECT ID, city.Name, CountryCode, District, city.Population "
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
                "SELECT ID, city.Name, CountryCode, District, city.Population "
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

        System.out.println("Enter the country code you would like to search within");
        //Checks whether the country code is within the database
        String input = checkCountryCode();

        // Create string for SQL statement
        String strSelect =
                "SELECT ID, city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "WHERE CountryCode = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     * All the capital cities in the world organised by largest population to smallest.
     */
    public ArrayList<City> report17(){

        // Create string for SQL statement
        String strSelect =
                "SELECT ID, city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "ORDER BY Population DESC";
        // Execute SQL statement
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
                "SELECT ID, city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
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
                "SELECT ID, city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "WHERE Region = " + "'" + input + "'"
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
                "SELECT ID, city.Name, CountryCode, District, city.Population "
                        + "FROM city "
                        + "JOIN country ON city.ID = country.Capital "
                        + "WHERE Continent = " + "'" + input + "'"
                        + "ORDER BY Population DESC "
                        + "LIMIT " + numInput;
        // Execute SQL statement
        return CityStatement(strSelect);
    }

    /**
     *
     * @return
     */
    public ArrayList<Country> report26(){
        ArrayList<Country> countryList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Region you would like to search within");
        String input = validateStringInput();

        //Checks whether the country code is within the database
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Population "
                            + "FROM country "
                            + "WHERE Region = " + "'" + input + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //If no records are returned then you'll be asked to enter another country code and that will be validated
            while(!rset.next())
            {
                System.out.println("Region does not exist. Try again");
                input = sc.next();
                while (true)
                {
                    if(input.matches("[a-zA-Z]+"))
                        break;
                    else {
                        System.out.println("Please enter a valid country code");
                        input = sc.next();
                    }
                }

                // Create string for SQL statement
                strSelect =
                        "SELECT Population "
                                + "FROM country "
                                + "WHERE Region = " + "'" + input + "'";
                // Execute SQL statement
                rset = stmt.executeQuery(strSelect);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 "
                            + "FROM country "
                            + "WHERE Region = " + "'" + input + "'"
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Return new CityList if valid.
            // Check one is returned
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
                countryList.add(country);
            }
            return countryList;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
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
    public ArrayList<Country> CountryStatement(String Query, String QueryD){
        ArrayList<Country> CountryList = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            ResultSet rset = stmt.executeQuery(Query);

            while (!rset.next()) {
                System.out.println("no data");
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }

        try{
            Statement stmt = con.createStatement();

            ResultSet rset = stmt.executeQuery(QueryD);

            // Return new CityList if valid.
            // Check one is returned
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
     * Selects which report to run
     * @param ReportNum
     */
    public void reportSelect(int ReportNum)
    {
        ArrayList<City> cities;

        switch (ReportNum)
        {
            case 7:
                cities = report7();
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
            case 17:
                cities = report17();
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
        }

        /**
         * if statement to get report
         *         if(ReportNum == 10){
         *
         *             ArrayList<City> cities = report10();
         *             //Display cities
         *             displayCities(cities);
         *         }
         *
         *         //This is report 10 just making use of New Method
         *         if(ReportNum == 11){
         *             Scanner sc20 = new Scanner(System.in);
         *
         *             System.out.println("Enter the city you would like to search within");
         *             String input20 = sc20.nextLine();
         *
         *             String str =
         *                     "SELECT CountryCode "
         *                             + "FROM city "
         *                             + "WHERE CountryCode = " + "'" + input20 + "'";
         *             String str1 =
         *                     "SELECT ID, Name, CountryCode, District, Population "
         *                             + "FROM city "
         *                             + "WHERE CountryCode = " + "'" + input20 + "'"
         *                             + "ORDER BY Population DESC";
         *
         *             ArrayList<City> cities = CityStatement(str1);
         *             displayCities(cities);
         *         }
         *
         *         //Report 25
         *         if(ReportNum == 25){
         *             Scanner s = new Scanner(System.in);
         *
         *             System.out.println("Enter the Continent you would like to search within");
         *             String input = s.nextLine();
         *
         *             String str =
         *                     "SELECT Population "
         *                             + "FROM country "
         *                             + "WHERE Continent = " + "'" + input + "'";
         *
         *             String str1 =
         *                     "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 "
         *                             + "FROM country "
         *                             + "WHERE Continent = " + "'" + input + "'"
         *                             + "ORDER BY Population DESC";
         *
         *             ArrayList<Country> countries = CountryStatement(str, str1);
         *             displayCountries(countries);
         *         }
         *
         *         //Report 26
         *         if(ReportNum == 26){
         *             Scanner s = new Scanner(System.in);
         *
         *             System.out.println("Enter the Region you would like to search within");
         *             String input = s.nextLine();
         *
         *             String str =
         *                     "SELECT Population "
         *                             + "FROM country "
         *                             + "WHERE Region = " + "'" + input + "'";
         *
         *             String str1 =
         *                     "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 "
         *                             + "FROM country "
         *                             + "WHERE Region = " + "'" + input + "'"
         *                             + "ORDER BY Population DESC";
         *
         *             ArrayList<Country> countries = CountryStatement(str, str1);
         *             displayCountries(countries);
         *         }
         */

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
     * Ensures that the given country code exists within the database
     * @return input
     */
    public String checkCountryCode()
    {
        String input = validateStringInput();

        //Checks whether the country code is within the database
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT CountryCode "
                            + "FROM city "
                            + "WHERE CountryCode = " + "'" + input + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //If no records are returned then you'll be asked to enter another country code and that will be validated
            while(!rset.next())
            {
                System.out.println("Country code does not exist. Try again");
                input = validateStringInput();

                // Create string for SQL statement
                strSelect =
                        "SELECT CountryCode "
                                + "FROM city "
                                + "WHERE CountryCode = " + "'" + input + "'";
                // Execute SQL statement
                rset = stmt.executeQuery(strSelect);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details1");
            return null;
        }
        return input;
    }

    /**
     * Ensures that the given district exists within the database
     * @return
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
                                + "FROM Continent "
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
        System.out.println(String.format("%-10s %-15s %-20s %-25s %-30s", "ID", "Name", "Country Code", "District", "Population"));
        //Loop through every city in the list
        for (City cit: cities)
        {
            if(cit == null)
                continue;
            String cit_string = String.format("%-10s %-15s %-20s %-25s %-30s",
                    cit.ID, cit.Name, cit.CountryCode, cit.District, cit.Population);
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
        System.out.println(String.format("%-10s %-15s %-20s %-25s %-30s %-35s %-40s %-45s %-50s %-55s %-60s %-65s %-70s %-75s %-80s", "Code", "Name", "Continent", "Region", "SurfaceArea", "IndepYear", "Population", "LifeExpectancy", "GNP", "GNPOld", "LocalName", "GovernmentForm", "HeadOfState", "Capital", "Code2"));

        for (Country cou: countries){
            if(cou == null){
                continue;
            }
            String cou_String = String.format("%-10s %-15s %-20s %-25s %-30s %-35s %-40s %-45s %-50s %-55s %-60s %-65s %-70s %-75s %-80s",
                    cou.Code, cou.Name, cou.Continent, cou.Region, cou.SurfaceArea, cou.IndepYear, cou.Population, cou.LifeExpectancy, cou.GNP, cou.OldGNP, cou.LocalName, cou.GovernmentForm, cou.HeadOfState, cou.Capital, cou.Code2);
            System.out.println(cou_String);
        }
    }
}