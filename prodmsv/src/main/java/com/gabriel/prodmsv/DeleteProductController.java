package com.gabriel.prodmsv;

import com.gabriel.prodmsapp.model.Product;
import com.gabriel.prodmsapp.service.ProductService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

@Setter
public class DeleteProductController implements Initializable {
    public TextField tfName;
    public TextField tfDesc;
    @Setter
    Stage stage;
    @Setter
    Scene parentScene;
    @Setter
    ProductService productService;
    @Setter
    Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("DeleteProductController: initialize");
        tfName.setText(product.getName());
        tfDesc.setText(product.getDescription());
    }

    public void onBack(ActionEvent actionEvent) {
        System.out.println("CreateProductController:onBack ");
        Node node = ((Node) (actionEvent.getSource()));
        Window window = node.getScene().getWindow();
        window.hide();

        stage.setScene(parentScene);
        stage.show();
    }

    public void onSubmit(ActionEvent actionEvent) {
    }

    public void onNext(ActionEvent actionEvent) {
    }
}
