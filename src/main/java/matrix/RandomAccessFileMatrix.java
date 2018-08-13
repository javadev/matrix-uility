package matrix;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileMatrix extends Matrix implements Closeable {
    private final RandomAccessFile raf;
    private final int rowCount;
    private final int colCount;

    public RandomAccessFileMatrix(String filename, int row, int col) throws IOException {
        this.raf = new RandomAccessFile(filename, "rw");
        this.rowCount = row;
        this.colCount = col;
    }

    protected long position(int x, int y) {
        return (long) y * rowCount + x;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public int get(int i, int j) throws MatrixIndexException {
        if (i < 1 || i > rowCount) {  
            throw new MatrixIndexException("Недопустимое число строк: " + i);
        }
        if (j < 1 || j > colCount) {
            throw new MatrixIndexException("Недопустимое число столбцов: " + j);
        }
        long p = position(i - 1, j - 1) * 8;
        try {
            raf.seek(p);
            return raf.readInt();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void put(int i, int j, int value) throws MatrixIndexException {
        if (i < 1 || i > rowCount) {
            throw new MatrixIndexException("Недопустимое число строк: " + i);
        }
        if (j < 1 || j > colCount) {
            throw new MatrixIndexException("Недопустимое число столбцов: " + j);
        }
        long p = position(i - 1, j - 1) * 8;
        try {
            raf.seek(p);
            raf.writeInt(value);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void close() throws IOException {
        raf.close();
    }
}
