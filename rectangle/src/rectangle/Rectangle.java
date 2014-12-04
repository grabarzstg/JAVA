package rectangle;
import java.util.Scanner;

public class Rectangle {

	
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Podaj d�ugo�ci bok�w");
		try {
		int bokA = in.nextInt();
		int bokB = in.nextInt();
		int bokC = in.nextInt();

		
		if (bokA < 1 || bokB < 1 || bokC <1 )
		{
			System.err.println("Boki tr�jk�ta powinny mie� dodatnia d�ugo��");
		}
		else{
			if ((bokA + bokB) <= bokC || (bokC + bokB) <= bokA || (bokA + bokC) <= bokB)
			{
				System.err.println("To nie jest prawid�owy tr�jk�t");
			}
			else
			{
				if (bokB!=bokC && bokA!=bokB && bokC!=bokA)
				{
					System.out.println("Tr�jk�t r�noboczny");
				}
				else {
					if (bokB==bokC && bokA==bokB && bokC==bokA)
					{
						System.out.println("Tr�jk�t r�wnoboczny");
					}
					else
					{
						System.out.println("Tr�jk�t r�wnoramienny");
					}
				}

			}

		}
		}
		catch (Exception e){
			System.err.println("Wprowad� liczby ca�kowite");
		}
	}

}
