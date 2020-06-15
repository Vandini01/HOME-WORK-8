package FlowerStore;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FlowerStore flowerStore = new FlowerStore();
        flowerStore.choiceOfFlowers(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }

}
