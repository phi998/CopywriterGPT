package it.chatgptcopy.chatgptcopy.adapters.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class QueryResponse {

    private Collection<ResultEntryResponse> documents;

    public QueryResponse() {
        this.documents = new ArrayList<>();
    }

}