package it.chatgptcopy.chatgptcopy.controller.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DocumentResultEntry {

    private boolean selected;

    private String documentId;

    private Map<String, String> label2Content;

    public DocumentResultEntry() {
        this.label2Content = new HashMap<>();
    }

}
