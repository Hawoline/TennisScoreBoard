package ru.hawoline.tennisscoreboard.data;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import ru.hawoline.tennisscoreboard.TennisScoreBoardApplication;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TennisScoreBoardApplication.class);
	}

}
