package Group3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060");
    }

    @Test
    void testGetCity()
    {
        City citi = app.getCity(559);
        assertEquals(citi.ID, 559);
        assertEquals(citi.Name, "Antofagasta");
        assertEquals(citi.CountryCode, "CHL");
        assertEquals(citi.District, "Antofagasta");
        assertEquals(citi.Population, 251429);
    }

    @Test
    void testGetCountry()
    {
        Country countri = app.getCountry("ETH");
        assertEquals(countri.Code, "ETH");
        assertEquals(countri.Name, "Ethiopia");
        assertEquals(countri.Continent, "Africa");
        assertEquals(countri.Region, "Eastern Africa");
        assertEquals(countri.SurfaceArea, 1104300.00);
        assertEquals(countri.IndepYear, -1000);
        assertEquals(countri.Population, 62565000);
        assertEquals(countri.LifeExpectancy, 45.2, 0.02);
        assertEquals(countri.GNP, 6353.00);
        assertEquals(countri.OldGNP, 6180.00);
        assertEquals(countri.LocalName, "YeItyop´iya");
        assertEquals(countri.GovernmentForm, "Republic");
        assertEquals(countri.HeadOfState, "Negasso Gidada");
        assertEquals(countri.Capital, 756);
        assertEquals(countri.Code2, "ET");
    }

    @Test
    void testGetCountryLanguage()
    {
        CountryLanguage lang = app.getLanguage("CUB");
        assertEquals(lang.CountryCode, "CUB");
        assertEquals(lang.Language, "Spanish");
        assertEquals(lang.IsOfficial, "T");
        assertEquals(lang.Percentage, 100.0);
    }
}