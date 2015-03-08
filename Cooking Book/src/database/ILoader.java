package database;

import java.io.File;
import java.util.List;

import entities.Recipe;

public interface ILoader {

	public List<Recipe> loadFromFile(File file);
}
