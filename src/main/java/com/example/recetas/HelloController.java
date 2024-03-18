package com.example.recetas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.io.IOException;

public class HelloController {

    @FXML
    private ListView<Receta> recetasListView;

    private ObservableList<Receta> listaRecetas = FXCollections.observableArrayList();

    @FXML
    protected void initialize() {
        recetasListView.setItems(listaRecetas);
        recetasListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                mostrarDetalleReceta();
            }
        });
    }

    @FXML
    protected void agregarReceta(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("agregar-receta.fxml"));
        try {
            Parent root = loader.load();
            AgregarRecetaController controller = loader.getController();
            controller.setListaRecetas(listaRecetas);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Agregar Receta");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void eliminarReceta(ActionEvent event) {
        int indiceSeleccionado = recetasListView.getSelectionModel().getSelectedIndex();
        if (indiceSeleccionado >= 0) {
            listaRecetas.remove(indiceSeleccionado);
        }
    }

    private void mostrarDetalleReceta() {
        Receta recetaSeleccionada = recetasListView.getSelectionModel().getSelectedItem();
        if (recetaSeleccionada != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detalle-receta.fxml"));
            try {
                Parent root = loader.load();
                DetalleRecetaController controller = loader.getController();
                controller.setReceta(recetaSeleccionada);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Detalle de Receta");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}