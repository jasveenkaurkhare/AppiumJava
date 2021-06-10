package com.test.Configurations;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class FlickrAPISearch {

    /*
    *   Method that fetches the search responses from flickr search endpoint for the specified search keyword
    */
    public List<String> getFlickrSearchResponses(String flickrSearchKeyword) throws ParseException {
        RestAssured.baseURI = "https://api.flickr.com/services/feeds/photos_public.gne";
        Response searchResponse = given().config( RestAssured.config().sslConfig(
                new SSLConfig().relaxedHTTPSValidation() ) )
                .contentType( "application/json" )
                .accept( "application/json" )
                .param("format","json")
                .param("nojsoncallback","1")
                .param("tags",flickrSearchKeyword)
                .request().get();
        String searchResponseOutput = searchResponse.body().asString();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(searchResponseOutput);
        List<JSONObject> jsonObjects = (List<JSONObject>) jsonObject.get("items");
        List<String> flickrSearchResults = new ArrayList<>();
        jsonObjects.forEach(jsonObject1 -> flickrSearchResults.add(jsonObject1.get("title").toString()));
        return flickrSearchResults;
    }
}
