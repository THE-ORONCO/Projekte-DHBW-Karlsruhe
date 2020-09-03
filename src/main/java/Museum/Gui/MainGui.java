package Museum.Gui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import de.dhbwka.swe.utils.gui.AttributeComponent;
import de.dhbwka.swe.utils.util.AppLogger;
import de.dhbwka.swe.utils.util.IAppLogger;
import de.dhbwka.swe.utils.util.IPropertyManager;
import de.dhbwka.swe.utils.util.PropertyManager;

public class MainGui {
	private static IAppLogger appLogger = AppLogger.getInstance();
	private static IPropertyManager propertyManager;

	public static void main(String[] args) {
		String propertyFileName = System.getProperty("user.dir") + File.separator + "componenttest.properties";
		try {
			propertyManager = new PropertyManager(propertyFileName, MainGui.class, "/componenttest.properties");
			((PropertyManager) propertyManager).setAppLogger(appLogger);

			new MainGui();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public MainGui() throws IOException {
		propertyManager.addProperties(AttributeComponent.getConfigurableProperties(AttributeComponent.class));
		propertyManager.saveConfiguration();

		JFrame mainFrame = new JFrame(getClass().getSimpleName());
	}
}
