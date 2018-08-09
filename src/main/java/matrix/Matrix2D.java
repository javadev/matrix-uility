package matrix;

public class Matrix2D {
    
 
    private int rowCount;
    private int colCount;
    private int[] data;

    

    Matrix2D(int row, int col) throws MatrixIndexException {
        if (row <= 0 || col <= 0) {
            throw new MatrixIndexException("Недопустимый размер матрицы.");
        }
        this.rowCount = row;
        this.colCount = col;
        data = new int[row * col];
    }

    Matrix2D(Matrix2D matrix) {

        this.rowCount = matrix.getRowCount();
        this.colCount = matrix.getColCount();
        data = new int[rowCount * colCount];

        for (int i = 1; i < rowCount; i++) { 
            for (int j = 1; j < colCount; j++) {
                data[i * colCount + j] = matrix.data[i * colCount + j];
            }
        }
    }

   

    public int get(int i, int j) throws MatrixIndexException {
        if (i < 1 || i > rowCount) {  
            throw new MatrixIndexException("Недопустимое число строк: " + i);
        }
        if (j < 1 || j > colCount) {
            throw new MatrixIndexException("Недопустимое число столбцов: " + j);
        }
        
        return data [(i-1) * colCount + j-1];
    }

    void put(int i, int j, int value) throws MatrixIndexException {
        if (i < 1 || i > rowCount) {
            throw new MatrixIndexException("Недопустимое число строк: " + i);
        }
        if (j < 1 || j > colCount) {
            throw new MatrixIndexException("Недопустимое число столбцов: " + j);
        }
        data[(i-1) * colCount + j-1] = value;


    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    @Override
    public boolean equals(Object obj) {
        Matrix2D m = (Matrix2D) obj;

        if (m.getRowCount() != rowCount || m.getColCount() != colCount) {
            return false;
        }

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (data[i * colCount + j] != m.data[i * colCount + j]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Matrix:\n[ ");
        for (int i = 0; i < rowCount; i++) {
            if (i != 0) {
                out.append("\n");
                out.append("  ");
            }
            for (int j = 0; j < colCount; j++) {
                out.append(data[i * colCount + j]);
                if (j == colCount - 1) {
                    continue;
                }
                for (int k = 0; k < getMaxLength() - getIntLength(data[i * colCount + j]) + 2; k++) {
                    out.append(" ");
                }
            }
        }
        out.append("  ]");
        return out.toString();
    }

    private int getMaxLength() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                int k = data[i * colCount + j];
                if (k > max) {
                    max = k;
                }
            }
        }
        return getIntLength(max);
    }

    private int getIntLength(int i) {
        return String.valueOf(i).length();
    }

}