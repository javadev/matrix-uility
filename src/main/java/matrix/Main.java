package matrix;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws MatrixIndexException, java.io.IOException {
        Matrix m1 = new LargeIntegerMatrix("data1.txt", 100, 100);
        Matrix m2 = new LargeIntegerMatrix("data2.txt", 100, 100);
        
        MatrixUtils.fillByRandom(m1);
        MatrixUtils.fillByRandom(m2);
        
        Matrix m = MatrixUtils.matrixMultiple(m1, m2);
        
        System.out.println(m.toString());
        MatrixUtils.writeMatrixToFile(m, "file.txt");
        Matrix m3 = MatrixUtils.loadMatrixFromFile("file.txt");
        System.out.println(m3.toString());
    }
}
