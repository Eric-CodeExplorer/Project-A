package application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Evaluator {
	
	public boolean is_DEG = true;		// True <-> DEG ; False <-> RAD
	
	public Evaluator() {
		Operations.initialize(); // Load all arithmetic functions 
	}
	
	private List<Token> parse(String expression) {
		List<Token> tokens = new ArrayList<>();
		String token = "";
//		int number = 0;		// Split a number into two parts : integer part and decimal part.
//	    double decimal = 0;		// This should be >= 0 and < 1
//	    int decimalPlaces = 1;
	    boolean isDecimal = false;
	    boolean flag = false; // A flag to distinguish between cases: "0+5" and "+5"
	    String buffer = ""; // To store characters for long name operations like "log"
	    
	    if (expression.isBlank()) {			// Check if the expression is empty
	    	tokens.add(ErrorToken.getDefaultToken());
	    	return tokens;
	    }
	    
	    
	    for (int i = 0; i < expression.length(); i++) {
	        char c = expression.charAt(i); // Go through the expression char by char
	        
	        // Integer or Decimal
	        if (c == '.') {
	        	if (isDecimal || buffer != "") {
	        		tokens.clear();
	        		tokens.add(ErrorToken.getDefaultToken());
	        		return tokens; // First case: two dots '.' Second case invalid operator: abc.123 
	        	}else {
	        		isDecimal = true;
	        		token += c;
	        	}
	        }else if (Character.isDigit(c)) {
	        	if (buffer != "") {
	        		tokens.clear();
	        		tokens.add(ErrorToken.getDefaultToken());
	        		return tokens; // invalid operator: abc123
	        	}
	        	if (!isDecimal) {
	        		flag = true;
	        	}
	        	token += c;
	        	
	        }else if (c == 'π' || c == 'e'){
	        	if (buffer != "" || token != "") {
	        		tokens.clear();
	        		tokens.add(ErrorToken.getDefaultToken());
	        		return tokens; 
	        	}
	        	tokens.add( (c == 'e') ? DecimalToken.token_e : DecimalToken.token_pi);
	        }else { 
	        	// Encounter a non-number character
	        	// Add new number/decimal token first
	        	if ( flag ) {
	        		if (isDecimal) {tokens.add(new NumberToken(token));}
	        		else { tokens.add(new DecimalToken(token)); }
	        		token = "";
//	                decimalPlaces = 1;
	                flag = false; 
	                isDecimal = false; // reset all flags
	        	}
	        	if(c == '(' || c == ')'){ //Parentheses
	        		tokens.add(new ParenthesesToken(c));
	        	}else if (OperatorToken.opMap.containsKey(Character.toString(c))) { // One char op : +,-,*,/,^
	        		tokens.add(OperatorToken.opMap.get(Character.toString(c)));
	        	}else { // Longer op: exp,log ...
	        		buffer += c;
	        		if (OperatorToken.opMap.containsKey(buffer)) {
	        			tokens.add(OperatorToken.opMap.get(buffer));
	        			buffer = "";
	        		}
	        	}
	        }
	    }
	    if ( flag ) { // Final check for numbers at the end
	    	if (isDecimal) {tokens.add(new NumberToken(token));}
    		else { tokens.add(new DecimalToken(token)); }
    		token = "";
    		isDecimal = false;
            flag = false; // reset all flags
    	}
	    
	    System.out.println("Parsing complete.");
	    for (Token t: tokens) {
	    	System.out.print(t.getSymbol());  // For testing
	    }
	    System.out.println();
	    
	    return tokens;
	}
	
	
	
	
	public List<Token> toPostfix(List<Token> infix) {
		List<Token> postfix = new ArrayList<>();
		if (infix.get(0).isErrorToken()) {
			postfix.add(infix.get(0));
			return postfix;
		}
		Stack<Token> operators = new Stack<>();
		
		for (Token t : infix) {
            if (t.isInteger() || t.isDecimal()) {
                postfix.add(t);	// A number or a decimal, add immediately  to the postfix .
            } else if (t.isOperator()) {
                OperatorToken operator = (OperatorToken) t;
                while (!operators.isEmpty() && operators.peek().getPriority() >= operator.getPriority()) {
                    postfix.add(operators.pop());
                    // pop operators higher or equal precedence from the stack and append them to the postfix , 
                    // then push the current operator onto the stack.
                }
                operators.push(operator);
            } else if (t.isParanthese()) {
            	if(t.isLeftParentheses()) {
                operators.push(t); //left parenthesis, push it onto the stack
            	}else {
            		while (!operators.isEmpty() && !operators.peek().isParanthese()) {
            			postfix.add(operators.pop()); //right parenthesis, 
            			//
            		}
            		operators.pop();
            	}
                
            }
        }
        while (!operators.isEmpty()) {
            postfix.add(operators.pop()); // Pop remaining to postfix
        }
        
        System.out.println("toPostfix complete.");
        for (Token t: postfix) {
	    	System.out.print(t.getSymbol());  // For test
	    }
	    System.out.println();
	    
	    return postfix;
	}
	
	
	
	private Token evaluation(List<Token> postfix) {
		if (postfix.get(0).isErrorToken()) {
			return postfix.get(0);
		}
		
		Stack<Token> stack = new Stack<>();
		for(Token t:postfix) {
			if (t.isDecimal() || t.isInteger()) {
				stack.push(t);
			}else { // if (t.isOperator()) 
				if (stack.isEmpty()) return new ErrorToken("Invalid Input. #1.1");
				DecimalToken newNum;
				OperatorToken opToken = (OperatorToken) t;
				BigDecimal x = new BigDecimal(stack.pop().getSymbol());
				try {
					if (!opToken.isUnary()) {
						if (stack.isEmpty()) return new ErrorToken("Invalid Input. #1.2"); 
						BigDecimal y = new BigDecimal(stack.pop().getSymbol());
						newNum = new DecimalToken(opToken.evaluate(y, x));
					}else {
						newNum = new DecimalToken(opToken.evaluate(x));
					}
					stack.push(newNum);
				}catch (Exception e) {
					return new ErrorToken("Arithmetic Error. Review expression.");
				}
			}
		}
		
		Token result = stack.pop();
		if(!stack.isEmpty()) { //Test
			System.out.println("Error:"+result.getSymbol());
			result = new ErrorToken("Program Bug, final stack is not empty");
			System.out.println("Stack:");
			while(!stack.isEmpty()) {
				System.out.println(stack.pop().getSymbol());
			}
		}
		return result;
	}
	
	public String evaluate(String expression) {
		System.out.println("Evaluating...");
		System.out.println(evaluation(toPostfix(parse(expression))).getSymbol());
		return evaluation(toPostfix(parse(expression))).getSymbol();
	}

}
