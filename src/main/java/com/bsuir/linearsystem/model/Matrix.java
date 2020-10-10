package com.bsuir.linearsystem.model;

import lombok.SneakyThrows;

public class Matrix implements Cloneable {
    private double[][] arr;
    private final int rows;
    private final int cols;

    public Matrix(double[][] arr) {
        this.rows = arr.length;
        this.cols = arr[0].length;
        this.arr = deepCopyArr(arr);
    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.arr = new double[rows][cols];
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    public double get(int i, int j) {
        return arr[i][j];
    }

    public void set(int i, int j, double val) {
        arr[i][j] = val;
    }

    @SneakyThrows
    @Override
    public Matrix clone() {
        Matrix copy = (Matrix) super.clone();
        copy.arr = deepCopyArr(arr);
        return copy;
    }

    private double[][] deepCopyArr(double[][] arr) {
        double[][] copyArr = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            copyArr[i] = arr[i].clone();
        }
        return copyArr;
    }
}
