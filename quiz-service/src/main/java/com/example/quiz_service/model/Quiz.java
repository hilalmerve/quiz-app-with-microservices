package com.example.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

//    @ElementCollection
//    private List<Integer> questionIds;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "quiz_questions", joinColumns = @JoinColumn(name = "quiz_id"))
    @OrderColumn(name = "list_order")
    private List<Integer> questionIds = new ArrayList<>();
}
