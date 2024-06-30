package it.chatgptcopy.chatgptcopy.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetPromptTemplateCategoryResponse {

    private Long id;

    private String name;

    private List<GetPromptTemplateResponse> templates;

    public GetPromptTemplateCategoryResponse() {
        this.templates = new ArrayList<>();
    }

    public void addTemplate(GetPromptTemplateResponse template) {
        this.templates.add(template);
    }

}
