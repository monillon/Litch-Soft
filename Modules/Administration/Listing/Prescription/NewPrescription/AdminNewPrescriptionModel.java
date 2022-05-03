package Modules.Administration.Listing.Prescription.NewPrescription;

import LITCH.DataBase;
import LITCH.Main;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNewPrescriptionModel {

    private Main main;

    public AdminNewPrescriptionModel(Main newMain){
        main = newMain;
    }

    public void addNewPrescri(TextField newPrescriName, Button addPrescriButton, Text errorPrescri) throws IOException {
        if (!newPrescriName.getText().isEmpty()) {
            DataBase.createPrescriRequest(main.tools.spaceToUnderscore(newPrescriName.getText().toUpperCase()));
            main.tools.switchScene((Stage) addPrescriButton.getScene().getWindow(),"Administration/Listing/Prescription/AdminListPrescription.fxml",main.getAdminListPrescriptionController());
            main.getAdminListPrescriptionController().addElementList();

            //tracabilité
            main.tools.applyTraceability(newPrescriName.getText().toUpperCase() + " a été ajouté aux prescriptions");
        }else{
            errorPrescri.setText("Veuillez renseigner le nom d'une prescription");
        }
    }
}
