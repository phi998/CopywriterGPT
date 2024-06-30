package it.chatgptcopy.chatgptcopy.engine.generator.impl;

import it.chatgptcopy.chatgptcopy.engine.generator.ArticleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ArticleGeneratorImpl implements ArticleGenerator {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String generateArticle(String context, String prompt) {
        return null;
    }
}
