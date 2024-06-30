package it.chatgptcopy.chatgptcopy.domain;

import it.chatgptcopy.chatgptcopy.adapters.WebpipesAdapter;
import it.chatgptcopy.chatgptcopy.adapters.dto.DataDocument;
import it.chatgptcopy.chatgptcopy.domain.model.PromptTemplate;
import it.chatgptcopy.chatgptcopy.domain.vo.GenerateArticleResponse;
import it.chatgptcopy.chatgptcopy.engine.filler.TemplateFiller;
import it.chatgptcopy.chatgptcopy.engine.filler.impl.TemplateFillerImpl;
import it.chatgptcopy.chatgptcopy.engine.generator.ArticleGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ArticleService {

    @Autowired
    private WebpipesAdapter webpipesAdapter;

    @Autowired
    private PromptTemplateService promptTemplateService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleGenerator articleGenerator;

    private static final String EMPTY_STRING = "";

    public GenerateArticleResponse generateArticle(String collectionName, List<String> documentIds, String articleType, Long promptTemplateId) {
        log.info("generateArticle(): documentIds={}, promptTemplateId={}", documentIds, promptTemplateId);

        List<DataDocument> documents = webpipesAdapter.getDocumentsById(collectionName, documentIds);

        PromptTemplate promptTemplate = promptTemplateService.getPromptTemplateById(promptTemplateId);

        TemplateFiller templateFiller = new TemplateFillerImpl();
        String prompt = templateFiller.fillTemplate(promptTemplate.getContent(), toCompactDocuments(documents, "\n\n"));

        String articleContent = articleGenerator.generateArticle(promptTemplate.getCategory().getName(), prompt);

        GenerateArticleResponse generateArticleResponse = new GenerateArticleResponse();
        generateArticleResponse.setArticleContent(articleContent);

        log.info("generateArticle(): generateArticleResponse={}", generateArticleResponse);

        return generateArticleResponse;
    }

    /**
     *
     * PRIVATE METHODS
     *
     * **/

    private static Map<String,String> toCompactDocuments(List<DataDocument> documents, String separator) {
        Map<String,String> compactDocuments = new HashMap<>();

        for(DataDocument document: documents) {
            for(Map.Entry<String, String> fieldName2content: document.getFields().entrySet()) {
                String fieldName = fieldName2content.getKey();
                String content = fieldName2content.getValue();

                compactDocuments.putIfAbsent(fieldName, EMPTY_STRING);
                compactDocuments.put(fieldName, compactDocuments.get(fieldName).equals(EMPTY_STRING)?content:separator+content);
            }
        }

        return compactDocuments;
    }

}
