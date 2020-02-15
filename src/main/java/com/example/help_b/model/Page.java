package com.example.help_b.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private Integer currentPage;
    private Integer lastPage;
    private List<Integer> pages;
}
