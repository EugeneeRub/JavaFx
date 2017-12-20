package sample;

import com.ForWork.lab7.ClientInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Class controller for LoadFromFileController.fxml
 * */
public class LoadFromFileController {
    @FXML
    private TextField tfLoadFromFile;

    public void loadFromFile() {
        Controller.clients = deserializeData(tfLoadFromFile.getText());
        Controller.primaryStage.hide();
    }

    /**
     * Method that deserialize data
     *
     * @param way way to file
     *            @return ArrayList - list of elements
     * */
    private ArrayList<ClientInfo> deserializeData(String way) {
        ArrayList<ClientInfo> conteiner = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(way));
            conteiner = (ArrayList<ClientInfo>) is.readObject();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conteiner;
    }
}
