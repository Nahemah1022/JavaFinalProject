package tokenTag;

import java.awt.Color;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class MarkdownTag extends TokenTag {
	public MarkdownTag(StyledDocument target, String start, String end) {
		super(target, start, end);
		
        Style def = StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE);
		Style s = doc.addStyle("markdown", def);
		StyleConstants.setBackground(s, Color.YELLOW);
	}

	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		int subnum = 0;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1-subnum), j=str.indexOf(this.endToken, i+1)) {
			//System.out.println("i=" + i);
			//System.out.println("j=" + j);
			subnum = 0;
			doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("markdown"), true);
			doc.remove(i, this.startToken.length());
			subnum += this.startToken.length();
			//doc.remove(j, this.endToken.length());
			if(this.endToken.equals("\n") == false) {
				//System.out.println("end"+this.endToken);
				//System.out.println(":"+str.substring(j-this.startToken.length(), j-this.startToken.length()+this.endToken.length()));
				doc.remove(j-this.startToken.length(), this.endToken.length());
				subnum += this.endToken.length();
			} 
				
			str = this.doc.getText(0, this.doc.getLength());
		}
	}
}
