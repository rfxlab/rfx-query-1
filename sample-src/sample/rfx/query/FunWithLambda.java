package sample.rfx.query;

import java.util.ArrayList;
import java.util.List;

import rfx.core.util.StringUtil;

public class FunWithLambda {
	static int MAX_POOL_SIZE = 1000000;
	
	static int compute(String expression) {
		String[] toks = expression.split("x");
		int base = StringUtil.safeParseInt(toks[0]);
		int times = StringUtil.safeParseInt(toks[1]);
		List<Integer> list = new ArrayList<>(times);
		for (int i = 0; i < times; i++) {
			list.add(base);
		}
		return list.parallelStream().reduce((Integer t, Integer u)->{
			int r = t+u;
			System.out.println(t +"+"+ u + " = "+r);
			return r;
		}).get();		
	}

	public static void main(String[] args) {
		String expression1 = "4x8";	
		System.out.println(expression1 + " = ? \n");
		System.out.println(compute(expression1) + " \n");
		
		String expression2 = "8x4";
		System.out.println(expression2 + " = ? \n");
		System.out.println(compute(expression2));
		
		System.out.println("computed by active threads "+Thread.activeCount());
	}
}
