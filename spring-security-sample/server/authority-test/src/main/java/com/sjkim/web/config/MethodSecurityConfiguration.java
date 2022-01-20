package com.sjkim.web.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize 적용
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

}
