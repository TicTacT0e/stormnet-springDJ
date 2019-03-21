//package app.config.beans;
//
//import app.entities.ProjectVersion;
//import app.service.impl.Version;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.core.env.Environment;
//
//@Configuration
//@PropertySource("classpath: project.properties")
//public class PropertiesConfig {
//
//    @Value("${version}")
//    public String version;
//
//    @Autowired
//    Environment environment;
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer configurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//
//    @Bean
//    public ProjectVersion getProjectVersion() {
//        return new Version().getVersion(version);
//    }
//}
