package tokenTag;

import java.awt.Color;
import java.awt.Dimension;

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
	}
	
	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		int subnum;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1-subnum), j=str.indexOf(this.endToken, i+1)) {
			subnum = 0;
			//doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("warning"), true);

			System.out.println(i+" "+j);
			String content = this.doc.getText(i+this.startToken.length(), j-i-this.startToken.length());
			//content += "\n";
			System.out.println(content);
			
			StringBuilder sBuilder = new StringBuilder(content);
			//String result = content.replace("\n","<br/>");
			//String newContent = "<html><body>"+result+"</body><html>";
			int newline_count = 0;
			for(int k=content.length()-1;k>0;k--) {
				if(content.charAt(k)=='\n') {
					sBuilder.insert(k, "<br/>");
					newline_count++;
				}	
			}
			
			String newContent = "<html><body>"+sBuilder+"</body><html>";
			System.out.println(newContent);
			String result = newContent.replace("\n","");
			System.out.println(result);
			//doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("warning"), true);
			System.out.println("qqqqqqqq="+doc.getText(i, j-i+1));
			System.out.println("end: "+this.endToken);
			System.out.println("ww="+doc.getText(i, this.startToken.length()));
			doc.remove(i, this.startToken.length());
			subnum += this.startToken.length();
			if(this.endToken != null) {
				System.out.println("doc= "+doc.getText(j-this.startToken.length(), this.endToken.length()));
				doc.remove(j-this.startToken.length(), this.endToken.length());
				subnum += this.endToken.length();
			}
			
			JLabel label = new JLabel();
			label.setPreferredSize(new Dimension(100, 30*(newline_count+1)));
			label.setBackground(new Color(255,250,180));
			label.setOpaque(true);
			label.setFont (label.getFont().deriveFont (22.0f));
			label.setForeground(new Color(138,109,132));
			//label.setBorder(new RoundedBorder(new Color(50, 50, 50), 30));
			label.setText(result);
			StyleConstants.setComponent(w, label);
			
			System.out.println("ffff="+doc.getText(i, content.length()-1));
			doc.remove(i, content.length()-1);
			subnum += content.length()-1;
			str = this.doc.getText(0, this.doc.getLength());
		}
	}
}
