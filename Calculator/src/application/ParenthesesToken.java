package application;

public class ParenthesesToken implements Token {
	
	private String symbol;
	private boolean leftParentheses;
	
	public ParenthesesToken (char p) {
		leftParentheses = p == '(' ? true:false;
		symbol = Character.toString(p);
	}
	
//	public static ParenthesesToken getLeftParentheses() { return new ParenthesesToken("(");}
//	
//	public static ParenthesesToken getRightParentheses() { return new ParenthesesToken(")");}
	
	@Override
	public boolean isInteger() {return false;}

	@Override
	public boolean isDecimal() {return false;}

	@Override
	public boolean isOperator() {return false;}

	@Override
	public boolean isParanthese() {return true;}
	
	@Override
	public boolean isErrorToken() {return false;}

	@Override
	public String getSymbol() {return symbol;}
	
	@Override
	public int getPriority() {return 0;}
	
	@Override
	public boolean isLeftParentheses() {return leftParentheses;}
	
	@Override
	public boolean isRightParentheses() {return !leftParentheses;}

}
