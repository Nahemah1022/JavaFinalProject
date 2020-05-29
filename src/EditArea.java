import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;


public class EditArea extends JTextPane{
	
	private static final long serialVersionUID = 1L;
	
	public static final int Width = Window.WIDTH/2;
	public static final int HEIGHT = Window.HEIGHT;
	
	public EditArea() {
		setFont(new Font("·L³n¥¿¶ÂÅé", Font.BOLD, 20));
		setBackground(Color.WHITE);
	}
}
