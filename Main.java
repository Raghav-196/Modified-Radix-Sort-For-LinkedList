import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;


/**
 * Does the comparison between time taken to sort data set using modified radix sort and the default mergesort 
 * for a linked list.  
 */
public class Main {

	public static void main(String[] args) {
		int size = 100000;				// size of list
		int MAX = Integer.MAX_VALUE;	// Max value of elements in the list
		Random random = new Random();	// for generating random integers

		LinkedList<Integer> list1 = new LinkedList<>();
		MyLinkedList<MyInteger> list2 = new MyLinkedList<>(MAX);
		int[] arr = new int[size];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(MAX);
			list1.add(arr[i]);
			list2.add(new MyInteger(arr[i]));
		}

		// Sorting the linked list using the modified version of radixsort
		long start = System.currentTimeMillis();
		long ns = System.nanoTime();

		list2.sort();

		long ne = System.nanoTime();
		long end = System.currentTimeMillis();

		System.out.printf("Time to sort linked list using modified radixsort :%n=%d ns%n=%d ms%n",ne-ns,end-start);
		System.out.println("==========================================================");
		
		
		// Sorting the linked list using default method - the mergesort
		start = System.currentTimeMillis();
		ns = System.nanoTime();

		list1.sort(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});

		ne = System.nanoTime();
		end = System.currentTimeMillis();

		System.out.printf("Time to sort linked list using default mergesort :%n=%d ns%n=%d ms%n",ne-ns,end-start);
		System.out.println("==========================================================");
		
		
		// Sorting the corresponding array using dual-pivot quicksort 
		start = System.currentTimeMillis();
		ns = System.nanoTime();

		Arrays.sort(arr);

		ne = System.nanoTime();
		end = System.currentTimeMillis();

		System.out.printf("Time to sort corresponding array using dual-pivot quicksort :%n=%d ns%n=%d ms%n",ne-ns,end-start);
		System.out.println("==========================================================");
		
		
		// Verify the sort for displayable size of the list
		if (size <= 100) {
			System.out.println("\nSorted Using Improved Radix Sort : ");
			System.out.println(list2);
			System.out.println("\nSorted using default method of linked list (MergeSort) : ");
			System.out.println(list1);
		}
	}
}
