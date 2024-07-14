package it.chatgptcopy.chatgptcopy.adapters;

import it.chatgptcopy.chatgptcopy.adapters.dto.DataDocument;
import it.chatgptcopy.chatgptcopy.adapters.dto.OntologiesResponse;
import it.chatgptcopy.chatgptcopy.adapters.dto.OntologyResponse;
import it.chatgptcopy.chatgptcopy.adapters.dto.QueryResponse;

import java.util.List;
import java.util.Map;

public interface WebpipesAdapter {

    List<DataDocument> getDocumentsById(String collectionName, List<String> documentIds);

    QueryResponse query(Map<String, List<String>> queryMap, String collectionName, int nResults);

    OntologiesResponse getOntologies();

    OntologyResponse getOntologyByName(String ontologyName);

}
