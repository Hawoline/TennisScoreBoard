package ru.hawoline.tennisscoreboard.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.hawoline.tennisscoreboard.data.controller.MatchController;
import ru.hawoline.tennisscoreboard.data.repository.HibernateMatchRepository;
import ru.hawoline.tennisscoreboard.data.repository.HibernatePlayerRepository;
import ru.hawoline.tennisscoreboard.domain.CurrentMatchService;
import ru.hawoline.tennisscoreboard.domain.MatchService;

@Configuration
@ComponentScan("ru.hawoline.tennisscoreboard")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/");
        bean.setSuffix(".html");
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.html").addResourceLocations("/WEB-INF/views");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/views/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("Postgres");
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    public HibernateMatchRepository matchesRepository() {
        return new HibernateMatchRepository(entityManager());
    }

    @Bean
    public HibernatePlayerRepository playerRepository() {
        return new HibernatePlayerRepository(entityManager());
    }

    @Bean
    public MatchController matchController() {
        return new MatchController(matchService());
    }

    @Bean
    public CurrentMatchService currentMatchService() {
        return new CurrentMatchService();
    }

    @Bean
    public MatchService matchService() {
        return new MatchService(currentMatchService(), matchesRepository(), playerRepository());
    }
}
