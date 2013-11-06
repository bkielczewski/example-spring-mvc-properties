package eu.kielczewski.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@SuppressWarnings("UnusedDeclaration")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
}