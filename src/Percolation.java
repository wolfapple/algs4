import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private int top = 0;
    private int bottom;
    private int size;
    private int openSites = 0;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        grid = new boolean[n][n];
        bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRange(row, col);
        grid[row-1][col-1] = true;
        int gridIndex = getGridIndex(row, col);
        if (row == 1) {
            uf.union(gridIndex, top);
        }
        if (row == size) {
            uf.union(gridIndex, bottom);
        }
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(gridIndex, getGridIndex(row - 1, col));
        }
        if (row < size && isOpen(row + 1, col)) {
            uf.union(gridIndex, getGridIndex(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(gridIndex, getGridIndex(row, col - 1));
        }
        if (col < size && isOpen(row, col + 1)) {
            uf.union(gridIndex, getGridIndex(row, col + 1));
        }
        openSites += 1;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRange(row, col);
        return grid[row-1][col-1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRange(row, col);
        return uf.connected(top, getGridIndex(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    private void validateRange(int row, int col) {
        if (!(row >= 1 && row <= size && col >= 1 && col <= size)) {
            throw new IllegalArgumentException();
        }
    }

    private int getGridIndex(int row, int col) {
        return (row - 1) * size + col;
    }
}
