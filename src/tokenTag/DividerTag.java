package tokenTag;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class DividerTag extends TokenTag {
	
	Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
	Style s = doc.addStyle("divide", def);
	
	public DividerTag(StyledDocument target, String start, String end) {
		super(target, start, end);
		
    }

	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		int subnum = 0;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1-subnum), j=str.indexOf(this.endToken, i+1)) {
			subnum = 0;
			String content = " ";
			//String content = str.substring(i+this.startToken.length(),j);
			StringBuilder sBuilder = new StringBuilder(content);
			String newContent = "<html><body>"+sBuilder+"</body><html>";
			//String result = newContent.replace("\n","");
			
			JLabel label = new JLabel();
			label.setPreferredSize(new Dimension(100, 5));
			label.setBackground(new Color(231, 231, 231));
			label.setOpaque(true);
			label.setFont (label.getFont().deriveFont (22.0f));
			label.setText(newContent);
			StyleConstants.setComponent(s, label);
			
			
			doc.setCharacterAttributes(i+this.startToken.length(), j-i-this.startToken.length(), this.doc.getStyle("divide"), true);
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
