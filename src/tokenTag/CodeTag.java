package tokenTag;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class CodeTag extends TokenTag{
	JLabel label ;
	Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
	Style w = doc.addStyle("codee", def);
	
	public CodeTag(StyledDocument target, String start, String end) {
		super(target, start, end);
	}
	
	public void apply() throws BadLocationException {
		int subnum = 0;
		String str = this.doc.getText(0, this.doc.getLength());
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken,  j+1-subnum), j=str.indexOf(this.endToken, i+1)) {
			
			subnum = 0;
			
			String content = str.substring(i+this.startToken.length(),j);
			//System.out.println("content = "+content);
			
			StringBuilder sBuilder = new StringBuilder(content);
			int newline_count = 0;
			for(int k=content.length()-1;k>0;k--) {
				
				if(content.charAt(k)=='\n') {
					if(k==content.length()-2) {
						sBuilder.insert(k, "<br/>");
						newline_count++;
					}
					else {
						sBuilder.insert(k, "<br/>&nbsp;&nbsp;&nbsp;&nbsp;");
						newline_count++;
					}
				}	
			}

			String newContent = "<html><body>"+sBuilder+"</body><html>";
			String result = newContent.replace("\n","");
			
			JLabel label = new JLabel();
			label.setPreferredSize(new Dimension(100, 50*(newline_count+1)));
			label.setBackground(Color.BLACK);
			label.setForeground(Color.WHITE);
			label.setOpaque(true);
			label.setFont (label.getFont().deriveFont (22.0f));
			label.setText(result);
			StyleConstants.setComponent(w, label);
			
			
			doc.setCharacterAttributes(i+this.startToken.length(), j-i-this.startToken.length(), this.doc.getStyle("codee"), true);
			doc.remove(i, this.startToken.length());
			subnum += this.startToken.length();
			if(this.endToken.equals("\n") == false) {
				doc.remove(j-this.startToken.length(), this.endToken.length());
				subnum += this.endToken.length();
				doc.remove(i, content.length()-1);
			}
			str = this.doc.getText(0, this.doc.getLength());
			
		}
	}
}
