package museumsGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import de.dhbwka.swe.utils.gui.ButtonComponent;
import de.dhbwka.swe.utils.gui.ButtonElement;

public class MainGui {
	private static JFrame mainView;

	public static void main(String[] args) {
		new MainGui();
	}

	public MainGui() {
		mainView = createMainView("Museum", new Dimension(800, 500));
		System.out.println(mainView.getJMenuBar().getSize());
	}

	private JFrame createMainView(String title, Dimension size) {
		JFrame view = new JFrame(title);
		view.setSize(size);
		view.setMinimumSize(new Dimension(520, 500));
		view.setLayout(new GridLayout(2, 0));
		view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		view.setJMenuBar(creatMenuBar());
		view.add(createMainTabComponent(), BorderLayout.CENTER);
		view.setVisible(true);
		return view;
	}

	private JMenuBar creatMenuBar() {
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
		mainMenuBar.add(createRightMenuButtonComponent());
		return mainMenuBar;
	}

	private JTabbedPane createMainTabComponent() {
		JTabbedPane mainTabbedPane = new JTabbedPane();

		mainTabbedPane.add("Mitarbeiter", new JLabel("Mitarbeiter"));
		mainTabbedPane.add("Förderer", new JLabel("Förderer"));
		mainTabbedPane.add("Räume", new JLabel("Räume"));
		mainTabbedPane.add("Exponat", new JLabel("Exponatej"));
		mainTabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		return mainTabbedPane;
	}

	private ButtonComponent createRightMenuButtonComponent() {
		ButtonElement[] rightMenuButtonElements = createRightMenuButtonElements();
		final ButtonComponent rightMenuButtons = ButtonComponent.builder("RightButtons")
				.buttonElements(rightMenuButtonElements).orientation(ButtonComponent.Orientation.RIGHT).build();
		return rightMenuButtons;
	}

	private ButtonElement[] createRightMenuButtonElements() {
		final ButtonElement[] rightMenuButtonElements = new ButtonElement[] {
				ButtonElement.builder("Exponat").buttonText("Exponat").type(ButtonElement.Type.BUTTON)
						.textColor(Color.blue).build(),
				ButtonElement.builder("Räume").buttonText("Räume").type(ButtonElement.Type.BUTTON).textColor(Color.blue)
						.build(),
				ButtonElement.builder("Förderer").buttonText("Förderer").type(ButtonElement.Type.BUTTON)
						.textColor(Color.blue).build(),
				ButtonElement.builder("Mitarbeiter").buttonText("Mitarbeiter").type(ButtonElement.Type.BUTTON)
						.textColor(Color.blue).build() };
		return rightMenuButtonElements;
	}
}
