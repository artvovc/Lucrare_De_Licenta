package com.university.nn.javabased.server.it.web;

import com.lordofthejars.nosqlunit.annotation.CustomComparisonStrategy;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoFlexibleComparisonStrategy;
import com.university.nn.javabased.server.it.AbstractControllerIntegrationTest;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;

import static com.lordofthejars.nosqlunit.core.LoadStrategyEnum.CLEAN_INSERT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@CustomComparisonStrategy(comparisonStrategy = MongoFlexibleComparisonStrategy.class)
@UsingDataSet(locations = "/com/test.json", loadStrategy = CLEAN_INSERT)
@ShouldMatchDataSet(location = "/com/test.json")
@WithUserDetails("asd0")
public class JavaIT extends AbstractControllerIntegrationTest {

    @Test
    public void testatt() throws Exception {
//        MvcResult result = mockMvc.perform(get("/bye")
//                .contentType(APPLICATION_JSON)
//                .with(testSecurityContext()))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertThat(null,notNullValue());
    }
    @Test
    public void testat2(){
    }
}
