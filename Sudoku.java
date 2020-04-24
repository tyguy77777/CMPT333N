package project3;

public class Sudoku {
	// function to make sure 9 unique numbers rule applies for columns and rows
	public static boolean numCheck(int[][] board, int row, int col, int num)
	{ 	// row has all unique numbers
		for (int d = 0; d < board.length; d++)
		{
			// if the number we are trying to place is already in that row, return false;
			if (board[row][d] == num)
			{
				return false;
			}
		}
		// column has all unique numbers
		for (int r = 0; r < board.length; r++)
		{
			// if the number we are trying to place is already in that column, return false;
	  
			if (board[r][col] == num)
			{
				return false;
			}
		}
	  
		// check corresponding squares for unique numbers 
		int sqrt = (int) Math.sqrt(board.length);
		int boxRowStart = row - row % sqrt;
		int boxColStart = col - col % sqrt;
	  
		for (int r = boxRowStart;
				r < boxRowStart + sqrt; r++)
		{
			for (int d = boxColStart;
					d < boxColStart + sqrt; d++)
			{
				if (board[r][d] == num)
				{
					return false;
				}
			}
		}
	  
		// if all checks satisfy, return true
		return true;
		}
	//function to solve the remaining numbers needed in the puzzle  
	public static boolean Solve(int[][] board, int n)
	{
		int row = -1;
		int col = -1;
		boolean isEmpty = true;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (board[i][j] == 0)
				{
					row = i;
					col = j;
	  
					// we still have some remaining missing values in Sudoku
					isEmpty = false;
					break;
				}
			}
			if (!isEmpty)
			{
				break;
			}
		}
	  
		// no empty space left
		if (isEmpty)
		{
			return true;
		}
	  
		for (int num = 1; num <= n; num++)
		{
			if (numCheck(board, row, col, num))
			{
				board[row][col] = num;
				if (Solve(board, n))
				{
					return true;
				}
				else
				{
					// replace it
					board[row][col] = 0; 
				}
			}
		}
		return false;
		}
	// Answer is obtained, but it must be printed with this function  
	public static void print(int[][] board, int N)
	{
		for (int r = 0; r < N; r++)
		{
			for (int d = 0; d < N; d++)
			{
				System.out.println("Here is your solution: ");
				System.out.print(board[r][d]);
				System.out.print(" ");
			}
			System.out.print("\n");
	  
			if ((r + 1) % (int) Math.sqrt(N) == 0)
			{
				System.out.print("");
			}
		}
	}
	  
	// Driver Code
	public static void sudokuMain(String args[])
	{
	  // Puzzle can be edited directly in the code to further test
		int[][] board = new int[][]
				{
			{5, 3, 0, 0, 7, 0, 0, 0, 0},
			{6, 0, 0, 1, 9, 5, 0, 0, 0},
			{0, 9, 8, 0, 0, 0, 0, 6, 0},
			{8, 0, 0, 0, 6, 0, 0, 0, 3},
			{4, 0, 0, 8, 0, 3, 0, 0, 1},
			{7, 0, 0, 0, 2, 0, 0, 0, 6},
			{0, 6, 0, 0, 0, 0, 2, 8, 0},
			{0, 0, 0, 4, 1, 9, 0, 0, 5},
			{0, 0, 0, 0, 8, 0, 0, 7, 9}
		};
		int N = board.length;
	  
		if (Solve(board, N))
		{
			// print solution
			print(board, N); 
		}
		else
		{
			// Check needed for if a puzzle cannot be solved
			System.out.println("No solution possible");
		}
	}
}

