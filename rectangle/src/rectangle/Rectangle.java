package rectangle;
import java.util.Scanner;

public class Rectangle {

	
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Podaj d³ugoœci boków");
		try {
		int bokA = in.nextInt();
		int bokB = in.nextInt();
		int bokC = in.nextInt();

		
		if (bokA < 1 || bokB < 1 || bokC <1 )
		{
			System.err.println("Boki trójk¹ta powinny mieæ dodatnia d³ugoœæ");
		}
		else{
			if ((bokA + bokB) <= bokC || (bokC + bokB) <= bokA || (bokA + bokC) <= bokB)
			{
				System.err.println("To nie jest prawid³owy trójk¹t");
			}
			else
			{
				if (bokB!=bokC && bokA!=bokB && bokC!=bokA)
				{
					System.out.println("Trójk¹t ró¿noboczny");
				}
				else {
					if (bokB==bokC && bokA==bokB && bokC==bokA)
					{
						System.out.println("Trójk¹t równoboczny");
					}
					else
					{
						System.out.println("Trójk¹t równoramienny");
					}
				}

			}

		}
		}
		catch (Exception e){
			System.err.println("WprowadŸ liczby ca³kowite");
		}
	}

}
