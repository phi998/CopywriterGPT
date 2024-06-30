package it.chatgptcopy.chatgptcopy.domain.vo;

import lombok.Data;

@Data
public class PromptTemplateCreatedResponse {

    private Long id;

    private String name;

    private Long categoryId;

    private String category;

}
