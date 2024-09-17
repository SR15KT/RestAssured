Feature: Add Place API Validation

  Scenario: Verify is place is successfully being added using AddPlaceApi
    Given: Add Place PayLoad
    When: user calls "AddPlaceApi" with post http request
    Then: the Api call got success withstatus code 200
    And: "status" in response is "Ok"
    And: "scope" in response body is "App"

