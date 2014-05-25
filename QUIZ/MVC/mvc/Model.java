package mvc;


public class Model {

	//STA�E
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
		//System.out.println("m_setUsername zako�czenie");
	}
	
	public void setWynik(int value) {
		m_wynik = value;
		System.out.println("m_SET zako�czenie" + m_wynik );
	}
	
	//ZMIEN ADD_PASSWORD
	public void setPasswordAdd(String value) {
		m_password_add = new String(value);
		//System.out.println("m_setPassword zako�czenie" );
	}
	
	public String getUsernameAdd() {
		//System.out.println("m_getUsernameAdd zako�czenie" + m_username_add);
		return m_username_add.toString();
	}
	
	public String getPasswordAdd() {
		//System.out.println("m_getPasswordAdd zako�czenie" + m_password_add);
		return m_password_add.toString();
	}
	
	public int getWynik()
	{
		return m_wynik;
	}
}
