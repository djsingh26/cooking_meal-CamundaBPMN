package com.camunda.process;

import com.camunda.RecipeWorkerApplication;
import com.camunda.dataModel.Recipe;
import com.camunda.httpRestClient.HttpClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RecipeAdapter {

    private final String API_KEY = "Please log in and get ur key :P";
    private String URL = "https://api.spoonacular.com/recipes/random?number=1&apiKey=";

    private Logger logger = LoggerFactory.getLogger(RecipeAdapter.class);

    @EventListener({RecipeWorkerApplication.class})
    @JobWorker
    public Map<String, Object> recipeWorker(){
        HashMap<String, Object> resultsVariables = new HashMap<>();

        logger.info("variable added ... ");

        HttpClient recipeClient = new HttpClient(URL, API_KEY);
        Recipe recipe = recipeClient.recipeRequest();
        System.out.println("recipe log "+recipe.getTitle());
        logger.info("************ Recipe Title:{} ", recipe.getTitle());
        logger.info("************ Recipe Content:{} " ,recipe.getInstructions());

        resultsVariables.put("recipe_title",recipe.getTitle());
        resultsVariables.put("recipe_content",recipe.getInstructions());

        return resultsVariables;

    }

}
