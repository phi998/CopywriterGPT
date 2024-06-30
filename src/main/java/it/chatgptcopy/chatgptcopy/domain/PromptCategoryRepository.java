package it.chatgptcopy.chatgptcopy.domain;

import it.chatgptcopy.chatgptcopy.domain.model.PromptCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PromptCategoryRepository extends CrudRepository<PromptCategory, Long> {

    List<PromptCategory> findAllByName(String name);

}
