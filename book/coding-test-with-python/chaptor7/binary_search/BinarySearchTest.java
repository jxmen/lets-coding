package chaptor7.binary_search;

import java.util.Arrays;

public class BinarySearchTest {

	public static void main(String[] args) {
		int[] ints = new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

		int searched = Arrays.binarySearch(ints, 7);

		System.out.println("searched = " + searched);
	}
}
