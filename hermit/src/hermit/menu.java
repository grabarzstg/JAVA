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
			initList.add(getInputData());
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
	
	static void polynomial()
	{	 
		double[] outTab = new double[101];
		double[] tabX = new double[101];
		Init node = initList.get(0);
		outTab[0] = tempArray[0][0];
		tabX[1]=1;
		tabX[0]=node.getX(); 
		for(int i=1; i<initList.size()*2; i++)
		{
		double[] tabN = new double[101];	
		double[] tabB = new double[101];
		node = initList.get((i-1)/2);

		System.out.print(node.getX());
		System.out.print("<<X<<");
		System.out.print(tempArray[i][0]);
		System.out.println("<<B<<");
		
		tabN = polynomialNum(tabX,node.getX());
		
		tabB = polynomialB(polynomialSum(tabN, tabX), tempArray[i][0]);
		outTab = polynomialSum(outTab, tabB);
		tabX = polynomialX(tabX);
		
		
		for(int a=0; a<10; a++){
			System.out.println(tabX[a]);
		}

		
		}

	}
	
	static double[] polynomialX(double initTab[])
	{
		double[] outTab = new double[101];
			for (int i=0; i<100; i++)
			{
				outTab[i+1] = initTab[i];			
			}			
		return outTab;
	}
	
	static double[] polynomialNum(double initTab[], double num)
	{
			for (int i=0; i<100; i++)
			{
				initTab[i]= initTab[i]*(num*(-1));			
			}			
		return initTab;
	}
	
	static double[] polynomialB(double initTab[], double b)
	{
			for (int i=0; i<100; i++)
			{
				initTab[i]= initTab[i]*b;			
			}			
		return initTab;
	}
	
	static double[] polynomialSum(double tabA[], double tabB[])
	{
			for (int i=0; i<100; i++)
			{
				tabA[i]= tabA[i]+tabB[i];			
			}			
		return tabA;
	}
	

	
	//MAIN
	public static  void main(String[] args) {
		getNodeAmount();
		printInitList();
		setTParameters();
		run();
		System.out.println("------");
		polynomial();
		System.out.println("------");
		printTempArray();
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
