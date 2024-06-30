package it.chatgptcopy.chatgptcopy.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PromptTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private PromptCategory category;

    @Column(columnDefinition = "TEXT")
    private String content;

}