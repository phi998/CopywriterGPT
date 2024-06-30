package it.chatgptcopy.chatgptcopy.controller;

import it.chatgptcopy.chatgptcopy.adapters.WebpipesAdapter;
import it.chatgptcopy.chatgptcopy.adapters.dto.DataDocument;
import it.chatgptcopy.chatgptcopy.adapters.dto.OntologyItem;
import it.chatgptcopy.chatgptcopy.controller.dto.DocumentsSearchForm;
import it.chatgptcopy.chatgptcopy.controller.dto.DocumentsSelectionForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class DataController {

    private final static String TABLE_NAME_KEY = "collectionName";
    private final static String PROMPT_TEMPLATE_KEY = "promptTemplate";
    private final static String N_RESULTS_KEY = "n";
    private final static int DEFAULT_N_RESULTS = 20;

    @Autowired
    private WebpipesAdapter webpipesAdapter;

    @GetMapping("/query")
    public String getDocuments(@RequestParam Map<String,String> queryParams,
                               @ModelAttribute("documentsSearchForm") DocumentsSearchForm documentsSearchForm,
                               Model model) {
        log.info("getDocuments(): params={}", queryParams);

        int nResults = Integer.parseInt(queryParams.get(N_RESULTS_KEY));
        String collectionName = queryParams.get(TABLE_NAME_KEY);
        Long promptTemplateId = Long.parseLong(queryParams.get(PROMPT_TEMPLATE_KEY));

        List<String> labels = webpipesAdapter.getOntologyByName(collectionName).getItems().stream().map(OntologyItem::getLabel).toList();
        for(String label:labels) { documentsSearchForm.getQuery().putIfAbsent(label, ""); }
        documentsSearchForm.getQuery().putIfAbsent(N_RESULTS_KEY, String.valueOf(DEFAULT_N_RESULTS));

        List<DataDocument> documents = this.webpipesAdapter.query(queryParams);

        model.addAttribute("documentsSearchForm", documentsSearchForm);
        model.addAttribute("documentsForm", toDocumentsSelectionForm(documents));
        model.addAttribute("promptTemplateId", promptTemplateId);

        return "select-data";
    }

    /** PRIVATE METHODS */

    private DocumentsSelectionForm toDocumentsSelectionForm(List<DataDocument> documents) {
        DocumentsSelectionForm dsf = new DocumentsSelectionForm();
        documents.forEach(d -> dsf.addDocument(d.getId(), d.getFields()));

        return dsf;
    }

}
