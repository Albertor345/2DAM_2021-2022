/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import configuration.Config;
import fx.controllers.customers.FXMLAddCustomerController;
import fx.controllers.customers.FXMLCustomerListController;
import fx.controllers.customers.FXMLdeleteCustomerController;
import fx.controllers.items.*;
import fx.controllers.reviews.FXMLAddReviewController;
import fx.controllers.reviews.FXMLdeleteReviewController;
import fx.controllers.purchases.FXMLAddPurchaseController;
import fx.controllers.purchases.FXMLDeletePurchasesController;
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
    private ServicesCustomers servicesCustomers;

    private Config config;
    private AnchorPane login;
    private FXMLLoginController loginController;
    private FXMLLoader loginLoader;
    private AnchorPane welcome;
    private FXMLWelcomeController welcomeController;
    private FXMLLoader welcomeLoader;
    private AnchorPane addSale;
    private FXMLAddPurchaseController addSalesController;
    private FXMLLoader addSaleLoader;
    private AnchorPane deletePurchases;
    private FXMLDeletePurchasesController deletePurchasesController;
    private FXMLLoader deletePurchasesLoader;
    private AnchorPane addCustomer;
    private AnchorPane customerList;
    private FXMLCustomerListController customerListController;
    private FXMLLoader customerListLoader;
    private FXMLAddCustomerController addCustomerController;
    private FXMLLoader addCustomerLoader;
    private AnchorPane deleteCustomer;
    private FXMLdeleteCustomerController deleteCustomerController;
    private FXMLLoader deleteCustomerLoader;
    private AnchorPane addReview;
    private FXMLAddReviewController addReviewController;
    private FXMLLoader addReviewLoader;
    private AnchorPane deleteReview;
    private FXMLdeleteReviewController deleteReviewController;
    private FXMLLoader deleteReviewLoader;
    private AnchorPane addItems;
    private FXMLAddItemsController addItemsController;
    private FXMLLoader addItemsLoader;
    private AnchorPane deleteItems;
    private FXMLDeleteItemsController deleteItemsController;
    private FXMLLoader deleteItemsLoader;
    private AnchorPane updateItem;
    private FXMLUpdateItemController updateItemController;
    private FXMLLoader updateItemLoader;

    private AnchorPane itemsList;
    private FXMLItemsList itemsListController;
    private FXMLLoader itemsListLoader;


    @Inject
    public FXMLPrincipalController(ServicesItems servicesItems, ServicesCustomers servicesCustomers, Config config, FXMLLoader loginLoader, FXMLLoader welcomeLoader, FXMLLoader addSaleLoader, FXMLLoader deleteLoader, FXMLLoader customerListLoader, FXMLLoader addCustomerLoader, FXMLLoader deleteCustomerLoader, FXMLLoader addReviewLoader, FXMLLoader deleteReviewLoader, FXMLLoader addItemsLoader, FXMLLoader deleteItemsLoader, FXMLLoader updateItemLoader, FXMLLoader itemsListLoader) {
        this.config = config;
        this.loginLoader = loginLoader;
        this.welcomeLoader = welcomeLoader;
        this.addSaleLoader = addSaleLoader;
        this.deletePurchasesLoader = deleteLoader;
        this.customerListLoader = customerListLoader;
        this.addCustomerLoader = addCustomerLoader;
        this.deleteCustomerLoader = deleteCustomerLoader;
        this.addReviewLoader = addReviewLoader;
        this.deleteReviewLoader = deleteReviewLoader;
        this.addItemsLoader = addItemsLoader;
        this.deleteItemsLoader = deleteItemsLoader;
        this.updateItemLoader = updateItemLoader;
        this.itemsListLoader = itemsListLoader;

        this.servicesCustomers = servicesCustomers;
        this.servicesItems = servicesItems;

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

    public void preloadAddSales() {
        try {
            addSale = addSaleLoader.load(getClass().getResourceAsStream("/fxml/purchases/FXMLAddSales.fxml"));
            addSalesController = addSaleLoader.getController();
            addSalesController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadDeleteSales() {
        try {
            deletePurchases = deletePurchasesLoader.load(getClass().getResourceAsStream("/fxml/purchases/FXMLDeleteSales.fxml"));
            deletePurchasesController = deletePurchasesLoader.getController();
            deletePurchasesController.setPrincipalController(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void preloadCustomerList() {
        try {
            customerList = customerListLoader.load(getClass().getResourceAsStream("/fxml/customers/FXMLCustomerList.fxml"));
            customerListController = customerListLoader.getController();
            customerListController.setPrincipal(this);

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

    public void preloadDeleteCustomer() {
        try {
            deleteCustomer = deleteCustomerLoader.load(getClass().getResourceAsStream("/fxml/customers/FXMLDeleteCustomer.fxml"));
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

    public void preloadDeleteReview() {
        try {
            deleteReview = deleteReviewLoader.load(getClass().getResourceAsStream("/fxml/reviews/FXMLDeleteReview.fxml"));
            deleteReviewController = deleteReviewLoader.getController();
            deleteReviewController.setPrincipal(this);

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

    public void chargeAddPurchase() {
        addSalesController.clear();
        addSalesController.load(servicesItems.getAll(), servicesCustomers.getAll());
        fxRoot.setCenter(addSale);
    }

    public void chargeDeletePurchases() {
        deletePurchasesController.loadPurchases();
        fxRoot.setCenter(deletePurchases);
    }

    public void chargeCustomerList() {
        customerListController.loadCustomers(servicesCustomers.getAll());
        fxRoot.setCenter(customerList);
    }

    public void chargeAddCustomer() {
        fxRoot.setCenter(addCustomer);
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
        deleteReviewController.loadCustomersList(getServicesCustomers().getAll());
        fxRoot.setCenter(deleteReview);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preloadWelcome();
        preloadLogin();


        preloadAddSales();
        preloadDeleteSales();

        preloadCustomerList();
        preloadAddCustomer();
        preloadDeleteCustomer();

        preloadAddReview();
        preloadDeleteReview();

        preloadAddItems();
        preloadItemsList();
        preloadDeleteItems();
        preloadUpdateItem();

        chargeLogin();

    }
}
