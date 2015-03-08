package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;



import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import database.RecipeBook;
import entities.Ingredient;
import entities.Recipe;

public class AddRecipeWindow implements WindowListener{

	private File dataFile;
	/*private String[] validExtensions = {".jpg", ".jpeg", ".png"};*/
	private Recipe newRecipe;
	private RecipeBook cookingBook;
	private JFrame frame;
	private JLabel lName, lImageFileName, lIngrList, lStepList;
	private JTextField tName;;
	private JList<String> listOfIngredients;
	private JList<String> listOfSteps;
	private JScrollPane scrollIngred, scrollSteps;
	private JButton  /*bChooseImage,*/ bAddRecipe, bBack;
	DefaultListModel<String> listModel, listModel2;
	
	private JLabel lNameOfIngredient, lAmount, lUnit;
	private JTextField tNameOfIngredient, tAmount, tUnit;
	private JButton bAddIngredient, bDeleteIngredient;
	
	private JLabel lNewStep;
	private JTextArea tNewStep;
	private JButton bAddStep, bDeleteStep;
	


	
	

	
	public AddRecipeWindow(File dataFile) {
		/*imagesDir = new File("C:/Users/Public/Documents/CookingBook/images");
		if(!imagesDir.exists()) {
			imagesDir.mkdir();
		}*/
		this.dataFile = dataFile;
		this.cookingBook = new RecipeBook(dataFile);
		newRecipe = new Recipe();
		buildAddRecipeWindow(cookingBook);
	}
	
	
	
	
	
	public void buildAddRecipeWindow(RecipeBook book) {
		
		frame = new JFrame("Add recipe");
		frame.setSize(500, 550);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.addWindowListener(this);
		frame.setResizable(false);

		
		// Dish name label
		lName = new JLabel("Name of the dish:");
		lName.setBounds(40, 10, 100, 30);
		frame.add(lName);
		
		// Dish name TextField
		tName = new JTextField(15);
		tName.setBounds(160, 10, 250, 30);
		tName.setFont(new Font("sansserif", Font.ITALIC, 18));
		frame.add(tName);
		
		  // choose image button
		/*bChooseImage = new JButton("Choose image");
		bChooseImage.setBounds(40, 60, 120, 20);
		bChooseImage.addActionListener(new ChooseImageButtonListener());
		frame.add(bChooseImage);*/
		
		  // image path label
		lImageFileName = new JLabel("(nie wybrano)");
		lImageFileName.setBounds(170, 60, 150, 20);
		frame.add(lImageFileName);
		
		
		// list of ingredients label
		lIngrList = new JLabel("LIST OF INGREDIENTS");
		lIngrList.setBounds(45, 120, 150, 20);
		frame.add(lIngrList);
		
		
		listModel = new DefaultListModel<String>();
		listOfIngredients = new JList<String>(listModel);
		
		// adding list to a scroll pane
		scrollIngred = new JScrollPane(listOfIngredients,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollIngred.setBounds(10, 145, 200, 210);
		frame.add(scrollIngred);
		
		
		
		
		// list of steps label
		lStepList = new JLabel("LIST OF STEPS");
		lStepList.setBounds(300, 120, 150, 20);
		frame.add(lStepList);
		
		
		
		listModel2 = new DefaultListModel<String>();
		listOfSteps = new JList<String>(listModel2);
		listOfSteps.setCellRenderer(new MyCellRenderer(180));
		
		
		scrollSteps = new JScrollPane(listOfSteps,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSteps.setBounds(220, 145, 250, 210);
		frame.add(scrollSteps);
		
		
		
		// BUTTONS //
		
		lNameOfIngredient = new JLabel("Ingredient:", SwingConstants.RIGHT);
		lNameOfIngredient.setBounds(5, 360, 65, 20);
		frame.add(lNameOfIngredient);
		
		tNameOfIngredient = new JTextField(20);
		tNameOfIngredient.setBounds(75, 360, 120, 20);
		frame.add(tNameOfIngredient);
		
		
		lAmount = new JLabel("Amount:", SwingConstants.RIGHT);
		lAmount.setBounds(5, 385, 50, 20);
		frame.add(lAmount);
		
		tAmount = new JTextField(20);
		tAmount.setBounds(60, 385, 40, 20);
		frame.add(tAmount);
		
		
		lUnit = new JLabel("Unit:", SwingConstants.RIGHT);
		lUnit.setBounds(115, 385, 30, 20);
		frame.add(lUnit);
		
		tUnit = new JTextField(20);
		tUnit.setBounds(150, 385, 45, 20);
		tUnit.addActionListener(new AddIngredientButtonListener());
		frame.add(tUnit);
		
		bAddIngredient = new JButton("Add");
		bAddIngredient.setBounds(15, 410, 60, 20);
		bAddIngredient.addActionListener(new AddIngredientButtonListener());
		frame.add(bAddIngredient);
		
		bDeleteIngredient = new JButton("Delete");
		bDeleteIngredient.setBounds(80, 410, 100, 20);
		bDeleteIngredient.addActionListener(new DeleteIngredientButtonListener());
		frame.add(bDeleteIngredient);
		
		
		
		
		lNewStep = new JLabel("Next step:");
		lNewStep.setBounds(300, 360, 100, 20);
		frame.add(lNewStep);
		
		
		tNewStep = new JTextArea(2, 20);
		tNewStep.setLineWrap(true);
		tNewStep.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(tNewStep,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(230, 380, 230, 35);
		frame.add(scroll);
		
		
		bAddStep = new JButton("Add step");
		bAddStep.setBounds(230, 420, 100, 20);
		bAddStep.addActionListener(new AddNewStepButtonListener());
		frame.add(bAddStep);
		
		
		bDeleteStep = new JButton("Delete step");
		bDeleteStep.setBounds(335, 420, 125, 20);
		bDeleteStep.addActionListener(new DeleteNewStepListener());
		frame.add(bDeleteStep);
		
		
		
		bAddRecipe = new JButton("ADD RECIPE");
		bAddRecipe.setBounds(170, 450, 260, 50);
		bAddRecipe.addActionListener(new AddRecipeButtonListener());
		frame.add(bAddRecipe);

		bBack = new JButton("BACK");
		bBack.setBounds(60, 450, 100, 50);
		bBack.addActionListener(new BackButtonListener());
		frame.add(bBack);
		
		
		
		
		
		frame.setVisible(true);
		
	}


	
	
	//----------------   BUTTON LISTENERS --------------------//
	

	
/*	private class ChooseImageButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser fc = new JFileChooser();
			int choise = fc.showOpenDialog(frame);
			
			if(choise == JFileChooser.APPROVE_OPTION) {
				sourceImage = fc.getSelectedFile();
				boolean extCheck = false;
				
				for(int i = 0; i < validExtensions.length; i++) {
					
					if(sourceImage.getName().endsWith(validExtensions[i])) {
						extCheck = true;
						break;
					}
					
				}
				
				if(extCheck) {
					destImage = new File(imagesDir.getAbsolutePath() + "/" + sourceImage.getName());
					lImageFileName.setText(sourceImage.getName());
					newRecipe.setDishImage(new DishImage(destImage));

				}
				else {
					JOptionPane.showMessageDialog(fc, "You need to choose a proper file with an .jpg, .jpeg or .png extension!",
							"Extension error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
	}  // eoic
	*/
	
	
	
	private class AddIngredientButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(!tNameOfIngredient.getText().isEmpty() && !tAmount.getText().isEmpty() && !tUnit.getText().isEmpty()) {
				
				String name, unit;
				double amount = 0;
				
				name = tNameOfIngredient.getText();
				unit = tUnit.getText();
				
				try {
					amount = Double.parseDouble(tAmount.getText());
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Value of amount must be numeral", "Warning", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Ingredient ingr = new Ingredient(name, unit, amount);
				newRecipe.getIngredients().add(ingr);
				listModel.addElement(ingr.toString());
				cleanIngr();
				
			} else {
				JOptionPane.showMessageDialog(frame, "You must fill in all of the ingredient info", "Warning", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			
		}
		
	}   //  End of inner class
	
	
	private class DeleteIngredientButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		
			String selectedItem = listOfIngredients.getSelectedValue();
			for(Ingredient i: newRecipe.getIngredients()) {
				if(selectedItem.equals(i.toString())) {
					newRecipe.getIngredients().remove(i);
					break;
				}
			}
			listModel.remove(listOfIngredients.getSelectedIndex());
			
		}
		
	}    // EOIC
	
	
	
	private class AddNewStepButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String newStep = tNewStep.getText();
			newRecipe.getRecipeSteps().add(newStep);
			listModel2.addElement("- "+newStep);
			cleanStep();
			
		}
		
	}
	
	
	
