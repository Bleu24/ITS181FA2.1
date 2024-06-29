package com.gabriel.prodmsv;

import com.gabriel.prodmsapp.model.Product;
import com.gabriel.prodmsapp.service.ProductService;
import com.gabriel.prodmsv.ServiceImpl.FileServiceImpl;
import com.gabriel.prodmsv.ServiceImpl.ProductServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Data;
import lombok.Setter;

import java.io.File;
import java.net.URL;
import java.util.*;
@Data
public class ProdManController implements Initializable {
    @Setter
    Stage stage;
    @Setter
    Scene createViewScene;
    @Setter
    Scene updateViewScene;
    @Setter
    Scene deleteViewScene;

    ProductService productService;

    public TextField tfId;
    public TextField tfName;
    public TextField tfDesc;
    public ImageView productImage;
    public VBox prodman;
    public TextField tfFile;

    String create = "Create";
    String newProd = "New";
    String modifyProd = "Modify";
    String confModify = "Confirm";
    String delProd  = "Delete";

    boolean imgToggle = true;
    Image puffy;
    Image wink;

    @FXML
    public Button createButton;

    @FXML
    public Button updateButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Button closeButton;

    @FXML
    public Button imageButton;

    Object selectedItem;
    com.gabriel.prodmsv.ServiceImpl.FileServiceImpl fileService;

    public Button helloBtn;

    @FXML
    private ListView<Product> lvProducts;

    ResourceBundle resourceBundle;

