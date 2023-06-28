package com.binga.binga;

import com.binga.binga.entities.RoleUser;
import com.binga.binga.entities.User;
import com.binga.binga.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class BingaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BingaApplication.class, args);
    }
  /*  @Bean
    CommandLineRunner start(UserService userService){
        return  args -> {
            userService.AddUser(new User(null,"user1","12341", RoleUser.Admin));
            userService.AddUser(new User(null,"user2","12342",RoleUser.Admin));
            userService.AddUser(new User(null,"user3","12343",RoleUser.Admin));
            userService.AddUser(new User(null,"user4","12344",RoleUser.Admin));
            userService.AddUser(new User(null,"user5","12345",RoleUser.Admin));
            userService.AddUser(new User(null,"user6","12346",RoleUser.Admin));
            userService.AddUser(new User(null,"user7","12347",RoleUser.Admin));
        };
    }*/

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

}
