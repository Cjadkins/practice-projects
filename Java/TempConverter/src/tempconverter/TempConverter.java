/*
 * Author: Cort Adkins
 */

package tempconverter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class TempConverter {

    public static void main(String[] args) throws IOException {

		//Initialize variables and arrays
		int count = 0, i = 0, k = 0, size = 100;
		double[] tempArr = new double[size];
		double[] conversion = new double[size];
		String[] degreeArr = new String[size];
                String fare = "F"; 
                String cel = "C";

		//pull file name from command line
		File tempFile = null;
		if (0 < args.length) {
			tempFile = new File(args[0]);
		} else {
			System.exit(0);
		}

		// Print title and column headers for temperature table
		System.out.println("Temperature Converter by Cort Adkins\n");
		System.out.println("Input\tType\tConversion");
		System.out.println("--------------------------");
		
		//scan file given from command line. Scans for double first 
                //followed by a string and stores in seperate arrays
		Scanner scan = new Scanner(tempFile);
		while (scan.hasNextLine()) {
			if (scan.hasNextDouble()) {
				tempArr[i] = scan.nextDouble();
				count++;
				i++;
			} else {
				degreeArr[k] = scan.next();
				k++;
			}

		}

		//loop to print out info and convert temperature
		for (i = 0; i < count; i++) {
			System.out.printf("%.2f", tempArr[i]);
			System.out.printf("\t%3s\t", degreeArr[i]);
			if (degreeArr[i].contains("C")) {
				conversion[i] = (tempArr[i] * 9) / 5 + 32;
				System.out.printf("%.2f %2s\n",conversion[i],fare);
			} else {
				conversion[i] = (tempArr[i] - 32) * 5 / 9;
				System.out.printf("%.2f %2s\n",conversion[i],cel);
			}
		}
		scan.close();
	}
} 