package application;

public interface Token {
	String getSymbol();
	boolean isInteger();
	boolean isDecimal();
	boolean isOperator();
	boolean isParanthese();
	boolean isErrorToken();
	boolean isLeftParentheses();
	boolean isRightParentheses();
	default int getPriority() {return 0;}
	default Number getValue() {return null;}
}

enum TokenType{
	Operator,Number,Decimal,Paranthese
}
