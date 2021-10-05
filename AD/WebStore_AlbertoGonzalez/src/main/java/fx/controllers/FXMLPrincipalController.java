/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import fx.controllers.purchases.*;
import fx.controllers.reviews.*;
import fx.controllers.customers.*;
import fx.controllers.items.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import services.ItemsServices;

import javax.inject.Inject;

@Getter
public class FXMLPrincipalController implements Initializable {

    @FXML
    private BorderPane fxRoot;
    @FXML
    private MenuBar fxMenuTop;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Inject
    public FXMLPrincipalController(FXMLLoader loginLoader, FXMLLoader welcomeLoader, FXMLLoader purchasesLoader, FXMLLoader datePurchasesLoader, FXMLLoader deleteLoader, FXMLLoader addCustomerLoader, FXMLLoader findCustomerLoader, FXMLLoader deleteCustomerLoader, FXMLLoader addReviewLoader, FXMLLoader findReviewLoader, FXMLLoader deleteReviewLoader, FXMLLoader addItemsLoader, ItemsServices itemsServices, FXMLLoader deleteItemsLoader) {
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
        this.itemsServices = itemsServices;
        this.deleteItemsLoader = deleteItemsLoader;
    }

    private ItemsServices itemsServices;


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
    private FXMLDeleteController deletePurchasesController;
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
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadAddCustomer() {
        try {
            addCustomer = addCustomerLoader.load(getClass().getResource("/fxml/customers/FXMLAddCustomer.fxml"));
            addCustomerController = addCustomerLoader.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadFindCustomer() {
        try {
            findCustomer = findCustomerLoader.load(getClass().getResource("/fxml/customers/FXMLfindCustomer.fxml"));
            findCustomerController = findCustomerLoader.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadDeleteCustomer() {
        try {
            deleteCustomer = deleteCustomerLoader.load(getClass().getResource("/fxml/customers/FXMLdeleteCustomer.fxml"));
            deleteCustomerController = deleteCustomerLoader.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadAddReview() {
        try {
            addReview = addReviewLoader.load(getClass().getResource("/fxml/reviews/FXMLaddReview.fxml"));
            addReviewController = addReviewLoader.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadFindReview() {
        try {
            findReview = findReviewLoader.load(getClass().getResource("/fxml/reviews/FXMLfindReview.fxml"));
            findReviewController = findReviewLoader.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadDeleteReview() {
        try {
            deleteReview = deleteReviewLoader.load(getClass().getResource("/fxml/reviews/FXMLdeleteReview.fxml"));
            deleteReviewController = deleteReviewLoader.getController();

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

    public void chargeLogin() {
        fxRoot.setCenter(login);
        fxMenuTop.setVisible(false);
    }

    public void chargeWelcome() {
        welcomeController.setLogin(this.getUsername());
        fxMenuTop.setVisible(true);
        fxRoot.setCenter(welcome);
    }

    public void chargePurchases() {
        purchasesController.load();
        fxRoot.setCenter(purchases);
    }

    public void chargeDatePurchases() {
        datePurchasesController.loadPurchasesList();
        fxRoot.setCenter(datePurchases);
    }

    public void chargeDelete() {
        deletePurchasesController.loadPurchases();
        fxRoot.setCenter(deletePurchases);
    }

    public void chargeAddCustomer() {
        addCustomerController.loadCustomersList();
        fxRoot.setCenter(addCustomer);
    }

    public void chargeFindCustomer() {

        fxRoot.setCenter(findCustomer);
    }

    public void chargeDeleteCustomer() {
        deleteCustomerController.loadCustomersList();
        fxRoot.setCenter(deleteCustomer);
    }

    public void chargeAddReview() {
        addReviewController.loadCustomers();
        fxRoot.setCenter(addReview);
    }

    public void chargeDeleteReview() {
        deleteReviewController.loadCustomersList();
        fxRoot.setCenter(deleteReview);
    }

    public void chargeFindReview() {
        findReviewController.loadItems();
        fxRoot.setCenter(findReview);
    }

    public void chargeAddItems() {
        addItemsController.loadList(itemsServices.getAllItems());
        fxRoot.setCenter(addItems);
    }

    public void chargeDeleteItems() {
        deleteItemsController.loadList(itemsServices.getAllItems());
        fxRoot.setCenter(deleteItems);
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
        preloadDeleteItems();

        chargeLogin();

    }


}
