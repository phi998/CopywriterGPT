package it.chatgptcopy.chatgptcopy.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetCategoriesCollectionResponse {

    private List<GetPromptTemplateCategoryResponse> categories;

    public GetCategoriesCollectionResponse() {
        this.categories=new ArrayList<>();
    }

    public void addCategory(GetPromptTemplateCategoryResponse category) {
        this.categories.add(category);
    }
}
