package com.database.ana.javer;

import com.database.ana.javer.config.SecurityConfig;
import com.database.ana.javer.controller.ClienteController;
import com.database.ana.javer.service.ClienteService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DatabaseServiceApplicationTests {//verificando contextos

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {assertThat(applicationContext).isNotNull();}

    @Test
    void clienteControllerBeanExists() {assertThat(applicationContext.getBean(ClienteController.class)).isNotNull();}

    @Test
    void clienteServiceBeanExists() {assertThat(applicationContext.getBean(ClienteService.class)).isNotNull();}

    @Test
    void securityConfigBeanExists() {assertThat(applicationContext.getBean(SecurityConfig.class)).isNotNull(); }
}
