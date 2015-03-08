package gui;


import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.RecipeBook;
import entities.Ingredient;
import entities.Recipe;

public class MainWindow {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menuFile, menuOptions, menuAbout;
	private JMenuItem mFileClose, mFileOpen, mFileSaveAs, mHelpAbout;
	private JList<String> listOfRecipies;
	private JTextArea listOfIngredients;
	private JScrollPane scroll, scroll2;
	private JButton bShowRecipe, bAddRecipe, bDeleteRecipe;
	private JLabel lIngredients;
	private RecipeBook cookingBook;
	private File dataFile;
	
	
	
	
	public MainWindow() {
		File dir = new File("C:/Users/Public/Documents/CookingBook");
		dir.mkdir();
		dataFile = new File("C:/Users/Public/Documents/CookingBook/przepisy.rcp");
		cookingBook = new RecipeBook(dataFile);
		buildMainWindow(cookingBook);
	}
	
	
	public MainWindow(File file) {
		this.dataFile = file;
		cookingBook = new RecipeBook(dataFile);
		buildMainWindow(cookingBook);
	}
	
	
	public void buildMainWindow(RecipeBook book) {
		
		String fileName = dataFile.getName();
		 frame = new JFrame("Cooking Book - " +fileName);
		 frame.setSize(500, 450);
		 frame.setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setResizable(false);
		 frame.setLayout(null);
 
		
		 
		
		 menuBar = new JMenuBar();
		 
			 menuFile = new JMenu("File");
			 menuFile.setMnemonic('F');
			 	mFileClose = new JMenuItem("Quit", 'Q');
			 	mFileClose.addActionListener(new QuitMenuListener());
			 	mFileClose.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
			 	mFileOpen = new JMenuItem("Open", 'O');
			 	mFileOpen.addActionListener(new OpenFileMenuListener());
			 	mFileOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
			 	mFileSaveAs = new JMenuItem("Save as", 'S');
			 	mFileSaveAs.addActionListener(new SaveAsMenuListener());
			 	mFileSaveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
			 menuFile.add(mFileOpen);
			 menuFile.add(mFileSaveAs);
			 menuFile.addSeparator();
			 menuFile.add(mFileClose);
		menuBar.add(menuFile);
			 	
			 menuOptions = new JMenu("Options");
			 menuOptions.setMnemonic('O');
		menuBar.add(menuOptions);
			 	
			 menuAbout = new JMenu("Help");
			 menuAbout.setMnemonic('H');
			 	mHelpAbout = new JMenuItem("About", 'A');
			 	mHelpAbout.addActionListener(new AboutMenuListener());
			 menuAbout.add(mHelpAbout);
		menuBar.add(menuAbout);
	
		 frame.setJMenuBar(menuBar);
		 
		 	// wyci¹ganie nazw z pliku i zamienianie na tablice, ktora da siê przekazac do JList
		 ArrayList<String> dishes = new ArrayList<String>();
		 for(Recipe r: cookingBook.getRecipies()) {
			 dishes.add(r.getNameOfTheDish());
		 }
		 String[] dishesForJList = new String[dishes.size()];
		 dishesForJList = dishes.toArray(dishesForJList);
		 
		 
		 
		 listOfRecipies = new JList<String>(dishesForJList);
		 listOfRecipies.addListSelectionListener(new ListSelectedItemListener());
		 scroll = new JScrollPane(listOfRecipies);
		 scroll.setBounds(10, 10, 200, 360);
		 scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		 scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		 frame.add(scroll);
		 
		 lIngredients = new JLabel("Ingredients:");
		 lIngredients.setFont(new Font("sansserif", Font.BOLD, 18));
		 lIngredients.setAlignmentX(Component.CENTER_ALIGNMENT);
		 lIngredients.setBounds(290, 10, 200, 40);
		 frame.add(lIngredients);
		 
		 
		 listOfIngredients = new JTextArea(10,15);
		 //listOfIngredients.setBackground(frame.getBackground());
		 listOfIngredients.setEditable(false);
		 scroll2 = new JScrollPane(listOfIngredients);
		 scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		 scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 scroll2.setBounds(230, 50, 220, 200);
		 
		 frame.add(scroll2);
		 
		 
		 
		 
		 bShowRecipe = new JButton("Show recipe");
		 bShowRecipe.setBounds(285, 260, 110, 30);
		 bShowRecipe.addActionListener(new ShowButtonListener());
		 frame.add(bShowRecipe);
		 
		 bAddRecipe = new JButton("Add recipe");
		 bAddRecipe.setBounds(285, 295, 110, 30);
		 bAddRecipe.addActionListener(new AddButtonListener());
		 frame.add(bAddRecipe);
		 
		 bDeleteRecipe = new JButton("Delete recipe");
		 bDeleteRecipe.setBounds(285, 330, 110, 30);
		 bDeleteRecipe.addActionListener(new DeleteButtonListener());
		 frame.add(bDeleteRecipe);
		 
		 frame.setVisible(true);
	
	
	}  // EOM
	
	
	
	
	
//--------------------------- EVENT LISTENERS  --------------------------------------//
	
