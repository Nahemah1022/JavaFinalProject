package tokenTag;

import java.awt.Color;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class FontTag extends TokenTag {
	SimpleAttributeSet key;
	public FontTag(StyledDocument target, String start, String end) {
		super(target, start, end);
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		Style s = doc.addStyle("font", def);
		StyleConstants.setFontFamily(s, "DialogInput");
	}
	
	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1), j=str.indexOf(this.endToken, i+1)) {
			doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("font"), true);
			doc.remove(i, this.startToken.length());
			if(this.endToken != "\n") 
				doc.remove(j-this.startToken.length(), this.endToken.length());
			str = this.doc.getText(0, this.doc.getLength());
		}
		
	}
}
