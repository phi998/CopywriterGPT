package it.chatgptcopy.chatgptcopy.adapters.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DataDocument {

    private Map<String,String> fields;

    public DataDocument() {
        this.fields = new HashMap<>();
    }

    @JsonIgnore
    public String getId() {
        return fields.get("id");
    }

}
