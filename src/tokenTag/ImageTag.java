package tokenTag;

import java.awt.Color;
import java.awt.Dimension;

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
	//JLabel label;
	/*
	public ImageTag(StyledDocument target, String start, String end,String path_start, String path_end) {
		super(target, start, end);
		super.path_start_token = path_start;
		super.path_end_token = path_end;
	}*/
	Style t = doc.addStyle("imagetex", def);
	
	public ImageTag(StyledDocument target, String start, String end) {
		super(target, start, end);
	}
	
	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		String nameString = str;
		String pathString;
		int subnum = 0;
		//System.out.println(nameString);
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1); 
				i!=-1 && j!=-1; 
				i=str.indexOf(this.startToken, j+1-subnum), j=str.indexOf(this.endToken, i+1)) {
			System.out.println(str);
			subnum = 0;
			nameString = str.substring(i+1, str.indexOf("]", i+1));
			System.out.println(nameString);
			pathString = str.substring(str.indexOf("]", i+1)+2, j);
			System.out.println(pathString);
			JLabel label = new JLabel(new ImageIcon(pathString));
			StyleConstants.setComponent(s, label);
			//HyperlinkDemo(nameString, pathString);
			doc.setCharacterAttributes(i, j-i+1, this.doc.getStyle("image"), true);
			doc.remove(i, this.startToken.length());
			subnum += this.startToken.length();
			if(this.endToken.equals("\n") == false) {
				doc.remove(j-this.startToken.length(), this.endToken.length());
				subnum += this.endToken.length();
			} 
				
			str = this.doc.getText(0, this.doc.getLength());
		}
	}
	
	/*
	@Override
	public void apply() throws BadLocationException {
		String str = this.doc.getText(0, this.doc.getLength());
		String path,img_txt;
		for(int i=str.indexOf(this.startToken), j=str.indexOf(this.endToken, i+1),
				k=str.indexOf(this.path_start_token,i+2),l=str.indexOf(this.path_end_token,i+3);
				
				i!=-1 && j!=-1 && k!=-1 && l!=-1; 
				
				i=str.indexOf(this.startToken, l+1), j=str.indexOf(this.endToken, i+1),
				k=str.indexOf(this.path_start_token,i+2),l=str.indexOf(this.path_end_token,i+3)) {
			
			System.out.println(i+" "+j+" "+k+" "+l);
			doc.setCharacterAttributes(i, l-i+1, this.doc.getStyle("image"), true);
			
			img_txt = this.doc.getText(i+this.startToken.length(), j-i-2);
			System.out.println("img_txt:"+img_txt);
			
			doc.remove(i, this.startToken.length()); // remove ![
			
			// remove ](
			doc.remove(i+img_txt.length(), this.endToken.length()+this.path_start_token.length());
			
			path = this.doc.getText(i+img_txt.length(), l-k-1);
			System.out.println("path:"+path);
			
			// remove )
			if(this.path_end_token != "\n") 
				doc.remove(i+img_txt.length()+path.length(), this.path_end_token.length());
			
			//StyleConstants.setIcon(s, new ImageIcon(path));
			
			JLabel label = new JLabel(new ImageIcon(path));
			StyleConstants.setComponent(s, label);
			
			doc.remove(i, img_txt.length()-1);
			doc.remove(i, path.length()-1);
			str = this.doc.getText(0, this.doc.getLength());
			System.out.println(str);
			
		}
	}*/
}
