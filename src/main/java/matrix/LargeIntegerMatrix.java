package matrix;

import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class LargeIntegerMatrix extends Matrix implements Closeable {
    private static final int MAPPING_SIZE = 1 << 30;
    private final RandomAccessFile raf;
    private final int rowCount;
    private final int colCount;
    private final List<MappedByteBuffer> mappings = new ArrayList<>();

    public LargeIntegerMatrix(String filename, int row, int col) throws IOException {
        this.raf = new RandomAccessFile(filename, "rw");
        try {
            this.rowCount = row;
            this.colCount = col;
            long size = 8L * row * col;
            for (long offset = 0; offset < size; offset += MAPPING_SIZE) {
                long size2 = Math.min(size - offset, MAPPING_SIZE);
                mappings.add(raf.getChannel().map(FileChannel.MapMode.READ_WRITE, offset, size2));
            }
        } catch (IOException e) {
            raf.close();
            throw e;
        }
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
        int mapN = (int) (p / MAPPING_SIZE);
        int offN = (int) (p % MAPPING_SIZE);
        return mappings.get(mapN).getInt(offN);
    }

    public void put(int i, int j, int value) throws MatrixIndexException {
        if (i < 1 || i > rowCount) {
            throw new MatrixIndexException("Недопустимое число строк: " + i);
        }
        if (j < 1 || j > colCount) {
            throw new MatrixIndexException("Недопустимое число столбцов: " + j);
        }
        long p = position(i - 1, j - 1) * 8;
        int mapN = (int) (p / MAPPING_SIZE);
        int offN = (int) (p % MAPPING_SIZE);
        mappings.get(mapN).putInt(offN, value);
    }

    public void close() throws IOException {
        for (MappedByteBuffer mapping : mappings) {
            clean(mapping);
        }
        raf.close();
    }

    private void clean(MappedByteBuffer mapping) {
        if (mapping == null) {
            return;
        }
        Cleaner cleaner = ((DirectBuffer) mapping).cleaner();
        if (cleaner != null) {
            cleaner.clean();
        }
    }
}
