package library.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

@Configuration

public class LibraryConfiguration {
	public static Pageable getPageable() {
		return Pageable.unpaged();
	}
}
