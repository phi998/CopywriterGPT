package it.chatgptcopy.chatgptcopy.adapters.impl;

import it.chatgptcopy.chatgptcopy.adapters.WebpipesAdapter;
import it.chatgptcopy.chatgptcopy.adapters.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class WebpipesAdapterImpl implements WebpipesAdapter {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoints.url.webpipes}")
    private String webpipesBaseUrl;

    @Override
    public List<DataDocument> getDocumentsById(String collectionName, List<String> documentIds) {
        Map<String,List<String>> query = new HashMap<>();
        query.put("id", documentIds);

        List<DataDocument> documents = new ArrayList<>();

        QueryResponse qr = this.query(query, collectionName, documentIds.size());
        for(ResultEntryResponse doc: qr.getDocuments()) {
            DataDocument document = new DataDocument();
            document.setFields(new HashMap<>(doc.getColumnName2Content()));
            documents.add(document);
        }

        return documents;
    }

    @Override
    public QueryResponse query(Map<String, List<String>> query, String collectionName, int n) {
        log.info("query(): collectionName={}, query={}, n={}", collectionName, query, n);

        query.remove("promptTemplate");
        query.put("n", List.of(String.valueOf(n)));
        query.put("collectionName", List.of(collectionName));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = webpipesBaseUrl + "/query";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, List<String>> entry : query.entrySet()) {
            builder.queryParam(entry.getKey(), String.join(",", entry.getValue()));
        }
        url = builder.build().toUriString();

        log.info("getTopResults(): url={}", url);

        ResponseEntity<QueryResponse> responseEntity = restTemplate.getForEntity(url, QueryResponse.class);

        return responseEntity.getBody();
    }

    @Override
    public OntologiesResponse getOntologies() {
        String url = webpipesBaseUrl + "/ontology/";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<OntologiesResponse> responseEntity =
                restTemplate.getForEntity(url, OntologiesResponse.class);

        return responseEntity.getBody();
    }

    @Override
    public OntologyResponse getOntologyByName(String ontologyName) {
        String url = webpipesBaseUrl + "/ontology?name=" + ontologyName;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<OntologiesResponse> responseEntity =
                restTemplate.getForEntity(url, OntologiesResponse.class);

        return responseEntity.getBody().getOntologies().get(0);
    }
}
