package com.test.screens;

import com.test.Configurations.FlickrAPISearch;
import com.test.Configurations.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class FlickrSearchScreen {

    private AppiumDriver driver;
    private FlickrAPISearch flickrAPISearch;


    @iOSFindBy(className = "SearchField")
    private IOSElement flickrSearchField;

    @iOSFindBy(className = "StaticText")
    private List<IOSElement> flickrSearchResults;

    /*
    *   FlickrSearchScreen class constructor to initialize the screen elements
    */
    public FlickrSearchScreen() {
        this.driver = new Hooks().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver),this);
    }

    /*
    *   Wait until the Flickr Search Screen is displayed
    */
    public void waitForFlickrSearchScreen() {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver,40);
        webDriverWait.until(ExpectedConditions.visibilityOf(flickrSearchField));
    }

    /*
    *   Search on Flickr using the specified keyword
    */
    public void searchOnFlickrByKeyword(String flickrSearchText) {
        waitForFlickrSearchScreen();
        flickrSearchField.click();
        flickrSearchField.sendKeys(flickrSearchText);
        flickrSearchField.sendKeys(Keys.RETURN);
        ((IOSDriver) driver).hideKeyboard("Return");
    }

    /*
    *   Returns the list of search results from Flickr
    */
    public List<String> getFlickrSearchResults(String flickrSearchText) throws ParseException {
        flickrAPISearch = new FlickrAPISearch();
        List<String> flickrSearchResult = new ArrayList<>();
        int count = flickrAPISearch.getFlickrSearchResponses(flickrSearchText).size();
        flickrSearchResults.forEach(iosElement -> flickrSearchResult.add(iosElement.getText()));
        while (flickrSearchResult.size() !=  count) {
            System.out.println("Search Results Size: " + flickrSearchResult.size());
            Dimension dimensions = driver.manage().window().getSize();
            Double screenHeightBegin = dimensions.getHeight() * 0.5;
            int scrollStart = screenHeightBegin.intValue();
            Double screenHeightEnd = dimensions.getHeight() * 0.30;
            int scrollEnd = screenHeightEnd.intValue();
            ((AppiumDriver<MobileElement>) driver).swipe(0, scrollStart, 0, scrollEnd, 1000);
            List<String> tempArray = new ArrayList<>();
            flickrSearchResults.forEach(iosElement -> tempArray.add(iosElement.getText()));
            for(int i = tempArray.lastIndexOf(flickrSearchResult.get(flickrSearchResult.size()-1)); flickrSearchResult.size() < count; i++ ) {
                flickrSearchResult.add(tempArray.get(i+1));
            }
        }
        return flickrSearchResult;
    }

}
