package hermit;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class menu {
	
	private static Scanner in = new Scanner(System.in);
	static List<Init> initList = new ArrayList<Init>();
	static List<Power> powerList = new ArrayList<Power>();
	static List<Power> powerTempList = new ArrayList<Power>();
	private static double tempArray[][] =  new double[100][100];

	private static void getNodeAmount()
	{		
		try {
		System.out.print("Ile wêz³ów chcesz wczytaæ?");
			int howMany = in.nextInt();
			if(howMany >0)
			{
				insertNodeIntoList(howMany);	
			}
			else
			{
				throw new InputMismatchException();
			}
		}
		catch (InputMismatchException e){
			System.err.println("B³¹d: \n Nieprawid³owe dane wejœciowe. \n");
		} 

		
	}
	
	static Init getInputData()
	{ 
			return new Init(getArgument("x"),getArgument("y"),getArgument("z") );
	}
	
	static void insertNodeIntoList (int amount)
	{
		for(int i=0; i<amount; i++)
		{
			Init node =getInputData();
			if(checkExistingX(node.getX())==true){
				throw new InputMismatchException();
			}
			else
			{
			initList.add(node);
			}
		}
	}
	
	
	static double getArgument(String name)
	{
		System.out.print("Podaj wartoœæ "+name+" :");
		
		return in.nextDouble();

	}

	
	static void setTParameters()
	{
		for (int i=0; i<initList.size(); i++){
			Init node = initList.get(i);
			tempArray[0][2*i] = node.getY();
			tempArray[0][(2*i)+1] = node.getZ();
		}
	}
	
	static double calculate(double t1,double t2, double x1, double x2)
	{
		if (x2 - x1 !=0){	
		return	(t2-t1)/(x2-x1);
		}
		else{
		return t2;
		}		
	}
	
	static void run()
	{
		int x = initList.size();
		for(int i=1;i<x*2;i++){
			 for(int j=0;j<x*2-i;j++){
				if(i==1){
					tempArray[i][j]= calculate(tempArray[i-1][j], tempArray[i-1][j+1],j/2,(j+1)/2); 	
				}
				else{
					tempArray[i][j]= calculate(tempArray[i-1][j], tempArray[i-1][j+1],0, 1); 
				} 
			 }
		}
	}
	
	static void Taylor()
	{
		System.out.print("Ile wêz³ów chcesz wczytaæ?");
		int howMany = in.nextInt();
		
		double[] polyTab = new double[howMany+1];
		
		System.out.print("Podaj punkt obrad:");
		double point = in.nextDouble();
		
		for (int i=(howMany) ;i>=0;i--)
		{
			System.out.print("Podaj a *x^"+i+": ");
			polyTab[i] = in.nextDouble();
		}
		
		
		double[] tempTab = new double[howMany+1];
		/*
		for(int i=0,j=howMany; i<=howMany ;i++,j--)
		{			
			
			System.out.print(tempTab[i]+"x^"+i+" + ");
			if (i!=0)
			{
			tempTab[i-1] = polyTab[i]*polyTab[i-1];
			
			tempTab[j]=polyTab[j]*(howMany-i);
			//System.out.println("temp = " + tempTab[i] + " poly = " + polyTab[i] + "howmany -i = " + (howMany-1) );
		}*/
		
		for(int k=0;k<howMany;k++){
		//poczatek petli liczacej pochodne
		tempTab=pochodna(polyTab,howMany);
		// i-ta pochodna =
		
		System.out.println((k+1) + " pochodna wynosi: ");
		for(int i=tempTab.length-1;i>0;i--){
			System.out.print(tempTab[i]+"x^"+(i-1));
			if(i>1){
				System.out.print(" + ");
			}
		}
		System.out.println("----------");

		polyTab=tempTab;
		}
		//kuniec pyntli
			
	}
	
	static double[] pochodna(double tab[],int howMany){
		double[] tab1 = new double[howMany+1];
		
		for(int i=0,j=howMany; i<=howMany ;i++,j--)
		{			
			tab1[j]=tab[j]*(howMany-i);
			//System.out.println("temp = " + tab1[i] + " poly = " + tab[i] + "howmany -i = " + (howMany-1) );
		}
		
		return tab1;
	}
		
	
		
	
	
	

	
	static void product()
	{
		String operation = new String();
		operation+=tempArray[0][0];
		for (int i=1; i<initList.size()*2; i++)
		{
			if(tempArray[i][0] > 0)
			{
				operation+=" + "+tempArray[i][0];
				operation+="*"+str(i);
			}
			
		}
		System.out.println(operation);
	}
	
	static String str (int a)
	{
		Init node = initList.get((a-1)/2);
		String temp= new String();
		if(a>1){
		temp+=str(a-1)+"(x-"+node.getX()+")";
		}
		else
		{
			temp+="(x-"+node.getX()+")";
		}
		
	return temp;
	}
	
	
	static boolean checkExistingX(double x)
	{
		for (int i=0; i<initList.size(); i++)
		{
			Init node = initList.get(i);
			if(node.getX()==x)
			{
				return true;
			}			
		}	
		
		return false;
	}

	
	//MAIN
	public static  void main(String[] args) {
		
		getNodeAmount();
		printInitList();
		setTParameters();
		run();
		System.out.println("------");
		product();
		//polynomial();
		System.out.println("------");
		Taylor();
		//printTempArray();
		//printPowerList(); 
	}

	//TESTS
	static void printTempArray()
	{
		for (int i=0; i<(initList.size()*2); i++){
			for (int j=0; j<(initList.size()*2); j++){
			System.out.print(tempArray[i][j]+ " ");
			}
			System.out.println("");
		}
	}
	
	static void printInitList()
	{
		for (int i=0; i<initList.size(); i++)
		{
			Init node = initList.get(i);
			System.out.println(node.getX());
			System.out.println(node.getY());
			System.out.println(node.getZ());
		}
	}
	
	static void printPowerList()
	{
		for (int i=0; i<powerList.size(); i++)
		{
			System.out.println("POWERLIST -------------");
			Power node = powerList.get(i);
			System.out.println(node.getNumber());
			System.out.println(node.getDegree());

		}
	}
}
