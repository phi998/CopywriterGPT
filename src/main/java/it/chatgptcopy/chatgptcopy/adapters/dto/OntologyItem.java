package it.chatgptcopy.chatgptcopy.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OntologyItem {

    private String label;

    private String type;

    private int importance; // a parameter that could be used for record linkage

    private String notes;

}
