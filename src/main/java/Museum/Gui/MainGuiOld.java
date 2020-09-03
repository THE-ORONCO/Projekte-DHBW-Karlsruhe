package Museum.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import de.dhbwka.swe.utils.gui.AttributeElement;
import de.dhbwka.swe.utils.gui.ButtonElement;
import de.dhbwka.swe.utils.util.IPropertyManager;

public class MainGuiOld {
	private static IPropertyManager propertyManager;
	private static JFrame mainView;
	private JTextField suchField;
	private JTextField mitarbeiterSuchField;
	private AttributeElement mitarbeiterRolle;

	public static void main(String[] args) {
		new MainGuiOld();
	}

	public MainGuiOld() {
		mainView = createMainView("Museum", new Dimension(800, 500));
	}

	private JFrame createMainView(String title, Dimension size) {
		JFrame mainView = new JFrame(title);
		mainView.setSize(size);
		mainView.setMinimumSize(new Dimension(520, 500));
		mainView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainView.setJMenuBar(creatMenuBar());
		mainView.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		mainView.getContentPane().add(createMainTabComponent());
		mainView.setVisible(true);
		return mainView;
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
		return mainMenuBar;
	}

	private JTabbedPane createMainTabComponent() {
		JTabbedPane mainTabbedPane = new JTabbedPane();

		createMitarbeiterTab(mainTabbedPane);

		JPanel Foerderer = new JPanel();
		mainTabbedPane.addTab("F\u00F6rderer", null, Foerderer, null);

		JPanel Raeume = new JPanel();
		mainTabbedPane.addTab("R\u00E4ume", null, Raeume, null);

		JPanel Exponat = new JPanel();
		mainTabbedPane.addTab("Exponat", null, Exponat, null);
		mainTabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		return mainTabbedPane;
	}

	private void createMitarbeiterTab(JTabbedPane mainTabbedPane) {

		String[] MITARBEITERROLLEN = new String[] { "User", "HR", "Admin" };

		JPanel mitarbeiterPanel = new JPanel();
		mainTabbedPane.addTab("Mitarbeiter", null, mitarbeiterPanel, null);
		mitarbeiterPanel.setLayout(new BorderLayout(0, 0));

		JPanel suchleistePanel = new JPanel();
		mitarbeiterPanel.add(suchleistePanel, BorderLayout.NORTH);
		suchleistePanel.setLayout(new BorderLayout(0, 0));

		suchField = new JTextField();
		suchleistePanel.add(suchField, BorderLayout.CENTER);
		suchField.setColumns(50);

		ButtonElement suchOptionBtn = ButtonElement.builder("Suchoptionen").buttonText("Suchoptionen")
				.type(ButtonElement.Type.BUTTON).build();
		suchOptionBtn.setForeground(Color.WHITE);
		suchOptionBtn.setBackground(Color.CYAN);
		suchleistePanel.add(suchOptionBtn, BorderLayout.EAST);

		JPanel mittarbeiterLeftPanel = new JPanel();
		mitarbeiterPanel.add(mittarbeiterLeftPanel, BorderLayout.WEST);
		mittarbeiterLeftPanel.setLayout(new BorderLayout(0, 0));

		JPanel mitarbeiterSuchleisite = new JPanel();
		mittarbeiterLeftPanel.add(mitarbeiterSuchleisite, BorderLayout.NORTH);
		mitarbeiterSuchleisite.setLayout(new GridLayout(0, 1, 0, 0));

		mitarbeiterSuchField = new JTextField();
		mitarbeiterSuchleisite.add(mitarbeiterSuchField);
		mitarbeiterSuchField.setColumns(10);

		ButtonElement mitarbeiterSuchBtn = ButtonElement.builder("Suchoptionen").buttonText("Suchoptionen")
				.type(ButtonElement.Type.BUTTON).build();
		mitarbeiterSuchleisite.add(mitarbeiterSuchBtn);

		JList mitarbeiterList = new JList();
		mittarbeiterLeftPanel.add(mitarbeiterList, BorderLayout.CENTER);

		JScrollBar scrollBar = new JScrollBar();
		mittarbeiterLeftPanel.add(scrollBar, BorderLayout.EAST);

		JTabbedPane mitarbeiterMidTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mitarbeiterPanel.add(mitarbeiterMidTabbedPane, BorderLayout.CENTER);

		JPanel personendatenPanel = new JPanel();
		mitarbeiterMidTabbedPane.addTab("Personendaten", null, personendatenPanel, null);

		JPanel exponatPanel = new JPanel();
		mitarbeiterMidTabbedPane.addTab("Exponate", null, exponatPanel, null);

		JPanel mitarbeiterRightPanel = new JPanel();
		mitarbeiterPanel.add(mitarbeiterRightPanel, BorderLayout.EAST);
		GridBagLayout gbl_mitarbeiterRightPanel = new GridBagLayout();
		gbl_mitarbeiterRightPanel.columnWeights = new double[] { 1.0, 0.0 };
		gbl_mitarbeiterRightPanel.rowWeights = new double[] { 1.0, 0.0, 1.0 };
		mitarbeiterRightPanel.setLayout(gbl_mitarbeiterRightPanel);

		JLabel bild = new JLabel("Bild");
		GridBagConstraints gbc_bild = new GridBagConstraints();
		gbc_bild.insets = new Insets(0, 0, 5, 5);
		gbc_bild.gridx = 0;
		gbc_bild.gridy = 0;
		mitarbeiterRightPanel.add(bild, gbc_bild);

		mitarbeiterRolle = AttributeElement.builder("Rolle").labelName("Rolle").value(MITARBEITERROLLEN[0])
				.actionType(AttributeElement.ActionType.COMBOBOX).data(MITARBEITERROLLEN).build();
		GridBagConstraints gbc_mitarbeiterRolle = new GridBagConstraints();
		gbc_mitarbeiterRolle.insets = new Insets(0, 0, 5, 0);
		gbc_mitarbeiterRolle.fill = GridBagConstraints.VERTICAL;
		gbc_mitarbeiterRolle.gridx = 1;
		gbc_mitarbeiterRolle.gridy = 1;
		mitarbeiterRightPanel.add(mitarbeiterRolle, gbc_mitarbeiterRolle);

		JTextArea mitarbeiterNotizen = new JTextArea();
		GridBagConstraints gbc_mitarbeiterNotizen = new GridBagConstraints();
		gbc_mitarbeiterNotizen.gridwidth = 2;
		gbc_mitarbeiterNotizen.fill = GridBagConstraints.BOTH;
		gbc_mitarbeiterNotizen.gridx = 0;
		gbc_mitarbeiterNotizen.gridy = 2;
		mitarbeiterRightPanel.add(mitarbeiterNotizen, gbc_mitarbeiterNotizen);
	}
}
