package version_pack;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
public class confirmbox  
{
   public static void main(String[] args) 
   {
      //ImageIcon icon = new ImageIcon("E −\\new.PNG");
	   
   }

   
public int showbox(String msg)
   {
	   JPanel panel = new JPanel();
	      panel.setSize(new Dimension(250, 100));
	      panel.setLayout(null);
	     
	      JLabel label2 = new JLabel(msg);
	      label2.setVerticalAlignment(SwingConstants.TOP);
	      label2.setHorizontalAlignment(SwingConstants.CENTER);
	      label2.setBounds(20, 80, 100, 20);
	      panel.add(label2);
	      UIManager.put("OptionPane.minimumSize", new Dimension(300, 200));
	      int res = JOptionPane.showConfirmDialog(null, panel, "File",
	      JOptionPane.YES_NO_CANCEL_OPTION,
	      JOptionPane.PLAIN_MESSAGE,null);
	      return res;
   }
}
