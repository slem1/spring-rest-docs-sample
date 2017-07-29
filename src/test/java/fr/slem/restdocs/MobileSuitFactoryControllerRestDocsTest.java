package fr.slem.restdocs;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.slem.dto.MobileSuitPostDto;
import fr.slem.model.MobileSuit;
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
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

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
                .andDo(MockMvcRestDocumentation.document("{ClassName}/{methodName}",
                        PayloadDocumentation.responseFields(PayloadDocumentation.fieldWithPath("[]")
                                .description("Array of mobile suits")).andWithPrefix("[]", ModelDescription.mobileSuit())));
    }

    @Test
    public void getById() throws Exception {

        mockMvc.perform(RestDocumentationRequestBuilders.get(MobileSuitFactoryController.API_ROOT_RESOURCE
                + "/{" + MobileSuitFactoryController.PARAM_ID + "}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{ClassName}/{methodName}",
                        PayloadDocumentation.responseFields(ModelDescription.mobileSuit()),
                        RequestDocumentation.pathParameters(
                                RequestDocumentation.parameterWithName(MobileSuitFactoryController.PARAM_ID).description("Mobile suit's id"))));
    }

    @Test
    public void create() throws Exception {
        MobileSuitPostDto mobileSuitPostDto = new MobileSuitPostDto();
        mobileSuitPostDto.setModelName("MS-09RS Rick Dom");
        mobileSuitPostDto.setWeapons(Arrays.asList("Heat Saber", "Scattering Beam Gun", "Machine Gun", "Bazooka"));
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(mobileSuitPostDto);

        mockMvc.perform(RestDocumentationRequestBuilders.post(MobileSuitFactoryController.API_ROOT_RESOURCE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{ClassName}/{methodName}", PayloadDocumentation.requestFields(ModelDescription.mobileSuitPostDto())));

    }

    @Test
    public void searchByModelName() throws Exception {

        final MobileSuit expectedResult = new MobileSuit();
        expectedResult.setId(1L);
        expectedResult.setModelName("RX-78");
        expectedResult.setWeapons(Arrays.asList("Saber", "Rifle"));

        ObjectMapper objectMapper = new ObjectMapper();

        String expectedJson = objectMapper.writeValueAsString(Arrays.asList(expectedResult));

        mockMvc.perform(RestDocumentationRequestBuilders.get(MobileSuitFactoryController.API_ROOT_RESOURCE + MobileSuitFactoryController.SEARCH_RESOURCE)
                .accept(MediaType.APPLICATION_JSON)
                .param(MobileSuitFactoryController.PARAM_MODEL_NAME, "RX"))
                .andExpect(MockMvcResultMatchers.content().json(expectedJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("{ClassName}/{methodName}",
                        RequestDocumentation.requestParameters(
                                RequestDocumentation.parameterWithName(MobileSuitFactoryController.PARAM_MODEL_NAME).description("Mobile suit's model name"))));

    }
}
