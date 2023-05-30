package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //Extract the limit
    @Test
    public void extractTheLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the total
    @Test
    public void extractTheTotal() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the name of 5th product
    @Test
    public void extractTheNameOf5thProduct() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the names of all the products
    @Test
    public void extractTheNamesOfAllProducts() {
        List<String> names = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all products are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the productId of all the products
    @Test
    public void extractTheProductIdOfAllProducts() {
        List<Integer> ids = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Ids of all products are : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //Print the size of the data list
    @Test
    public void theSizeOfDataList() {
        int dataSize = response.extract().path("data.size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + dataSize);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
    //Pack)
    @Test
    public void getAllTheValuesOfTheProduct() {
        List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-pack)' are: " + value);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
    //Pack)
    @Test
    public void test08() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Model Name of Energizer - N Cell E90 Batteries (2-Pack): " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the categories of 8th products
    @Test
    public void getAllTheCategories() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-pack)' are: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get categories of the store where product id = 150115
    @Test
    public void getCategoriesOfStore() {
        List<HashMap<String, ?>> categories = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of the store where product id = 150115 are: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the descriptions of all the products
    @Test
    public void getAllTheDescriptions() {
        List<String> descriptions = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the descriptions of all the products are: " + descriptions);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get id of all the all categories of all the products
    @Test
    public void getIdOfAllTheCategories() {
        List<String> ids = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Ids of all the categories of all the products are: " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the product names Where type = HardGood
    @Test
    public void productNames() {
        List<String> productNames = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Product names are : " + productNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the Total number of categories for the product where product name = Duracell - AA
    //1.5V CopperTop Batteries (4-Pack)
    @Test
    public void totalNumberOfCategories() {
        List<String> totalCategories = response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of categories are: " + totalCategories.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the createdAt for all products whose price < 5.49
    @Test
    public void createdAt() {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products whose price is less than 5.49 are: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void nameOfAllCategories() {
        List<String> allCategories = response.extract().path("data.find{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack) are: " + allCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the manufacturer of all the products
    @Test
    public void findTheManufacturer() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Manufacturer of all the products are : " + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the image of products whose manufacturer is = Energizer
    @Test
    public void findTheImage() {
        String image = response.extract().path("data[8].image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The image of products whose manufacturer is = Energizer : " + image);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the createdAt for all categories products whose price > 5.99
    @Test
    public void findTheCreatedAt() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all categories products whose price > 5.99 are: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }
    //Find the url of all the products
    @Test
    public void findTheUri(){
        List<String> url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The url of all the products are: " + url);
        System.out.println("------------------End of Test---------------------------");

    }
}





