package tokenTag;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

import java.io.IOException;


public class HyperLinkTag extends TokenTag {
	Style def = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);
	Style s = doc.addStyle("link", def);
	Style t = doc.addStyle("linktex", def);
	
	public HyperLinkTag(StyledDocument target, String start, String end) {
		super(target, start, end);
	}
	
	public void HyperlinkDemo(String text, String url) throws HeadlessException {
		
		JLabel hylink = new JLabel(text);
        hylink.setForeground(Color.BLUE.darker());
        hylink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hylink.setFont(new Font(hylink.getFont().getName(), Font.PLAIN,20));
        
        hylink.addMouseListener(new MouseAdapter() {
 
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
                hylink.setText(text);
            }
 
            @Override
            public void mouseEntered(MouseEvent e) {
                hylink.setText("<html><a href=''>" + text + "</a></html>");
            }
 
        });
        StyleConstants.setComponent(s, hylink);
    }
	

	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		String nameString = str;
		String pathString;
		int subnum = 0;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1-subnum), j=str.indexOf(this.endToken, i+1)) {
			
			subnum = 0;
			nameString = str.substring(i+1, str.indexOf("]", i+1));
			pathString = str.substring(str.indexOf("]", i+1)+2, j);
			HyperlinkDemo(nameString, pathString);
			doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("link"), true);
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
