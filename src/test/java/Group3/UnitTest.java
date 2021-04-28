package Group3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }

    @Test
    void displayLanguageTestNull()
    {
        app.displayLanguage(null);
    }

    @Test
    void displayCitiesTestNull()
    {
        app.displayCities(null);
    }

    @Test
    void displayCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<>();
        app.displayCities(cities);
    }

    @Test
    void displayCityTestNull()
    {
        app.displayCity(null);
    }

    @Test
    void displayCountryTestNull()
    {
        app.displayCountry(null);
    }

    @Test
    void cityStatementTestNull()
    {
        app.CityStatement(null);
    }

    @Test
    void cityStatementTestEmpty()
    {
        app.CityStatement("");
    }

    @Test
    void countryStatementTestNull()
    {
        app.CountryStatement(null);
    }

    @Test
    void countryStatementTestEmpty()
    {
        app.CountryStatement("");
    }

    @Test
    void populationStatementTestNull()
    {
        app.PopulationStatement(null);
    }

    @Test
    void populationStatementTestEmpty()
    {
        app.PopulationStatement("");
    }
}