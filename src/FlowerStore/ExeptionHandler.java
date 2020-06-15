package FlowerStore;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ExeptionHandler extends BorderPane {

private Button button;

public ExeptionHandler() {
    this.button = new Button ("Вы ввели что-то не то!");
}


    public void exceptionWindowShow (Stage primaryStage) {

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(12.0);
        dropShadow.setOffsetX(12.0);
        dropShadow.setOffsetY(12.0);
        dropShadow.setColor(Color.color(0.2, 0.7, 0.8));
        button.setStyle("-fx-font-size: 24;");
        button.setEffect(dropShadow);
        getChildren().add(button);

        button.setOnAction(e ->
                {
                    FlowerStore flowerStore = new FlowerStore();
                    flowerStore.choiceOfFlowers(primaryStage);
                }
        );

        BorderPane root = new BorderPane();
        root.setCenter(button);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wrong parameter");
        primaryStage.show();


    }
}
