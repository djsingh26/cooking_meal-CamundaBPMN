package com.camunda;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@Deployment(resources = "classpath:cool_meal.bpmn")
public class RecipeWorkerApplication {

	@Autowired
	private ZeebeClient zbclient;

	private final String API_KEY = "8e25792745b8451dbf2b762db6df6d67";
	private String URL = "https://api.spoonacular.com/recipes/random?number=1&apiKey=";

	private Logger logger = LoggerFactory.getLogger(RecipeWorkerApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(RecipeWorkerApplication.class, args);
	}

//	@JobWorker(type="recipeWorker")
//	public void handleJob(final JobClient client, final ActivatedJob job){

//		logger.info("******************** Inside handleJob");
		//Business Logic
//		HttpClient recipeClient = new HttpClient(URL, API_KEY);
//		Recipe recipe = recipeClient.recipeRequest();
//		System.out.println("recipe log "+recipe.getTitle());
//		logger.info("************ Recipe Title:{} ", recipe.getTitle());
//		logger.info("************ Recipe Content:{} " ,recipe.getInstructions());

//		HashMap<String, Object> variables = new HashMap<String, Object>();
//		variables.put("recipe_title", recipe.getTitle());
//		variables.put("recipe_content", recipe.getInstructions());
//
//		zbclient.newCreateInstanceCommand()
//				.bpmnProcessId("Find_Cook_Meal")
//				.latestVersion()
//				.variables(variables).send().join();


//		client.newCompleteCommand(job.getKey()).variables("{\"recipe_title\": \"" + recipe.getTitle() + "\"" + ", \"recipe_content\":" + "\"" + recipe.getInstructions() + "\"" + "}").send().exceptionally((throwable) -> {
//			throw new RuntimeException("Could not complete Job"+ job, throwable);
//		});

//	}


}