	private class DeleteNewStepListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			

			String selectedItem = listOfSteps.getSelectedValue();
			for(String s: newRecipe.getRecipeSteps()) {
				if(selectedItem.equals(s)) {
					newRecipe.getRecipeSteps().remove(s);
					break;
				}
			}
			listModel2.remove(listOfSteps.getSelectedIndex());

		}
		
	}
	
	
	private class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			new MainWindow();
		}
		
	}   // end of inner class
	
	
	
	
	private class AddRecipeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(!tName.getText().isEmpty()) {
				if(!newRecipe.getIngredients().isEmpty()) {
					if(!newRecipe.getRecipeSteps().isEmpty()) {
						

							newRecipe.setNameOfTheDish(tName.getText());
							cookingBook.addRecipe(newRecipe);
							frame.dispose();
							new MainWindow(dataFile);
							
						
					} else {
						JOptionPane.showMessageDialog(frame, "You must add at least one step to your recipe!", "Warning", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "You must add at least one ingredient!", "Warning", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(frame, "You must name your new recipe!", "Warning", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	
	
	
	private void cleanIngr() {
		tNameOfIngredient.setText("");
		tAmount.setText("");
		tUnit.setText("");
		tNameOfIngredient.requestFocus();
	}   // EOM
	
	private void cleanStep() {
		tNewStep.setText("");
		tNewStep.requestFocus();
	}   // EOM
	
	


	@Override
	public void windowClosing(WindowEvent e) {
		
		frame.dispose();
		new MainWindow();
		
	}

	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	
	
	public class MyCellRenderer extends DefaultListCellRenderer {
		   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public static final String HTML_1 = "<html><body style='width: ";
		   public static final String HTML_2 = "px'>";
		   public static final String HTML_3 = "</html>";
		   private int width;

		   public MyCellRenderer(int width) {
		      this.width = width;
		   }

		   @Override
		   public Component getListCellRendererComponent(JList<?> list, Object value,
		         int index, boolean isSelected, boolean cellHasFocus) {
		      String text = HTML_1 + String.valueOf(width) + HTML_2 + value.toString()
		            + HTML_3;
		      return super.getListCellRendererComponent(list, text, index, isSelected,
		            cellHasFocus);
		   }

		}
	
	
/*	@SuppressWarnings("resource")
	private static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!sourceFile.exists()) {
			return;
		}
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		source = new FileInputStream(sourceFile).getChannel();
		destination = new FileOutputStream(destFile).getChannel();
		if (destination != null && source != null) {
			destination.transferFrom(source, 0, source.size());
		}
		if (source != null) {
			source.close();
		}
		if (destination != null) {
			destination.close();
		}

	}*/
	
	
}
