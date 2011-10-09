import my.datatypes.Fraction;

/**
 * @author David
 * @description The simplex-method computer.
 */
public class Computer {
	protected Fraction[][] table;
	protected Fraction[] marks;
	protected Fraction[] arguments;
	protected int[] basis;
	
	public void init() {
		int[] arg = {1, 2}; //this is temporary. delete this code.
		
		int[][] tab = new int[2][]; //this is temporary. delete this code.
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++) {
				tab[i][j] = 0;
			}
			tab[i][i] = 1;
		}
		
		this.arguments = new Fraction[arg.length];
		for(int i = 0; i < arg.length; i++) {
			this.arguments[i] = new Fraction(arg[i]);
		}
		
		marks = new Fraction[arg.length];
		
	}
	
	public int[] initBasis(Fraction[][] table) {
		
		return this.basis;
	}
}
