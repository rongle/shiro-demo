package com.shiro.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:shiro-config.xml"})
public class ShiroFilterConfigure {
}
