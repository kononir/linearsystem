package com.bsuir.linearsystem.logic.impl;

import com.bsuir.linearsystem.logic.LinearSystemService;
import com.bsuir.linearsystem.logic.strategies.LinearSystemStrategy;
import com.bsuir.linearsystem.model.Vector;
import com.bsuir.linearsystem.model.Matrix;

public class LinearSystemServiceImpl implements LinearSystemService {
    private final LinearSystemStrategy linearSystemStrategy;

    public LinearSystemServiceImpl(LinearSystemStrategy linearSystemStrategy) {
        this.linearSystemStrategy = linearSystemStrategy;
    }

    public void solveSystem(Matrix aMatrix, Vector bVector, Vector xVector, int n) {
        linearSystemStrategy.solveSystem(aMatrix, bVector, xVector, n);
    }

    public double calculateDiscrepancy(Matrix aMatrix, Vector bVector, Vector xVector, int n) {
        double max = 0;
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < aMatrix.cols(); j++) {
                sum += aMatrix.get(i, j) * xVector.get(j);
            }
            max = Math.max(Math.abs(bVector.get(i) - sum), max);
        }
        return max;
    }
}
