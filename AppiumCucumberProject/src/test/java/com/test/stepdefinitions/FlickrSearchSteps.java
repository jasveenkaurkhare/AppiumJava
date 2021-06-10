package com.test.stepdefinitions;

import com.test.Configurations.FlickrAPISearch;
import com.test.screens.FlickrSearchScreen;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

public class FlickrSearchSteps {

    FlickrSearchScreen flickrSearchScreen;
    FlickrAPISearch flickrAPISearch;

    @Given("^User is on Flickr Search Screen$")
    public void userIsOnFlickrSearchScreen() throws Throwable {
        flickrSearchScreen = new FlickrSearchScreen();
        flickrSearchScreen.waitForFlickrSearchScreen();
    }

    @When("^User searches for a text \"([^\"]*)\" on Flickr Search Screen$")
    public void flickrSearch(String flickrSearchText) throws Throwable {
        flickrSearchScreen = new FlickrSearchScreen();
        flickrSearchScreen.searchOnFlickrByKeyword(flickrSearchText);
    }

    @Then("^Search results should be displayed for the search criteria \"([^\"]*)\"$")
    public void validateFlickrSearchResults(String flickrSearchText) throws Throwable {
        flickrSearchScreen = new FlickrSearchScreen();
        flickrAPISearch = new FlickrAPISearch();
        List<String> flickrSearchResponse = flickrAPISearch.getFlickrSearchResponses(flickrSearchText);
        Assert.assertTrue(flickrSearchScreen.getFlickrSearchResults(flickrSearchText).equals(flickrSearchResponse));
    }
}
