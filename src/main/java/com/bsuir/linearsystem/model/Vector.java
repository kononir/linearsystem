package com.bsuir.linearsystem.model;

import lombok.SneakyThrows;

import java.util.Iterator;
import java.util.List;

public class Vector implements Cloneable, Iterable<Double> {
    private double[] vec;
    private final int len;

    public Vector(double[] vec) {
        this.vec = vec.clone();
        this.len = vec.length;
    }

    public Vector(int len) {
        this.len = len;
        this.vec = new double[len];
    }

    public Vector(List<Double> list) {
        this.len = list.size();
        this.vec = createArrayFromList(list);
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
            double val = vec[curr];
            curr++;
            return val;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static double[] createArrayFromList(List<Double> list) {
        double[] arr = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
