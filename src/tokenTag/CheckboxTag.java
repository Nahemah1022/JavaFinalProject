package tokenTag;

import javax.swing.*;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class CheckboxTag extends TokenTag {
	
	Style def = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);
	Style s = doc.addStyle("chebox", def);
	
	public CheckboxTag(StyledDocument target, String start, String end) {
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
			
			JCheckBox chk = new JCheckBox(str.substring(i+5, j));
			StyleConstants.setComponent(s, chk);
			
			
			doc.setCharacterAttributes(i+5, j-i-5, this.doc.getStyle("chebox"), true);
			doc.remove(i, this.startToken.length());
			subnum += this.startToken.length();
			if(this.endToken.equals("\n") == false) {
				doc.remove(j-this.startToken.length(), this.endToken.length());
				subnum += this.endToken.length();
			}
				
			str = this.doc.getText(0, this.doc.getLength());
		}
	}
}
