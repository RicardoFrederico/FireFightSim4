package oop.firefight.numbers;

import java.util.SplittableRandom;

public class ValueGen {
	
	
	SplittableRandom random = new SplittableRandom();
	
	int num = random.nextInt(1,101);
	
	

	public int getNum() {
		return num;
	}



	public static void main(String[] args) {
		
		ValueGen v = new ValueGen();
		System.out.println(v.getNum());
		
		System.out.println("-1--------------------------");
		System.out.println();
		
		String fruit = "strawberries";
		
		System.out.println(fruit.substring(2, 5));
		
		System.out.println("-----2----------------------");
		System.out.println();
		
		int int0 = 6;
		
		boolean isDivisibleBy5 = int0 % 5 == 0;
		
		System.out.println(isDivisibleBy5);
		
		
		System.out.println("---------3------------------");
		System.out.println();
		
		String message = "Hello world!";
        String newMessage = message.substring(6, 12);// + message.substring(12, 6);
        System.out.println(newMessage);
		
		
	

	}
	
	

}
