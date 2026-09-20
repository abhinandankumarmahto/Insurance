package com.avit.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "plan-api")
public class AppProps 
{
	Map<String, String> messages=new HashMap<>();
}
