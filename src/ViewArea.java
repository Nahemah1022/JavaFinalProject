
import java.awt.Font;

import javax.swing.JTextPane;

public class ViewArea extends JTextPane{
	
	private static final long serialVersionUID = 1L;

	ViewArea(){
		setFont(new Font("Arial", Font.BOLD, 20));
		setOpaque(true);
		setEditable(false);
	}
}
