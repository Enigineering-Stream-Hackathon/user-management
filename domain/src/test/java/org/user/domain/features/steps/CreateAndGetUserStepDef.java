package org.user.domain.features.steps;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.val;
import org.user.domain.UserService;
import org.user.domain.commands.UserCommand;
import org.user.domain.entities.Role;
import org.user.domain.entities.User;
import org.user.domain.features.stubs.TestContext;

public class CreateAndGetUserStepDef {

        private TestContext testContext;
        private UserService service;

        public CreateAndGetUserStepDef(TestContext testContext, UserService service) {
                this.testContext = testContext;
                this.service = service;
        }

        private UserCommand command;
        private final String id = "lucifer";
        private User fetchedUser;

        @Before
        public void setUp(){
                testContext.init();
        }

        @Given("an user \"([^\"]*)\" of role \"([^\"]*)\" should be added")
        public void given(String name, Role role){
                command = new UserCommand(id, name, role, "password");
        }

        @When("the admin adds the user from the dashboard")
        public void when(){
                service.create(command);
        }

        @Then("\"([^\"]*)\" with role \"([^\"]*)\" should been created")
        public void then(String studentName, String role){
                val actual = testContext.getUsers().stream().filter(it -> it.getId().equals(id)).findFirst().get();
                assertThat(actual.getName()).isEqualTo(studentName);
                assertThat(actual.getRole().name()).isEqualTo(role);
        }

        @Given("([^\"]*) wants to view all her user details and edit")
        public void given_user_already_exists(String userName){
                command = new UserCommand(userName, userName, Role.USER, "password");
                service.create(command);
        }

        @When("([^\"]*) clicks on edit user")
        public void when_user_wants_to_view(String userName){
                fetchedUser = service.findByUserName(userName);
        }

        @Then("([^\"]*) should able to see her details")
        public void then_gets_user_details(String userName){
                assertThat(fetchedUser.getId()).isEqualTo(userName);
                assertThat(fetchedUser.getName()).isEqualTo(userName);
                assertThat(fetchedUser.getRole()).isEqualTo(Role.USER);
        }

        @After
        public void after() {
                testContext.clearContext();
        }

}
