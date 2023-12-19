package TestScripts;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Resources.ExcelDataReader;

public class CountryTranslationTest {

    @Test(dataProvider = "translations")
    public void testCountryTranslation(String translation) {
        // the base URI for the API
        RestAssured.baseURI = "https://restcountries.com/v3.1/translation/";

        // Triggering the API with the provided translation
        Response response = RestAssured.given()
                .pathParam("translation", translation)
                .get("{translation}");

        // Validating the response as needed
        response.then().statusCode(200);

        
    }
    
    
    @DataProvider(name = "translations")
    public Object[][] translationsTestData() {
       // path to Excel file and the correct sheet name
        String filePath = "src\\main\\java\\TestData\\CountryTranslations.xlsx";
        String sheetName = "translations";  

        Object[][] data = ExcelDataReader.readTestData(filePath, sheetName);

        if (data == null) {
            System.out.println("Data Provider: No data loaded from the Excel sheet.");
        } else {
            System.out.println("Data Provider: Data loaded successfully.");
        }

        return data;
    }
}
