package com.bsuir.linearsystem.model;

import lombok.SneakyThrows;

import java.util.Iterator;

public class Vector implements Cloneable, Iterable<Double> {
    private double[] vec;
    private final int len;

    public Vector(double[] vec) {
        this.vec = vec;
        this.len = vec.length;
    }

    public Vector(int len) {
        this.len = len;
        this.vec = new double[len];
    }

    public int len() {
        return len;
    }

    public double get(int i) {
        return vec[i];
    }

    public void set(int i, double val) {
        vec[i] = val;
    }

    @SneakyThrows
    @Override
    public Vector clone() {
        Vector copy = (Vector) super.clone();
        copy.vec = vec.clone();
        return copy;
    }

    public Iterator<Double> iterator() {
        return new VectorIterator();
    }

    class VectorIterator implements Iterator<Double> {
        int curr;

        public VectorIterator() {
            this.curr = 0;
        }

        public boolean hasNext() {
            return curr < len;
        }

        public Double next() {
            return vec[curr];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
