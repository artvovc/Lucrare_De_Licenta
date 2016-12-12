package com.university.nn.javabased.server.it;

import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.university.nn.javabased.server.it.config.IntegrationTestConfig;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Created by Artemie on 03.12.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {IntegrationTestConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public abstract class AbstractControllerIntegrationTest {
    @Rule
    @Autowired
    public MongoDbRule mongoDbRule;
    @Autowired
    protected MongoOperations mongoOperations;
    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wab;
    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wab)
                .addFilter(springSecurityFilterChain)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }
}
