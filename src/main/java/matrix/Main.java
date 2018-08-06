package matrix;

import java.util.LinkedList;
import java.util.List;

public class Main extends Exception {

    public static void main(String[] args) throws MatrixIndexException {Matrix2D m1 = new Matrix2D(3, 3);
        Matrix2D m2 = new Matrix2D(3, 3);
        
        MatrixUtils.fillByRandom(m1);
        MatrixUtils.fillByRandom(m2);
        
        Matrix2D m = MatrixUtils.MatrixMultiple(m1, m2);
        
        System.out.println(m.toString());
    }
}
