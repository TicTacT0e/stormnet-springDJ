package app;

import app.resources.AssignmentResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


public class ValidateApiBySpecification {

    private static final String SWAGGER_JSON_SPEC_PATH = "/swagger/api-spec.json";

    private MockMvc mockMvc;

    private static final String ASSIGNMENT_TEST_JSON_MODEL = "{\"employeeName\" : \"name\", " +
            "\"activities\" : [{" +
            "\"name\" : \"name\"}], " +
            "\"workLoad\" : 100}";

    @Before
    public void setup() {
        final AssignmentResource assignmentResource = new AssignmentResource();
        mockMvc = MockMvcBuilders.standaloneSetup(assignmentResource).build();
    }

    @Test
    public void assignmentGetAll() throws Exception {
        mockMvc.perform(get("company/1/assignment"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void assignmentGet() throws Exception {
        mockMvc.perform(get("company/1/assignment/1"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void assignmentPost() throws Exception {
        mockMvc.perform(post("company/1/assignment")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ASSIGNMENT_TEST_JSON_MODEL))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void assignmentPut() throws Exception {
        mockMvc.perform(put("company/1/assignment/1")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ASSIGNMENT_TEST_JSON_MODEL))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void assignmentDelete() throws Exception {
        mockMvc.perform(delete("company/1/assignment/1"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }
}
