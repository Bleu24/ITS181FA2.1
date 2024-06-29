package com.gabriel.prodmsv;

import com.gabriel.prodmsapp.model.Product;
import com.gabriel.prodmsapp.service.ProductService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

@Setter
public class UpdateProductController implements Initializable {
    public TextField tfName;
    public TextField tfDesc;
    public ComboBox cbCategory;
    @Setter
    Stage stage;

    @Setter
    Scene parentScene;

    @Setter
    ProductService productService;

    @Setter
    Product product;
    int id;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("UpdateProductController: initialize");

        try {
            tfName.setText(product.getName());
            tfDesc.setText(product.getDescription());
        }
        catch(Exception ex){
            System.out.println("UpdateProductController: " + ex.getMessage());
        }
    }

    public void onSubmit(ActionEvent actionEvent) {
    }

    public void onNext(ActionEvent actionEvent) {
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
