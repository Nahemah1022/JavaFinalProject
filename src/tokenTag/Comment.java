package tokenTag;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class Comment extends TokenTag {

	Style def = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);
	Style s = doc.addStyle("comment", def);
	
	public Comment(StyledDocument target, String start, String end) {
		super(target, start, end);		
	}

	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		int subnum = 0;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1-subnum), j=str.indexOf(this.endToken, i+1)) {
			subnum = 0;
			
			JLabel label = new JLabel(str.substring(i, j));
			StyleConstants.setComponent(s, label);
			label.setForeground(Color.lightGray);
			
			doc.setCharacterAttributes(i, j-i, this.doc.getStyle("comment"), true);
			str = this.doc.getText(0, this.doc.getLength());
		}
	}
}
