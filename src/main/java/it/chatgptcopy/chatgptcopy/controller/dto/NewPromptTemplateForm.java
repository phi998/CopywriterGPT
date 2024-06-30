package it.chatgptcopy.chatgptcopy.controller.dto;

import lombok.Data;

@Data
public class NewPromptTemplateForm {

    private Long categoryId;

    private String name;

    private String content;

}
