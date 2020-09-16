package com.bsuir.linearsystem;

import com.bsuir.linearsystem.model.Vector;
import com.bsuir.linearsystem.model.Matrix;

public class LinearSystemServiceImpl implements LinearSystemService {
    public Vector solveSystem(Matrix aMatrix, Vector bVector) {
        Matrix aMatrixCopy = aMatrix.clone();
        Vector bVectorCopy = bVector.clone();
        forwardStep(aMatrixCopy, bVectorCopy);
        return backStep(aMatrixCopy, bVectorCopy);
    }

    public double calculateDiscrepancy(Matrix aMatrix, Vector bVector, Vector xVector) {
        int n = getN(aMatrix);
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

    private int getN(Matrix aMatrix) {
        return aMatrix.rows();
    }

    private void forwardStep(Matrix aMatrix, Vector bVector) {
        int n = getN(aMatrix);
        for (int k = 0; k < n - 1; k++) {
            int p = k;
            for (int m = k + 1; m <= n - 1; m++) {
                if (Math.abs(aMatrix.get(p, k)) < Math.abs(aMatrix.get(m, k))) {
                    p = m;
                }
            }
            double r;
            for (int j = k; j < n - 1; j++) {
                r = aMatrix.get(k, j);
                aMatrix.set(k, j, aMatrix.get(p, j));
                aMatrix.set(p, j, r);
            }
            r = bVector.get(k);
            bVector.set(k, bVector.get(p));
            bVector.set(p, r);
            for (int m = k + 1; m <= n - 1; m++) {
                double c = aMatrix.get(m, k) / aMatrix.get(k, k);
                bVector.set(m, bVector.get(m) - c * bVector.get(k));
                for (int i = k; i <= n - 1; i++) {
                    aMatrix.set(m, i, aMatrix.get(m, i) - c * aMatrix.get(k, i));
                }
            }
        }
    }

    private Vector backStep(Matrix aMatrix, Vector bVector) {
        int n = getN(aMatrix);
        Vector xVector = new Vector(n);
        xVector.set(n, bVector.get(n) / aMatrix.get(n, n));
        for (int k = n - 2; k >= 0; k--) {
            double s = 0;
            for (int i = k + 1; i <= n - 1; i++) {
                s = s + aMatrix.get(k, i) * xVector.get(i);
            }
            xVector.set(k, (bVector.get(k) - s) / aMatrix.get(k, k));
        }
        return xVector;
    }
}
