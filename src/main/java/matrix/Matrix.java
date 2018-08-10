package matrix;

public abstract class Matrix {
    
    public abstract int get(int i, int j) throws MatrixIndexException;
    
    public abstract void put(int i, int j, int value) throws MatrixIndexException; 
        
    public abstract int getRowCount();

    public abstract int getColCount();
    
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Matrix:\n[ ");

        for (int i = 0; i < getRowCount(); i++) {
            if (i != 0) {
                out.append("\n");
                out.append("  ");
            }
            for (int j = 0; j < getColCount(); j++) {
                try {
                    out.append(get(i + 1, j + 1));
                    if (j != getColCount() - 1) {
                        out.append(" ");
                    }
                } catch (MatrixIndexException e) {

                }
                if (j == getColCount() - 1) {

                    out.append(" ]");
                }

            }
        }
        return out.toString();
    }   

    public String toStringOneColumn() {
        StringBuilder out = new StringBuilder();
        out.append("Matrix:\n[ \n");

        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColCount(); j++) {
                try {
                    out.append(get(i + 1, j + 1));
                    out.append("\n");
                } catch (MatrixIndexException e) {

                }
            }
        }
        out.append("]");
        return out.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Matrix m = (Matrix) obj;
        try {
            if (m.getRowCount() != getRowCount() || m.getColCount() != getColCount()) {
                return false;
            }
    
            for (int i = 0; i < getRowCount(); i++) {
                for (int j = 0; j < getColCount(); j++) {
                    if (this.get(i + 1, j + 1) != m.get(i + 1, j + 1)) {
                        return false;
                    }
                }
            }
    
            return true;
        } catch (MatrixIndexException ex) {
            return false;
        }
    }
}
