package com.example.recetas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.File;
import java.util.List;

public class AgregarRecetaController {

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextArea ingredientesTextArea;

    @FXML
    private TextArea instruccionesTextArea;

    @FXML
    private ImageView imagenRecetaView;

    private File imagenReceta;

    private List<Receta> listaRecetas;

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    @FXML
    protected void agregarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        imagenReceta = fileChooser.showOpenDialog(new Stage());

        if (imagenReceta != null) {
            Image imagen = new Image(imagenReceta.toURI().toString());
            imagenRecetaView.setImage(imagen);
        }
    }

    @FXML
    protected void guardarReceta(ActionEvent event) {
        String nombre = nombreTextField.getText();
        String ingredientes = ingredientesTextArea.getText();
        String instrucciones = instruccionesTextArea.getText();

        if (nombreTextField.getText().isEmpty() || ingredientesTextArea.getText().isEmpty() || instruccionesTextArea.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error al guardar receta");
            alert.setHeaderText(null);
            alert.setContentText("Completa todos los campos antes de guardar la receta :D (La imagen es opcional)");
            alert.showAndWait();
            return;
        }

        Image imagen = null;
        if (imagenReceta != null) {
            String imageUrl = imagenReceta.toURI().toString();
            imagen = new Image(imageUrl);
        }

        Receta nuevaReceta = new Receta(nombre, ingredientes, instrucciones, imagen);
        listaRecetas.add(nuevaReceta);

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}