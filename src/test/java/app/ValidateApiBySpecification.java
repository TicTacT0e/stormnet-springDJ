package app;

import app.resources.AssignmentResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class ValidateApiBySpecification {

    private static final String SWAGGER_JSON_SPEC_PATH = "/swagger/api-spec.json";

    private MockMvc mockMvc;

    @Before
    public void setup() {
        final AssignmentResource assignmentResource = new AssignmentResource();
        mockMvc = MockMvcBuilders.standaloneSetup(assignmentResource).build();
    }

    @Test
    public void assignmentGetAll() throws Exception {
        mockMvc.perform(get("company/1/assignment/1")
                .header("api_key", "API_KEY"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void assignmentPost() throws Exception {
        mockMvc.perform(post("company/1/assignment")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"employeeName\" : \"name\", " +
                        "\"activities\" : [{" +
                        "\"name\" : \"name\"}], " +
                        "\"workLoad\" : 100}"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }
}
