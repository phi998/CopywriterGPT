package it.chatgptcopy.chatgptcopy.adapters;

public interface LlmAdapter {

    String getResponse(String task, String prompt);

}
