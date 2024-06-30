package it.chatgptcopy.chatgptcopy.adapters.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OntologiesResponse {

    private List<OntologyResponse> ontologies;

    public OntologiesResponse() {
        this.ontologies = new ArrayList<>();
    }

}
