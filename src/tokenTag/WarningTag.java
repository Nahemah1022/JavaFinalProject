package tokenTag;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class WarningTag extends TokenTag{
	JLabel label ;
	Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
	Style w = doc.addStyle("warning", def);
	
	public WarningTag(StyledDocument target, String start, String end) {
		super(target, start, end);
		StyleConstants.setBackground(w, Color.cyan);
	}
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1), j=str.indexOf(this.endToken, i+1)) {
			doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("warning"), true);
			doc.remove(i, this.startToken.length());
			if(this.endToken != "\n") 
				doc.remove(j-this.startToken.length(), this.endToken.length());
			/*
			System.out.println(str.charAt(this.doc.getLength()-1));
			
			int buffer[]=new int[5];
			int index=0;
			for(int k=str.indexOf('\n');k!=-1;k=str.indexOf('\n',k+1)) {
				System.out.println(k);
				buffer[index++] = k-1;
			}
			
			String space = " ", singleString=" ";
			for(int q=0;q<99;q++) {
				space += singleString;
			}
			for(int m=index-1;m>0;m--) {
				this.doc.insertString( buffer[m],space, w);
			}*/
			
			str = this.doc.getText(i, this.doc.getLength());
				
		}
	}
}
