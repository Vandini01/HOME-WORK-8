package FlowerStore;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlowersLoader {

private FlowersLoader() {      //  private конструктор делает невозможным создание экземпляра этого класса
}

    public static <T> List<T> load (String pathToFile) throws Exception {

        List<T> flowersList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(pathToFile);
            Scanner scan = new Scanner(reader);
            String bouquet = scan.nextLine();

            flowersList.add((T) bouquetToScreen(bouquet));

            reader.close();
        }
        catch (Exception ex) {
            System.out.println("Что-то с чтением из файла " + pathToFile + " пошло не так !");
        }

        return flowersList;
    }


    public static String bouquetToScreen(String bouquet) {         //  public так как этот метод вызывется из другого класса

        StringBuffer sb1 = new StringBuffer(bouquet);
        sb1.delete((bouquet.length()-1), bouquet.length());
        bouquet = String.valueOf(sb1);

        StringBuffer sb2 = new StringBuffer(bouquet);
        sb2.delete(0, 1);
        bouquet = String.valueOf(sb2);

    return bouquet;
    }


}

