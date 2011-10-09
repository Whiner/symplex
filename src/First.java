public class First {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Computer first = new Computer();
		int[] basis = first.initBasis(first.table);
		for(int i = 0; i < first.table.length; i++) {
			System.out.print(basis[i] + "\t");
			for(int j = 0; j < first.table[i].length; j++) {
				System.out.print(first.table[i][j].Numerator() + " ");
			}
			System.out.println();
		}
	}
}
