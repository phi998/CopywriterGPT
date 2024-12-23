package it.chatgptcopy.chatgptcopy.adapters.impl;

import it.chatgptcopy.chatgptcopy.adapters.LlmAdapter;
import it.chatgptcopy.chatgptcopy.adapters.dto.ChatGptExample;
import it.chatgptcopy.chatgptcopy.adapters.dto.ChatGptRequest;
import it.chatgptcopy.chatgptcopy.adapters.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatGptAdapterImpl implements LlmAdapter {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoints.url.chatgpt}")
    private String chatGptBaseUrl;

    @Override
    public String getResponse(String task, String prompt) {
        return this.getResponse(task,prompt,null,null);
    }

    @Override
    public String getResponse(String task, String prompt, String promptInput, List<ChatGptExample> examples) {
        ChatGptRequest chatGptRequest = new ChatGptRequest();
        chatGptRequest.setTask(task);
        chatGptRequest.setPrompt(prompt);
        chatGptRequest.setPromptInput(promptInput);
        chatGptRequest.setExamples(examples);

        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(chatGptBaseUrl + "/chat", chatGptRequest, ChatGptResponse.class);
        ChatGptResponse chatGptResponse = responseEntity.getBody();

        return chatGptResponse.getContent();
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setChatGptBaseUrl(String chatGptBaseUrl) {
        this.chatGptBaseUrl = chatGptBaseUrl;
    }
}
