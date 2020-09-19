package com.bsuir.linearsystem.logic.strategies;

import com.bsuir.linearsystem.model.Matrix;
import com.bsuir.linearsystem.model.Vector;

public interface LinearSystemStrategy {
    void solveSystem(Matrix aMatrix, Vector bVector, Vector xVector, int n);
}
