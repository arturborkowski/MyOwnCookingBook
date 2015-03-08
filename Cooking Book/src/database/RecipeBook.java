package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import entities.Ingredient;
import entities.Recipe;

public class RecipeBook implements ISaver, ILoader {

	private List<Recipe> recipies;
	private File savingFile;
	
	
	
	public RecipeBook(File file) {
		this.savingFile = file;
		if(savingFile.exists()) {
			recipies = new ArrayList<Recipe>();
			loadFromFile(savingFile);
		}
		else
			recipies = new ArrayList<Recipe>();
	}
	
	
	
	public File getSavingFile() {
		return this.savingFile;
	}
	
	public void setSavingFile(File file) {
		this.savingFile = file;
	}

	public List<Recipe> getRecipies() {
		return recipies;
	}

	public void setRecipies(List<Recipe> recipies) {
		this.recipies = recipies;
	}
	
	
	
	
	public void showIngredients(int indexOfRecipe) {
		for(Ingredient i: recipies.get(indexOfRecipe).getIngredients()) {
			System.out.println(i);
		}
	}  //EOM
	
	public void showSteps(int indexOFRecipe) {
		int i = 0;
		for(String s: recipies.get(indexOFRecipe).getRecipeSteps()) {
			i++;
			System.out.println(i + ". " + s);
		}
	}  //EOM
	
	
	public void showRecipe(int index) {
		showIngredients(index);
		System.out.println();
		showSteps(index);
	}  //EOM
	
	
	
	public boolean addRecipe(Recipe recipe) {
		if(recipies.add(recipe)) {
			saveIntoFile(savingFile);
			return true;
		}
		else 
			return false;
	}

	@Override
	public boolean saveIntoFile(File file) {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(this.recipies);
			oos.close();
			return true;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}

	}  // EOM



	@SuppressWarnings("unchecked")
	@Override
	public List<Recipe> loadFromFile(File file) {

		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			this.recipies = (ArrayList<Recipe>) ois.readObject();
			ois.close();	
		} catch (Exception e) {e.printStackTrace();}
		
		return this.recipies;
	}
	

}
