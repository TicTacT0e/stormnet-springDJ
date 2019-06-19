package app.resources;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


public class ValidateApiBySpecification {

    private static final String SWAGGER_JSON_SPEC_PATH = "/swagger/api-spec.json";
    private static final String DEFAULT_CHARACTERS_ENCODING = "UTF-8";

    private <T> MockMvc getMockMvc(final T resource) {
        return MockMvcBuilders.standaloneSetup(resource).build();
    }

    @Test
    public void assignmentGetAll() throws Exception {
        MockMvc mockMvc = getMockMvc(new AssignmentResource());
        mockMvc.perform(get("company/1/assignment"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void assignmentGet() throws Exception {
        MockMvc mockMvc = getMockMvc(new AssignmentResource());
        mockMvc.perform(get("company/1/assignment/1"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void assignmentPost() throws Exception {
        InputStream inputStream = ValidateApiBySpecification.class
                .getResourceAsStream("/validate-swagger-spec"
                        + "/assignment-json-body-test.json");
        String assignmentJsonBody = IOUtils.toString(inputStream);
        MockMvc mockMvc = getMockMvc(new AssignmentResource());
        mockMvc.perform(post("company/1/assignment")
                .characterEncoding(DEFAULT_CHARACTERS_ENCODING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(assignmentJsonBody))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void assignmentPut() throws Exception {
        InputStream inputStream = ValidateApiBySpecification.class
                .getResourceAsStream("/validate-swagger-spec"
                        + "/assignment-json-body-test.json");
        String assignmentJsonBody = IOUtils.toString(inputStream);
        MockMvc mockMvc = getMockMvc(new AssignmentResource());
        mockMvc.perform(put("company/1/assignment/1")
                .characterEncoding(DEFAULT_CHARACTERS_ENCODING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(assignmentJsonBody))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void assignmentDelete() throws Exception {
        MockMvc mockMvc = getMockMvc(new AssignmentResource());
        mockMvc.perform(delete("company/1/assignment/1"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void settingsGet() throws Exception {
        MockMvc mockMvc = getMockMvc(new SettingsResource());
        mockMvc.perform(get("company/1/settings"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void activityGetAll() throws Exception {
        MockMvc mockMvc = getMockMvc(new ActivityResource());
        mockMvc.perform(get("company/1/activity"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void activityGet() throws Exception {
        MockMvc mockMvc = getMockMvc(new ActivityResource());
        mockMvc.perform(get("company/1/activity/1"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void activityPost() throws Exception {
        InputStream inputStream = ValidateApiBySpecification.class
                .getResourceAsStream("/validate-swagger-spec"
                        + "/activity-json-body-test.json");
        String activityJsonBody = IOUtils.toString(inputStream);
        MockMvc mockMvc = getMockMvc(new ActivityResource());
        mockMvc.perform(post("company/1/activity")
                .characterEncoding(DEFAULT_CHARACTERS_ENCODING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(activityJsonBody))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void activityPut() throws Exception {
        InputStream inputStream = ValidateApiBySpecification.class
                .getResourceAsStream("/validate-swagger-spec"
                        + "/activity-json-body-test.json");
        String activityJsonBody = IOUtils.toString(inputStream);
        MockMvc mockMvc = getMockMvc(new ActivityResource());
        mockMvc.perform(put("company/1/activity/1")
                .characterEncoding(DEFAULT_CHARACTERS_ENCODING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(activityJsonBody))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }

    @Test
    public void activityDelete() throws Exception {
        MockMvc mockMvc = getMockMvc(new ActivityResource());
        mockMvc.perform(delete("company/1/activity/1"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH)); 
    }

    @Test
    public void projectGetProfile() throws Exception {
        MockMvc mockMvc = getMockMvc(new ProjectEditPageResources());
        mockMvc.perform(get("company/1/project/1"))
                .andExpect(openApi().isValid(SWAGGER_JSON_SPEC_PATH));
    }
}
