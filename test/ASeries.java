package test;

import java.util.*;
import java.util.HashMap;

/**
 * find the longest arithmetic series in a given input
 * 
 * @author Danxun Jiao
 *
 */

public class ASeries {

	public static void main(String[] args) {
		ASeries aSeries = new ASeries();
		String value = "{3,8,4,5,6,2,2}";
		int ret = aSeries.longest(value);
		System.out.println("The longest arithmetic series is: " + ret);
	}

	private int longest(String value) {
		int ret = 0;

		String ss = value.substring(1, value.length() - 1);
		String[] orginalArray = ss.split(",");
		int sizeOfArray = orginalArray.length;
		if (sizeOfArray == 1)
			return 0;
		int[] orderedArray = new int[sizeOfArray];
		// convert String to int
		for (int i = 0; i < sizeOfArray; i++) {
			orderedArray[i] = Integer.parseInt(orginalArray[i]);
		}
		// sort the original list
		Arrays.sort(orderedArray);

		//build the HashMap, the key is the difference of every two numbers, the value is count of this difference
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = sizeOfArray - 1; i > 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				Integer diff = orderedArray[i] - orderedArray[j];

				if (map.containsKey(diff)) {
					Integer count = map.get(diff);
					count++;
					map.put(diff, count);
				} else {
					map.put(diff, 1);
				}
			}
		}

		// sort the HashMap by value
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1,
					Map.Entry<Integer, Integer> o2) {
				if (o2.getValue().compareTo(o1.getValue()) > 0)
					return 1;
				else
					return -1;

			}

		});
		
		//get the longest value
		ret = list.get(0).getValue();

		return ret;
	}

}
