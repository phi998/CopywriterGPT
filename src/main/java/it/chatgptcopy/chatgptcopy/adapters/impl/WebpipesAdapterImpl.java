package it.chatgptcopy.chatgptcopy.adapters.impl;

import it.chatgptcopy.chatgptcopy.adapters.WebpipesAdapter;
import it.chatgptcopy.chatgptcopy.adapters.dto.DataDocument;
import it.chatgptcopy.chatgptcopy.adapters.dto.OntologiesResponse;
import it.chatgptcopy.chatgptcopy.adapters.dto.OntologyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class WebpipesAdapterImpl implements WebpipesAdapter {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoints.url.}")
    private String webpipesBaseUrl;

    @Override
    public List<DataDocument> getDocumentsById(String collectionName, List<String> documentIds) {
        return null;
    }

    @Override
    public List<DataDocument> query(Map<String, String> queryMap) {
        return null;
    }

    @Override
    public OntologiesResponse getOntologies() {
        return null;
    }

    @Override
    public OntologyResponse getOntologyByName(String ontologyName) {
        return null;
    }
}
