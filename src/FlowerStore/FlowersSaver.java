package FlowerStore;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FlowersSaver {

private FlowersSaver() {      //  private конструктор делает невозможным создание экземпляра этого класса
}

    public static <T> void save(String pathToFile, List<T> flowersList) throws IOException {

        try {
             FileWriter writer = new FileWriter(pathToFile);
             writer.write(String.valueOf(flowersList));
             writer.close();
        }
        catch (Exception ex) {
             System.out.println("Что-то с записью в файл " + pathToFile + " пошло не так !");
        }

    }

}

