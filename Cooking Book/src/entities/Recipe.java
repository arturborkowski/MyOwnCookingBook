package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6262092145117691132L;
	private String nameOfTheDish;
	private List<Ingredient> ingredients;
	private List<String> recipeSteps;


	
	
	public Recipe() {
		recipeSteps = new ArrayList<String>();
		ingredients = new ArrayList<Ingredient>();
	}
	
	public Recipe(String name) {
		this.nameOfTheDish = name;
		recipeSteps = new ArrayList<String>();
		ingredients = new ArrayList<Ingredient>();
		
	}
	
	public Recipe(String name, List<Ingredient> ingredients, List<String> steps) {
		this.nameOfTheDish = name;
		this.ingredients = ingredients;
		this.recipeSteps = steps;
	}
	
	/*public Recipe(String name, List<Ingredient> ingredients, List<String> steps, DishImage image) {
		this.nameOfTheDish = name;
		this.ingredients = ingredients;
		this.recipeSteps = steps;

	}*/
	
	
	
	
	
	public String getNameOfTheDish() {
		return nameOfTheDish;
	}
	public void setNameOfTheDish(String nameOfTheDish) {
		this.nameOfTheDish = nameOfTheDish;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public List<String> getRecipeSteps() {
		return recipeSteps;
	}
	public void setRecipeSteps(List<String> recipeSteps) {
		this.recipeSteps = recipeSteps;
	}
	
	
	
	
	
	

	
	
	
}
