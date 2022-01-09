/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import configuration.Config;
import fx.controllers.customers.FXMLAddCustomerController;
import fx.controllers.customers.FXMLdeleteCustomerController;
import fx.controllers.customers.FXMLfindCustomerController;
import fx.controllers.items.*;
import fx.controllers.purchases.FXMLDatePurchasesController;
import fx.controllers.purchases.FXMLDeletePurchasesController;
import fx.controllers.purchases.FXMLPurchasesController;
import fx.controllers.reviews.FXMLAddReviewController;
import fx.controllers.reviews.FXMLdeleteReviewController;
import fx.controllers.reviews.FXMLfindReviewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import model.User;
import services.ServicesCustomers;
import services.ServicesItems;
import services.ServicesPurchases;
import services.ServicesReviews;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class FXMLPrincipalController implements Initializable {

    @FXML
    private MenuItem topMenuDeleteCustomers;
    @FXML
    private Menu topMenuCustomers;
    @FXML
    private MenuItem topMenuAddItem;
    @FXML
    private MenuItem topMenuFindItemByID;
    @FXML
    private MenuItem topMenuDeleteItems;
    @FXML
    private MenuItem topMenuUpdateItems;
    @FXML
    private MenuItem topMenuFindByDate;
    @FXML
    private MenuItem topMenuDeletePurchases;
    @FXML
    private BorderPane fxRoot;
    @FXML
    private MenuBar fxMenuTop;

    private User user;
    private boolean admin;

    private ServicesItems servicesItems;
    private ServicesPurchases servicesPurchases;
    private ServicesCustomers servicesCustomers;
    private ServicesReviews servicesReviews;

    private Config config;
    private AnchorPane login;
    private FXMLLoginController loginController;
    private FXMLLoader loginLoader;
    private AnchorPane welcome;
    private FXMLWelcomeController welcomeController;
    private FXMLLoader welcomeLoader;
    private AnchorPane purchases;
    private FXMLPurchasesController purchasesController;
    private FXMLLoader purchasesLoader;
    private AnchorPane datePurchases;
    private FXMLDatePurchasesController datePurchasesController;
    private FXMLLoader datePurchasesLoader;
    private AnchorPane deletePurchases;
    private FXMLDeletePurchasesController deletePurchasesController;
    private FXMLLoader deletePurchasesLoader;
    private AnchorPane addCustomer;
    private FXMLAddCustomerController addCustomerController;
    private FXMLLoader addCustomerLoader;
    private AnchorPane findCustomer;
    private FXMLfindCustomerController findCustomerController;
    private FXMLLoader findCustomerLoader;
    private AnchorPane deleteCustomer;
    private FXMLdeleteCustomerController deleteCustomerController;
    private FXMLLoader deleteCustomerLoader;
    private AnchorPane addReview;
    private FXMLAddReviewController addReviewController;
    private FXMLLoader addReviewLoader;
    private AnchorPane findReview;
    private FXMLfindReviewController findReviewController;
    private FXMLLoader findReviewLoader;
    private AnchorPane deleteReview;
    private FXMLdeleteReviewController deleteReviewController;
    private FXMLLoader deleteReviewLoader;
    private AnchorPane addItems;
    private FXMLAddItemsController addItemsController;
    private FXMLLoader addItemsLoader;
    private AnchorPane deleteItems;
    private FXMLDeleteItemsController deleteItemsController;
    private FXMLLoader deleteItemsLoader;
    private AnchorPane findItemsByID;
    private FXMLFindByIDController findItemsByIDController;
    private FXMLLoader findItemsByIDLoader;
    private AnchorPane updateItem;
    private FXMLUpdateItemController updateItemController;
    private FXMLLoader updateItemLoader;

    private AnchorPane itemsList;
    private FXMLItemsList itemsListController;
    private FXMLLoader itemsListLoader;


    @Inject
    public FXMLPrincipalController(ServicesItems servicesItems, ServicesPurchases servicesPurchases, ServicesCustomers servicesCustomers, ServicesReviews servicesReviews, Config config, FXMLLoader loginLoader, FXMLLoader welcomeLoader, FXMLLoader purchasesLoader, FXMLLoader datePurchasesLoader, FXMLLoader deleteLoader, FXMLLoader addCustomerLoader, FXMLLoader findCustomerLoader, FXMLLoader deleteCustomerLoader, FXMLLoader addReviewLoader, FXMLLoader findReviewLoader, FXMLLoader deleteReviewLoader, FXMLLoader addItemsLoader, FXMLLoader deleteItemsLoader, FXMLLoader findItemsByIDLoader, FXMLLoader updateItemLoader, FXMLLoader itemsListLoader) {
        this.config = config;
        this.loginLoader = loginLoader;
        this.welcomeLoader = welcomeLoader;
        this.purchasesLoader = purchasesLoader;
        this.datePurchasesLoader = datePurchasesLoader;
        this.deletePurchasesLoader = deleteLoader;
        this.addCustomerLoader = addCustomerLoader;
        this.findCustomerLoader = findCustomerLoader;
        this.deleteCustomerLoader = deleteCustomerLoader;
        this.addReviewLoader = addReviewLoader;
        this.findReviewLoader = findReviewLoader;
        this.deleteReviewLoader = deleteReviewLoader;
        this.addItemsLoader = addItemsLoader;
        this.deleteItemsLoader = deleteItemsLoader;
        this.findItemsByIDLoader = findItemsByIDLoader;
        this.updateItemLoader = updateItemLoader;
        this.itemsListLoader = itemsListLoader;

        this.servicesReviews = servicesReviews;
        this.servicesCustomers = servicesCustomers;
        this.servicesItems = servicesItems;
        this.servicesPurchases = servicesPurchases;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void preloadLogin() {
        try {
            login = loginLoader.load(getClass().getResourceAsStream("/fxml/FXMLLogin.fxml"));
            loginController = loginLoader.getController();
            loginController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadWelcome() {
        try {
            welcome = welcomeLoader.load(getClass().getResourceAsStream("/fxml/FXMLWelcome.fxml"));
            welcomeController = welcomeLoader.getController();
            welcomeController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadPurchases() {
        try {
            purchases = purchasesLoader.load(getClass().getResourceAsStream("/fxml/purchases/FXMLPurchases.fxml"));
            purchasesController = purchasesLoader.getController();
            purchasesController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadDatePurchases() {
        try {
            datePurchases = datePurchasesLoader.load(getClass().getResourceAsStream("/fxml/purchases/FXMLDatePurchases.fxml"));
            datePurchasesController = datePurchasesLoader.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadDeletePurchases() {
        try {
            deletePurchases = deletePurchasesLoader.load(getClass().getResourceAsStream("/fxml/purchases/FXMLDeletePurchases.fxml"));
            deletePurchasesController = deletePurchasesLoader.getController();
            deletePurchasesController.setPrincipalController(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadAddCustomer() {
        try {
            addCustomer = addCustomerLoader.load(getClass().getResourceAsStream("/fxml/customers/FXMLAddCustomer.fxml"));
            addCustomerController = addCustomerLoader.getController();
            addCustomerController.setPrincipal(this);

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadFindCustomer() {
        try {
            findCustomer = findCustomerLoader.load(getClass().getResourceAsStream("/fxml/customers/FXMLfindCustomer.fxml"));
            findCustomerController = findCustomerLoader.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadDeleteCustomer() {
        try {
            deleteCustomer = deleteCustomerLoader.load(getClass().getResourceAsStream("/fxml/customers/FXMLdeleteCustomer.fxml"));
            deleteCustomerController = deleteCustomerLoader.getController();
            deleteCustomerController.setPrincipalController(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadAddReview() {
        try {
            addReview = addReviewLoader.load(getClass().getResourceAsStream("/fxml/reviews/FXMLaddReview.fxml"));
            addReviewController = addReviewLoader.getController();
            addReviewController.setPrincipalController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadFindReview() {
        try {
            findReview = findReviewLoader.load(getClass().getResourceAsStream("/fxml/reviews/FXMLfindReview.fxml"));
            findReviewController = findReviewLoader.getController();
            findReviewController.setPrincipalController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadDeleteReview() {
        try {
            deleteReview = deleteReviewLoader.load(getClass().getResourceAsStream("/fxml/reviews/FXMLdeleteReview.fxml"));
            deleteReviewController = deleteReviewLoader.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadItemsList() {
        try {
            itemsList = itemsListLoader.load(getClass().getResourceAsStream("/fxml/items/FXMLItemsList.fxml"));
            itemsListController = itemsListLoader.getController();
            itemsListController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadAddItems() {
        try {
            addItems = addItemsLoader.load(getClass().getResourceAsStream("/fxml/items/FXMLAddItems.fxml"));
            addItemsController = addItemsLoader.getController();
            addItemsController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadDeleteItems() {
        try {
            deleteItems = deleteItemsLoader.load(getClass().getResourceAsStream("/fxml/items/FXMLDeleteItems.fxml"));
            deleteItemsController = deleteItemsLoader.getController();
            deleteItemsController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadFindItemsByID() {
        try {
            findItemsByID = findItemsByIDLoader.load(getClass().getResourceAsStream("/fxml/items/FXMLFindByID.fxml"));
            findItemsByIDController = findItemsByIDLoader.getController();
            findItemsByIDController.setPrincipalController(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadUpdateItem() {
        try {
            updateItem = updateItemLoader.load(getClass().getResourceAsStream("/fxml/items/FXMLUpdateItem.fxml"));
            updateItemController = updateItemLoader.getController();
            updateItemController.setPrincipalController(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hideMenus() {
        resetTopMenu();
        if (!isAdmin()) {
            topMenuAddItem.setVisible(false);
            topMenuCustomers.setVisible(false);
            topMenuDeleteItems.setVisible(false);
            topMenuCustomers.setVisible(false);
            topMenuFindItemByID.setVisible(false);
            topMenuUpdateItems.setVisible(false);
            topMenuDeletePurchases.setVisible(false);
            topMenuFindByDate.setVisible(false);
        }
    }

    public void resetTopMenu() {
        fxMenuTop.getMenus().forEach(it -> {
            if (!it.getItems().isEmpty()) {
                it.getItems().forEach(item -> item.setVisible(true));
            }
            it.setVisible(true);
        });
    }

    public void chargeLogin() {
        fxMenuTop.setVisible(false);
        loginController.clear();
        fxRoot.setCenter(login);
    }

    public void chargeWelcome() {
        welcomeController.setLogin(user);
        fxMenuTop.setVisible(true);
        fxRoot.setCenter(welcome);
    }

    public void chargePurchases() {
        purchasesController.clear();
        purchasesController.load(servicesPurchases.getAll(), servicesItems.getAll(), servicesCustomers.getAll());
        fxRoot.setCenter(purchases);
    }

    public void chargeDatePurchases() {
        datePurchasesController.loadPurchasesList();
        fxRoot.setCenter(datePurchases);
    }

    public void chargeDeletePurchases() {
        deletePurchasesController.loadPurchases(servicesPurchases.getAll());
        fxRoot.setCenter(deletePurchases);
    }

    public void chargeAddCustomer() {
        addCustomerController.loadCustomersList(servicesCustomers.getAll());
        fxRoot.setCenter(addCustomer);
    }

    public void chargeFindCustomer() {
        findCustomerController.setPrincipalController(this);
        fxRoot.setCenter(findCustomer);
    }

    public void chargeDeleteCustomer() {
        deleteCustomerController.loadCustomersList(servicesCustomers.getAll());
        fxRoot.setCenter(deleteCustomer);
    }

    public void chargeAddReview() {
        addReviewController.loadCustomers(servicesCustomers.getAll());
        fxRoot.setCenter(addReview);
    }

    public void chargeDeleteReview() {
        deleteReviewController.loadCustomersList();
        fxRoot.setCenter(deleteReview);
    }

    public void chargeFindReview() {
        findReviewController.loadItems(servicesItems.getAll());
        fxRoot.setCenter(findReview);
    }

    public void chargeListItems(ActionEvent actionEvent) {
        itemsListController.loadList(servicesItems.getAll());
        fxRoot.setCenter(itemsList);
    }

    public void chargeAddItems() {
        addItemsController.loadList(servicesItems.getAll());
        fxRoot.setCenter(addItems);
    }

    public void chargeDeleteItems() {
        deleteItemsController.loadList(servicesItems.getAll());
        fxRoot.setCenter(deleteItems);
    }

    public void chargeUpdateItems() {
        updateItemController.loadItems(servicesItems.getAll());
        fxRoot.setCenter(updateItem);
    }

    public void chargeFindItemsByID() {
        findItemsByIDController.setPrincipalController(this);
        fxRoot.setCenter(findItemsByID);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preloadWelcome();
        preloadLogin();

        preloadPurchases();
        preloadDatePurchases();
        preloadDeletePurchases();

        preloadAddCustomer();
        preloadFindCustomer();
        preloadDeleteCustomer();

        preloadAddReview();
        preloadDeleteReview();
        preloadFindReview();

        preloadAddItems();
        preloadItemsList();
        preloadDeleteItems();
        preloadFindItemsByID();
        preloadUpdateItem();

        chargeLogin();

    }


}
