package co.edu.poli.examen2_Largo.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/co/edu/poli/examen2_Largo/formCard.fxml")
        );

        // 🔥 DEBUG (puedes borrarlo después)
        System.out.println("FXML PATH: " + loader.getLocation());

        stage.setScene(new Scene(loader.load()));

        stage.setTitle("App");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}