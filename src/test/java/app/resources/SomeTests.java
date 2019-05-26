package app.resources;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class SomeTests {

    private static final String SWAGGER_JSON_SPEC_PATH = "/swagger/api-spec.json";

    @Test
    public void testGet() throws Exception {
        final AssignmentResource assignmentResource = new AssignmentResource();
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(assignmentResource).build();

        mockMvc.perform(get("company/1/assignment/1")
                .header("api_key", "API_KEY"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH))
                .andExpect(jsonPath("$.id", 1)
                        .value(equalTo(1)));;
    }
}
