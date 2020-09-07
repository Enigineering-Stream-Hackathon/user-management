package org.user;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.user.domain.entities.Role.ADMIN;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.val;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.user.domain.entities.Role;
import org.user.domain.entities.User;

@AutoConfigureTest
@RunWith(SpringRunner.class)
public class UserControllerIntegrationTest {


  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private FluentJdbc fluentJdbc;

  @Autowired
  private ObjectMapper mapper;


  @Test
  public void should_create_student_details_when_post_request() throws Exception {

    val request = post("/user")
        .contentType(APPLICATION_JSON)
        .content("{\n" +
            "  \"id\":\"thanos.king\",\n" +
            "  \"name\": \"Thanos\",\n" +
            "  \"roles\": [\"ADMIN\"],\n" +
            "  \"password\" : \"password\"\n" +
            "}");
    // When
    mockMvc.perform(request)
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();

    // Then
    val created = fluentJdbc.query().select("select * from T_USER where ID = ?")
        .params("thanos.king")
        .firstResult(it -> {
          try {
            return new User(
                it.getString("ID"),
                it.getString("NAME"),
                mapper.readValue(it.getString("ROLES"), new TypeReference<List<Role>>() {
                }),
                it.getString("PASSWORD"));
          } catch (JsonProcessingException e) {
            e.printStackTrace();
          }
          return null;
        })
        .orElse(null);

    assertThat(created.getId()).isEqualTo("thanos.king");
    assertThat(created.getName()).isEqualTo("Thanos");
    assertThat(created.getRoles()).isEqualTo(singletonList(ADMIN));
    assertThat(created.getPassword()).isEqualTo("password");
  }


}
