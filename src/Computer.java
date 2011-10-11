import java.io.FileNotFoundException;
import java.io.IOException;

import my.datatypes.Fraction;

/**
 * @author David
 * @description The simplex-method computer.
 */
public class Computer {
	protected Fraction[][] table;
	protected Fraction[] marks;
	protected Fraction[] arguments;
	protected Fraction[] freeElems;
	protected int[] basis; //holds the indexes of basis variables. basis[1] can be x6, the basis variable of <second> row is <6>
	protected Fraction result;
	
	public Computer() throws FileNotFoundException, IOException {
			Loader instance = new Loader("E:\\text.txt");
			this.table = Fraction.IntegerMatrixTranslate(instance.Equation());
			this.arguments = Fraction.IntegerArrayTranslate(instance.Function());
			this.freeElems = Fraction.IntegerArrayTranslate(instance.FinalVars());
	}
	
	public int[] initBasis(Fraction[][] table) {
		basis = new int[table.length];
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				if(table[i][j].fractionCompare(1)) {
					//basis?
					boolean isBasis = true;
					for(int k = 0; k < table.length; k++) {
						if(!table[k][j].fractionCompare(0) && k != i) { 	//check the whole column, except the same element.
							//this is not the basis
							isBasis = false;
							break;
						}
					}
					
					if(isBasis) {
						basis[i] = j;
						break;
					}
				}
			}
		}
		return this.basis;
	}
	
	public Fraction[] initMarks(Fraction[][] table, Fraction[] arguments, int[] basis) {
		marks = new Fraction[table[0].length];
		//lets make all marks = 0
		for(int i = 0; i < marks.length; i++) {
			marks[i] = new Fraction(0);
		}
		
		//find the real marks
		for(int i = 0; i < table.length; i++) { 
			for(int j = 0; j < table[i].length; j++) {
				//forgot the formula. need to write it in comments.
				marks[j].addFraction(Fraction.MultiplyFractions(table[i][j],arguments[basis[i]])); 
			}
		}
		
		//the last part of formula
		for(int i = 0; i < marks.length; i++) {
			marks[i].subtractFraction(arguments[i]);
		}
		
		return this.marks;
	}
	
	public Fraction initResult(Fraction[] freeElems, Fraction[] arguments, int[] basis) {
		result = new Fraction(0); //set result to 0
		for(int i = 0; i < freeElems.length; i++) {
			result.addFraction(Fraction.MultiplyFractions(freeElems[i], arguments[basis[i]]));
		}
		return this.result;
	}
}
