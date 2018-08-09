package matrix;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws MatrixIndexException {
        Matrix m1 = new Matrix1D(3, 3);
        Matrix m2 = new Matrix1D(3, 3);
        
        MatrixUtils.fillByRandom(m1);
        MatrixUtils.fillByRandom(m2);
        
        Matrix m = MatrixUtils.matrixMultiple(m1, m2);
        
        System.out.println(m.toString());
        MatrixUtils.writeMatrixToFile(m, "file.txt");
        Matrix m3 = MatrixUtils.loadMatrixFromFile("file.txt");
        System.out.println(m3.toString());
    }
}
