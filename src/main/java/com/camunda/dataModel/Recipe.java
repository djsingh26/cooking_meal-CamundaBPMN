package com.camunda.dataModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    private String title;
    private int readyInMinutes;
    private String summary;
    private String instructions;
}
