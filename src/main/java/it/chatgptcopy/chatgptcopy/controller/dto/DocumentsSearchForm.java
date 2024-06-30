package it.chatgptcopy.chatgptcopy.controller.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DocumentsSearchForm {

    private Map<String,String> query;

    public DocumentsSearchForm() {
        this.query = new HashMap<>();
    }

}
