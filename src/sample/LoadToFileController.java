package sample;

import com.ForWork.lab7.ClientInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Class controller for LoadToFileController.fxml
 * */
public class LoadToFileController {
    @FXML
    private TextField tfLoadToFile;

    public void loadToFile() {
        serializeData(Controller.clients, tfLoadToFile.getText());
        Controller.primaryStage.hide();
    }

    /**
     * Method that serialize data
     *
     * @param way way to file
     * @param list - list of elements
     * */
    private void serializeData(List<ClientInfo> list, String way) {
        if (list == null)
            return;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(way));
            oos.writeObject(list);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
