package application;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class TestMain {

	public static void main(String[] args) {
		Evaluator e = new Evaluator();
		Operations O = new Operations();
		
		String e1 = "3.3+6";
//		String e2 = "Neg1";
//		String e3 = "2*6";
//		String e4 = "1+2*3";
//		String e5 = "(1+2)*2";
//		
		
		String[] tests = {e1};
		
		for (int i =0;i<tests.length;i++) {
			System.out.println("Test"+(i+1)+" :"+e.evaluate(tests[i]));
		}
		

	}

}
