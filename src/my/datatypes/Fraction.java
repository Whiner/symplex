package my.datatypes;
/**
 * @author David
 *
 */
public class Fraction {
	protected int numerator;
	protected int denominator;
	
	public int Numerator() {
		return numerator;
	}
	
	public int Denominator() {
		return denominator;
	}
	
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public Fraction(int numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}
	
	public static int EuclidGCD(int first, int second) {
		while(second != 0) {
			int r = first % second;
			first = second;
			second = r;
		}
		return first;
	}
	
	public static Fraction AddFractions(Fraction first, Fraction second) {
		int numerator, denominator;
		denominator = findCommonDenominator(first, second);
		numerator = denominator * (first.numerator + second.numerator);
		return SimplifyFraction(new Fraction(numerator, denominator));
	}
	
	public static Fraction AddFractions(Fraction first, int num) {
		Fraction second = new Fraction(num);
		return AddFractions(first, second);
	}
	
	public Fraction addFraction(Fraction term) {
		this.denominator = findCommonDenominator(this, term);
		this.numerator = this.denominator * (this.numerator + term.numerator);
		return SimplifyFraction(this);
	}
	
	public Fraction addFraction(int num) {
		Fraction term = new Fraction(num);
		return addFraction(term);
	}
	
	public static Fraction SubtractFractions(Fraction first, Fraction second) {
		int numerator, denominator;
		denominator = findCommonDenominator(first, second);
		numerator = denominator * (first.numerator - second.numerator);
		return SimplifyFraction(new Fraction(numerator, denominator));
	}
	
	public static Fraction SubtractFractions(Fraction first, int num) {
		Fraction second = new Fraction(num);
		return SubtractFractions(first, second);
	}
	
	public Fraction subtractFraction(Fraction term) {
		this.denominator = this.denominator * term.denominator;
		this.numerator = this.denominator * (this.numerator - term.numerator);
		return SimplifyFraction(this);
	}
	
	public Fraction subtractFraction(int num) {
		Fraction term = new Fraction(num);
		return subtractFraction(term);
	}
	
	public static Fraction MultiplyFractions(Fraction first, Fraction second) {
		int numerator, denominator;
		numerator = first.numerator * second.numerator;
		denominator = first.denominator * second.denominator;
		return SimplifyFraction(new Fraction(numerator, denominator));
	}
	
	public Fraction multiplyFraction(Fraction term) {
		this.numerator = this.numerator * term.numerator;
		this.denominator = this.denominator * term.denominator;
		return SimplifyFraction(this);
	}
	
	public Fraction multiplyFraction(int num) {
		Fraction term = new Fraction(num);
		return multiplyFraction(term);
	}
	
	public static Fraction DivideFractions(Fraction first, Fraction second) {
		second = new Fraction(second.denominator, second.numerator);
		return MultiplyFractions(first, second);
	}
	
	public Fraction divideFraction(Fraction term) {
		term = new Fraction(term.denominator, term.numerator);
		return this.multiplyFraction(term);
	}
	
	public Fraction divideFraction(int num) {
		Fraction term = new Fraction(num);
		return this.divideFraction(term);
	}
	
	public static boolean FractionsCompare(Fraction first, Fraction second) {
		if(first.numerator == second.numerator && first.denominator == second.denominator) {
			return true;
		}
		else return false;
	}
	
	public boolean fractionCompare(Fraction term) {
		if(this.numerator == term.numerator && this.denominator == term.denominator) {
			return true;
		}
		else return false;
	}
	
	public boolean fractionCompare(int num) {
		Fraction term = new Fraction(num);
		return fractionCompare(term);
	}
	
	/**
	 * @param
	 * @param second
	 * @return a correct denominator, but not the simplest.
	 */
	protected static int findCommonDenominator(Fraction first, Fraction second) {
		int denominator;
		if(first.denominator != second.denominator) {
			denominator = first.denominator * second.denominator;	
		}
		else denominator = first.denominator;
		return denominator;
	}
	
	public static Fraction SimplifyFraction(Fraction main) {
		int simplifyer = EuclidGCD(main.numerator, main.denominator);
		main.denominator /= simplifyer;
		main.numerator /= simplifyer;
		return main;
	}
}
