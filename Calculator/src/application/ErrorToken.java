package application;

public class ErrorToken implements Token {
	
	private String message;
	
	public ErrorToken(String pMessage) {
		message = pMessage;
	}
	
	@Override
	public String getSymbol() {
		return String.valueOf(message);
	}
	
	public static ErrorToken getDefaultToken() {
		return new ErrorToken("Invalid Expression.");
	}
	@Override
	public boolean isInteger() {return false;}

	@Override
	public boolean isDecimal() {return false;}

	@Override
	public boolean isOperator() {return false;}

	@Override
	public boolean isParanthese() {return false;}

	@Override
	public boolean isErrorToken() { return true;}
	
	@Override
	public boolean isLeftParentheses() {return false;}
	
	@Override
	public boolean isRightParentheses() {return false;}

}
