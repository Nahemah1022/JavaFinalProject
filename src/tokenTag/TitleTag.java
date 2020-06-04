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
		StyleConstants.setFontSize(s, 25);
		StyleConstants.setBold(s, true);
		
		s = doc.addStyle("h5", def);
		StyleConstants.setFontSize(s, 28);
		StyleConstants.setBold(s, true);
		
		s = doc.addStyle("h4", def);
		StyleConstants.setFontSize(s, 31);
		StyleConstants.setBold(s, true);
		
		s = doc.addStyle("h3", def);
		StyleConstants.setFontSize(s, 34);
		StyleConstants.setBold(s, true);
		
		s = doc.addStyle("h2", def);
		StyleConstants.setFontSize(s, 37);
		StyleConstants.setBold(s, true);
		
		s = doc.addStyle("h1", def);
		StyleConstants.setFontSize(s, 40);
		StyleConstants.setBold(s, true);
	}

	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		int hCount = 1, subnum = 0;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1-subnum), j=str.indexOf(this.endToken, i+1)) {
			subnum = 0;
			hCount = 1;
			while(str.charAt(++i) == '#') {
				hCount++;
			}
			doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("h" + hCount), true);
			doc.remove(i-hCount, this.startToken.length()-1+hCount);
			subnum += this.startToken.length()-1+hCount;
			if(!this.endToken.equals("\n")) {
				doc.remove(j-this.startToken.length(), this.endToken.length());
				subnum += this.endToken.length();
			}
			str = this.doc.getText(0, this.doc.getLength());
		}
	}

}
