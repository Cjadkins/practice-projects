package jacsim;

/*
 * University of Central Florida
 * COP 3330 - Fall 2015
 * Author: Cort Adkins  
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class JacSim {

	public static void main(String[] args) throws FileNotFoundException{
		
		//Read in file from command line
		File file = null;
		if (0 < args.length) {
			file = new File(args[0]);
		} else {
			System.exit(0);
		}

		//scan file into myList while there is a next line to scan
		Scanner scan = new Scanner(file);
		String line;
		List<String> myList = new ArrayList<String>();
		int count = 0;
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			myList.add(line);
			count++;
		}	
		
		//print the sentences on separate numbered lines
		System.out.println("Input Sentences:\n");
		for (int i=0; i<count; i++){
			String c = myList.get(i);						
			System.out.println(i + " : " + c);
		}				
	
		//print the sorted shingle arrays
		System.out.println("\nSorted Shingle Arrays:\n");
		String[] temp = new String[myList.size()];
		for (int i=0; i<=count-1; i++){
			String c = myList.get(i);
			temp = shingles(c);
			System.out.println(i + " : " + Arrays.toString(temp));
		}
		
		//print Jaccard Similarity Matrix
		String[] temp2 = new String[myList.size()];
		String[] temp3 = new String[myList.size()];
		System.out.println("\nJaccard Similarity Matrix:\n");
		String a = myList.get(0);
		temp2 = shingles(a);
		double index = 0.0;
		double max = 0;
		int[] placeholder = new int[count];
		for (int i=1; i<=count-1; i++){
			String c = myList.get(i);
			temp = shingles(c);
			index = compare(temp2, temp);
			if (index > max){
				placeholder[0] = i;
				max = index;
			}
			System.out.printf("%1.4f\n" , index);
			temp2 = shingles(c);
		}
		//get first and last sentence jaccard value
		String last = myList.get(count-1);
		temp2 = shingles(a);
		temp3 = shingles(last);
		index = compare(temp2, temp3);
		if (index > max){
			placeholder[0] = count-1;
			max = index;
		}
		System.out.printf("%1.4f\n" , index);
		System.out.printf("\nMost similar sentence: %d and %d with "
                        + "Jaccard value = %1.4f\n" , placeholder[0], placeholder[0]-1, max);
		scan.close();
	}

	//method to add substring to a shingles set then sort in an array
	private static String[] shingles(String line) {
	    HashSet<String> shingles = new HashSet<String>();
	    String[] array = new String[line.length()];
	    for (int i = 0; i < line.length() - 1; i++) {
	      String shingle = line.substring(i, i + 2);
	      shingles.add(shingle);
	      array = shingles.toArray(new String[shingles.size()]);
	    }
	    Arrays.sort(array);
	    return array;
	  }
	
	//method to compute jaccard index
	private static double compare(String[] a, String[] b){
		double size = a.length + b.length;
		int common = 0; 
		
		List<String> myList = Arrays.asList(b);
		for (int i=0; i<a.length; i++){
			if (myList.contains(a[i])){
				common++;
			}
		}
		double index = (double)common /(size - common);
		return index;	
	}
}
