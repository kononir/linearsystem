package com.bsuir.linearsystem;

import com.bsuir.linearsystem.logic.LinearSystemService;
import com.bsuir.linearsystem.logic.impl.LinearSystemServiceImpl;
import com.bsuir.linearsystem.logic.strategies.LinearSystemStrategy;
import com.bsuir.linearsystem.logic.strategies.impl.GaussStrategy;
import com.bsuir.linearsystem.logic.strategies.impl.SquareRootStrategy;
import com.bsuir.linearsystem.model.Matrix;
import com.bsuir.linearsystem.model.Vector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Controller {
    private final LinearSystemStrategy gaussStrategy = new SquareRootStrategy();
    private final LinearSystemService linearSystemService = new LinearSystemServiceImpl(gaussStrategy);

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
        int n = aMatrix.cols();
        Vector xVector = new Vector(n);
        linearSystemService.solveSystem(aMatrix, bVector, xVector, n);
        showResultBox();
        drawResult(xVector);
        double discrepancy = linearSystemService.calculateDiscrepancy(aMatrix, bVector, xVector, n);
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
