package com.bsuir.linearsystem.logic;

import com.bsuir.linearsystem.model.Matrix;
import com.bsuir.linearsystem.model.Vector;

public interface LinearSystemService {
    void solveSystem(Matrix aMatrix, Vector bVector, Vector xVector, int n);

    double calculateDiscrepancy(Matrix aMatrix, Vector bVector, Vector xVector, int n);
}
