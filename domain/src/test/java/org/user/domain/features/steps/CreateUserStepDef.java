package org.user.domain.features.steps;

import static java.util.Collections.singletonList;
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
import org.user.domain.features.stubs.TestContext;

public class CreateUserStepDef {

        private TestContext testContext;
        private UserService service;

        public CreateUserStepDef(TestContext testContext, UserService service) {
                this.testContext = testContext;
                this.service = service;
        }

        private UserCommand command;
        private final String id = "lucifer";

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

        @After
        public void after() {
                testContext.clearContext();
        }

}
