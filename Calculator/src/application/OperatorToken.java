package application;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class OperatorToken implements Token{
	
	public static Map<String,OperatorToken> opMap = new HashMap<>();
	
	private final String symbol;
	private final int priority;
	private final boolean unary;
	private final BiFunction<BigDecimal, BigDecimal, BigDecimal> operation;
    private final Function<BigDecimal, BigDecimal> unaryOperation;
	
	public OperatorToken(String pSymbol,int pPriority, BiFunction<BigDecimal, BigDecimal, BigDecimal> pBiFunction) {
		symbol = pSymbol;
		priority = pPriority; // 0 for "( )", 1 for low (+,-), 2 for high (*,/)
		operation = pBiFunction;
		unaryOperation = null;
		unary = false;
		if(!opMap.containsKey(symbol)) opMap.put(pSymbol, this);
	}
	
	public OperatorToken(String pSymbol,int pPriority, Function<BigDecimal,BigDecimal> pFunction) {
		symbol = pSymbol;
		priority = pPriority; // 0 for "( )", 1 for low (+,-), 2 for high (*,/)
		unaryOperation = pFunction;
		operation = null;
		unary = true;
		if(!opMap.containsKey(symbol)) opMap.put(pSymbol, this);
	}
	@Override
	public String getSymbol() {return symbol;}
	@Override
	public boolean isInteger() {return false;}

	@Override
	public boolean isDecimal() {return false;}

	@Override
	public boolean isOperator() {return true;}

	@Override
	public boolean isParanthese() {return false;}
	
	@Override
	public boolean isLeftParentheses() {return false;}
	
	@Override
	public boolean isRightParentheses() {return false;}
	
	@Override
	public boolean isErrorToken() {return false;}
	
	public int getPriority() {return priority;}
	
	public boolean isUnary() { return unary; }
	
	public BigDecimal evaluate(BigDecimal x, BigDecimal y){
		return operation.apply(x, y);
	}
	public BigDecimal evaluate(BigDecimal x) {
		return unaryOperation.apply(x);
		
	}
}
