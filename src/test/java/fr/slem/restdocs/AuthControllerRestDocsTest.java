package fr.slem.restdocs;

import fr.slem.rest.AuthController;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class AuthControllerRestDocsTest {

    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Before
    public void before() {

        mockMvc = MockMvcBuilders.standaloneSetup(new AuthController())
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void tokens() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get(AuthController.API_ROOT_RESOURCE + AuthController.TOKEN_RESOURCE)
                .accept(MediaType.APPLICATION_JSON).header(AuthController.AUTHORIZATION_HEADER, "Basic QWxhZGRpbjpPcGVuU2VzYW1l"))
                .andDo(MockMvcRestDocumentation.document("{ClassName}/{methodName}",
                        HeaderDocumentation.requestHeaders(
                                HeaderDocumentation.headerWithName(AuthController.AUTHORIZATION_HEADER).description("Basic Authorization header"))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
