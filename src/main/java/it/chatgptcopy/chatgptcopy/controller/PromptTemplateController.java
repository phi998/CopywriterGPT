package it.chatgptcopy.chatgptcopy.controller;

import it.chatgptcopy.chatgptcopy.controller.dto.NewPromptTemplateForm;
import it.chatgptcopy.chatgptcopy.domain.PromptTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class PromptTemplateController {

    @Autowired
    private PromptTemplateService promptTemplateService;

    @PostMapping("/templates")
    public String createNewTemplate(
            @ModelAttribute("newPromptTemplateForm") NewPromptTemplateForm newPromptTemplateForm
            ) {
        log.info("createNewTemplate()");

        promptTemplateService.createPromptTemplate(
                newPromptTemplateForm.getCategoryId(),
                newPromptTemplateForm.getName(),
                newPromptTemplateForm.getContent());

        return "redirect:/";
    }

}
