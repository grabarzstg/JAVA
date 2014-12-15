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

	static double epsilon, a, b, x0, x1, x2, fa, fb, f0, bOut, sOut;	
	static int bisectionSteps, slashingSteps, i;
	private static Scanner in = new Scanner(System.in);
	
	static void getEpsilon ()
	{
		try {
		System.out.println("Podaj ε: (liczba z przedziału {0..1})");
		epsilon = in.nextDouble();
		if (epsilon > 1 || epsilon < 0 ){
			throw new InputMismatchException();
			}
		
		getRange();
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
	
	static void getRange()
	{
		System.out.println("Podaj zakres poszukiwań pierwiastka: \n Podaj a:");
		a= in.nextDouble();
		x1 = a;
		System.out.println("Podaj b:");
		b= in.nextDouble();
		x2 = b;
		setFunc(a, b);
	}
	
	static void setFunc (double x1, double x2)
	{
		fa = f(x1); 
		fb = f(x2);
	}
	
	static void checkBisection ()
	{
		if ((fa * fb) > 0){
			System.err.println("Funkcja nie spełnia założeń bisekcji!");
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
		bOut = x0;	
	}
	
	
	static void slashing()
	{
		x0 = 0;
		f0 = 0;
		i = 64;
		setFunc(x1, x2);
		
		while ( (i > 0) && (Math.abs(x1 - x2) > epsilon) ){
			slashingSteps++;
			if (Math.abs(fa - fb) < epsilon){
				System.err.println("Złe punkty startowe");
				i = 0;
				break;
			}
			x0 = x1 - fa * (x1 - x2) / (fa - fb);
			f0 = f(x0);
			
			if (Math.abs(f0) < epsilon) {break; }
			
			x2 = x1; fb = fa;
			x1 = x0; fa = f0;
			i--;
			if (i <= 0){
				System.err.println("Błąd: Przekroczony limit obiegów.");
			}
		}
		if (i > 0){
			sOut = x0;
		}
	}
	
	static void compare(){
		if (bisectionSteps != 0 && slashingSteps != 0){
			System.out.println("[BISEKCJA] x0 = "+ bOut);
			System.out.println("[SIECZNE ] x0 = "+ sOut);
			System.out.println("[BISEKCJA] ilość kroków: " + bisectionSteps);
			System.out.println("[SIECZNE ] ilośc kroków: "+ slashingSteps);    
		}
	}
	
	public static void main(String[] args) {
		getEpsilon();
		checkBisection();
		slashing();
		compare();
	}

}