    FileChooser fil_chooser = new FileChooser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ProdManController: initialize");
        try {
            this.resourceBundle = resourceBundle;
            productService = new ProductServiceImpl();
            fileService = new FileServiceImpl();
            Product[] products = productService.getProducts();


           lvProducts.getItems().addAll(products);
            try {
               //File file = new File("images/s2.PNG");
               //Image image = new Image(file.toURI().toString());

              puffy = new Image(getClass().getResourceAsStream("/images/puffy.gif"));
              wink = new Image(getClass().getResourceAsStream("/images/wink.gif"));

                productImage.setImage(puffy);
            }
            catch(Exception ex){
                System.out.println("Error with image: " + ex.getMessage());
            }
            createButton.setText(newProd);
            updateButton.setText(modifyProd);
            deleteButton.setText(delProd);
            tfId.setDisable(true);
            tfName.setDisable(true);
            tfDesc.setDisable(true);
        }
        catch (Exception ex){
            showErrorDialog("Message: " + ex.getMessage());
        }

    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        selectedItem = lvProducts.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            return;
        }
        Product p = (Product ) selectedItem;
        tfId.setText(Integer.toString(p.getId()));
        tfName.setText(p.getName());
        tfName.setDisable(true);
        tfDesc.setText(p.getDescription());
        tfDesc.setDisable(true);
        System.out.println("clicked on " + selectedItem);

    }

    public void onCreate(ActionEvent actionEvent) {
        System.out.println("ProdmanController:onNewProduct ");
        Node node = ((Node) (actionEvent.getSource()));
        Scene currentScene = node.getScene();
        Window window = currentScene.getWindow();
        window.hide();
        try {
            if(createViewScene ==null) {
                FXMLLoader fxmlLoader = new FXMLLoader(SplashApp.class.getResource("create-product.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                CreateProductController createProductController = fxmlLoader.getController();
                createProductController.setStage(this.stage);
                createProductController.setParentScene(currentScene);
                createProductController.setProductService(productService);
                createProductController.setProdManController(this);
                createProductController.setParentScene(currentScene);
                createViewScene = new Scene(root, 300, 600);
                stage.setTitle("Manage Product");
                stage.setScene(createViewScene);
                stage.show();
            }
            else{
                stage.setScene(createViewScene);
                stage.show();
            }

        }
        catch(Exception ex){
            System.out.println("ProdmanController: "+ ex.getMessage());
        }
    }

    public void onUpdate(ActionEvent actionEvent) {
        System.out.println("ProdmanController:onUpdate ");
        Node node = ((Node) (actionEvent.getSource()));
        Scene currentScene = node.getScene();
        Window window = currentScene.getWindow();
        window.hide();
        try {
            if(updateViewScene ==null) {
                FXMLLoader fxmlLoader = new FXMLLoader(SplashApp.class.getResource("update-product.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                UpdateProductController updateProductController = fxmlLoader.getController();
                updateProductController.setStage(this.stage);
                updateProductController.setParentScene(currentScene);
                updateProductController.setProductService(productService);

                Product product = (Product) selectedItem;
                updateProductController.setProduct(product);;
                updateProductController.initialize(null, null);

                updateViewScene = new Scene(root, 300, 600);
                stage.setTitle("Create Product");
                stage.setScene(updateViewScene);
                stage.show();
            }
            else{
                stage.setScene(updateViewScene);
                stage.show();
            }

        }
        catch(Exception ex){
            System.out.println("ProdmanController: "+ ex.getMessage());
        }
    }
    public void onDelete(ActionEvent actionEvent) {
        System.out.println("ProdmanController:onDelete ");
        Node node = ((Node) (actionEvent.getSource()));
        Scene currentScene = node.getScene();
        Window window = currentScene.getWindow();
        window.hide();
        try {
            if(deleteViewScene  ==null) {
                FXMLLoader fxmlLoader = new FXMLLoader(SplashApp.class.getResource("delete-product.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                DeleteProductController deleteProductController = fxmlLoader.getController();
                deleteProductController.setStage(this.stage);
                deleteProductController.setParentScene(currentScene);
                deleteProductController.setProductService(productService);
                Product product = (Product) selectedItem;

                deleteProductController.setProduct(product);
                deleteProductController.initialize(null, null);

                deleteViewScene = new Scene(root, 300, 600);
                stage.setTitle("Create Product");
                stage.setScene(deleteViewScene);
                stage.show();
            }
            else{
                stage.setScene(deleteViewScene);
                stage.show();
            }

        }
        catch(Exception ex){
            System.out.println("ProdmanController: "+ ex.getMessage());
        }
    }

    public void onClose(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Do you really want to quit amd lose your edits?");
        // alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(message)));
        alert.showAndWait();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    void showErrorDialog(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
        // alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(message)));
        alert.showAndWait();
    }

    public void onImage(ActionEvent actionEvent) {

        System.out.println("Image button pressed");
        if(imgToggle){
            productImage.setImage(wink);
            imgToggle = false;
        }
        else {
            productImage.setImage(puffy);
            imgToggle = true;
        }
        Stage stage = (Stage) prodman.getScene().getWindow();
        File file = fil_chooser.showOpenDialog(stage);
        if (file != null) {
            tfFile.setText(file.getAbsolutePath());
            fileService.uploadStream(tfFile.getText());
        }
    }

    public void onNewProduct(ActionEvent actionEvent) {
        System.out.println("ProdmanController:onNewProduct ");
        Node node = ((Node) (actionEvent.getSource()));
        Scene currentScene = node.getScene();
        Window window = currentScene.getWindow();
        window.hide();
        try {
            if(createViewScene ==null) {
                FXMLLoader fxmlLoader = new FXMLLoader(SplashApp.class.getResource("create-product.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                CreateProductController createProductController = fxmlLoader.getController();
                createProductController.setStage(this.stage);
                createProductController.setParentScene(currentScene);
                createProductController.setProductService(productService);

                createViewScene = new Scene(root, 300, 600);
                stage.setTitle("Create Product");
                stage.setScene(createViewScene);
                stage.show();
            }
            else{
                stage.setScene(createViewScene);
                stage.show();
            }

        }
        catch(Exception ex){
            System.out.println("ProdmanController: "+ ex.getMessage());
        }
    }

    public void addItem(Product product){
        lvProducts.getItems().add(product);

    }
}
