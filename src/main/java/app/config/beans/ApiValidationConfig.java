package app.config.beans;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.io.IOException;

@Configuration
@Profile("timesheet_validate")
public class ApiValidationConfig implements WebMvcConfigurer {

    private OpenApiValidationInterceptor validationInterceptor;

    @Autowired
    public ApiValidationConfig() throws IOException {
        this.validationInterceptor = new OpenApiValidationInterceptor(
                OpenApiInteractionValidator
                        .createFor("/swagger/api-spec.yaml").build());
    }

    @Bean
    public Filter validationFilter() {
        return new OpenApiValidationFilter(true, true);
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(validationInterceptor);
    }
}
