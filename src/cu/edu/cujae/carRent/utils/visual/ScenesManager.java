package cu.edu.cujae.carRent.utils.visual;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.lang.reflect.Field;

public class ScenesManager {
    public static Object changeApContentTo(AnchorPane ap, String pageName) throws IOException, NoSuchFieldException, IllegalAccessException {
        Field field = Locations.class.getField(pageName);
        String location = (String) field.get(new Locations());
        FXMLLoader fxmlLoader = new FXMLLoader(ScenesManager.class.getResource(location));
        Node node = fxmlLoader.load();
        ap.getChildren().setAll(node);

        return fxmlLoader.getController();
    }
}