	private class ListSelectedItemListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {

			listOfIngredients.setText("");
			Recipe r = null;
			String selectedNameOfDish = listOfRecipies.getSelectedValue();
			for(Recipe rec: cookingBook.getRecipies()) {
				if(selectedNameOfDish.equals(rec.getNameOfTheDish())) {
					r = rec;
					break;
				}
			}
			
			for(Ingredient in: r.getIngredients()) {
				listOfIngredients.append("- "+in + "\n");
			}
			
			listOfIngredients.setCaretPosition(0);

		}  // EOM
		
	}   // EOIC
	
	
	
	private class ShowButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Recipe r;
			
			if((r = getSelectedRecipe()) != null) {
				new ShowWindow(r);
			}
			else {
				JOptionPane.showMessageDialog(frame, "You have to choose a recipe to be shown", "", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}

	
	
	private class DeleteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Recipe r;
			
			if((r = getSelectedRecipe()) != null) {
					String message = "Are you sure you want to remove " + r.getNameOfTheDish()+" recipe?";
					int choise = JOptionPane.showConfirmDialog(frame, message, "", JOptionPane.YES_NO_OPTION);
					if(choise == JOptionPane.YES_OPTION) {
					cookingBook.getRecipies().remove(r);
					cookingBook.saveIntoFile(dataFile);
					frame.dispose();
					JOptionPane.showMessageDialog(null, r.getNameOfTheDish()+" has been removed.");
					buildMainWindow(cookingBook);
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "You have to choose a recipe to be deleted", "", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		
	}
	
	
	
	private class AddButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			new AddRecipeWindow(dataFile);
		}
		
	}
	
	
	private class OpenFileMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser chooser = new JFileChooser(dataFile);
			int retValue = chooser.showOpenDialog(frame);
			
			
			if(retValue == JFileChooser.APPROVE_OPTION) {
				dataFile = chooser.getSelectedFile();
				if(dataFile.getName().endsWith(".rcp")) {
					cookingBook = new RecipeBook(dataFile);
					frame.dispose();
					buildMainWindow(cookingBook);
				}
				else {
					String message = "You need to choose a proper file with an .rcp extension!";
					JOptionPane.showMessageDialog(frame, message, "Extension error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
	}
	
	
	private class SaveAsMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JFileChooser chooser = new JFileChooser(dataFile);
			int retValue = chooser.showSaveDialog(frame);

			
			if(retValue == JFileChooser.APPROVE_OPTION) {
				File savingPath = chooser.getSelectedFile();
				if(!savingPath.getName().endsWith(".rcp")) {
					savingPath = new File(chooser.getSelectedFile().getAbsolutePath() + ".rcp");
				}
				cookingBook.saveIntoFile(savingPath);
			}
			
		}
		
	}
	
	
	private class QuitMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "Are you sure you want to leave the CookingBook?";
			int choice = JOptionPane.showConfirmDialog(frame, message, "", JOptionPane.YES_NO_OPTION);
			if(choice == JOptionPane.YES_OPTION) {
				frame.dispose();
			}
		}
		
	}
	
	
	private class AboutMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String message = "Cooking book version 1.0a\n"+(char)169+" Copyright 2015 by Poozone";
			JOptionPane.showMessageDialog(frame, message);
			
		}
		
	}
	
	
	private Recipe getSelectedRecipe() {
		Recipe r = null;
		String selectedNameOfDish = listOfRecipies.getSelectedValue();
		if(selectedNameOfDish != null) {
			for(Recipe rec: cookingBook.getRecipies()) {
				if(selectedNameOfDish.equals(rec.getNameOfTheDish())) {
					r = rec;
					break;
				} 	
			}
		}
		else {
			return null;
		}
		
		return r;
	}

	
	
}
