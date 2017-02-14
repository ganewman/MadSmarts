public class SelectionSort{
    public static void sort(int[] arr){
	for(int maxPos = arr.length - 1; maxPos > 0; maxPos --){
	    //index = linear search result for largest in the range [0,maxPos]
	    int index = 0;
	    for(int i = 0; i <= maxPos; i ++){
		if (arr[index] < arr[i])
		    index = i;
	    }
	    int tmp = arr[index];
	    arr[index] = arr[maxPos];
	    arr[maxPos] = tmp;
	}
    }
}
