package matrix;

public class Main {

    public static void main(String[] args) throws MatrixIndexException, java.io.IOException {
        Matrix m1 = new Matrix1D(3, 3);
        Matrix m2 = new Matrix1D(3, 3);
        
        MatrixUtils.fillByRandom(m1);
        MatrixUtils.fillByRandom(m2);
        
        Matrix m = MatrixUtils.matrixMultiple(m1, m2);
        
        System.out.println(m.toStringOneColumn());
        MatrixUtils.writeMatrixToFile(m, "file.txt");
        Matrix m3 = MatrixUtils.loadMatrixFromFile("file.txt");
        System.out.println(m3.toStringOneColumn());
    }
}
