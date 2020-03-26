package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;

public class Error {
    static void errorBaseLetter() {
        Alert error = new Alert(AlertType.ERROR);
        error.setHeaderText("Input not valid");
        error.setContentText("You may only input numbers greater than 1");
        error.showAndWait();
    }

    static void errorExpLetter() {
        Alert error = new Alert(AlertType.ERROR);
        error.setHeaderText("Input not valid");
        error.setContentText("You may only input numbers greater than 0");
        error.showAndWait();
    }

    static void errorModLetter() {
        Alert error = new Alert(AlertType.ERROR);
        error.setHeaderText("Input not valid");
        error.setContentText("You may only input numbers greater than 1");
        error.showAndWait();
    }

    static void errorNoValue() {
        Alert error = new Alert(AlertType.ERROR);
        error.setHeaderText("Input not valid");
        error.setContentText("You must enter a number for each field");
        error.showAndWait();
    }

    static void errorChoice() {
        Alert error = new Alert(AlertType.ERROR);
        error.setHeaderText("Input not valid");
        error.setContentText("You must select a payment frequency");
        error.showAndWait();
    }
}
