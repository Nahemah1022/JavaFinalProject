
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class ViewArea extends JTextPane{
	
	private static final long serialVersionUID = 1L;

	public static final int Width = Window.WIDTH/2;
	public static final int HEIGHT = Window.HEIGHT;
	
	ViewArea(){
		setFont(new Font("Arial", Font.BOLD, 20));
		setOpaque(true);
		setEditable(false);
		
        StyledDocument doc = getStyledDocument();
        addStylesToDocument(doc);
	}
	
	private void addStylesToDocument(StyledDocument doc) {
        Style def = StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE);

		Style regular = doc.addStyle("regular", def);
		StyleConstants.setFontFamily(def, "SansSerif");
		
		Style s = doc.addStyle("italic", regular);
		StyleConstants.setItalic(s, true);
		
		s = doc.addStyle("bold", regular);
		StyleConstants.setBold(s, true);
		
		s = doc.addStyle("h6", regular);
		StyleConstants.setFontSize(s, 10);
		
		s = doc.addStyle("h1", regular);
		StyleConstants.setFontSize(s, 30);

		
	}

	public void setViewText(Document source) throws BadLocationException {
		StyledDocument doc = getStyledDocument();
		String str = source.getText(0, source.getLength());
		doc.remove(0, doc.getLength());
		//doc.insertString(0, str, doc.getStyle("regular"));
        for (int i=0; i < str.length(); i++) {
        	System.out.println(str.substring(i, i+1));
        	doc.insertString(doc.getLength(), str.substring(i, i+1), doc.getStyle("italic"));
        }
		//System.out.print(source.getText(0, source.getLength()));
	}
}
