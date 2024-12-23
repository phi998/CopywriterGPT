package it.chatgptcopy.chatgptcopy.adapters.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGptRequest {

    private String task;

    private String prompt;

    private String promptInput;

    private List<ChatGptExample> examples = new ArrayList<>();

}
