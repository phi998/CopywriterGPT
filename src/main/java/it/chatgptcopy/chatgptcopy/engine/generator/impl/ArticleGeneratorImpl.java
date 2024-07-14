package it.chatgptcopy.chatgptcopy.engine.generator.impl;

import it.chatgptcopy.chatgptcopy.adapters.dto.ChatGptRequest;
import it.chatgptcopy.chatgptcopy.adapters.dto.ChatGptResponse;
import it.chatgptcopy.chatgptcopy.engine.generator.ArticleGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class ArticleGeneratorImpl implements ArticleGenerator {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoints.url.chatgpt}")
    private String chatgptEndopint;

    @Override
    public String generateArticle(String context, String prompt) {
        log.info("generateArticle(): context={}, prompt={}",context,prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ChatGptRequest chatGptRequest = new ChatGptRequest();
        chatGptRequest.setTask(context);
        chatGptRequest.setPrompt(prompt);

        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(chatgptEndopint + "/chat", chatGptRequest, ChatGptResponse.class);
        ChatGptResponse chatGptResponse = responseEntity.getBody();

        return chatGptResponse.getContent();
    }
}
