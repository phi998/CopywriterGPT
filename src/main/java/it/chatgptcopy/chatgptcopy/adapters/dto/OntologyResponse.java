package it.chatgptcopy.chatgptcopy.adapters.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OntologyResponse {

    private Long id;

    private String name;

    private List<OntologyItem> items;

    public OntologyResponse() {
        this.items = new ArrayList<>();
    }

}
