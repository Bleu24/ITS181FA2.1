package com.gabriel.prodmsv;

import com.gabriel.prodmsapp.model.Product;
import com.gabriel.prodmsapp.service.ProductService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

@Setter
public class CreateProductController implements Initializable {
    @Setter
    ProdManController prodManController;
    public TextField tfName;
    public TextField tfDesc;
    public ComboBox cbCategory;
    public Button btnSubmit;
    public Button btnNext;
    @Setter
    Stage stage;
    @Setter
    Scene parentScene;
    @Setter
    ProductService productService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("CreateProductController: initialize");

    }

    public void onNext(ActionEvent actionEvent) {
        System.out.println("CreateProductController:onBack ");
        Node node = ((Node) (actionEvent.getSource()));
        Window window = node.getScene().getWindow();
        window.hide();

        stage.setScene(parentScene);
        stage.show();
    }

    public void onSubmit(ActionEvent actionEvent) {
        Product product = new Product();
        product.setName(tfName.getText());
        product.setDescription(tfDesc.getText());
        product.setCategoryId(1);
        try{
            product=productService.create(product);
            prodManController.addItem(product);
            onBack(actionEvent);
        }
        catch(Exception ex){
            System.out.println("CreateProductController:onSubmit Error: " + ex.getMessage());
        }
    }

    public void onBack(ActionEvent actionEvent) {
        System.out.println("CreateProductController:onBack ");
        Node node = ((Node) (actionEvent.getSource()));
        Window window = node.getScene().getWindow();
        window.hide();

        stage.setScene(parentScene);
        stage.show();
    }
}

