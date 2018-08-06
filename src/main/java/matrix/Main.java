package matrix;

import java.util.LinkedList;
import java.util.List;

public class Main extends Exception {

    public static void main(String[] args) throws MatrixIndexException {Matrix1D m1 = new Matrix1D(3, 3);
        Matrix1D m2 = new Matrix1D(3, 3);
        
        MatrixUtils.fillByRandom(m1);
        MatrixUtils.fillByRandom(m2);
        
        Matrix1D m = MatrixUtils.MatrixMultiple(m1, m2);
        
        System.out.println(m.toString());
    }
}
