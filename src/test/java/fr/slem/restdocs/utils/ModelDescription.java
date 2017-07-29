package fr.slem.restdocs.utils;

import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;

public class ModelDescription {

    private ModelDescription(){}

    /**
     * @return The full mobile suit description
     */
    public static FieldDescriptor[] mobileSuit() {
        return new FieldDescriptor[]{
                PayloadDocumentation.fieldWithPath("id").description("The mobile suit's id"),
                PayloadDocumentation.fieldWithPath("modelName").description("The mobile suit's model name"),
                PayloadDocumentation.fieldWithPath("weapons").description("Array of mobile suit's weapons")
        };
    }
}
