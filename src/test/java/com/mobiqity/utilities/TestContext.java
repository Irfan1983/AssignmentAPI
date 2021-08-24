package com.mobiqity.utilities;

import cucumber.runtime.java.guice.ScenarioScoped;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

@Data
@ScenarioScoped
public class TestContext {

    private int id;

    public Response getApi(String api) throws Throwable {

        RequestSpecification httpRequest = RestAssured.given();
        //	Response response = httpRequest.get(url);
        Response response = httpRequest.given().headers(
                "Authorization",
                "Bearer" + "",
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON).when().contentType(ContentType.JSON).get(api);


        return response;
    }


}
