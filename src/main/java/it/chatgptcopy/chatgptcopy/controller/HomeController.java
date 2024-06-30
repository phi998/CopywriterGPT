package it.chatgptcopy.chatgptcopy.controller;

import it.chatgptcopy.chatgptcopy.adapters.WebpipesAdapter;
import it.chatgptcopy.chatgptcopy.adapters.dto.OntologiesResponse;
import it.chatgptcopy.chatgptcopy.adapters.dto.OntologyItem;
import it.chatgptcopy.chatgptcopy.adapters.dto.OntologyResponse;
import it.chatgptcopy.chatgptcopy.controller.dto.NewPromptTemplateForm;
import it.chatgptcopy.chatgptcopy.domain.PromptCategoryService;
import it.chatgptcopy.chatgptcopy.domain.model.PromptCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private PromptCategoryService promptCategoryService;

    @Autowired
    private WebpipesAdapter webpipesAdapter;

    @GetMapping("/")
    public String home(Model model) {
        log.info("HomeController - home()");

        OntologiesResponse ontologies = webpipesAdapter.getOntologies();
        Map<String,List<String>> ontology2items = new HashMap<>();
        ontologies.getOntologies().forEach(o -> ontology2items.put(o.getName(), o.getItems().stream().map(OntologyItem::getLabel).toList()));

        List<PromptCategory> categories = promptCategoryService.getAllCategories();
        model.addAttribute("newPromptForm", new NewPromptTemplateForm());
        model.addAttribute("categories", categories);
        model.addAttribute("ontologiesMap", ontology2items);

        return "home";
    }

    @GetMapping("/refresh")
    public String refresh() {

        OntologiesResponse ontologies = webpipesAdapter.getOntologies();
        List<String> ontologiesNames = new ArrayList<>(ontologies.getOntologies().stream().map(OntologyResponse::getName).toList());
        List<PromptCategory> categories = this.promptCategoryService.getAllCategories();
        List<String> categoriesNames = categories.stream().map(PromptCategory::getName).toList();

        ontologiesNames.removeAll(categoriesNames);
        for(String remainingOntologyName: ontologiesNames) { // TODO Optimize with single INSERT
            this.promptCategoryService.createNewPromptCategory(remainingOntologyName);
        }

        return "redirect:/";
    }

}
