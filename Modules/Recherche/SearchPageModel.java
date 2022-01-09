package Modules.Recherche;

import LITCH.DataBase;
import LITCH.Main;
import LITCH.Project;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchPageModel {

    private Main main;
    private ArrayList<Project> projectList;


    public SearchPageModel(Main newMain){
        main = newMain;
        projectList = new ArrayList<Project>();
    }


    public void setProjectList(ComboBox projectSearch) throws IOException {
        projectSearch.getItems().clear();
        for (JSONObject object : DataBase.getAllProjectRequest()) {
            projectSearch.getItems().add(object.getString("CODE_PROJET"));
            projectList.add(new Project(object.getInt("ID_PROJET"), object.getString("CODE_PROJET"), object.getString("NOM_PROJET"), object.getString("IMG_PROJET")));
        }
    }

    public void setTypeList(ComboBox typeSearch){
        typeSearch.getItems().add("SUJET");
        typeSearch.getItems().add("PRELEVEMENT");
        typeSearch.getItems().add("MANIPULATION");
    }

    public void searchFunction(ComboBox projectSearch, TableView<List<String>> tableView, TextField searchText, ComboBox typeSearch, Button download) throws IOException {
        if (!projectSearch.getItems().isEmpty()) {
            tableView.getColumns().clear();

            String text = "";
            if (main.tools.checkIfInt(searchText.getText())) {
                text = searchText.getText();
            } else {
                text = "%" + searchText.getText() + "%";
                text = text.toUpperCase();
            }

            int id_projet = 0;
            for (Project project : projectList){
                if (project.getcodeProject().equals(projectSearch.getValue().toString())){
                    id_projet = project.getidProject();
                }
            }


            if (typeSearch.getValue().toString().equals("SUJET")) {
                tableView.setItems(loadTable(DataBase.searchSubjectRequest(id_projet, text), tableView));
                download.setDisable(false);
            } else if (typeSearch.getValue().toString().equals("PRELEVEMENT")) {
                tableView.setItems(loadTable(DataBase.searchSampleRequest(id_projet, text), tableView));
                download.setDisable(false);
            } else if(typeSearch.getValue().toString().equals("MANIPULATION")) {
                tableView.setItems(loadTable(DataBase.searchManipRequest(id_projet, text), tableView));
                download.setDisable(false);
            }else{
                //do nothing
            }
            tableView.refresh();
        }
    }


    /**
     * Cette methode permet de remplir le tableau TableView avec le contenu de la ArrayList<JSONObject>
     * @param list
     * @param tableView
     * @return
     * @throws IOException
     */
    public ObservableList<List<String>> loadTable(ArrayList<JSONObject> list, TableView<List<String>> tableView) throws IOException {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        int i = 1;
        for (JSONObject object : list) {
            if (i == 1) {
                int e = 0;
                for (String key : object.keySet()) {
                    int r = e;
                    TableColumn<List<String>, String> column = new TableColumn<>(key);
                    column.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().get(r)));
                    tableView.getColumns().add(column);
                    tableView.refresh();
                    e++;
                }
            }
            i++;
            List<String> unit = new ArrayList<>();
            for (String key : object.keySet()) {
                if (key.equals("SEXE SUJET")){
                    if (object.getInt("SEXE SUJET") == 0){
                        unit.add("Mâle");
                    }else {
                        unit.add("Femelle");
                    }
                }else {
                    unit.add(main.tools.checkStringIsNull(object, key));
                }
            }
            data.add(unit);
        }
        return data;
    }


    /**
     * Cette methode permet de sauvegarder le contenue du TableView dans un fichier excel
     * @param event
     * @throws IOException
     */
    public void save(TableView<List<String>> tableView) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sample sheet");
        Row columnTitleRow = sheet.createRow(0);


        for (int i = 0; i < tableView.getColumns().size(); i++){
            columnTitleRow.createCell(i).setCellValue(tableView.getColumns().get(i).getText());
        }

        for (int row = 0; row < tableView.getItems().size(); row++) {
            Row hssfRow = sheet.createRow(row + 1);
            for (int column = 0; column < tableView.getColumns().size(); column++) {
                Object celValue = tableView.getColumns().get(column).getCellObservableValue(row).getValue();
                try {
                    if (celValue != null) {
                        hssfRow.createCell(column).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (NumberFormatException e) {

                    hssfRow.createCell(column).setCellValue(celValue.toString());
                }

            }
        }

        JFileChooser fc = new JFileChooser();
        int retrival = fc.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream out = new FileOutputStream(fc.getSelectedFile()+".xls");
                workbook.write(out);
                out.close();
                System.out.println("Excel written successfully..");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
