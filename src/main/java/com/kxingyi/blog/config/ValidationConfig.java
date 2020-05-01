package com.kxingyi.blog.config;

import com.kxingyi.blog.utils.validate.BeanValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {
    @Bean
    public LocalValidatorFactoryBean Validator(){
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public BeanValidator BeanValidator(LocalValidatorFactoryBean Validator){
        BeanValidator.setValidator(Validator);
        return new BeanValidator();
    }

}
