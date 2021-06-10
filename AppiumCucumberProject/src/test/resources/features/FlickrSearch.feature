Feature: Flickr Search

  Scenario Outline: Validate the search results on Flickr Search Screen

    Given User is on Flickr Search Screen
    When User searches for a text "<keyword>" on Flickr Search Screen
    Then Search results should be displayed for the search criteria "<keyword>"

    Examples:
      | keyword   |
      | Windsor   |
      | Chelsea   |
      | Sample    |