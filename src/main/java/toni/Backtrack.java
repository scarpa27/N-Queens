package toni;

public class Backtrack {
    int N;

    public Backtrack(int n) {
        N = n;
    }

    // print the final solution matrix
    void printSolution(int board[][])
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j]
                        + " ");
            System.out.println();
        }
    }

    // function to check whether the position is safe or not
    boolean isSafe(int board[][], int row, int col)
    {
        int i, j;
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;


        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // The function that solves the problem using backtracking
    public boolean solveNQueen(int board[][], int col)
    {
        if (col >= N)
            return true;

        for (int i = 0; i < N; i++) {
            //if it is safe to place the queen at position i,col -> place it
            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                if (solveNQueen(board, col + 1))
                    return true;

                //backtrack if the above condition is false
                board[i][col] = 0;
            }
        }
        return false;
    }

    public void main()
    {
        long time=System.currentTimeMillis();
        int polje[][]=new int[N][N];


        if (!solveNQueen(polje, 0)) {
            System.out.print("Solution does not exist");
            return;
        }

        System.out.println("Proteklo "+(System.currentTimeMillis()-time)+"ms");

    }}
