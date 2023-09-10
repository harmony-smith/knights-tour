package code;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Input grid dimension: ");
		int size = input.nextInt();
		
		LinkedGrid lg = new LinkedGrid(size);
		lg.link();
		
		System.out.println("Paths Found: ");
		lg.tour(size);
		System.out.print(lg.getPaths());
		input.close();
	}

}
