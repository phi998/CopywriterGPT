package it.chatgptcopy.chatgptcopy.adapters;

import it.chatgptcopy.chatgptcopy.adapters.dto.ChatGptExample;

import java.util.List;

public interface LlmAdapter {

    String getResponse(String task, String prompt);

    String getResponse(String task, String prompt, String promptInput, List<ChatGptExample> examples);

}
