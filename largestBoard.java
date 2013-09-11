
public class largestBoard {
	public static int[] findSquare(int[][] matrix){
		for(int len=matrix.length;len>=1;len--){
			int[] ret = findSquare(matrix,len);
			if (ret!=null) return ret;
		}
		return null;
	}
	private static int[] findSquare(int[][] matrix, int len){
		int[] ret = null;
		int count = matrix.length - len + 1;
		for(int i=0;i<count;i++){
			for(int j=0;j<count;j++){
				if(isValid(matrix,i,j,len)){
					if (ret == null || len > ret[2]){
						if (ret==null){
							ret = new int[3];
						}
						ret[0] = i;
						ret[1] = j;
						ret[2] = len;
					}
				}
			}
		}
		return ret;
	}
	private static boolean isValid(int[][] matrix, int row, int col, int len){
		for(int j=0;j<len;j++){
			if (matrix[row][col+j]==1){
				return false;
			}
			if (matrix[row+len-1][col+j]==1){
				return false;
			}
		}
		for(int i=1;i<len-1;i++){
			if (matrix[row+i][col]==1){
				return false;
			}
			if (matrix[row+i][col+len-1]==1){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args){
		int[][] matrix = {{0,0,0,0},
						  {1,0,0,0},
						  {1,0,1,0},
						  {1,0,0,0}};
		int[] ret = largestBoard.findSquare(matrix);
		System.out.println("The largest board start at (" + ret[0] + "," + ret[1] + "), and len is-->" + ret[2]);
	}
}



