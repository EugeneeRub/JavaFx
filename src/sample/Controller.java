package sample;

import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab7.Human;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static sample.ControllerForAdding.callAlertDialog;

/**
 * Class controller for controller.fxml
 * */
public class Controller {
    @FXML
    private ListView<String> listOfCommands;// list of commands
    //----personal info----//
    @FXML
    private Label infoName;
    @FXML
    private Label infoDateBirth;
    @FXML
    private Label infoGrowth;
    @FXML
    private Label infoColorEyes;
    @FXML
    private Label infoSex;
    @FXML
    private Label infoRegCode;
    @FXML
    private Label infoDateReg;
    //----hobbi(personal info)----//
    @FXML
    private ListView<String> infoList1;
    //----pretend info----//
    @FXML
    private Label infoNamePretend;
    @FXML
    private Label infoDateBirthPretend;
    @FXML
    private Label infoGrowthPretend;
    @FXML
    private Label infoColorEyesPretend;
    @FXML
    private Label infoSexPretend;
    //----hobbi(pretend info)----//
    @FXML
    private ListView<String> infoList2;

    public static ArrayList<ClientInfo> clients = new ArrayList<>();// list of clients
    private ObservableList<String> items;// parametrizing-list of commands
    private int indexToDelete;// index for deleting elements
    public static Stage primaryStage = new Stage();// stage for work with another classes

    /**
     * Method that check clicking on items of listView
     * when item will be clicked this method will be worked
     * */
    public void OnMyMouseClicked(){
        try {
            int currentIndexOfElem = listOfCommands.getSelectionModel().getSelectedIndex();
            seeElem(clients.get(currentIndexOfElem));
        }catch (Exception ex){
            callAlertDialog("Ошибка выбора",
                    "ERROR",
                    "Произошла ошибка выбора элемента, но похоже его не существует");
        }

    }

    /**
     * Method that add element
     * */
    public void addElem(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ControllerForAdding.fxml"));
            primaryStage.setTitle("Adding");
            primaryStage.setScene(new Scene(root));
            primaryStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClientInfo info = ControllerForAdding.information;
        if (info == null)
            return;
        clients.add(0,info);
        items = listOfCommands.getItems();
        items.add(0,info.getMName());
        listOfCommands.setItems(items);
        seeElem(info);
    }

    /**
     * Method that print element
     * */
    private void seeElem(ClientInfo info) {
        infoName.setText(info.getMName());
        infoDateBirth.setText(info.getMDateOfBirth());
        infoColorEyes.setText(info.getMColorEyes());
        infoGrowth.setText(Float.toString(info.getMGrowth()));
        infoSex.setText(info.getMSex());
        infoDateReg.setText(info.getMDateOfRegistr());
        infoRegCode.setText(info.getMRegNumber());

        ObservableList<String> items = infoList1.getItems();
        items.clear();
        items.addAll(info.getHobbi());
        infoList1.setItems(items);

        Human human = info.getMDemandsHuman();
        infoNamePretend.setText(human.getMName());
        infoDateBirthPretend.setText(human.getMDateOfBirth());
        infoColorEyesPretend.setText(human.getMColorEyes());
        infoGrowthPretend.setText(Float.toString(human.getMGrowth()));
        infoSexPretend.setText(human.getMSex());

        items = infoList2.getItems();
        items.clear();
        items.addAll(human.getHobbi());
        infoList2.setItems(items);
    }

    /**
     * Method that delete element
     * */
    public void deleteElem() {
        try {
            int currentIndexOfElem = listOfCommands.getSelectionModel().getSelectedIndex();
            ObservableList<String> items = listOfCommands.getItems();
            items.remove(currentIndexOfElem);
            clients.remove(currentIndexOfElem);
            listOfCommands.setItems(items);
        }catch (Exception ex){
            callAlertDialog("Ошибка удаления",
                    "ERROR",
                    "Произошла ошибка удаления элемента");
        }
    }

    /**
     * Method that sort elements
     * */
    public void sortElems(){
        ObservableList<String> items = listOfCommands.getItems();
        ArrayList<String> lisssst = new ArrayList<>();
        lisssst.addAll(items);
        items.clear();
        Collections.sort(lisssst,new CompareForString());
        Collections.sort(clients,new CompareForClients());

        items.addAll(lisssst);
        listOfCommands.setItems(items);
    }

    /**
     * Method that serialize data
     * */
    public void serialize(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("LoadToFileController.fxml"));
            primaryStage.setTitle("Save Files");
            primaryStage.setScene(new Scene(root));
            primaryStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that deserialize data
     * */
    public void deserialize() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("LoadFromFileController.fxml"));
            primaryStage.setTitle("Load Files");
            primaryStage.setScene(new Scene(root));
            primaryStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (clients == null)
            return;
        ArrayList<String> lists = new ArrayList<>();
        for (ClientInfo info : clients)
            lists.add(info.getMName());
        items = listOfCommands.getItems();
        items.clear();
        items.addAll(lists);
        listOfCommands.setItems(items);
    }

    /**
     * Class that uses by Collection.sort for compare list<String> elements
     * */
    class CompareForString implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            for (int i = 0; i < o1.length(); i++) {
                if (o1.charAt(i) < o2.charAt(i))
                    return -1;
                else if (o1.charAt(i) < o2.charAt(i))
                    return 1;
            }
            return 0;
        }
    }

    /**
     * Class that uses by Collection.sort for compare list<ClientInfo> elements
     * */
    class CompareForClients implements Comparator<ClientInfo>{
        @Override
        public int compare(ClientInfo client1, ClientInfo client2) {
            String s1 = client1.getMName();
            String s2 = client2.getMName();
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) < s2.charAt(i))
                    return -1;
                else if (s1.charAt(i) < s2.charAt(i))
                    return 1;
            }
            return 0;
        }
    }
}
