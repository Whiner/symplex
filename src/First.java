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
		System.out.print(" \t");
		for(int i = 0; i < first.arguments.length; i++) {
			System.out.print(first.arguments[i].Numerator() + "\t");
		}
		
		Print(first.table, first.marks, first.freeElems, first.basis, first.result);
		
		first.Step();
	}
	
	public static void Print(Fraction[][] table, Fraction[] marks, Fraction[] freeElems, int[] basis, Fraction result) {
		System.out.println();
		for(int i = 0; i < table.length; i++) {
			System.out.print(basis[i] + "\t");
			for(int j = 0; j < table[i].length; j++) {
				System.out.print(table[i][j].Numerator() + "/" + table[i][j].Denominator() + "\t");
			}
			System.out.print("\t" + freeElems[i].Numerator() + "/" + freeElems[i].Denominator());
			System.out.println();
		}
		
		System.out.print(" \t");
		for(int i = 0; i < marks.length; i++) {
			System.out.print(marks[i].Numerator() + "/" + marks[i].Denominator() + "\t");
		}
		System.out.print("\t" + result.Numerator() + "/" + result.Denominator());
		System.out.println();
	}
}
