package com.university.nn.kotlinbased.server.it

import com.university.nn.kotlinbased.server.it.config.IntegrationTestConfig
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.security.web.FilterChainProxy
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import javax.annotation.Resource


@RunWith(SpringJUnit4ClassRunner::class)
@WebAppConfiguration
@ContextConfiguration(classes = arrayOf(IntegrationTestConfig::class))
@TestExecutionListeners(DependencyInjectionTestExecutionListener::class,
        DirtiesContextTestExecutionListener::class,
        TransactionalTestExecutionListener::class,
        WithSecurityContextTestExecutionListener::class)
open class AbstractControllerIntegrationTest {
    @Autowired
    private val wab: WebApplicationContext? = null

    var mockMvc: MockMvc? = null

    @Resource
    private val springSecurityFilterChain: FilterChainProxy? = null

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wab!!)
                .addFilter<DefaultMockMvcBuilder>(springSecurityFilterChain)
                .apply<DefaultMockMvcBuilder>(springSecurity(springSecurityFilterChain))
                .build()
    }
}