package gui;


import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;



import entities.Ingredient;
import entities.Recipe;

public class ShowWindow {

	JFrame frame;
	Recipe recipe;
	ImageIcon image;
	JLabel lTitle, lImage;
	JTextArea tIngredients, tSteps;
	JScrollPane scrollIngredients, scrollSteps;
	
	public ShowWindow(Recipe recipe) {
		this.recipe = recipe;
		buildShowWindow();
	}

	
	
	public void buildShowWindow() {
		
		frame = new JFrame(recipe.getNameOfTheDish());
		frame.setSize(450, 450);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		
		
		lTitle = new JLabel(recipe.getNameOfTheDish());
		lTitle.setFont(new Font("sansserif", Font.BOLD, 20));
		lTitle.setBounds(30, 10, 200, 40);
		frame.add(lTitle);
		
		/*image = new ImageIcon(recipe.getDishImage().getImage());
		lImage = new JLabel(image);
		lImage.setBounds(10, 55, 230, 180);
		frame.add(lImage);*/
		
		
		tIngredients = new JTextArea();
		tIngredients.setBackground(frame.getBackground());
		tIngredients.setWrapStyleWord(true);
		tIngredients.setLineWrap(true);
		tIngredients.setEditable(false);
		scrollIngredients = new JScrollPane(tIngredients, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollIngredients.setBounds(250, 50, 180, 180);
		scrollIngredients.setBorder(null);
		fillInIngredients();
		
		frame.add(scrollIngredients);
		
		
		
		
		tSteps = new JTextArea();
		tSteps.setBackground(frame.getBackground());
		tSteps.setWrapStyleWord(true);
		tSteps.setLineWrap(true);
		tSteps.setEditable(false);
		scrollSteps = new JScrollPane(tSteps, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSteps.setBounds(5, 240, 420, 160);
		scrollSteps.setBorder(null);
		fillInSteps();
		
		
		frame.add(scrollSteps);
		
		
		frame.setVisible(true);
	}
	
	
	private void fillInIngredients() {
		
		for(Ingredient in: recipe.getIngredients()) {
			tIngredients.append("- " + in.toString() + "\n");
		}
		tIngredients.setCaretPosition(0);

	}
	
	
	private void fillInSteps() {
		
		for(int i = 1; i <= recipe.getRecipeSteps().size(); i++) {
			tSteps.append(i+". "+recipe.getRecipeSteps().get(i-1)+"\n");
		}
		tSteps.setCaretPosition(0);
		
	}
	
	
}
