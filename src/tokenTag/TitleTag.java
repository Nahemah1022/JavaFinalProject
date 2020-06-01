package tokenTag;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class TitleTag extends TokenTag {

	public TitleTag(StyledDocument target, String start, String end) {
		super(target, start, end);
		
        Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		Style s = doc.addStyle("h6", def);
		StyleConstants.setFontSize(s, 10);
		
		s = doc.addStyle("h5", def);
		StyleConstants.setFontSize(s, 14);
		
		s = doc.addStyle("h4", def);
		StyleConstants.setFontSize(s, 18);
		
		s = doc.addStyle("h3", def);
		StyleConstants.setFontSize(s, 24);
		
		s = doc.addStyle("h2", def);
		StyleConstants.setFontSize(s, 30);
		
		s = doc.addStyle("h1", def);
		StyleConstants.setFontSize(s, 36);
		StyleConstants.setBold(s, true);
	}

	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1), j=str.indexOf(this.endToken, i+1)) {
			doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("h1"), true);
			doc.remove(i, this.startToken.length());
			if(this.endToken.equals("\n") == false) 
				doc.remove(j-this.startToken.length(), this.endToken.length());
			str = this.doc.getText(0, this.doc.getLength());
		}
	}

}
