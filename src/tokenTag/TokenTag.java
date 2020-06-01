package tokenTag;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public abstract class TokenTag {
	protected StyledDocument doc;
	protected String startToken;
	protected String endToken;
	protected String path_start_token;
	protected String path_end_token;
	
	TokenTag(StyledDocument target, String start, String end) {
		this.doc = target;
		this.startToken = start;
		this.endToken = end;
	}
	
	public abstract void apply() throws BadLocationException;
}