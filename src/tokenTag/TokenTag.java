package tokenTag;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class TokenTag {
	protected StyledDocument doc;
	protected String startToken;
	protected String endToken;
	
	public TokenTag(StyledDocument target, String start, String end){
		this.doc = target;
		this.startToken = start;
		this.endToken = end;
	}
	
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1), j=str.indexOf(this.endToken, i+1)) {
			doc.setCharacterAttributes(i, j-i, this.doc.getStyle("h1"), true);
			doc.remove(i, this.startToken.length());
			if(this.endToken != "\n") 
				doc.remove(j-this.startToken.length(), this.endToken.length());
			str = this.doc.getText(0, this.doc.getLength());
		}
	}
}