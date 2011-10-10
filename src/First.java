import my.datatypes.Fraction;

public class First {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Computer first = new Computer();
		int[] basis = first.initBasis(first.table);
		Fraction[] marks = first.initMarks(first.table, first.arguments, basis);
		
		for(int i = 0; i < first.table.length; i++) {
			System.out.print(basis[i] + "\t");
			for(int j = 0; j < first.table[i].length; j++) {
				System.out.print(first.table[i][j].Numerator() + " ");
			}
			System.out.println();
		}
		
		System.out.print(" \t");
		for(int i = 0; i < marks.length; i++) {
			System.out.print(marks[i].Numerator() + " ");
		}
	}
}
