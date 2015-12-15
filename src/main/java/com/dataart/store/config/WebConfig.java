package com.dataart.store.config;

import com.dataart.store.auxiliary.HeaderFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
public class WebConfig
{
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new StandardPasswordEncoder();
	}
	
	@Bean
	public FilterRegistrationBean headerFilter()
	{
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.addUrlPatterns("*.htm");
		registrationBean.setFilter(new HeaderFilter());
		return registrationBean;
	}
}
