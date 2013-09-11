
public class maxSubMatrix {
	public static int search (int[][] matrix){
		int max = Integer.MIN_VALUE;
		int rowLen = matrix.length;
		int colLen = matrix[0].length;
		for(int rowStart = 0; rowStart < rowLen; rowStart++){
			int[] arr = new int[colLen];
			for(int rowEnd = rowStart; rowEnd < rowLen; rowEnd++){
				for(int i=0;i<colLen;i++){
					arr[i] += matrix[rowEnd][i];
				}
				int maxArray = maxSubArray(arr);
				max = Math.max(max,maxArray);
			}
		}
		return max;
	}
	public static int maxSubArray(int[] arr){
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for(int i=0;i<arr.length;i++){
			if (sum > 0) {
				sum += arr[i];
			} else {
				sum = arr[i];
			}
			max = Math.max(max,sum);
		}
		return max;
	}
	
	public static void main(String[] args){
		int[][] matrix = {{1, 2, -1, -4, -20},
      		  {-8, -3, 4, 2, 1},
      		  {3, 8, 10, 1, 3},
      		  {-4, -1, 1, 7, -6}
     			  };
		System.out.println(maxSubMatrix.search(matrix));
	}

}





