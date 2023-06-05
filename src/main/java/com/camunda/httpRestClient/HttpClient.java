package com.camunda.httpRestClient;

import com.camunda.dataModel.Recipe;
import com.camunda.RecipeWorkerApplication;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClient {
    private String API_KEY;
    private String URL;

    public HttpClient(String url, String api_key){
        this.API_KEY = api_key ;
        this.URL = url;
    }

    public Recipe recipeRequest(){
        String response= "";
        try{
            java.net.URL url = new URL(this.URL + this.API_KEY);
            response = this.httpGET(url);
        }catch(Exception var3){
            System.out.println(var3);
        }

        Recipe recipe = this.parseResponseToRecipe(response);
        return recipe;
    }

    public String httpGET(URL url){
        StringBuilder response = new StringBuilder();
        try{
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while((line = bufferedReader.readLine()) != null){
                response.append(line);
            }
            bufferedReader.close();
        }
        catch(Exception var6){
            System.out.println(var6.toString());
        }

        return response.toString();
    }

    private Recipe parseResponseToRecipe(String response) {
        Recipe recipe= new Recipe();
        JSONObject jsonObject = null;
        JSONObject recipeObject = null;

        try{
            jsonObject = new JSONObject(response);
            recipeObject = jsonObject.getJSONArray("recipes").getJSONObject(0);
            recipe.setTitle(recipeObject.getString("title"));
            recipe.setInstructions(recipeObject.getString("instructions"));
            recipe.setSummary(recipeObject.getString("summary"));
            recipe.setReadyInMinutes(recipeObject.getInt("readyInMinutes"));
        }catch(JSONException exception){
            exception.printStackTrace();
        }
        return recipe;
    }

}
