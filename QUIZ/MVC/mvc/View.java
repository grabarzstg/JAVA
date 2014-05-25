package mvc;




import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.MenuListener;



class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	//STA£E
	private static final String UZUPELNIJ = "admin";
	private static final String PASS = "daka";
	int wynik =0;
	
	//KLOCKI
	private JTextField m_username_add = new JTextField(25); //Nazwa u¿ytkownika i has³o to max 25 znaków!!!
	private JTextField m_password_add = new JTextField(25);
	
	
	private JButton m_sendbtn = new JButton("Wyslij");
	private JButton m_clearbtn = new JButton("Clear");
	private JButton v_sprawdz = new JButton("SprawdŸ");
	private JTextField m_odpowiedz = new JTextField(20);
	
    private JRadioButton ans_a1 = new JRadioButton("A) true true true true");
    private  JRadioButton ans_b1 = new JRadioButton("B) false true false true");
    private JRadioButton ans_c1 = new JRadioButton("C) true true false true");
    private JRadioButton ans_d1 = new JRadioButton("D) false false false false");
	JMenuItem elem_wyslij = new JMenuItem("Wyslij wynik");
	JMenuItem moj_wynik = new JMenuItem("Moj najlepszy wynik");
	private JMenu menuPlik = new JMenu("Plik");
	
	JMenuItem styl_windows = new JMenuItem("Windows");
	JMenuItem styl_metal = new JMenuItem("Metal");
	private JMenu menuStyl = new JMenu("Styl");
    
	private Model m_model;
	
	JPanel content = new JPanel();
	
	View(Model model) {
	
		//DOMYSLNE
		m_model = model;
		m_model.setUsernameAdd(UZUPELNIJ);
		m_model.setPasswordAdd(PASS);
		

		//INICJALIZACJA
		m_username_add.setText(m_model.getUsernameAdd()); 
		m_password_add.setText(m_model.getPasswordAdd()); 
		m_odpowiedz.setEditable(false);
		m_odpowiedz.setVisible(false);
		m_odpowiedz.setText("BUAHAHA");
		
		
	       Toolkit zestaw = Toolkit.getDefaultToolkit();
	       Dimension rozmiarEkranu = zestaw.getScreenSize();
	       int wysEkranu = rozmiarEkranu.height;
	       int szerEkranu = rozmiarEkranu.width;

		//PANEL
		
		content.setLayout(new FlowLayout());
	    //   content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		//content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));
		content.add(new JLabel("Login:"));
		content.add(m_username_add);
		content.add(new JLabel("Password"));
		content.add(m_password_add);
		content.add(m_sendbtn);
		content.add(m_clearbtn);
	    setSize((szerEkranu/2), (wysEkranu/2));
	    setLocation((szerEkranu/4), (wysEkranu/4));
		
		//KONIEC PANELU
		this.setContentPane(content);
		this.pack();
		
		this.setTitle("Obs³uga - MVC itd.");
		// The window closing event should probably be passed to the
		// Controller in a real program, but this is a short example.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}

		//RESETUJE POLA
	void reset() {
		m_username_add.setText(UZUPELNIJ);
		m_password_add.setText(PASS);
	}

		//ZWRACA USERNAME_ADD & PASSWORD_ADD
	String getUsernameAdd() {
		//System.out.println("v_getUsernameAdd zakoñczenie");
		return m_username_add.getText();
	}
	
	String getPasswordAdd() {
		//System.out.println("v_getPasswordAdd zakoñczenie");
		return m_password_add.getText();
		}

	
	void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage, "B³¹d krytyczny", JOptionPane.ERROR_MESSAGE);
	}
	
	void showWynik(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage, "Wynik", JOptionPane.INFORMATION_MESSAGE);
	}
	
	void showInfo(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	void loginError(String err) {
		JOptionPane.showMessageDialog(this, err, "B³¹d logowania", JOptionPane.ERROR_MESSAGE);
	}
	
	//SET USERNAME & PASSWORD
	
void setUsernameAdd(String newUsername) {
		m_username_add.setText(newUsername);
		//System.out.println("v_setUsernameAdd zakoñczenie");
	}

	void setPasswordAdd(String newPassword) {
		m_password_add.setText(newPassword);
		//System.out.println("v_setPasswordAdd zakoñczenie");
	}
	
	//S£UCHACZE
	void addSendBtnListener(ActionListener sal){
		m_sendbtn.addActionListener(sal);
	}

	void addClearListener(ActionListener cal) {
		m_clearbtn.addActionListener(cal);
	}
	
	void addAnsA1Listener(ActionListener a1)
	{
	ans_a1.addActionListener(a1);
	}
	
	void addAnsB1Listener(ActionListener b1)
	{    
    ans_b1.addActionListener(b1);
	}
    
	void addAnsC1Listener(ActionListener c1)
	{
    ans_c1.addActionListener(c1);
	}
    
	void addAnsD1Listener(ActionListener d1)
	{
    ans_d1.addActionListener(d1);
	}
	
	void MenuPlikListener(MenuListener d1)
	{
    menuPlik.addMenuListener(d1);
	}
	
	void MenuWyslij(MouseListener d1)
	{
    elem_wyslij.addMouseListener(d1);
	}
	
	void MojWynik(MouseListener d1)
	{
    moj_wynik.addMouseListener(d1);
	}
	
	void StylWindows(MouseListener d1)
	{
    styl_windows.addMouseListener(d1);
	}
	
	void StylMetal(MouseListener d1)
	{
    styl_metal.addMouseListener(d1);
	}
	
	void goodAnswer() {
		m_odpowiedz.setText("Prawid³owa odpowiedŸ");
        m_odpowiedz.setVisible(true);
        ans_a1.setVisible(false);
        ans_b1.setVisible(false);
        ans_c1.setVisible(false);
        ans_d1.setVisible(false);
        wynik++;
	}
	
	void badAnswer() {
		m_odpowiedz.setText("B³êdna odpowiedŸ");
        m_odpowiedz.setVisible(true);
        ans_d1.setVisible(false);
        ans_d1.setVisible(true);
        wynik--;
	}
	

	
	int getWynik() {
		System.out.println("v_GET zakoñczenie" + wynik);
		return wynik;
	}
	
	
	
	//OKNO DRUGIE----------------------------------------------------	
	void  window2(){
		
		 
	       Toolkit zestaw = Toolkit.getDefaultToolkit();
	       Dimension rozmiarEkranu = zestaw.getScreenSize();
	       int wysEkranu = rozmiarEkranu.height;
	       int szerEkranu = rozmiarEkranu.width;

		//PANEL 
		
		content.removeAll();

		// MENU-----------------------------------------------------------------------------

	/*	menuPlik.add( new 
  	          AbstractAction("Wyslij wynik")
  	          {
					private static final long serialVersionUID = 1L;
					public void actionPerformed(ActionEvent zdarzenie)
  	             {
						System.out.println("Wynik" + wynik);
  	             }
				
  	          });
				*/
		menuPlik.add(moj_wynik);	
		menuPlik.add(elem_wyslij);		
		menuPlik.addSeparator();
		menuPlik.add(new 
	    	          AbstractAction("Zamknij")
	    	          {
						private static final long serialVersionUID = 1L;
						public void actionPerformed(ActionEvent zdarzenie)
	    	             {
	    	                System.exit(0);
	    	             }
	    	          });
		 
	       

	       
	       JMenuBar pasekMenu = new JMenuBar();
	       setJMenuBar(pasekMenu);
	       pasekMenu.add(menuPlik);
	       
	       menuStyl.add(styl_windows);
	       menuStyl.add(styl_metal);
	       pasekMenu.add(menuStyl);
	       
	    setSize((szerEkranu/2), (wysEkranu/2));
	    setLocation((szerEkranu/4), (wysEkranu/4));
	    
	    
	    //RADIO BUTTONS----------------------------------------
	    ButtonGroup group1 = new ButtonGroup();
	    group1.add(ans_a1);
	    group1.add(ans_b1);
	    group1.add(ans_c1);
	    group1.add(ans_d1);


	    // ZAKLADKI -------------------------------------------------------------------------
	       
	    
	    JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("kopiuj.gif");
        
        JComponent panel1 = paneltab();
        tabbedPane.addTab("Pytanie 1", icon, panel1,
                "Pytanie 1");
        //panel1.setPreferredSize(new Dimension((szerEkranu/3), (wysEkranu/3)));  
        panel1.add(new JLabel("<html> <b> Co zobaczymy na ekranie po wykonaniu poni¿szego kodu?</b> </html>"));
        panel1.add(new JLabel(" "));
        panel1.add(new JLabel("public class Test { "));
        panel1.add(new JLabel("public static void main(String args[]){"));
        panel1.add(new JLabel("String s1 = 'Ala';"));
        panel1.add(new JLabel("String s2 = 'Ala';"));
        panel1.add(new JLabel("System.out.println(s1==s2);"));
        panel1.add(new JLabel("System.out.println(s1.equals(s2));"));
        panel1.add(new JLabel("String s3 = s1;"));
        panel1.add(new JLabel("String s4 = new String('Ala');"));
        panel1.add(new JLabel("System.out.println(s3==s4);"));
        panel1.add(new JLabel("System.out.println(s3.equals(s4));"));
        panel1.add(new JLabel("} }"));
        
        panel1.add(ans_a1);
        panel1.add(ans_b1);
        panel1.add(ans_c1);
        panel1.add(ans_d1);

        panel1.add(m_odpowiedz);
        
        v_sprawdz.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JComponent panel2 = paneltab();
        tabbedPane.addTab("Pytanie 2", icon, panel2,
                "Pytanie 2");

        
        JComponent panel3 = paneltab();
        tabbedPane.addTab("Pytanie 3", icon, panel3,
                "Pytanie 3");

        
        JComponent panel4 = paneltab();
        //panel4.setPreferredSize(new Dimension((szerEkranu/3), (wysEkranu/3)));
        tabbedPane.addTab("Pytanie 4", icon, panel4,
                "Pytanie 4");

        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
		
	}
	

	
    protected static JPanel paneltab() {
    	JPanel paneltab = new JPanel();
    	  paneltab.setLayout(new BoxLayout(paneltab, BoxLayout.Y_AXIS));
    	return paneltab;
    }
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = View.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
   
}
