package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.text.BadLocationException;

import window.EditArea;

public class TagButton extends Button {

	private static final long serialVersionUID = 1L;

	public TagButton(String iconPath, EditArea source, String startToken, String endToken) throws IOException {
		super(iconPath, source);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(source.getSelectedText() != null) {
						source.getDocument().insertString(source.getSelectionStart(), startToken, null);
						source.getDocument().insertString(source.getSelectionEnd(), endToken, null);
					}
					else {
						source.getDocument().insertString(source.getCaretPosition(), startToken + endToken, null);
						source.grabFocus();
						source.setCaretPosition(source.getCaretPosition() - endToken.length());
					}

				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
		});

	}

}
