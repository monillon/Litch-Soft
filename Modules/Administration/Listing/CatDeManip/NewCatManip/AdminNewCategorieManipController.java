package Modules.Administration.Listing.CatDeManip.NewCatManip;

import LITCH.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminNewCategorieManipController {

    private Main main;
    private AdminNewCategorieManipModel AdminNewCategorieManipController;
    @FXML private Button home;
    @FXML private Button addCatDeManipButton;
    @FXML private Text errorCatDeManip;
    @FXML private TextField newNameCatDeManip;

    public AdminNewCategorieManipController(Main newMain){
        main = newMain;
        AdminNewCategorieManipController = new AdminNewCategorieManipModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void addNewCatDeManip() throws IOException {
        AdminNewCategorieManipController.addNewCatDeManip(newNameCatDeManip, addCatDeManipButton, errorCatDeManip);
    }

    public void goListBack(ActionEvent e) {
        Button leBouton = (Button) e.getSource();
        main.tools.previous((Stage) leBouton.getScene().getWindow());
    }

    public void onEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            this.addNewCatDeManip();
        }
    }

}
