package me.myblog.admin.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "me.myblog.framework.domain.entity")
@Configuration
@ComponentScan(basePackages = "me.myblog.framework")
@EnableJpaRepositories(basePackages = "me.myblog.framework.repository")
public class AdminConfig {
}
