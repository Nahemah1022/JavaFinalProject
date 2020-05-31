package Windows;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;


public class EditArea extends JTextPane{
	
	private static final long serialVersionUID = 1L;
	
	public static final int Width = Window.WIDTH/2;
	public static final int HEIGHT = Window.HEIGHT;
	private Window window;
	
	public EditArea(Window window) {
		this.window = window;
		setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
		setBackground(Color.WHITE);
		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					window.passSource(e.getDocument());
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
	}
}
