package mvc;


public class Model {

	//STAŁE
	private static final String UZUPELNIJ = "uzupelnij";
	
	//ZMIENNE
	private String m_username_add;
	private String m_password_add;
	private int m_wynik=0;
	//KONSTRUKTOR
	Model() {
		reset();
	}
	
	
	//RESET
	public void reset() {
		m_username_add = new String(UZUPELNIJ);
		m_password_add = new String(UZUPELNIJ);
	}
	
	//ZMIEN ADD_USERNAME
	public void setUsernameAdd(String value) {
		m_username_add = new String(value);
		//System.out.println("m_setUsername zakończenie");
	}
	
	public void setWynik(int value) {
		m_wynik = value;
		System.out.println("m_SET zakończenie" + m_wynik );
	}
	
	//ZMIEN ADD_PASSWORD
	public void setPasswordAdd(String value) {
		m_password_add = new String(value);
		//System.out.println("m_setPassword zakończenie" );
	}
	
	public String getUsernameAdd() {
		//System.out.println("m_getUsernameAdd zakończenie" + m_username_add);
		return m_username_add.toString();
	}
	
	public String getPasswordAdd() {
		//System.out.println("m_getPasswordAdd zakończenie" + m_password_add);
		return m_password_add.toString();
	}
	
	public int getWynik()
	{
		return m_wynik;
	}
}
