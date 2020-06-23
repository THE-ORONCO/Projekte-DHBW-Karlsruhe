package museumsGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

public class MainGui {
	public static void main(String[] args) {
		final JFrame mainView = createMainView("test", new Dimension(800, 500));
		mainView.setJMenuBar(creatMenuBar());
	}

	private static JFrame createMainView(String title, Dimension size) {
		JFrame view = new JFrame(title);
		view.setSize(size);
		view.setMinimumSize(new Dimension(520, 500));
		view.setLayout(new GridLayout(2, 0));
		view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		view.setVisible(true);
		return view;
	}

	private static JMenuBar creatMenuBar() {
		JMenuBar mainMenuBar = new JMenuBar();
		// Creating the left MenuItems
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu optionsMenu = new JMenu("Options");
		JMenu toolsMenu = new JMenu("Tools");
		JMenu windowMenu = new JMenu("Window");
		JMenu helpMenu = new JMenu("Help");
		// adding the left MenuItems
		mainMenuBar.add(fileMenu);
		mainMenuBar.add(editMenu);
		mainMenuBar.add(optionsMenu);
		mainMenuBar.add(toolsMenu);
		mainMenuBar.add(windowMenu);
		mainMenuBar.add(helpMenu);
		// Space between Items
		JSeparator jSeparator = new JSeparator();
		jSeparator.setOpaque(false);
		mainMenuBar.add(jSeparator);
		// Creating the right MenuItems
		JMenu exponatMenu = new JMenu("Exponat");
		exponatMenu.setForeground(Color.BLUE);
		JMenu raumMenu = new JMenu("Räume");
		raumMenu.setForeground(Color.BLUE);
		JMenu foerdererMenu = new JMenu("Förderer");
		foerdererMenu.setForeground(Color.BLUE);
		JMenu mitarbeiterMenu = new JMenu("Mitarbeiter");
		mitarbeiterMenu.setForeground(Color.BLUE);
		// adding the right MenuItems
		mainMenuBar.add(exponatMenu);
		mainMenuBar.add(raumMenu);
		mainMenuBar.add(foerdererMenu);
		mainMenuBar.add(mitarbeiterMenu);
		return mainMenuBar;
	}
}
