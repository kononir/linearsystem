package com.bsuir.linearsystem.model;

import lombok.SneakyThrows;

public class Matrix implements Cloneable {
    private double[][] arr;
    private final int rows;
    private final int cols;

    public Matrix(double[][] arr) {
        this.arr = arr;
        this.rows = arr.length;
        this.cols = arr[0].length;
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
        copy.arr = arr.clone();
        return copy;
    }
}
