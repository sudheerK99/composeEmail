#Author: Sudheer P

Feature: Compose Function in Gmail

	@compose
  Scenario: Compose an email with specific body and subject
    Given I am logged in to Gmail
    When I click on the "Compose" button
    And I enter the recipient's email address
    And I enter "Incubyte" in the subject field
    And I enter "Automation QA test for Incubyte" in the email body
    And I click the "Send" button
    Then the email should be sent successfully

	@compose
  Scenario: Compose an email with missing subject
    Given I am logged in to Gmail
    When I click on the "Compose" button
    And I enter the recipient's email address
    And I leave the subject field empty
    And I enter "Automation QA test for Incubyte" in the email body
    And I click the "Send" button
    Then I should see an error message for the missing subject

	@compose
  Scenario: Compose an email with missing recipient
    Given I am logged in to Gmail
    When I click on the "Compose" button
    And I leave the recipient's email address field empty
    And I enter "Incubyte" in the subject field
    And I enter "Automation QA test for Incubyte" in the email body
    And I click the "Send" button
    Then I should see an error message for the missing recipient

	@compose
  Scenario: Compose an email with missing body
    Given I am logged in to Gmail
    When I click on the "Compose" button
    And I enter the recipient's email address
    And I enter "Incubyte" in the subject field
    And I leave the email body empty
    And I click the "Send" button
    Then I should see an error message for the missing email body

