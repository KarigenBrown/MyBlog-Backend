package me.myblog.guest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(basePackages = "me.myblog.framework.domain.entity")
@Configuration
@ComponentScan(basePackages = "me.myblog.framework")
@EnableScheduling
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "me.myblog.framework.repository")
public class GuestConfig {

}
