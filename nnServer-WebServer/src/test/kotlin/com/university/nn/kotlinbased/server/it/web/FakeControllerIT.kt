//package com.university.nn.kotlinbased.server.it.web
//
//import com.university.nn.kotlinbased.server.it.AbstractControllerIntegrationTest
//import org.hamcrest.MatcherAssert.assertThat
//import org.hamcrest.Matchers.notNullValue
//import org.junit.Test
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
//
//class FakeControllerIT : AbstractControllerIntegrationTest() {
//
//    @Test
//    @Throws(Exception::class)
//    fun test1() {
//        mockMvc!!.perform(get("/hello").with(user("ad").password("ad")))
//        assertThat(null, notNullValue())
//    }
//
//
//}
