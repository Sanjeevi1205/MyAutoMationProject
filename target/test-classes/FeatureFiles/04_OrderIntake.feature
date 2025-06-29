Feature: OrderIntake-New Case

  @OrderIntakeCustomLink  @Regression @OrderIntake
  Scenario: Verify that the Client places an order with custom links
    Given the client logs in using valid credentials
    When the client clicks on Add a new record to place an order
    And the client enters the required details, selects some services, and clicks the next button
    And the client selects case type and subtype from the second form
    And the client enables the estimate toggle
    And the client provides the upload link and download link, and clicks submit button
    And the client edits some details
    And the  client enters a casenumber manually
    And the clients saves and submits the details
    And the client clicks the confirm button in the confirmation pop up
    And the client sees the order placed success message and is redirected to the case list page
    And the client verifies whether the entered case number is being reflected in the caselist page
    And the client verifies whether the case status is "Estimation in process", case version is "New" and case priority is "Standard"
    Then verify whether the mail for "New Case" is being received

  @OrderIntakeFileUpload @Regression @OrderIntake @FileUpload
  Scenario: Verify that the client places an order by uploading Different file Types
    Given the client logs in using valid credentials
    When the client clicks on Add a new record to place an order
    And the client enters the required details, selects some services, and clicks the next button
    And the client selects case type and subtype from the second form
    And the client enters the case overview
    And the client uploads different file types, and clicks the submit button
    And the client edits some details
    And the clients saves and submits the details
    And the client clicks the confirm button in the confirmation pop up
    And the client sees the order placed success message and is redirected to the case list page
    And the client verifies whether the case number is generated automatically in the caselist page
    And the client verifies whether the case status is "Open", case version is "New" and case priority is "Standard"
    Then verify whether the mail for "New Case" is being received

  @OrderIntakeNegative @Regression @OrderIntake
  Scenario: Verify that the client cannot place an order without uploading files
    Given the client logs in using valid credentials
    When the client clicks on Add a new record to place an order
    And the client enters the required details, selects some services, and clicks the next button
    And the client selects case type and subtype from the second form
    And the client enters the case overview
    And the client does not upload any files and clicks the submit button
    Then the client verifies that an error message is displayed, indicating files must be uploaded