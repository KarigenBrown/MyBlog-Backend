package me.myblog.guest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EntityScan(basePackages = "me.myblog.framework.domain.jooq.tables.pojos")
@Configuration
@ComponentScan(basePackages = "me.myblog.framework")
public class GuestConfig {
}
