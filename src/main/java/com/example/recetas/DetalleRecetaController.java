package com.example.recetas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DetalleRecetaController {

    @FXML
    private Label nombreLabel;

    @FXML
    private Label ingredientesLabel;

    @FXML
    private Label instruccionesLabel;

    @FXML
    private ImageView imagenRecetaView;

    private Receta receta;

    public void setReceta(Receta receta) {
        this.receta = receta;
        if (receta != null) {
            nombreLabel.setText(receta.getNombre());
            ingredientesLabel.setText(receta.getIngredientes());
            instruccionesLabel.setText(receta.getInstrucciones());
            //Arreglar esta mamada
            Image imagen = receta.getImagen();
            if (imagen != null) {
                imagenRecetaView.setImage(imagen);
            }
        }
    }
}