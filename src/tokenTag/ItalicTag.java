package tokenTag;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class ItalicTag extends TokenTag {

	public ItalicTag(StyledDocument target, String start, String end) {
		super(target, start, end);
		
        Style def = StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE);
		Style s = doc.addStyle("italic", def);
		StyleConstants.setItalic(s, true);
	}

	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		int subnum = 0;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1 - subnum), j=str.indexOf(this.endToken, i+1)) {
			subnum = 0;
			doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("italic"), false);
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
