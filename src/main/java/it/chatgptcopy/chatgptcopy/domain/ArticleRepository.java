package it.chatgptcopy.chatgptcopy.domain;

import it.chatgptcopy.chatgptcopy.domain.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

}
