package application;

import java.math.BigInteger;

public class NumberToken implements Token {
	
	private BigInteger value;
	private String symbol;
	
	public NumberToken(String pValue) {
		value = new BigInteger(pValue);
		symbol = pValue;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public boolean isInteger() {return true;}

	@Override
	public boolean isDecimal() {return false;}

	@Override
	public boolean isOperator() {return false;}

	@Override
	public boolean isParanthese() {return false;}
	
	@Override
	public boolean isErrorToken() {return false;}
	
	@Override
	public boolean isLeftParentheses() {return false;}
	
	@Override
	public boolean isRightParentheses() {return false;}
	
	@Override
	public Number getValue() {return value;}

}
