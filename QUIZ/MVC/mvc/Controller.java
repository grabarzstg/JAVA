package mvc;

import java.awt.event.*;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;





public class Controller {
	//KOMUNIKACJA Z VIEW I MODEL	
	private Model m_model;
	private View v_view;
	private SQL s_sql;
	
	//KONSTRUKTOR
	Controller(Model model, View view, SQL sql) {
		m_model = model;
		v_view = view;
		s_sql = sql;
		//DODAJE S£UCHACZY 
		view.addSendBtnListener(new SendBtnListener());
		view.addClearListener(new ClearListener());
		view.addAnsA1Listener(new AnsBADListener());
		view.addAnsB1Listener(new AnsGOODListener());
		view.addAnsC1Listener(new AnsBADListener());
		view.addAnsD1Listener(new AnsBADListener());
	    view.MenuPlikListener(new SluchaczMenuPlik());
	    view.MenuWyslij(new wyslijListener());
	    view.MojWynik(new mojListener());
	    view.StylWindows(new windowsListener());
	    view.StylMetal(new metalListener());
	}
	
	
	
	//WYSYLA DANE
	class SendBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				System.out.println("SEND PRESSED");
				m_model.setUsernameAdd(v_view.getUsernameAdd());
				s_sql.setUsernameAdd(m_model.getUsernameAdd());
				m_model.setPasswordAdd(v_view.getPasswordAdd());
				
				s_sql.setPasswordAdd(m_model.getPasswordAdd());
				s_sql.s_UserCheck(m_model.getUsernameAdd(), m_model.getPasswordAdd());
			} catch (Exception e2) {
				v_view.showError("Error: '" + e2.getMessage() + "'");
			}
		}
	}

	
	// RESETUJE MODEL & VIEW
	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("CLEAR PRESSED");
			m_model.reset();
			v_view.reset();
		}
	}	

	class AnsBADListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			v_view.badAnswer();
			System.err.println("WYNIK:" + v_view.wynik);
		}
	}
	class AnsGOODListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			v_view.goodAnswer();
			System.out.println("WYNIK:" + v_view.wynik);
		}
	}

    private class SluchaczMenuPlik implements MenuListener
    {
       public void menuSelected(MenuEvent zd) {

       }
       public void menuDeselected(MenuEvent zd) {}
       
       public void menuCanceled(MenuEvent zd) {}
    }

    
    public class wyslijListener  implements MouseListener
    {
    	public void mouseClicked(MouseEvent e)
    	{}
    	public void mouseReleased(MouseEvent e){}

    	public void mouseExited(MouseEvent e){}
    	
    	public void mousePressed(MouseEvent e){
    		try {
    		m_model.setWynik(v_view.getWynik());
    		s_sql.s_wynik(m_model.getUsernameAdd(), m_model.getWynik());
    		}
    		catch (Exception e2) {
				v_view.showError("Error: '" + e2.getMessage() + "'");
			}
    	}
    	
    	public void mouseEntered(MouseEvent e){}
    }
    
    public class mojListener  implements MouseListener
    {
    	public void mouseClicked(MouseEvent e)
    	{}
    	public void mouseReleased(MouseEvent e){}

    	public void mouseExited(MouseEvent e){}
    	
    	public void mousePressed(MouseEvent e){
    		try {

    		s_sql.s_mojWynik(m_model.getUsernameAdd());
    		}
    		catch (Exception e2) {
				v_view.showError("Error: '" + e2.getMessage() + "'");
			}
    	}
    	
    	public void mouseEntered(MouseEvent e){}
    }
    
    public class windowsListener  implements MouseListener
    {
    	public void mouseClicked(MouseEvent e)
    	{}
    	public void mouseReleased(MouseEvent e){}

    	public void mouseExited(MouseEvent e){}
    	
    	public void mousePressed(MouseEvent e){
    		try
            {  
               UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
               SwingUtilities.updateComponentTreeUI(v_view.content);
            }
            catch(Exception w) { w.printStackTrace(); }
    	}
    	
    	public void mouseEntered(MouseEvent e){}
    }
    
    public class metalListener  implements MouseListener
    {
    	public void mouseClicked(MouseEvent e)
    	{}
    	public void mouseReleased(MouseEvent e){}

    	public void mouseExited(MouseEvent e){}
    	
    	public void mousePressed(MouseEvent e){
    		try
            {  
               UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
               SwingUtilities.updateComponentTreeUI(v_view.content);
            }
            catch(Exception w) { w.printStackTrace(); }
    	}
    	
    	public void mouseEntered(MouseEvent e){}
    }
    
}