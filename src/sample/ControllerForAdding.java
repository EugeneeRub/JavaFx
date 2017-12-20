package sample;

import com.ForWork.lab7.ClientInfo;
import com.ForWork.lab7.Human;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.LinkedList;

/**
 * Class controller for ControllerForAdding.fxml
 * */
public class ControllerForAdding {
    //----personal info----//
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDateBirth;
    @FXML
    private TextField tfGrowth;
    @FXML
    private TextField tfColorEyes;
    @FXML
    private TextField tfSex;
    @FXML
    private TextField tfRegCode;
    @FXML
    private TextField tfRegDate;
    //----hobbi(personal info)----//
    @FXML
    private TextField tfAddText1;
    @FXML
    private ListView<String> tfList1;
    private LinkedList<String> listOfHobbies = new LinkedList<>();
    //----pretend info----//
    @FXML
    private TextField tfNamePretend;
    @FXML
    private TextField tfDateBirthPretend;
    @FXML
    private TextField tfGrowthPretend;
    @FXML
    private TextField tfColorEyesPretend;
    @FXML
    private TextField tfSexPretend;
    //----hobbi(pretend info)----//
    @FXML
    private TextField tfAddText2;
    @FXML
    private ListView<String> tfList2;
    private LinkedList<String> listOfHobbiesPretend = new LinkedList<>();
    //----- add all elements----//
    @FXML
    private Button addAll;

    public static ClientInfo information = null;

    public void addAllElements() {
        information = new ClientInfo();
        try {
            //adding all about person//
            String text = tfName.getText();
            information.setMName(text.equals("") ? "-" : text);
            text = tfDateBirth.getText();
            information.setMDateOfBirth(text.equals("") ? "-" : text);
            text = tfGrowth.getText();
            float f;
            if (text.equals("")) {
                f = 0.0f;
            } else {
                f = Float.parseFloat(tfGrowth.getText());
            }
            information.setMGrowth(f);
            text = tfColorEyes.getText();
            information.setMColorEyes(text.equals("") ? "-" : text);
            text = tfSex.getText();
            information.setMSex(text.equals("") ? "-" : text);
            text = tfRegCode.getText();
            information.setMRegNumber(text.equals("") ? "-" : text);
            text = tfRegDate.getText();
            information.setMDateOfRegistr(text.equals("") ? "-" : text);
            information.setHobbi(listOfHobbies);
            //adding info about pretend//
            Human human = createHuman();
            information.setMDemandsHuman(human);

            Controller.primaryStage.hide();
        } catch (Exception ex) {
            callAlertDialog("Ошибка добавления",
                    "ERROR",
                    "Произошла ошибка добавления элемента(Проверьте введеные данные)");
        }
    }

    public void addStringToHobbi(){
        try {
            String text = tfAddText1.getText();
            listOfHobbies.add(0,text);
            ObservableList<String> observableList = tfList1.getItems();
            observableList.add(0,text);
            tfList1.setItems(observableList);
        }catch (Exception ex){
            callAlertDialog("Ошибка добавления",
                    "ERROR",
                    "Произошла ошибка добавления элемента(Проверьте введеные данные)");
        }
    }

    public void addStringToHobbiPretend(){
        try {
            String text = tfAddText2.getText();
            listOfHobbiesPretend.add(0,text);
            ObservableList<String> observableList = tfList2.getItems();
            observableList.add(0,text);
            tfList2.setItems(observableList);
        }catch (Exception ex){
            callAlertDialog("Ошибка добавления",
                    "ERROR",
                    "Произошла ошибка добавления элемента(Проверьте введеные данные)");
        }
    }

    public static void callAlertDialog(String title, String hText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(hText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private Human createHuman(){
        String text;
        Human human = new Human();
        text = tfNamePretend.getText();
        human.setMName(text.equals("") ? "-" : text);
        text = tfDateBirthPretend.getText();
        human.setMDateOfBirth(text.equals("") ? "-" : text);
        text = tfGrowthPretend.getText();
        float f;
        if (text.equals("")) {
            f = 0.0f;
        } else {
            f = Float.parseFloat(tfGrowthPretend.getText());
        }
        human.setMGrowth(f);
        text = tfColorEyesPretend.getText();
        human.setMColorEyes(text.equals("") ? "-" : text);
        text = tfSexPretend.getText();
        human.setMSex(text.equals("") ? "-" : text);
        human.setHobbi(listOfHobbiesPretend);
        return human;
    }
}
