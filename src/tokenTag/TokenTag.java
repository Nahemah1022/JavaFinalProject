package tokenTag;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public abstract class TokenTag {
	protected StyledDocument doc;
	protected String startToken;
	protected String endToken;
	
	TokenTag(StyledDocument target, String start, String end) {
		this.doc = target;
		this.startToken = start;
		this.endToken = end;
	}
	
	public abstract void apply() throws BadLocationException;
}