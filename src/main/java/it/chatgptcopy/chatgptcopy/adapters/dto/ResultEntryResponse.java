package it.chatgptcopy.chatgptcopy.adapters.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultEntryResponse {

    private Map<String, String> columnName2Content;

    public ResultEntryResponse() {
        this.columnName2Content = new HashMap<>();
    }

}
