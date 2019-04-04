package test;

import java.util.Random;

public class Student {
public static void main(String[] args) {
	java.util.Random ra = new Random();
	for(int i=0;i<100;i++){
		System.out.println(ra.nextInt(2));		
	}
}
}
