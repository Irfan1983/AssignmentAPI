@TestFlow
Feature:   To perform the validations for the specific flow

  Scenario Outline:Search for the user with username “Delphine”.
    Given I have API Header with resources "users"
    When Verify the status code 200
    And Verify response contains "<testData>"
    Examples:
      | testData |
      | Delphine |

  @TestFlow
  Scenario Outline:Use the details fetched to make a search for the posts written by the user.
    Given I have API Header with resources "users"
    When Verify the status code 200
    And Verify response contains "<testData>"
    And Use the details fetch the data for "<testData>"
    And To make a search for the posts written by the user
    And Verify the status code 200
    And Verify fields in the response "userId","id","title"
    Examples:
      | testData |
      | Delphine |

  @TestFlow
  Scenario: For each post, fetch the comments and validate if the emails in the comment section are in the proper format.
    Given I have API Header with resources "comments"
    When Verify the status code 200
    And validate if the emails in the comment section are in the proper format.

  @SmokeTesting
  Scenario: Verify the post request
    Given I have API Header with resources "posts"
    When Verify the status code 200
    And Verify fields in the response "userId","id","title"

  @SmokeTesting
  Scenario: Verify the post comments requests
    Given I have API Header with resources "comments"
    When Verify the status code 200
    And Verify fields in the response "id","name","email"

  @SmokeTesting
  Scenario: Verify the  album requests
    Given I have API Header with resources "albums"
    When Verify the status code 200

  @SmokeTesting
  Scenario: Verify the  photos requests
    Given I have API Header with resources "photos"
    When Verify the status code 200
    And Verify fields in the response "id","title","url"

  @SmokeTesting
  Scenario: Verify the  todos requests
    Given I have API Header with resources "todos"
    When Verify the status code 200
    And Verify fields in the response "userId","id","title"

  @SmokeTesting
  Scenario: Verify the users requests
    Given I have API Header with resources "users"
    When Verify the status code 200
    And Verify fields user in the response