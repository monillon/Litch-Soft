package Modules.Projet;


import LITCH.Group;
import LITCH.Main;
import LITCH.Project;
import Modules.Projet.ProjectScreenModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectScreenController {

    @FXML private Button home;
    @FXML private Button addHumanGroupButton;
    @FXML private Button addRatGroupButton;
    @FXML private Button addCellGroupButton;
    @FXML private Pane groupPaneHuman;
    @FXML private Pane groupPaneRat;
    @FXML private Pane groupPaneCell;
    @FXML private Pane hpnt, hpt, hnpnt, hnpt, rpnt, rpt, rnpnt, rnpt, cpnt, cpt, cnpnt, cnpt;
    @FXML private Text screenProjectName;


    private Main main;
    private ProjectScreenModel projectScreenModel;



    public ProjectScreenController(Main newMain){
        main = newMain;
        projectScreenModel = new ProjectScreenModel(newMain);
    }

    public void goHome() throws IOException, SQLException {
        main.tools.goHome(home);
    }

    public void loadProjectView(Project project) throws IOException {
        this.applyRoles();
        screenProjectName.setText("Projet " + project.getnameProject());
        ArrayList<Group> listGroup = project.getListOfGroup();
        for (Group group : listGroup) {
            String nameOrganism = "";
            try {
                nameOrganism = group.getOrganism().getNameOrganism();
            }
            catch(Exception e) {
                //  Block of code to handle errors
            }
            if (group.getGroupPatho() && group.getGroupTreated()) {
                if (nameOrganism.equals("HUMAIN")) {

                    projectScreenModel.buildGroupPanel(hpt, group);

                } else if (nameOrganism.equals("RAT")) {

                    projectScreenModel.buildGroupPanel(rpt, group);

                } else if (nameOrganism.equals("CELLULE")) {

                    projectScreenModel.buildGroupPanel(cpt, group);
                }
            } else if (!group.getGroupPatho() && group.getGroupTreated()) {
                if (nameOrganism.equals("HUMAIN")) {

                    projectScreenModel.buildGroupPanel(hnpt, group);

                }else if (nameOrganism.equals("RAT")){

                    projectScreenModel.buildGroupPanel(rnpt, group);

                }else if (nameOrganism.equals("CELLULE")){

                    projectScreenModel.buildGroupPanel(cnpt, group);
                }
            } else if (group.getGroupPatho() && !group.getGroupTreated()) {
                if (nameOrganism.equals("HUMAIN")) {

                    projectScreenModel.buildGroupPanel(hpnt, group);

                } else if (nameOrganism.equals("RAT")) {

                    projectScreenModel.buildGroupPanel(rpnt, group);

                } else if (nameOrganism.equals("CELLULE")) {

                    projectScreenModel.buildGroupPanel(cpnt, group);
                }
            } else if (!group.getGroupPatho() && !group.getGroupTreated()) {
                if (nameOrganism.equals("HUMAIN")) {

                    projectScreenModel.buildGroupPanel(hnpnt, group);

                }else if (nameOrganism.equals("RAT")){

                    projectScreenModel.buildGroupPanel(rnpnt, group);

                }else if (nameOrganism.equals("CELLULE")){

                    projectScreenModel.buildGroupPanel(cnpnt, group);
                }
            }
        }
    }


    public void applyRoles(){
        if (main.getCurrentUser().getRole().equals(Main.roles[3])) {
            main.tools.applyRoles(groupPaneHuman,addHumanGroupButton);
            main.tools.applyRoles(groupPaneRat,addRatGroupButton);
            main.tools.applyRoles(groupPaneCell,addCellGroupButton);
        }
    }


    public void addNewSubject(ActionEvent e) throws IOException {
        projectScreenModel.addNewSubject(e);
    }


    /**
    It allows to switch to the screen of a group and to see all subject's details of the group
     */
    public void goGroupView(ActionEvent e) throws IOException {

        projectScreenModel.goGroupView(e);
    }

    /**
     It allows to switch to the screen of a group and to see all subject's details of the group
     */
    public void goManipulationView(ActionEvent e) throws IOException {

        projectScreenModel.goManipulationView(e);
    }


}
