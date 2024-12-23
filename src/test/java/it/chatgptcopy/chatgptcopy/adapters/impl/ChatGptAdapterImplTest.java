package it.chatgptcopy.chatgptcopy.adapters.impl;

import it.chatgptcopy.chatgptcopy.adapters.LlmAdapter;
import it.chatgptcopy.chatgptcopy.adapters.dto.ChatGptExample;
import it.chatgptcopy.chatgptcopy.adapters.dto.ChatGptRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatGptAdapterImplTest {

    private ChatGptAdapterImpl chatGptAdapterImpl = new ChatGptAdapterImpl();

    @Test
    void testGetResponse() {

        chatGptAdapterImpl.setChatGptBaseUrl("http://localhost:8100/");
        chatGptAdapterImpl.setRestTemplate(new RestTemplate());

        String task = "You are an expert about books";
        String prompt = "I give you some examples about prompts about books, write in output in json format the attributes you" +
                "find in the prompts and their values. These are the attributes I want: title, content, author, year, genre.";
        List<ChatGptExample> examples = new ArrayList<>();
        examples.add(new ChatGptExample("Write an article about orwell 1984","{\"title\":\"1984\", \"author\":\"orwell\",\"content\":null}"));
        examples.add(new ChatGptExample("What about isaacson steve jobs biografy","{\"title\":\"steve jobs\", \"author\":\"isaacson\", \"genre\":\"biography\"}"));

        System.out.println(chatGptAdapterImpl.getResponse(task, prompt, "Write an article about Verga's book: I Malavoglia", examples));
    }
}