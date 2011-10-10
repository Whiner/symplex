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
	public int[][] load(String path) throws FileNotFoundException, IOException {
		File input = new File(path);
		FileReader source = new FileReader(input);
		BufferedReader bufRead = new BufferedReader(source);
		
		String line;    // String that holds current file line
		             
		// Read first line
		line = bufRead.readLine();
		        
		//the function reading. first line
		String[] functionLine = line.split(" ");
		int[] function = new int[functionLine.length];
		for(int i = 0; i < function.length; i++) {
			functionLine[i] = functionLine[i].trim();
			function[i] = Integer.parseInt(functionLine[i]);
			//System.out.println(function[i]);
		}
		
		ArrayList<String> rows = new ArrayList<String>();
		ArrayList<String> finals = new ArrayList<String>();
		
		// Read through file one line at time. Print line # and line
		while (true) {
		//	System.out.println(count+": "+line);
			line = bufRead.readLine();
			if(line == null || line == "")
				break;
			String[] res = line.split("\t");
			rows.add(res[0]);
			finals.add(res[1]);
		}
		
		//the worst code ever. need to optimize
		String[] equationString = rows.toString().replace("[", "").replace("]", "").replace(",", "\n").split("\n ");
		int[][] equation = new int[equationString.length][];
		for(int i = 0; i < equationString.length; i++) {
			String[] tmp = equationString[i].split(" ");
			equation[i] = new int[tmp.length];
			for(int j = 0; j < equation.length; j++) {
				equation[i][j] = Integer.parseInt(tmp[j]);	
			}
		}
		
		String[] vars = finals.toString().replace("[", "").replace("]", "").replace(",", "\n").split("\n ");;
		int[] finalVars = new int[vars.length];
		for(int i = 0; i < finalVars.length; i++) {
			finalVars[i] = Integer.parseInt(vars[i]);
		}
		
		//small test
		{
			for(int i = 0; i < function.length; i++) {
				System.out.print(function[i] + " ");	
			}
			System.out.println();
			for(int i = 0; i < equation.length; i++) {
				for(int j = 0; j < equation[i].length; j++) {
					System.out.print(equation[i][j] + " ");	
				}
				System.out.println("\t" + finalVars[i]);
			}
		};
		return equation;
	}
}
