package Group3;
//defining imports
import java.sql.*;
import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        if (args.length < 1)
        {
            a.connect("localhost:33060");
        }
        else
        {
            a.connect(args[0]);
        }

        //Get cities
        ArrayList<City> cities = a.report10();

        //Display cities
        a.displayCities(cities);

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

    //All the cities in the world organised by largest population to smallest.
    public ArrayList<City> report7(){
        //Initialization
        ArrayList<City> cityList = new ArrayList<>();

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
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

    //All the cities in a country organised by largest population to smallest.
    public ArrayList<City> report10(){
        //Initialization
        ArrayList<City> cityList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the country code you would like to search within");
        String input = sc.nextLine();
        //Ensures that there are only letters in the input
        while (true)
        {
            if(input.matches("[a-zA-Z]+"))
                break;
            else {
                System.out.println("Please enter a valid country code");
                input = sc.nextLine();
            }
        }

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

        //
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE CountryCode = " + "'" + input + "'"
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Return new CityList if valid.
            // Check one is returned
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

    //All the cities in a district organised by largest population to smallest.
    public ArrayList<City> report11() {
        //Initialization
        ArrayList<City> cityList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the district you would like to search within");
        String input = sc.nextLine();

        //Checks whether the country code is within the database
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

            //If no records are returned then you'll be asked to enter another country code and that will be validated
            while(!rset.next())
            {
                System.out.println("District does not exist. Try again");
                input = sc.nextLine();

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

        //
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE District = " + "'" + input + "'"
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Return new CityList if valid.
            // Check one is returned
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

    //The top N populated cities in the world where N is provided by the user.
    public ArrayList<City> report12(){
        //Initialization
        ArrayList<City> cityList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int input;

        System.out.println("Enter the number of cities you would like to print");
        //Ensures that the input only contains numbers
        while(!sc.hasNextInt())
        {
            System.out.println("Please enter a number");
            sc.next();
        }
        input = sc.nextInt();

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "ORDER BY Population DESC "
                            + "LIMIT " + input;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Return new City if valid.
            // Check one is returned
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

    //All the capital cities in the world organised by largest population to smallest.
    public ArrayList<City> report17(){
        //Initialization
        ArrayList<City> cityList = new ArrayList<>();

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, city.Name, CountryCode, District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.ID = country.Capital "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
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

    public ArrayList<City> report14(){
        //Initialization
        ArrayList<City> cityList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter the number of cities you would like to print");
        //Ensures that the input only contains numbers
        while(!sc.hasNextInt())
        {
            System.out.println("Please enter a number");
            sc.next();
        }
        int numInput = sc.nextInt();

        System.out.println("Enter the continent you would like to search within");
        String input = sc.nextLine();
        //Ensures that there are only letters in the input
        while (true)
        {
            if(input.matches("[a-zA-Z]+"))
                break;
            else {
                System.out.println("Please enter a valid continent");
                input = sc.nextLine();
            }
        }

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

            //If no records are returned then you'll be asked to enter another country code and that will be validated
            while(!rset.next())
            {
                System.out.println("Continent does not exist. Try again");
                while (true)
                {
                    if(input.matches("[a-zA-Z]+"))
                        break;
                    else {
                        System.out.println("Please enter a valid continent");
                        input = sc.nextLine();
                    }
                }

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

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, city.Name, CountryCode, District, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.CountryCode = country.Code "
                            + "WHERE Continent = " + "'" + input + "'"
                            + "ORDER BY Population DESC "
                            + "LIMIT " + numInput;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Return new City if valid.
            // Check one is returned
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

    hey

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
}