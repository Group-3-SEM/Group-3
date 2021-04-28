package Group3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



public class UnitTest {
    static App app;



    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060");
    }



    @Test
    void displayCitiesTestNull() {
        app.displayCities(null);
    }

    @Test
    void TestReport7(){
        app.report7();
    }

    @Test
    void TestReport10(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("AUS".getBytes());
        System.setIn(in);
        assertNotNull(app.report10());
        System.setIn(sysInBackup);
    }

    @Test
    void TestReport11(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Kabol".getBytes());
        System.setIn(in);
        assertNotNull(app.report11());
        System.setIn(sysInBackup);
    }

    @Test
    void TestReport12(){

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);


        assertNotNull(app.report12());
        System.setIn(sysInBackup);
    }
    /*
    @Test
    void TestReport13(){
        String simulatedUserInput = "3";
        String simulatedUserIn = "Asia";

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        ByteArrayInputStream ini = new ByteArrayInputStream(simulatedUserIn.getBytes());
        System.setIn(in);
        System.setIn(ini);


        assertNotNull(app.report13());
        System.setIn(sysInBackup);
    }

     */








    @Test
    void TestReport17(){
        assertNotNull(app.report17());
    }

    @Test
    void TestReport20(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);
        assertNotNull(app.report20());
        System.setIn(sysInBackup);
    }

    @Test
    void TestReport25(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("As".getBytes());
        System.setIn(in);
        assertNotNull(app.report25());
        System.setIn(sysInBackup);
    }

    @Test
    void TestReport26(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Middle East".getBytes());
        System.setIn(in);
        assertNotNull(app.report26());
        System.setIn(sysInBackup);
    }

    @Test
    void TestReport42(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Middle East".getBytes());
        System.setIn(in);
        assertNotNull(app.report42());
        System.setIn(sysInBackup);
    }

    @Test
    void TestReport46(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Europe".getBytes());
        System.setIn(in);

        assertEquals("Population in Cities 241942813 Population not in Cities 488131787", app.report46());
    }

    @Test
    void TestReport47(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Middle East".getBytes());
        System.setIn(in);

        assertEquals("Population in Cities 70371374 Population not in Cities 118009326", app.report47());
    }

    @Test
    void TestReport48() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("AUS".getBytes());
        System.setIn(in);

        assertEquals("Population in Cities 11313666 Population not in Cities 7572334", app.report48());
    }

    @Test
    void TestReport49() {
        assertEquals("6078749450", app.report49());
    }

    @Test
    void TestReport50() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Europe".getBytes());
        System.setIn(in);

        assertEquals("730074600", app.report50());

        System.setIn(sysInBackup);
    }

    @Test
    void TestReport51(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Middle East".getBytes());
        System.setIn(in);

        assertEquals("188380700", app.report51());

        System.setIn(sysInBackup);
    }

    @Test
    void TestReport52(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("AUS".getBytes());
        System.setIn(in);

        assertEquals("18886000", app.report52());

        System.setIn(sysInBackup);
    }

    @Test
    void TestReport53(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Kabol".getBytes());
        System.setIn(in);

        assertEquals("1780000", app.report53());

        System.setIn(sysInBackup);
    }

    @Test
    void TestReport54(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Amsterdam".getBytes());
        System.setIn(in);

        assertEquals("731200", app.report54());

        System.setIn(sysInBackup);
    }

    @Test
    void TestReport55(){
        int i = 0;
        String str1 = "The number of people that speak Chinese is 1968265472 And that is 32.379444 percentage of the world population";
        String str2 = "The number of people that speak Hindi is 1046302976 And that is 17.21247 percentage of the world population";
        String str3 = "The number of people that speak Spanish is 750296832 And that is 12.342947 percentage of the world population";
        String str4 = "The number of people that speak English is 627418304 And that is 10.321503 percentage of the world population";
        String str5 = "The number of people that speak Arabic is 552045120 And that is 9.081557 percentage of the world population";

        assertEquals(str1, app.report55(i));
        i++;
        assertEquals(str2, app.report55(i));
        i++;
        assertEquals(str3, app.report55(i));
        i++;
        assertEquals(str4, app.report55(i));
        i++;
        assertEquals(str5, app.report55(i));
    }

    @Test
    void TestReport60(){
        assertNotNull(app.report60());
    }

    @Test
    void TestReport61(){
        assertNotNull(app.report61());
    }

    @Test
    void TestReport62(){
        assertNotNull(app.report62());
    }

    @Test
    void TestValidateIntegerInput(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        assertNotNull(app.validateIntInput());

    }



    @Test
    void TestValidateStringInput(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Amsterdam".getBytes());
        System.setIn(in);
        assertNotNull(app.validateStringInput());
    }

    @Test
    void TestCityStatement(){
        String strSelect =
                "SELECT ID, Name, CountryCode, District, Population "
                        + "FROM city";
        assertNotNull(app.CityStatement(strSelect));
    }

    @Test
    void TestCountryStatement(){
        String strSelect =
                "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 "
                        + "FROM country";

        assertNotNull(app.CountryStatement(strSelect));
    }

    @Test
    void TestPopulationStatement(){
        String strSelect =
                "SELECT SUM(Population) "
                        + "FROM country ";

        assertNotNull(app.PopulationStatement(strSelect));
    }

    @Test
    void TestLanguageStatement(){
        String str =
                "SELECT SUM(Population) "
                        + "FROM country ";

        String strSelect =
                "SELECT SUM(Population) "
                        + "FROM country "
                        + "JOIN countrylanguage ON country.Code = countrylanguage.CountryCode "
                        + "WHERE Language = 'English'";

        assertNotNull(app.LanguageStatement("English", strSelect, str));
    }

    @Test
    void TestDifferenceStatement(){

        String str1 =
                "SELECT Sum(city.Population) "
                        + "FROM city "
                        + "JOIN country ON city.CountryCode = country.Code "
                        + "WHERE Continent = 'Europe'";


        String str2 =
                "SELECT Sum(Population) "
                        + "FROM country "
                        + "WHERE Continent = 'Europe'";

        assertNotNull(app.DifferenceStatement(str1, str2));
    }

    @Test
    void TestCheckRegion(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Middle East".getBytes());
        System.setIn(in);

        assertEquals("Middle East", app.checkRegion());
    }

    @Test
    void TestCheckCountryCode(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("AUS".getBytes());
        System.setIn(in);

        assertEquals("AUS", app.checkCountryCode());
    }

    @Test
    void TestCheckDistrict(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Kabol".getBytes());
        System.setIn(in);

        assertEquals("Kabol", app.checkDistrict());
    }

    @Test
    void TestCheckContinent(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Asia".getBytes());
        System.setIn(in);

        assertEquals("Asia", app.checkContinent());
    }

    @Test
    void TestCheckCity(){
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Amsterdam".getBytes());
        System.setIn(in);

        assertEquals("Amsterdam", app.checkCity());
    }

    @Test
    void DisplayCity() {
        ArrayList<City> cities = new ArrayList<City>();
        City c = new City();
        c.ID = 1;
        c.Name = "Kabu";
        c.CountryCode = "KAB";
        c.District = "Kalal";
        c.Population = 348983;
        app.displayCity(c);
    }

    @Test
    void displayCitiesTestEmpty() {
        ArrayList<City> cities = new ArrayList<>();
        app.displayCities(cities);
    }

    @Test
    void displayCityTestNull() {
        app.displayCity(null);
    }

    @Test
    void DisplayCountry(){
        Country c = new Country();
        c.Code = "AGO";
        c.Name = "Angola";
        c.Continent = "Africa";
        c.Region = "Central Africa";
        c.SurfaceArea = 1246700.00F;
        c.IndepYear = 1975;
        c.Population = 12878000;
        c.LifeExpectancy = 38.3F;
        c.GNP = 6648.00F;
        c.OldGNP = 7984.00F;
        c.LocalName = "Angola";
        c.GovernmentForm = "Republic";
        c.HeadOfState = "José Eduardo dos Santos";
        c.Capital = 56;
        c.Code2 = "AO";
    }

    @Test
    void displayCountryTestEmpty(){
        ArrayList<Country> countries = new ArrayList<>();
        app.displayCountries(countries);
    }

    @Test
    void displayCountryTestNull() {
        app.displayCountry(null);
    }

    @Test
    void Displaypopulation(){
        String Population = "739788";
        app.displayPopulation(Population);
    }

    @Test
    void displayPopulationEmpty(){
        app.displayPopulation("");
    }

    @Test
    void displayPopulationTestNull(){
        app.displayLanguage(null);
    }

    @Test
    void DisplayLanguage(){
        app.displayLanguageReport("English");
    }

    @Test
    void displayLanguageEmpty(){
        app.displayLanguageReport("");
    }

    @Test
    void DisplayLanguageTestNull(){
        app.displayLanguageReport(null);
    }

    @Test
    void DisplayPopulationDifference(){
        app.displaypopDif("7463647");
    }

    @Test
    void DisplayPopulationEmpty(){
        app.displaypopDif("");
    }

    @Test
    void DisplayPopulationDifferenceNull(){
        app.displaypopDif(null);
    }
    /*
    @AfterAll
    static void Close() {
        app.disconnect();
    }

     */
}