import java.io.FileNotFoundException;
import java.io.IOException;

import my.datatypes.Fraction;

public class First {
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {		
		Computer first = new Computer();
		int[] basis = first.initBasis(first.table);
		Fraction[] marks = first.initMarks(first.table, first.arguments, basis);
		Fraction result = first.initResult(first.freeElems, first.arguments, basis);
		
		System.out.print(" \t");
		for(int i = 0; i < first.arguments.length; i++) {
			System.out.print(first.arguments[i].Numerator() + " ");
		}
		
		System.out.println();
		for(int i = 0; i < first.table.length; i++) {
			System.out.print(basis[i] + "\t");
			for(int j = 0; j < first.table[i].length; j++) {
				System.out.print(first.table[i][j].Numerator() + " ");
			}
			System.out.print("\t" + first.freeElems[i].Numerator());
			System.out.println();
		}
		
		System.out.print(" \t");
		for(int i = 0; i < marks.length; i++) {
			System.out.print(marks[i].Numerator() + " ");
		}
		System.out.print("\t" + result.Numerator());
	}
}
