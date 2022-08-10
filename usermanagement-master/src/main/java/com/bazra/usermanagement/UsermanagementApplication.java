package com.bazra.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bazra.usermanagement.repository.UserRepository;

@EnableJpaRepositories(basePackageClasses = UserRepository.class)
//@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UserRepository.class))
//@EnableSwagger2
@SpringBootApplication
//@ComponentScan({"com.bazra.wallet.Services", "com.bazra.wallet.repository"})
public class UsermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermanagementApplication.class, args);
	}

}
