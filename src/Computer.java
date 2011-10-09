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
	
	public Computer() {
		int[] arg = {1, 2}; //this is temporary. delete this code.
		
		this.table = new Fraction[2][];
		for(int i = 0; i < 2; i++){ //this is temporary. delete this code
			this.table[i] = new Fraction[2];
			for(int j = 0; j < 2; j++) {
				this.table[i][j] = new Fraction(0);
			}
			this.table[i][i] = new Fraction(1);
		}
		
		this.arguments = new Fraction[arg.length];
		for(int i = 0; i < arg.length; i++) {
			this.arguments[i] = new Fraction(arg[i]);
		}
		
		marks = new Fraction[arg.length];
		
	}
	
	public int[] initBasis(Fraction[][] table) {
		basis = new int[table.length];
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				if(table[i][j].fractionCompare(1)) {
					//basis?
					boolean isBasis = true;
					for(int k = 0; k < table.length; k++) {
						if(!table[k][j].fractionCompare(0) && k != i) {
							//this is not the basis
							isBasis = false;
							break;
						}
					}
					
					if(isBasis) {
						basis[i] = j;
						continue;
					}
				}
			}
		}
		return this.basis;
	}
}
