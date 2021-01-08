package com.example.cookpad.ui.home;

public class RecipeCard {
    private String username;
    private String recipe_name;
    private int like_counter = 69;
    private String idUser = "";
    private String idRecipe = "";

    public RecipeCard(String username, String recipe_name, int like_counter, String idUser, String idRecipe) {
        this.username = username;
        this.recipe_name = recipe_name;
        this.like_counter = like_counter;
        this.idUser = idUser;
        this.idRecipe = idRecipe;
    }

    public String getUsername() {
        return username;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public int getLike_counter() { return like_counter; }

    public String getIdUser() { return idUser; }

    public String getIdRecipe() { return idRecipe; }
}
