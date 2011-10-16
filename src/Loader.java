import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author David
 * @description Load a matrix from ... will use only core data types.
 */
public class Loader {
	private int[] function;
	private int[][] equation;
	private int[] finalVars;
	
	public int[] Function() {
		return this.function;
	}
	
	public int[][] Equation() {
		return this.equation;
	}
	
	public int[] FinalVars() {
		return this.finalVars;
	}
	
	public Loader(String path) throws FileNotFoundException, IOException {
		File input = new File(path);
		FileReader source = new FileReader(input);
		BufferedReader bufRead = new BufferedReader(source);
		
		String line;    // String that holds current file line
		             
		// Read first line
		line = bufRead.readLine();
		this.function = setFunction(line);        
		
		ArrayList<String> rows = new ArrayList<String>();
		ArrayList<String> finals = new ArrayList<String>();
		
		// Read through file one line at time. Print line # and line
		while (true) {
		//	System.out.println(count+": "+line);
			line = bufRead.readLine();
			if(line == null)
				break;
			String[] res = line.split("\t");
			rows.add(res[0]);
			finals.add(res[1]);
		}
		
		this.equation = setEquations(rows);
		this.finalVars = setFinalVars(finals);
	}
	
	protected int[] setFunction(String line) {
		String[] functionLine = line.split(" ");
		functionLine = clearEmpty(functionLine);
		int[] function = new int[functionLine.length];
		for(int i = 0; i < function.length; i++) {
			functionLine[i] = functionLine[i].trim();
			if(!functionLine[i].isEmpty())
				function[i] = Integer.parseInt(functionLine[i]);
		}
		return function;
	}
	
	protected int[][] setEquations(ArrayList<String> rows) {
		int[][] equations;
		String[] equationString = new String[rows.size()];
		int i = 0;
		for(String val: rows) {
			equationString[i] = val;
			i++;
		}
		
		equations = new int[equationString.length][];
		for(i = 0; i < equationString.length; i++) {
			String[] tmp = equationString[i].split(" ");
			tmp = clearEmpty(tmp);
			equations[i] = new int[tmp.length];
			for(int j = 0; j < equations[i].length; j++) {
				equations[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		return equations;
	}
	
	protected int[] setFinalVars(ArrayList<String> finals) {
		int[] finalVars = new int[finals.size()];
		int i = 0;
		for(String a: finals) {
			finalVars[i] = Integer.parseInt(a);
			i++;
		}
		return finalVars;
	}
	
	protected String[] clearEmpty(String[] broken) {
		ArrayList<String> fixed = new ArrayList<String>();
		for(int i = 0; i < broken.length; i++) {
			if(!broken[i].isEmpty()) {
				fixed.add(broken[i]);
			}
		}
		return fixed.toString().replace("[", "").replace("]", "").replace(",", "\n").split("\n ");
	}
}
