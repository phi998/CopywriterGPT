package it.chatgptcopy.chatgptcopy.adapters.impl;

import it.chatgptcopy.chatgptcopy.adapters.LlmAdapter;
import it.chatgptcopy.chatgptcopy.adapters.dto.ChatGptRequest;
import it.chatgptcopy.chatgptcopy.adapters.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChatGptAdapterImpl implements LlmAdapter {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoints.url.chatgpt}")
    private String chatGptBaseUrl;

    @Override
    public String getResponse(String task, String prompt) {
        ChatGptRequest chatGptRequest = new ChatGptRequest();
        chatGptRequest.setTask(task);
        chatGptRequest.setPrompt(prompt);

        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(chatGptBaseUrl + "/chat", chatGptRequest, ChatGptResponse.class);
        ChatGptResponse chatGptResponse = responseEntity.getBody();

        return chatGptResponse.getContent();
    }

}
