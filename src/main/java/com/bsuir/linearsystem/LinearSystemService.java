package com.bsuir.linearsystem;

import com.bsuir.linearsystem.model.Matrix;
import com.bsuir.linearsystem.model.Vector;

public interface LinearSystemService {
    Vector solveSystem(Matrix aMatrix, Vector bVector);

    double calculateDiscrepancy(Matrix aMatrix, Vector bVector, Vector xVector);
}
