package com.bsuir.linearsystem;

import com.bsuir.linearsystem.model.Matrix;
import com.bsuir.linearsystem.model.Vector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Controller {
    private final LinearSystemService linearSystemService = new LinearSystemServiceImpl();

    @FXML
    private TextField qField;

    @FXML
    private TextField dField;

    @FXML
    private Pane pane;

    @FXML
    private Label discrepancyLabel;

    @FXML
    private VBox resultVBox;

    private final double[][] aValues = new double[][]
            {
                    {0, 1, 0, 0, 0},
                    {1, -2, 1, 0, 0},
                    {0, 1, -2, 1, 0},
                    {0, 0, 1, -2, 1},
                    {0, 0, 0, 1, 0}
            };

    private final double[] bValues = new double[]{0, 0, 0, 0, 0};

    @FXML
    public void solveSystemAndCalculateDiscrepancy() {
        Matrix aMatrix = createAMatrixAndFillQValue(resolveDoubleFieldValue(qField));
        Vector bVector = createBVectorAndFillDValue(resolveDoubleFieldValue(dField));
        Vector xVector = linearSystemService.solveSystem(aMatrix, bVector);
        showResultBox();
        drawResult(xVector);
        double discrepancy = linearSystemService.calculateDiscrepancy(aMatrix, bVector, xVector);
        drawDiscrepancy(discrepancy);
    }

    private double resolveDoubleFieldValue(TextField field) {
        return Double.parseDouble(field.getText());
    }

    private Matrix createAMatrixAndFillQValue(double qValue) {
        Matrix aMatrix = new Matrix(aValues);
        aMatrix.set(0, 0, qValue);
        aMatrix.set(4, 4, qValue);
        return aMatrix;
    }

    private Vector createBVectorAndFillDValue(double dValue) {
        Vector bVector = new Vector(bValues);
        bVector.set(1, dValue);
        bVector.set(2, dValue);
        bVector.set(3, dValue);
        return bVector;
    }

    private void showResultBox() {
        resultVBox.setVisible(true);
    }

    private void drawResult(Vector xVector) {
        for (int i = 0; i < 5; i++) {
            Label resLabel = (Label) pane.getScene().lookup("#res" + i);
            resLabel.setText(String.valueOf(xVector.get(i)));
        }
    }

    private void drawDiscrepancy(double discrepancy) {
        discrepancyLabel.setText(String.valueOf(discrepancy));
    }
}
