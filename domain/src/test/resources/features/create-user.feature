Feature: As an Admin, I want to add new users

    Scenario: Add new user in the platform
      Given an user "Lucifer" of role "ADMIN" should be added
      When the admin adds the user from the dashboard
      Then "Lucifer" with role "ADMIN" should been created