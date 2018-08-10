package matrix;

import java.util.Random;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MatrixUtils {

    public static void fillByRandom(Matrix m) {
        int rowCount = m.getRowCount();
        int colCount = m.getColCount();
        Random random = new Random();

        for (int i = 1; i <= rowCount; i++) {

            for (int j = 1; j <= colCount; j++) {
                try {
                    m.put(i, j, random.nextInt(50));
                } catch (MatrixIndexException e) {
                    System.err.println(e.getMessage());

                }

            }
        }
    }

    public static void fillByNumber(Matrix m, int number) {
        int rowCount = m.getRowCount();
        int colCount = m.getColCount();

        for (int i = 1; i <= colCount; i++) {

            for (int j = 1; j <= rowCount; j++) {
                try {
                    m.put(i, j, number);

                } catch (MatrixIndexException e) {
                    System.err.println(e.getMessage());

                }

            }
        }

    }

    public static Matrix fillByKeyboard() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter The Number Of Matrix Rows:");
        int rowCount = scan.nextInt();
        System.out.println("Enter The Number Of Matrix Columns:");
        int colCount = scan.nextInt();
        try {
            Matrix m = new Matrix1D(rowCount, colCount);
            System.out.println("Enter Matrix Data:");
            for (int i = 1; i <= rowCount; i++) {
                for (int j = 1; j <= colCount; j++) {
                    int value = scan.nextInt();
                     m.put(i, j, value);
                }
            }
            return m;

        } catch (MatrixIndexException ex) {
            System.err.println(ex.getMessage());

        }
        return null;

    }
    
    public static Matrix matrixMultiple(Matrix a, Matrix b) throws MatrixIndexException {
       
        int aRows = a.getRowCount();
        int aColumns = a.getColCount();
        int bRows = b.getRowCount();
        int bColumns = b.getColCount();

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        Matrix c = new Matrix1D(aRows, bColumns);
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                c.put(i+1,j+1,0);
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    c.put(i+1,j+1,c.get(i+1,j+1) + a.get(i+1, k+1) * b.get(k+1, j+1));
                }
            }
        }

        return c;   
    }

    public static void writeMatrixToFile(Matrix m, String filename) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {
            dataOutputStream.writeInt(m.getRowCount());
            dataOutputStream.writeInt(m.getColCount());
            for (int i = 1; i <= m.getRowCount(); i++){
                for (int j = 1; j <= m.getColCount(); j++){
                    dataOutputStream.writeInt(m.get(i, j));
                }
            }
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(MatrixIndexException e) {
             e.printStackTrace();
        }
    }

    public static Matrix loadMatrixFromFile(String filename) {

        try (FileInputStream fileInputStream = new FileInputStream(filename);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream)) {
            int rowCount = dataInputStream.readInt();
            int colCount = dataInputStream.readInt();
            Matrix result = new Matrix1D(rowCount, colCount);
            int row = 1;
            int col = 1;
            for (int i = 0; i < rowCount * colCount; i++){
                result.put(row, col, dataInputStream.readInt());
                col++;
                if (col == result.getColCount() + 1){
                    col = 1;
                    row++;
                }
            }
            return result;
        } catch (MatrixIndexException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
