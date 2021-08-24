package com.mobiqity.assignment.stepdefinition;


import com.google.inject.Inject;
import com.mobiqity.utilities.CommonFunction;
import com.mobiqity.utilities.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class stepdefinition {
    @Inject
    TestContext testContext = new TestContext();
    CommonFunction conf = new CommonFunction();
    Response response;
    Properties properties;
    int id = 0;

    public stepdefinition() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\test\\Config\\ConfigFile"));
        properties = new Properties();
        properties.load(reader);
    }

    @Given("I have API Header with resources {string}")
    public void iHaveAPIHeader(String resource) throws Throwable {
        response = testContext.getApi(properties.getProperty("url") + resource);

    }

    @When("Verify the status code {int}")
    public void verifyTheStatusCode(int statuscode) {
        Assert.assertEquals(response.statusCode(), statuscode);

    }

    @And("Verify response contains {string}")
    public void verifyResponseContainsUsername(String testdata) {
        String temp = response.asString();
        Assert.assertTrue(temp.contains(testdata));
    }

    @And("Use the details fetch the data for {string}")
    public void useTheDetailsFetchedToMakeASearchForThePostsWrittenByTheUser(String username) {
        String jsonString = response.asString();
        List<Map<String, String>> responsemap = JsonPath.from(jsonString).get("");


        for (Map<String, String> stringStringMap : responsemap) {
            String temp = stringStringMap.get("username");
            if (temp.contains(username)) {
                id = Integer.parseInt(String.valueOf(stringStringMap.get("id")));
                testContext.setId(id);
            }

        }

    }

    @And("To make a search for the posts written by the user")
    public void toMakeASearchForThePostsWrittenByTheUser() throws Throwable {
        response = testContext.getApi(properties.getProperty("url") + "posts/" + testContext.getId());
    }


    @And("Verify fields in the response \"([^\\\"]*)\\\",\"([^\\\"]*)\\\",\"([^\\\"]*)\\\"$")
    public void verifyFieldsInTheResponse(String userId, String id, String title) {
        String jsonString = response.asString();
        Assert.assertTrue(jsonString.contains(userId));
        Assert.assertTrue(jsonString.contains(id));
        Assert.assertTrue(jsonString.contains(title));

    }

    @And("validate if the emails in the comment section are in the proper format.")
    public void validateIfTheEmailsInTheCommentSectionAreInTheProperFormat() {

        String jsonString = response.asString();
        List<Map<String, String>> responsemap = JsonPath.from(jsonString).get("");
        int id = 0;
        for (Map<String, String> stringStringMap : responsemap) {
            String email = stringStringMap.get("email");
            id = Integer.parseInt(String.valueOf(stringStringMap.get("id")));
            Assert.assertTrue(conf.isValidEmailAddress(email));
        }
    }

    @And("Verify fields user in the response")
    public void verifyFieldsUserInTheResponse() {
        String jsonString = response.asString();
        List<Map<String, String>> responsemap = JsonPath.from(jsonString).get("");
        for (Map<String, String> stringStringMap : responsemap) {
            Assert.assertTrue(stringStringMap.containsKey("id"));
            Assert.assertTrue(stringStringMap.containsKey("name"));
            Assert.assertTrue(stringStringMap.containsKey("username"));
            Assert.assertTrue(stringStringMap.containsKey("email"));
            Assert.assertTrue(stringStringMap.containsKey("address"));
            Assert.assertTrue(stringStringMap.containsKey("phone"));
            Assert.assertTrue(stringStringMap.containsKey("website"));
            Assert.assertTrue(stringStringMap.containsKey("company"));
            Assert.assertTrue(conf.isValidEmailAddress(stringStringMap.get("email")));

        }
    }
}