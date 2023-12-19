Feature: Selenium Capstone Project

  Scenario: Verify various actions on the-internet.herokuapp.com
    Given User launches the URL "http://the-internet.herokuapp.com/"
    Then User verifies the title of the page
    When User clicks on A/B Testing link
    Then User verifies the text on the page as "A/B Test Control"
    When User navigates back to Home page and clicks on dropdown link
    And User selects "Option 1" from the dropdown
    Then User confirms if "1" is selected
    When User navigates back to Home Page and clicks on Frames
    Then User verifies the presence of hyperlinks on the Frames Page
      And verifies Nested Frames hyperlink
      And verifies iFrame hyperlink
