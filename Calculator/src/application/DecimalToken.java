package application;

import java.math.BigDecimal;
import java.math.MathContext;
import ch.obermuhlner.math.big.BigDecimalMath;

public class DecimalToken implements Token {
	
	private BigDecimal value;
	private String symbol;
	public static final MathContext precision = MathContext.DECIMAL64;
	
	// Constant values
	private static final BigDecimal pi = BigDecimalMath.pi(precision);
	private static final BigDecimal e = BigDecimalMath.e(precision);

	public static final DecimalToken token_pi = new DecimalToken(pi);
	public static final DecimalToken token_e = new DecimalToken(e);
	
	public DecimalToken	(String pValue) {
		value = new BigDecimal(pValue);
		symbol = pValue;
	}
	
	public DecimalToken(BigDecimal pValue) {
		value = pValue;
		symbol = pValue.toString();
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public boolean isInteger() {return false;}

	@Override
	public boolean isDecimal() {return true;}

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
