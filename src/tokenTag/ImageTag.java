package tokenTag;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class ImageTag extends TokenTag {
	
	Style def = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);
	Style s = doc.addStyle("cb", def);
	
	public ImageTag(StyledDocument target, String start, String end) {
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
			
			String path = str.substring(i+4, j);
			//JCheckBox chk = new JCheckBox(str.substring(i+4, j));
			//StyleConstants.setComponent(s, chk);
			try {
				Image image = ImageIO.read(new File(path));
				BufferedImage bimg = ImageIO.read(new File(path));
				int width          = bimg.getWidth();
				int height         = bimg.getHeight();
				JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
				label.setName(path);
				StyleConstants.setComponent(s, label);
				//System.out.println("name: "+StyleConstants.getComponent(s));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			doc.setCharacterAttributes(i+4, j-i-4, this.doc.getStyle("cb"), true);
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