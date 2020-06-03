package tokenTag;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class ImageTag extends TokenTag {
	Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
	Style s = doc.addStyle("image", def);
	JLabel label;
	
	public ImageTag(StyledDocument target, String start, String end,String path_start, String path_end) {
		super(target, start, end);
		super.path_start_token = path_start;
		super.path_end_token = path_end;
	}
	
	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		String path="",img_txt="";
		int subnum=0;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1),
				k=str.indexOf(this.path_start_token,i+2),l=str.indexOf(this.path_end_token,i+3);
				
				i!=-1 && j!=-1 && k!=-1 && l!=-1; 
				
				i=str.indexOf(this.startToken, l+1-subnum), j=str.indexOf(this.endToken, i+1),
				k=str.indexOf(this.path_start_token,i+2),l=str.indexOf(this.path_end_token,i+3)) {

			subnum = 0;
			doc.setCharacterAttributes(i, l-i+1, this.doc.getStyle("image"), false);
			
			img_txt = this.doc.getText(i+this.startToken.length(), j-i-2);
			
			// remove ![
			doc.remove(i, this.startToken.length()); 
			subnum +=this.startToken.length();
			// remove ](
			doc.remove(i+img_txt.length(), this.endToken.length()+this.path_start_token.length());
			subnum =subnum + this.endToken.length()+this.path_start_token.length();
			path = this.doc.getText(i+img_txt.length(), l-k-1);
			
			// remove )
			if(this.path_end_token != "\n") {
				doc.remove(i+img_txt.length()+path.length(), this.path_end_token.length());
				subnum += this.path_end_token.length();
			}
				
			System.out.println(path);
			//StyleConstants.setIcon(s, new ImageIcon(path));
			try {
				Image image = ImageIO.read(new File(path));
				BufferedImage bimg = ImageIO.read(new File(path));
				int width          = bimg.getWidth();
				int height         = bimg.getHeight();
				JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
				label.setName(path);
				StyleConstants.setComponent(s, label);
				System.out.println("name: "+StyleConstants.getComponent(s));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(img_txt.length()!=0)
				doc.remove(i, img_txt.length()-1);
			
			path="";
			str = this.doc.getText(0, this.doc.getLength());
		}
	}
}
