package LITCH;

import Modules.Administration.*;
import Modules.Administration.Listing.CatDeManip.AdminListCategorieManipController;
import Modules.Administration.Listing.CatDeManip.NewCatManip.AdminNewCategorieManipController;
import Modules.Administration.Listing.DonneesPreOp.AdminListPreOpController;
import Modules.Administration.Listing.DonneesPreOp.NewPreOp.AdminNewPreOpController;
import Modules.Administration.Listing.Organe.AdminListOrganeController;
import Modules.Administration.Listing.Organe.NewOrgane.AdminNewOrganeController;
import Modules.Administration.Listing.Pathologie.AdminListPathologieController;
import Modules.Administration.Listing.Pathologie.NewPathologie.AdminNewPathologieController;
import Modules.Administration.Listing.Prescription.AdminListPrescriptionController;
import Modules.Administration.Listing.Prescription.NewPrescription.AdminNewPrescriptionController;
import Modules.Administration.Listing.Tissu.AdminListTissuController;
import Modules.Administration.Listing.Tissu.NewTissu.AdminNewTissuController;
import Modules.Administration.Listing.Unit.AdminListUnitController;
import Modules.Administration.Listing.Unit.NewUnit.AdminNewUnitController;
import Modules.Connexion.ConnexionController;
import Modules.ManagementFrigo.FridgeManagementController;
import Modules.Menu.AjoutProjet.AddProjectController;
import Modules.Projet.AjoutSujet.AddSubjectPageController;
import Modules.Projet.Groupe.AjoutEchantillon.AddSampleController;
import Modules.Projet.Groupe.GroupViewController;
import Modules.Menu.MenuController;
import Modules.Projet.Manipulations.AjoutManipulation.AddManipulationController;
import Modules.Projet.Manipulations.ManipulationViewController;
import Modules.Projet.ProjectScreenController;
import Modules.Recherche.SearchPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    // ajout d'une ligne de test


    private User currentUser;
    private Project currentProject;

    private ConnexionController connexionController;
    private MenuController menuController;
    private ProjectScreenController projectScreenController;
    private AddProjectController addProjectController;
    private GroupViewController groupViewController;
    private AddSubjectPageController addSubjectPageController;
    private AdminPageController adminPageController;
    private AdminOrganController adminOrganController;
    private AdminPathologyController adminPathologyController;
    private AdminPreopDataController adminPreopDataController;
    private AdminNewPrescriptionController adminNewPrescriptionController;
    private AddSampleController addSampleController;
    private AdminUserController adminUserController;
    private AdminManageNewBoxController adminManageNewBoxController;
    private AddManipulationController addManipulationController;
    private AdminManipulationController adminManipulationController;
    private ManipulationViewController manipulationViewController;
    private SearchPageController searchPageController;
    private FridgeManagementController fridgeManagementController;
    private AdminListTissuController adminListTissuController;
    private AdminNewTissuController adminNewTissuController;
    private AdminListOrganeController adminListOrganeController;
    private AdminNewOrganeController adminNewOrganeController;
    private AdminListPreOpController adminListPreOpController;
    private AdminNewPreOpController adminNewPreOpController;
    private AdminListUnitController adminListUnitController;
    private AdminNewUnitController adminNewUnitController;
    private AdminListPathologieController adminListPathologieController;
    private AdminNewPathologieController adminNewPathologieController;
    private AdminListPrescriptionController adminListPrescriptionController;
    private AdminListCategorieManipController adminListCategorieManipController;
    private AdminNewCategorieManipController adminNewCategorieManipController;



    public Tools tools;

    public static String[] roles = {"Admin", "Gestionnaire", "Utilisateur", "Stagiaire"};

    @Override
    public void init() {
        connexionController = new ConnexionController(this);
        menuController = new MenuController(this);
        projectScreenController = new ProjectScreenController(this);
        addProjectController = new AddProjectController(this);
        groupViewController = new GroupViewController(this);
        addSubjectPageController = new AddSubjectPageController(this);
        adminPageController = new AdminPageController(this);
        adminOrganController = new AdminOrganController(this);
        adminPathologyController = new AdminPathologyController(this);
        adminPreopDataController = new AdminPreopDataController(this);
        adminNewPrescriptionController = new AdminNewPrescriptionController(this);
        addSampleController = new AddSampleController(this);
        adminUserController = new AdminUserController(this);
        adminManageNewBoxController = new AdminManageNewBoxController(this);
        addManipulationController = new AddManipulationController(this);
        adminManipulationController = new AdminManipulationController(this);
        manipulationViewController = new ManipulationViewController(this);
        searchPageController = new SearchPageController(this);
        fridgeManagementController = new FridgeManagementController(this);
        adminListTissuController = new AdminListTissuController(this);
        adminNewTissuController = new AdminNewTissuController(this);
        adminListOrganeController = new AdminListOrganeController(this);
        adminNewOrganeController = new AdminNewOrganeController(this);
        adminListPreOpController = new AdminListPreOpController(this);
        adminNewPreOpController = new AdminNewPreOpController(this);
        adminListUnitController = new AdminListUnitController(this);
        adminNewUnitController = new AdminNewUnitController(this);
        adminListPathologieController = new AdminListPathologieController(this);
        adminNewPathologieController = new AdminNewPathologieController(this);
        adminListPrescriptionController = new AdminListPrescriptionController(this);
        tools = new Tools(this);
        adminListCategorieManipController = new AdminListCategorieManipController(this);
        adminNewCategorieManipController = new AdminNewCategorieManipController(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader newFXML = new FXMLLoader(getClass().getResource("/Modules/Connexion/connexion.fxml"));
        newFXML.setController(connexionController);
        Parent root = (Parent) newFXML.load();
        primaryStage.getIcons().add(new Image("/IMG/logo LitCh[369].png"));
        primaryStage.setTitle("LITCH");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public ConnexionController getConnexionController() {
        return connexionController;
    }

    public void setConnexionController(ConnexionController connexionController) {
        this.connexionController = connexionController;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public ProjectScreenController getProjectScreenController() {
        return projectScreenController;
    }

    public void setProjectScreenController(ProjectScreenController projectScreenController) {
        this.projectScreenController = projectScreenController;
    }

    public AddProjectController getAddProjectController() {
        return addProjectController;
    }

    public void setAddProjectController(AddProjectController addProjectController) {
        this.addProjectController = addProjectController;
    }

    public GroupViewController getGroupViewController() {
        return groupViewController;
    }

    public void setGroupViewController(GroupViewController groupViewController) {
        this.groupViewController = groupViewController;
    }

    public AdminPageController getAdminPageController() {
        return adminPageController;
    }

    public void setAdminPageController(AdminPageController adminPageController) {
        this.adminPageController = adminPageController;
    }

    public AdminOrganController getAdminOrganController() {
        return adminOrganController;
    }

    public void setAdminOrganController(AdminOrganController adminOrganController) {
        this.adminOrganController = adminOrganController;
    }

    public AdminPathologyController getAdminPathologyController() {
        return adminPathologyController;
    }

    public void setAdminPathologyController(AdminPathologyController adminPathologyController) {
        this.adminPathologyController = adminPathologyController;
    }

    public AdminPreopDataController getAdminPreopDataController() {
        return adminPreopDataController;
    }

    public void setAdminPreopDataController(AdminPreopDataController adminPreopDataController) {
        this.adminPreopDataController = adminPreopDataController;
    }

    public AdminNewPrescriptionController getAdminNewPrescriptionController() {
        return adminNewPrescriptionController;
    }

    public void setAdminNewPrescriptionController(AdminNewPrescriptionController adminNewPrescriptionController) {
        this.adminNewPrescriptionController = adminNewPrescriptionController;
    }

    public AddSampleController getAddSampleController() {
        return addSampleController;
    }

    public void setAddSampleController(AddSampleController addSampleController) {
        this.addSampleController = addSampleController;
    }

    public AddSubjectPageController getAddSubjectPageController() {
        return addSubjectPageController;
    }

    public void setAddSubjectPageController(AddSubjectPageController addSubjectPageController) {
        this.addSubjectPageController = addSubjectPageController;
    }

    public AdminUserController getAdminUserController() {
        return adminUserController;
    }

    public void setAdminUserController(AdminUserController adminUserController) {
        this.adminUserController = adminUserController;
    }

    public AdminManageNewBoxController getAdminManageNewBoxController() {
        return adminManageNewBoxController;
    }

    public void setAdminManageNewBoxController(AdminManageNewBoxController adminManageNewBoxController) {
        this.adminManageNewBoxController = adminManageNewBoxController;
    }

    public AddManipulationController getAddManipulationController() {
        return addManipulationController;
    }

    public void setAddManipulationController(AddManipulationController addManipulationController) {
        this.addManipulationController = addManipulationController;
    }

    public AdminManipulationController getAdminManipulationController() {
        return adminManipulationController;
    }

    public void setAdminManipulationController(AdminManipulationController adminManipulationController) {
        this.adminManipulationController = adminManipulationController;
    }

    public ManipulationViewController getManipulationViewController() {
        return manipulationViewController;
    }

    public SearchPageController getSearchPageController() {
        return searchPageController;
    }

    public FridgeManagementController getFridgeManagementController() {
        return fridgeManagementController;
    }

    public void setFridgeManagementController(FridgeManagementController fridgeManagementController) {
        this.fridgeManagementController = fridgeManagementController;
    }

    public AdminListTissuController getAdminListTissuController() {
        return adminListTissuController;
    }

    public AdminNewTissuController getAdminNewTissuController() {
        return adminNewTissuController;
    }

    public AdminListOrganeController getAdminListOrganeController() {
        return adminListOrganeController;
    }

    public AdminNewOrganeController getAdminNewOrganeController() {
        return adminNewOrganeController;
    }

    public AdminListPreOpController getAdminListPreOpController() {
        return adminListPreOpController;
    }

    public AdminNewPreOpController getAdminNewPreOpController() {
        return adminNewPreOpController;
    }
  
    public AdminListUnitController getAdminListUnitController() { 
      return adminListUnitController; 
    }

    public AdminNewUnitController getAdminNewUnitController() { 
      return adminNewUnitController; 
    }

    public AdminListPathologieController getAdminListPathologieController() {
        return adminListPathologieController;
    }

    public AdminNewPathologieController getAdminNewPathologieController() {
        return adminNewPathologieController;
    }

    public AdminListPrescriptionController getAdminListPrescriptionController() {
        return adminListPrescriptionController;
    }

    public AdminListCategorieManipController getAdminListCategorieManipController() {
        return adminListCategorieManipController;
    }

    public AdminNewCategorieManipController getAdminNewCategorieManipController() {
        return adminNewCategorieManipController;
    }
}

