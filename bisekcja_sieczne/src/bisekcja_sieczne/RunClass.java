package bisekcja_sieczne;

import java.util.InputMismatchException;
import java.util.Scanner;


/*
 Równanie x^5+x+7=0 rozwiązać na dwa sposoby: 
 -metodą siecznych
 -metodą połowienia
 
 Porównać ilość wykonanych kroków w każdej z tych metod 
 do uzyskania danej z wejścia dokładności 0 < e < 1.
 */

public class RunClass {

	static double epsilon, a, b, x0, fa, fb, f0;	
	static int bisectionSteps = 0;
	private static Scanner in = new Scanner(System.in);
	static void getEpsilon ()
	{
		try {
		System.out.println("Podaj ε: (liczba z przedziału {0..1})");
		epsilon = in.nextDouble();
		
		getAB();
		}
		catch (InputMismatchException e){
			System.err.println("Podano nieprawidłową liczbę.");
			System.err.println("Wskazówka: użyj przecinka zamiast kropki dla liczby niecałkowitej.");
		}
		catch (Exception e){
			System.err.println("Błąd krytyczny.");
		}
	}
	
	static double f(double x)
	{
	  return x * x * x * x * x + x + 7;
	}
	
	static void getAB()
	{
		System.out.println("Podaj zakres poszukiwań pierwiastka: \n Podaj a:");
		a= in.nextDouble();
		System.out.println("Podaj b:");
		b= in.nextDouble();
		setFunc();
	}
	
	static void setFunc ()
	{
		fa = f(a); 
		fb = f(b);
		checkBisection();
	}
	
	static void checkBisection ()
	{
		if ((fa * fb) > 0){
			System.err.println("Funkcja nie spełnia założeń!");
		}
		else{
			bisection();
		}
	}
	
	static void bisection()
	{
		while (Math.abs(a-b) > epsilon){
			bisectionSteps++;
			x0 = (a + b) / 2; 
			f0 = f(x0);
			
			if (Math.abs(f0) < epsilon){ break; }
			
		      if (fa * f0 < 0){ 
		    	  b = x0;
		      }
		      	else{
		      		a = x0; 
		      		fa = f0;
		      	}    	        
		}
		System.out.println("x0 = " + x0);
	}
	
	
	public static void main(String[] args) {
		getEpsilon();



		

	}

}
