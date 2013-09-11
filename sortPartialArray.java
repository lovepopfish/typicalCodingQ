
public class sortPartialArray {
	public void sort(int[] arr){
		int leftEnd = findLeftEnd(arr);
		int rightStart = findRightStart(arr);
		int min = leftEnd+1;
		int max = rightStart-1;
		if (min>=arr.length) return;
		for(int i=leftEnd+1;i<rightStart;i++){
			if (arr[i] < arr[min]) min = i;
			if (arr[i] > arr[max]) max = i;
		}
		int l = leftSort(arr,min,leftEnd);
		int r = rightSort(arr,max,rightStart);
		System.out.println(l + "--->" + r);
	}
	private int findLeftEnd(int[] arr){
		for(int i=1;i<arr.length;i++){
			if (arr[i] < arr[i-1]) {
				return i-1;
			}
		}
		return arr.length - 1;
	}
	private int findRightStart(int[] arr){
		for(int i=arr.length-2;i>=0;i--){
			if (arr[i] > arr[i+1]){
				return i+1;
			}
		}
		return 0;
	}
	private int leftSort(int[] arr, int min, int leftEnd){
		for(int i=leftEnd-1;i>=0;i--){
			if (arr[i] <= arr[min]){
				return i+1;
			}
		}
		return 0;
	}
	private int rightSort(int[] arr, int max, int rightStart){
		for(int i=rightStart+1;i<arr.length;i++){
			if (arr[i] >= arr[max]){
				return i-1;
			}
		}
		return arr.length-1;
	}
	
	public static void main(String[] args){
		sortPartialArray s = new sortPartialArray();
		s.sort(new int[]{1,2,4,7,10,11,7,12,6,7,16,18,19});
		
	} 
}
