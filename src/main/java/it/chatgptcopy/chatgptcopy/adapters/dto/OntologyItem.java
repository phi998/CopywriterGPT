package it.chatgptcopy.chatgptcopy.adapters.dto;

import lombok.Data;

@Data
public class OntologyItem {

    private String label;

    private String type;

    private int importance; // a parameter that could be used for record linkage

    private String notes;

}
