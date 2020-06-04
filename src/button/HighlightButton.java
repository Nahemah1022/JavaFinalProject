package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

import window.EditArea;
import window.ViewArea;

public class HighlightButton extends Button {

	private static final long serialVersionUID = 1L;
	private boolean isDrawing;

	public HighlightButton(String iconPath, EditArea source, ViewArea view) throws IOException {
		
		super(iconPath, source);
		isDrawing = false;
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isDrawing = !isDrawing;
			}
			
		});

		view.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				if(isDrawing) {
				    Runnable doHighlight = new Runnable() {
				        @Override
				        public void run() {
							int index, offset;
							try {
								String s1 = source.getDocument().getText(0, source.getDocument().getLength());
								String s2 = view.getDocument().getText(0, view.getDocument().getLength());
								offset = s1.length() - s2.length();
								index = s1.substring(0, view.getSelectionEnd() + offset).lastIndexOf(view.getSelectedText());
								source.getDocument().insertString(index + view.getSelectedText().length(), "==", null);
								source.getDocument().insertString(index, "==", null);
								isDrawing = false;
							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								//e1.printStackTrace();
							}
				        }
				    };       
				    SwingUtilities.invokeLater(doHighlight);
				}
			}
			
		});
		
	}

}
