public class InsertionSort {

    public static void sort(int[] data){
	for (int i = 1; i < data.length; i++){
	    for (int x = i; x > 0; x--){
		if (data[x] < data[x - 1]){
		    int tempVal = data[x];
		    data[x] = data[x-1];
		    data[x-1] = tempVal;
		}
	    }
	}
    }

    public static void main(String[] args){
    }

} 
