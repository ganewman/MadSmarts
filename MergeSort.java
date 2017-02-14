/*=====================================
  class MergeSort
  Implements mergesort on array of ints.
  Summary of Algorithm: 
  ======================================*/

public class MergeSort {

   /******************************************************
     * int[] merge(int[],int[]) 
     * Merges two input arrays
     * Precond:  Input arrays are sorted in ascending order
     * Postcond: Input arrays unchanged, and 
     * output array sorted in ascending order.
     ******************************************************/
    private static int[] merge( int[] a, int[] b ) 
    {
	int length = a.length + b.length;
	int[] retArr = new int[length];
	int indexA = 0;//these are the indices that we are checking 
	int indexB = 0;
	for(int x = 0; x < length; x++){
	    if(indexA >= a.length){
		//this means that all of a is in the retArr
		//so you wanna put the rest of b into reArr
		for(int i = indexB; i < b.length; i++){//start at indexB and go through B until the end, adding each element to retArr 
		    retArr[x] = b[i];
		    x++;//increment x 
		}
	    }
	    else if(indexB >= b.length){
		//this means that all of b is in the retArr
		//so you wanna put the rest of a into reArr
		for(int n = indexA; n < a.length; n++){//start at indexA and go through A until the end, adding each element to retArr 
		    retArr[x] = a[n];
		    x++;//increment x 
		}
	    }
	    else if(a[indexA] <= b[indexB]){ 
		retArr[x] = a[indexA];
		indexA++;
	    }
	    else{
		retArr[x] = b[indexB];
		indexB++;
	    }
	}
	return retArr;
    }//end merge()


    /******************************************************
     * int[] sort(int[]) 
     * Sorts input array using mergesort algorithm
     * Returns sorted version of input array (ascending)
     ******************************************************/
    public static int[] sort( int[] arr ) 
    {
	if(arr.length == 1){//base case: if the length is 1, the dividing array thing has to stop 
	    return arr;
	}
	else{
	    int firstLength = (int)(arr.length / 2.0 + 0.5);//length of the array that'll hold the first half of arr
	    int secondLength = arr.length / 2;//second array that holds arr
	    int[] firstArr = new int[firstLength];
	    int[] secondArr = new int[secondLength];
	    int i = 0;//counter that represents the index of secondArr 
	    for(int x = 0; x < arr.length; x++){
		if(x < firstLength){//up until the first "firstLength-1 --th" index will be added to the first array 
		    firstArr[x] = arr[x];
		}
		else{//rest goes to second array
		    secondArr[i] = arr[x];
		    i++;
		}
	    }
	    return merge(sort(firstArr), sort(secondArr));//the recursive step: merge the sorted results of firstArr and that of secondArr
	}
    }//end sort()

    //-------------------HELPERS-------------------------
    //tester function for exploring how arrays are passed
    //usage: print array, mess(array), print array. Whaddayasee?
    public static void mess( int[] a ) {
	for( int i = 0 ; i<a.length; i++ )
	    a[i] = 0;
    }

    //helper method for displaying an array
    public static void printArray( int[] a ) {
	System.out.print("[");
	for( int i : a )
	    System.out.print( i + ",");
	System.out.println("]");
    }
    //---------------------------------------------------

    
    //main method for testing
    public static void main( String[] args ) {
	int[] arr0 = {0};
	int[] arr1 = {1};
	int[] arr2 = {1,2};
	int[] arr3 = {3,4};
	int[] arr4 = {1,2,3,4};
	int[] arr5 = {4,3,2,1};
	int[] arr6 = {9,42,17,63,0,512,23};
	int[] arr7 = {9,42,17,63,0,9,512,23,9};
        
	printArray( merge(arr4,arr7) );
	System.out.println("\nTesting mess-with-array method...");
	printArray( arr3 );
	mess(arr3);
	printArray( arr3 );
	System.out.println("\nMerging arr1 and arr0: ");
	printArray( merge(arr1,arr0) );
	System.out.println("\nMerging arr4 and arr6: ");
	printArray( merge(arr4,arr6) );
        
	System.out.println("\nSorting arr4-7...");
	printArray( sort( arr4 ) );
	printArray( sort( arr5 ) );
	printArray( sort( arr6 ) );
	printArray( sort( arr7 ) );
    }//end main()

}//end class MergeSort
