package com.bsuir.linearsystem.logic.strategies.impl;

import com.bsuir.linearsystem.logic.strategies.LinearSystemStrategy;
import com.bsuir.linearsystem.model.Matrix;
import com.bsuir.linearsystem.model.Vector;

public class GaussStrategy implements LinearSystemStrategy {
    public void solveSystem(Matrix aMatrix, Vector bVector, Vector xVector, int n) {
        Matrix aMatrixModifiable = aMatrix.clone();
        Vector bVectorModifiable = bVector.clone();
        forwardStep(aMatrixModifiable, bVectorModifiable, n);
        backStep(aMatrixModifiable, bVectorModifiable, xVector, n);
    }

    private void forwardStep(Matrix aMatrix, Vector bVector, int n) {
        for (int k = 0; k < n - 1; k++) {
            int p = k;
            for (int m = k + 1; m < n; m++) {
                if (Math.abs(aMatrix.get(p, k)) < Math.abs(aMatrix.get(m, k))) {
                    p = m;
                }
            }
            double r;
            for (int j = k; j < n; j++) {
                r = aMatrix.get(k, j);
                aMatrix.set(k, j, aMatrix.get(p, j));
                aMatrix.set(p, j, r);
            }
            r = bVector.get(k);
            bVector.set(k, bVector.get(p));
            bVector.set(p, r);
            for (int m = k + 1; m < n; m++) {
                double c = aMatrix.get(m, k) / aMatrix.get(k, k);
                bVector.set(m, bVector.get(m) - c * bVector.get(k));
                for (int i = k; i < n; i++) {
                    aMatrix.set(m, i, aMatrix.get(m, i) - c * aMatrix.get(k, i));
                }
            }
        }
    }

    private void backStep(Matrix aMatrix, Vector bVector, Vector xVector, int n) {
        int last = n - 1;
        xVector.set(last, bVector.get(last) / aMatrix.get(last, last));
        for (int k = last - 1; k >= 0; k--) {
            double s = 0;
            for (int i = k + 1; i < n; i++) {
                s = s + aMatrix.get(k, i) * xVector.get(i);
            }
            xVector.set(k, (bVector.get(k) - s) / aMatrix.get(k, k));
        }
    }
}
