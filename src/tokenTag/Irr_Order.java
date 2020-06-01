package tokenTag;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class Irr_Order extends TokenTag {

	public Irr_Order(StyledDocument target, String start, String end) {
		super(target, start, end);
		
        Style def = StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE);
		Style s = doc.addStyle("s_irregular", def);
	}

	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		int subnum = 0;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1-subnum), j=str.indexOf(this.endToken, i+1)) {
			subnum = 0;
			doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("s_irregular"), true);
			doc.remove(i+1, this.startToken.length());
			doc.insertString(i+1, "¡C", doc.getStyle("s_irregular"));
			subnum += this.startToken.length();
			if(this.endToken != "\n") {
				doc.remove(j-this.startToken.length(), this.endToken.length());
				subnum += this.startToken.length();
			}
			str = this.doc.getText(0, this.doc.getLength());
		}
	}
}
