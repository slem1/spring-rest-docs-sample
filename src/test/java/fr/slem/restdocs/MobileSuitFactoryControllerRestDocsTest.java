package fr.slem.restdocs;

import fr.slem.rest.MobileSuitFactoryController;
import fr.slem.restdocs.utils.ModelDescription;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author slemoine
 */
@RunWith(SpringRunner.class)
public class MobileSuitFactoryControllerRestDocsTest {

    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();


    @Before
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(new MobileSuitFactoryController())
                .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get(MobileSuitFactoryController.API_ROOT_RESOURCE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{ClassName}/{methodName}"));
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get(MobileSuitFactoryController.API_ROOT_RESOURCE+"/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{ClassName}/{methodName}",
                        PayloadDocumentation.responseFields(ModelDescription.mobileSuit())));
    }
}
