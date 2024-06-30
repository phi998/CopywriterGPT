package it.chatgptcopy.chatgptcopy.adapters;

import it.chatgptcopy.chatgptcopy.adapters.dto.DataDocument;
import it.chatgptcopy.chatgptcopy.adapters.dto.OntologiesResponse;
import it.chatgptcopy.chatgptcopy.adapters.dto.OntologyResponse;

import java.util.List;
import java.util.Map;

public interface WebpipesAdapter {

    List<DataDocument> getDocumentsById(String collectionName, List<String> documentIds);

    List<DataDocument> query(Map<String, String> queryMap);

    OntologiesResponse getOntologies();

    OntologyResponse getOntologyByName(String ontologyName);

}
