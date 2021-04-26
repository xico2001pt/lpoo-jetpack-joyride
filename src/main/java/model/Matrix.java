package model;

import java.util.ArrayList;
import java.util.List;

public class Matrix<T> {
    private final List<List<T>> matrix;

    public Matrix(int nRows, int nColumns) {
        this(nRows, nColumns, null);
    }

    public Matrix(int nRows, int nColumns, T initializer) {
        this.matrix = new ArrayList<>();
        for (int i = 0; i < nRows; ++i) {
            List<T> list = new ArrayList<>();
            for (int j = 0; j < nColumns; ++j)
                list.add(initializer);
            this.matrix.add(list);
        }
    }

    public T getValue(int row, int column) {
        return this.matrix.get(row).get(column);
    }

    public void setValue(int row, int column, T value) {
        this.matrix.get(row).set(column, value);
    }

    public int getNumberRows() {
        return matrix.size();
    }

    public int getNumberCol(int row) {
        return matrix.get(row).size();
    }


    // Apenas para debug
    public void print() {
        for (List<T> list : this.matrix) {
            for (T v : list) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
