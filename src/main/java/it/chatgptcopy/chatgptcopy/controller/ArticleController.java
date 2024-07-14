package it.chatgptcopy.chatgptcopy.controller;

import it.chatgptcopy.chatgptcopy.controller.dto.DocumentResultEntry;
import it.chatgptcopy.chatgptcopy.controller.dto.DocumentsSelectionForm;
import it.chatgptcopy.chatgptcopy.domain.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/article")
    public String generateArticle(
            @RequestParam("collectionName")String collectionName,
            @RequestParam("templateId")Long templateId,
            @ModelAttribute("selectedDocuments")DocumentsSelectionForm documentsSelectionForm,
            Model model
            ) {
        log.info("generateArticle(): selectedDocuments={}", documentsSelectionForm);

        List<DocumentResultEntry> selectedDocuments = documentsSelectionForm.getDocuments().stream().filter(DocumentResultEntry::isSelected).toList();
        List<String> documentsIds = selectedDocuments.stream().map(DocumentResultEntry::getDocumentId).toList();

        String articleContent = articleService.generateArticle(collectionName, documentsIds, "", templateId).getArticleContent();

        model.addAttribute("articleContent", articleContent);

        return "article";
    }

}
