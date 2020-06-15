package FlowerStore;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FlowerStore extends BorderPane {

private Button buttonBouquetUsual;
private Button buttonBouquetBeautiful;

private TextField amountOfRosesTextField;
private TextField amountOfChamomileTextField;
private TextField amountOfTulipsTextField;

private Label enterAmountOfRoses;
private Label enterAmountOfChamomile;
private Label enterAmountOfTulips;

private int amountOfRoses;
private int amountOfChamomile;
private int amountOfTulips;

private static long walletFlowerStore = 0L;

private Label walletFlowerStoreLabel;
private Button buttonEmptyWallet;


FlowerStore() {

    this.buttonBouquetUsual = new Button(" Обычный букет !");
    this.buttonBouquetBeautiful = new Button(" Красивый букет !");

    this.amountOfRosesTextField = new TextField();
    this.amountOfChamomileTextField = new TextField();
    this.amountOfTulipsTextField = new TextField();

    this.enterAmountOfRoses = new Label ("     Количество роз  :              ");
    this.enterAmountOfChamomile = new Label ("     Количество ромашек  :     ");
    this.enterAmountOfTulips = new Label ("     Количество тюльпанов  :  ");

    this.walletFlowerStoreLabel = new Label (String.valueOf(walletFlowerStore));
    this.buttonEmptyWallet = new Button("Очистить счет");
}


    public void choiceOfFlowers(Stage primaryStage) {

        System.out.println("\nFlower Menu!");

        BorderPane root = new BorderPane();
        GridPane centerPane = new GridPane();

        DropShadow dropShadow1 = new DropShadow();
        dropShadow1.setRadius(5.0);
        dropShadow1.setOffsetX(3.0);
        dropShadow1.setOffsetY(3.0);
        dropShadow1.setColor(Color.color(0.4, 0.5, 0.5));
        buttonBouquetUsual.setEffect(dropShadow1);
        buttonBouquetBeautiful.setEffect(dropShadow1);
        buttonEmptyWallet.setEffect(dropShadow1);

        String setStyleForButtons = "-fx-background-color: \n" +
                "        rgba(0,0,0,0.08),\n" +
                "        linear-gradient(#93FFE8, #32ccbc),\n" +
                "        linear-gradient(#90f7ec 0%,#57FEFF 10%, #32ccbc 50%, #32ccbc 51%, #0fb4e7 100%);\n" +
                "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
                "    -fx-background-radius: 5,5,4;\n" +
                "    -fx-padding: 3 30 3 30;\n" +
                "    -fx-text-fill: #242d35;\n" +
                "    -fx-font-size: 17px";
        buttonBouquetUsual.setStyle(setStyleForButtons);
        buttonBouquetBeautiful.setStyle(setStyleForButtons);

        String setStyleForButtonEmptyWallet = "-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,1;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-font-size: 14px;" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );";
        buttonEmptyWallet.setStyle(setStyleForButtonEmptyWallet);

        try {
        this.buttonBouquetUsual.setGraphic(addImageToButton("resourses/button_flowers_usual.png"));
        this.buttonBouquetBeautiful.setGraphic(addImageToButton("resourses/button_flowers.png"));
        }
        catch (Exception ignored) {  //  в методе addImageToButton уже есть обработчик исключений
        }


        buttonBouquetUsual.setOnAction(e -> {
                    try {
                        amountOfRoses = convertTextToInt(primaryStage, amountOfRosesTextField);
                        amountOfChamomile = convertTextToInt(primaryStage, amountOfChamomileTextField);
                        amountOfTulips = convertTextToInt(primaryStage, amountOfTulipsTextField);

                        List<Flowers> flowersList;
                        flowersList = sell(amountOfRoses, amountOfChamomile, amountOfTulips);

                        showBouquetOnScreen(flowersList);

                    } catch (Exception ignored) {
                    }
              }
        );


        buttonBouquetBeautiful.setOnAction(e -> {
                    try {
                        amountOfRoses = convertTextToInt(primaryStage, amountOfRosesTextField);
                        amountOfChamomile = convertTextToInt(primaryStage, amountOfChamomileTextField);
                        amountOfTulips = convertTextToInt(primaryStage, amountOfTulipsTextField);

                        List<Flowers> flowersList;
                        flowersList = sellSequence(amountOfRoses, amountOfChamomile, amountOfTulips);    // букет с чередующимися цветами

                        showBouquetOnScreen(flowersList);

                    } catch (Exception ignored) {
                    }
              }
        );


        buttonEmptyWallet.setOnAction(e -> {
                    try {
                        if (walletFlowerStore == 0) {return;}
                        walletFlowerStore = 0;
                        walletFlowerStoreLabel.setText(String.valueOf(walletFlowerStore));

                        System.out.println("\nВы только что раздали все деньги магазина бедным !");
                        System.out.println("О вас написали в новостях !!!\n");
                    }
                    catch (Exception ignored) {
                    }
        }
        );


        VBox vbox0 = new VBox();  //  Нужен для отступа сверху
        vbox0.getChildren().addAll(new Label("                                                 \n\n"));

        HBox hbox0 = new HBox(new Label(" На счету магазина  :  "), walletFlowerStoreLabel);
        hbox0.getChildren().addAll(new Label("  \n\n"));

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(enterAmountOfRoses, amountOfRosesTextField);
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(enterAmountOfChamomile, amountOfChamomileTextField);
        HBox hbox3 = new HBox();
        hbox3.getChildren().addAll(enterAmountOfTulips, amountOfTulipsTextField);

        centerPane.add(vbox0, 2, 1);
        centerPane.add(hbox0, 2, 2);         //  Вывод счета магазина на экран
        centerPane.add(buttonBouquetUsual, 5,3);   //  Вывод кнопок <букетов> на экран

        centerPane.add(new Label("            \n\n"), 4, 4);
        centerPane.add(hbox1, 12, 5);      //  Вывод полей <количества цветов> на экран
        centerPane.add(hbox2, 12, 6);
        centerPane.add(hbox3, 12, 7);
        centerPane.add(new Label("            \n\n"), 4, 8);
        centerPane.add(buttonBouquetBeautiful, 5,10);

        VBox vbox1 = new VBox();
        vbox1.getChildren().addAll(new Label("  \n\n"), buttonEmptyWallet);
        centerPane.add(vbox1, 12,12);   //  Вывод кнопки обнуления счета на экран


        root.setCenter(centerPane);

        primaryStage.setTitle("Flowers Menu ");
        primaryStage.setScene(new Scene(root, 890, 550));
        primaryStage.centerOnScreen();
        primaryStage.show();

    }


    private void showBouquetOnScreen(List<Flowers> flowersList) throws Exception {

        walletFlowerStore = walletFlowerStore + getPriceOfThisBouquet(flowersList);       //  Пополнение счета магазина
        walletFlowerStoreLabel.setText(String.valueOf(walletFlowerStore));

        System.out.println("\n" + FlowersLoader.bouquetToScreen(String.valueOf(flowersList)));     //  Вывод букета на экран

        FlowersSaver.save("save_flowerlist.txt", flowersList);     //  Запись в файл

        System.out.println("\nВывод букета на экран из файла : ");
        System.out.println(FlowersLoader.bouquetToScreen(String.valueOf(FlowersLoader.load("save_flowerlist.txt"))));

    }


    private ImageView addImageToButton(String stringPathToResourse) throws IOException {

        ImageView imageView = null;
        InputStream input = null;
        try {
            input = getClass().getResourceAsStream(stringPathToResourse);
            Image image = new Image(input);
            imageView = new ImageView(image);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка с файлом изображения !\nВозможно он где-то потерялся!\n");
        }
        finally {
            if (input != null) { input.close(); }
        }
        return imageView;
    }



    private int convertTextToInt(Stage primaryStage, TextField textString) {
        int value = 0;

        String StringToConvert = textString.getText();

        try {
            value = Integer.parseInt(StringToConvert);
        }
        catch (Exception ex) {
            System.out.println("Вы ввели что-то не то (или оставили поля незаполненными) !");
            ExeptionHandler exeption = new ExeptionHandler();
            exeption.exceptionWindowShow(primaryStage);
        }

        if (value < 0) {
            value = -(value);   //  Заказ отрицательных чисел цветов будем считать сбоем в Матрице, а не тонким намеком
        }

        if (value > 500) {
            value = 500;      //  Все же количество цветов в магазине ограничено размером помещения
            System.out.println("\nУ нас в магазине нет столько цветов !");
            System.out.println("Алло, Голландия?!... Присылайте все, что у вас растет!!!");
        }

        return value;
    }



    private List<Flowers> sell(int amountOfRoses, int amountOfChamomile, int amountOfTulips) {

        List<Flowers> flowersList = new ArrayList<>();   //  создание букета

        for (int i = 0; i < amountOfRoses; i++) {
            flowersList.add(new Rose());
        }

        for (int i = 0; i < amountOfChamomile; i++) {
            flowersList.add(new Chamomile());
        }

        for (int i = 0; i < amountOfTulips; i++) {
            flowersList.add(new Tulip());
        }

    return flowersList;
}


    private List<Flowers> sellSequence(int amountOfRoses, int amountOfChamomile, int amountOfTulips) {

        List<Flowers> flowersList = new ArrayList<>();
        int maxAmountOfFlowers;

        if (amountOfRoses > amountOfChamomile)  { maxAmountOfFlowers = amountOfRoses; }
        else maxAmountOfFlowers = amountOfChamomile;

        if (maxAmountOfFlowers < amountOfTulips) { maxAmountOfFlowers = amountOfTulips; }


        for (int i = 0; i < maxAmountOfFlowers; i++) {   //  создание букета с чередующимися цветами

            if (amountOfRoses > 0) {
                flowersList.add(new Rose());
                amountOfRoses--;
            }

            if (amountOfChamomile > 0) {
                flowersList.add(new Chamomile());
                amountOfChamomile--;
            }

            if (amountOfTulips > 0) {
                flowersList.add(new Tulip());
                amountOfTulips--;
            }

        }

        return flowersList;
    }



    private int getPriceOfThisBouquet(List<Flowers> flowersList) {
        int price = 0;

        for (Flowers flowers : flowersList) {
            price = price + flowers.getPriceOfFlower();
        }

        return price;
    }



//  Внутренние классы цветов

private abstract class Flowers {
  public abstract int getPriceOfFlower();
}


private class Rose extends Flowers {

  private int priceOfFlower = 100;
  public int getPriceOfFlower() { return this.priceOfFlower; };

  @Override
  public String toString() { return "Rose"; }
}



private class Chamomile extends Flowers {

  private int priceOfFlower = 70;
  public int getPriceOfFlower() { return this.priceOfFlower; };

  @Override
  public String toString() { return "Chamomile"; }
}



private class Tulip extends Flowers {

  private int priceOfFlower = 45;
  public int getPriceOfFlower() { return this.priceOfFlower; };

  @Override
  public String toString() { return "Tulip"; }
}


}
