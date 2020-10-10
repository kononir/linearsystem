package com.bsuir.linearsystem.logic.strategies.impl;

import com.bsuir.linearsystem.logic.strategies.LinearSystemStrategy;
import com.bsuir.linearsystem.model.Matrix;
import com.bsuir.linearsystem.model.Vector;

public class SquareRootStrategy implements LinearSystemStrategy {
    public void solveSystem(Matrix aMatrix, Vector bVector, Vector xVector, int n) {
        Vector dVector = new Vector(n);
        Matrix sMatrix = new Matrix(n, n);
        for (int k = 0; k < n; k++) {
            double del = aMatrix.get(k, k);
            if (k != 0) {
                for (int i = 0; i < k - 1; i++) {
                    double sik = sMatrix.get(i, k);
                    del = del - dVector.get(i) * sik * sik;
                }
            }
            dVector.set(k, Math.signum(del));
            sMatrix.set(k, k, Math.sqrt(Math.abs(del)));
            for (int j = k + 1; j < n; j++) {
                del = aMatrix.get(k, j);
                if (k != 0) {
                    for (int i = 0; i < k - 1; i++) {
                        del = del - dVector.get(i) * sMatrix.get(i, k) * sMatrix.get(i, j);
                    }
                }
                sMatrix.set(k, j, del / (sMatrix.get(k, k) * dVector.get(k)));
            }
        }

        Vector yVector = new Vector(n);
        yVector.set(0, bVector.get(0) / (sMatrix.get(0, 0) * dVector.get(0)));
        for (int i = 1; i < n; i++) {
            double del = bVector.get(i);
            for (int k = 0; k < i - 1; k++) {
                del = del - dVector.get(k) * yVector.get(k) * sMatrix.get(k, i);
            }
            yVector.set(i, del / (sMatrix.get(i, i) * dVector.get(i)));
        }

        int last = n - 1;
        xVector.set(last, yVector.get(last) / sMatrix.get(last, last));
        for (int i = last - 1; i >= 0; i--) {
            double del = yVector.get(i);
            for (int k = i + 1; k < n; k++) {
                del = del - xVector.get(k) * sMatrix.get(i, k);
            }
            xVector.set(i, del / sMatrix.get(i, i));
        }
    }
}
