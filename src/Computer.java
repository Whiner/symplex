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
	private final int M = 1000;
	
	public Computer() throws FileNotFoundException, IOException {
			Loader instance = new Loader("E:\\text.txt");
			//initial data
			this.table = Fraction.IntegerMatrixTranslate(instance.Equation());
			this.arguments = Fraction.IntegerArrayTranslate(instance.Function());
			this.freeElems = Fraction.IntegerArrayTranslate(instance.FinalVars());
			this.basis = initBasis(this.table);
			this.marks = initMarks(this.table, this.arguments, this.basis);
			this.result = initResult(this.freeElems, this.arguments, this.basis);
	}
	
	protected int[] initBasis(Fraction[][] table) {
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
	
	protected Fraction[] initMarks(Fraction[][] table, Fraction[] arguments, int[] basis) {
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
	
	protected Fraction initResult(Fraction[] freeElems, Fraction[] arguments, int[] basis) {
		result = new Fraction(0); //set result to 0
		for(int i = 0; i < freeElems.length; i++) {
			result.addFraction(Fraction.MultiplyFractions(freeElems[i], arguments[basis[i]]));
		}
		return this.result;
	}

	public void Step() {
		int col = maxMark(marks);
		int row = minDivision(table, freeElems, col);

		//table[row][col] - is the element;
		Fraction elem = new Fraction(table[row][col]);
		for(int i = 0; i < table[row].length; i++) {
			table[row][i].divideFraction(elem);
		}
		
		for(int i = 0; i < table.length; i++) {
			if(i == row) 
				continue;
			Fraction term = Fraction.MultiplyFractions(table[i][col], new Fraction(-1));
			for(int j = 0; j < table[i].length; j++) {
				elem = Fraction.MultiplyFractions(table[row][j], term);
				table[i][j].addFraction(elem);
			}
		}
		
		System.out.println();
		System.out.println();
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				System.out.print(table[i][j].Numerator() + "/" + table[i][j].Denominator() + "\t");
			}
			System.out.println();
		}
	}
	
	protected int maxMark(Fraction[] input) {
		Fraction max = new Fraction(input[0]);
		int maxI = 0;
		for(int i = 1; i < input.length; i++) {
			if(Fraction.GreaterEquals(input[i], max)) {
				max = new Fraction(input[i]);
				maxI = i;
			}
		}
		return maxI;
	}
	
	protected int minDivision(Fraction[][] input, Fraction[] freeElems, int col) {
		int minI = 0;
		Fraction min = new Fraction(M);
		for(int i = 0; i < freeElems.length; i++) {
			if(input[i][col].isPositive()) {
				if(Fraction.DivideFractions(freeElems[i], input[i][col]).less(min)) {
					min = new Fraction(Fraction.DivideFractions(freeElems[i], input[i][col]));
					minI = i;
				}
			}
		}
		return minI;
	}
}
