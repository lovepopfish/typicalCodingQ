
public class largestBoardOptimal {
	private class Cell{
		int zeroRight;
		int zeroBelow;
		public Cell(int zeroRight, int zeroBelow){
			this.zeroRight = zeroRight;
			this.zeroBelow = zeroBelow;
		}
	}
	public int[] findSquare(int[][] matrix){
		Cell[][] cell = processSquare(matrix);
		for(int len = matrix.length; len>=1; len--){
			int[] ret = findSquare(cell,len);
			if (ret!=null){
				return ret;
			}
		}
		return null;
	}
	public int[] findSquare(Cell[][] cell, int len){
		int[] ret = null;
		int count = cell.length - len + 1;
		for(int i = 0; i < count; i++){
			for(int j = 0; j < count; j++){
				if (isValid(cell, i, j, len)){
					if (ret == null || len > ret[2]){
						if (ret==null) ret = new int[3];
						ret[0] = i;
						ret[1] = j;
						ret[2] = len;
					}
				}
			}
		}
		return ret;
	}
	private Cell[][] processSquare(int[][] matrix){
		Cell[][] process = new Cell[matrix.length][matrix.length];
		for(int i = matrix.length-1; i >= 0; i--){
			for(int j = matrix.length-1; j>=0; j--){
				int zeroRight = 0;
				int zeroBelow = 0;
				if (matrix[i][j]==0){
					zeroRight++;
					zeroBelow++;
					if (i < matrix.length - 1){
						Cell prev = process[i+1][j];
						zeroBelow += prev.zeroBelow;
					}
					if (j < matrix.length - 1){
						Cell prev = process[i][j+1];
						zeroRight += prev.zeroRight;
					}
				}
				process[i][j] = new Cell(zeroRight,zeroBelow);
			}
		}
		return process;
	}
	public boolean isValid(Cell[][] process, int row, int col, int len){
		Cell topLeft = process[row][col];
		Cell topRight = process[row][col+len-1];
		Cell bottomLeft = process[row+len-1][col];
		if (topLeft.zeroRight < len){
			return false;
		}
		if (topLeft.zeroBelow < len){
			return false;
		}
		if (topRight.zeroBelow < len){
			return false;
		}
		if (bottomLeft.zeroRight < len){
			return false;
		}
		return true;
	}
	public static void main(String[] args){
		int[][] matrix = {{0,0,0,0},
						  {1,0,0,0},
						  {1,0,1,0},
						  {1,0,0,0}};
		largestBoardOptimal l = new largestBoardOptimal();
		int[] ret = l.findSquare(matrix);
		System.out.println("The largest board start at (" + ret[0] + "," + ret[1] + "), and len is-->" + ret[2]);
	}
}
