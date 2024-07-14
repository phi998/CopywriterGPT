package it.chatgptcopy.chatgptcopy.adapters.impl.mock;

import it.chatgptcopy.chatgptcopy.adapters.WebpipesAdapter;
import it.chatgptcopy.chatgptcopy.adapters.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class MockedWebpipesAdapterImpl implements WebpipesAdapter {
    @Override
    public List<DataDocument> getDocumentsById(String collectionName, List<String> documentIds) {
        List<DataDocument> documents = new ArrayList<>();

        DataDocument doc1 = new DataDocument();
        doc1.getFields().put("id","1");
        doc1.getFields().put("title","mai più");
        doc1.getFields().put("author","lollo84");
        doc1.getFields().put("content","i piatti sono freddi e il cameriere scortese. Non ci tornerò");
        documents.add(doc1);

        DataDocument doc2 = new DataDocument();
        doc1.getFields().put("id","2");
        doc1.getFields().put("title","mi piace");
        doc1.getFields().put("author","pippo99");
        doc1.getFields().put("content","i piatti sono deliziosi e i camerieri, compreso il direttore mi hanno accolto calorosamente. Da provare!");
        documents.add(doc2);

        return documents;
    }

    @Override
    public QueryResponse query(Map<String, List<String>> queryMap, String collectionName, int nResults) {
        QueryResponse response  = new QueryResponse();


        ResultEntryResponse doc1 = new ResultEntryResponse();
        doc1.getColumnName2Content().put("id","1");
        doc1.getColumnName2Content().put("title","mai più");
        doc1.getColumnName2Content().put("author","lollo84");
        doc1.getColumnName2Content().put("content","i piatti sono freddi e il cameriere scortese. Non ci tornerò");
        response.getDocuments().add(doc1);

        ResultEntryResponse doc2 = new ResultEntryResponse();
        doc2.getColumnName2Content().put("id","2");
        doc2.getColumnName2Content().put("title","mi piace");
        doc2.getColumnName2Content().put("author","pippo99");
        doc2.getColumnName2Content().put("content","i piatti sono deliziosi e i camerieri, compreso il direttore mi hanno accolto calorosamente. Da provare!");
        response.getDocuments().add(doc2);

        return response;
    }

    @Override
    public OntologiesResponse getOntologies() {
        OntologiesResponse ontologiesResponse = new OntologiesResponse();

        OntologyResponse ontologyResponse = new OntologyResponse();
        ontologyResponse.setId(1L);
        ontologyResponse.setName("reviews");
        ontologyResponse.getItems().add(new OntologyItem("title","STRING",5,""));
        ontologyResponse.getItems().add(new OntologyItem("author","STRING",5,""));
        ontologyResponse.getItems().add(new OntologyItem("content","STRING",5,""));

        ontologiesResponse.getOntologies().add(ontologyResponse);

        return ontologiesResponse;
    }

    @Override
    public OntologyResponse getOntologyByName(String ontologyName) {
        OntologyResponse ontologyResponse = new OntologyResponse();
        ontologyResponse.setId(1L);
        ontologyResponse.setName("reviews");
        ontologyResponse.getItems().add(new OntologyItem("title","STRING",5,""));
        ontologyResponse.getItems().add(new OntologyItem("author","STRING",5,""));
        ontologyResponse.getItems().add(new OntologyItem("content","STRING",5,""));

        return ontologyResponse;
    }
}
