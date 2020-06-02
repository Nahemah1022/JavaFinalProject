
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

//import com.sun.javafx.css.Stylesheet;

//import jdk.internal.org.objectweb.asm.Label;
import tokenTag.*;

public class ViewArea extends JTextPane{
	
	private static final long serialVersionUID = 1L;

	public static final int Width = Window.WIDTH/2;
	public static final int HEIGHT = Window.HEIGHT;
	
	private TitleTag title;
	private BoldTag bold;
	private ItalicTag italic;
	private StrikeTag strike;
	private ImageTag image;
	private FontTag font;
	private WarningTag warning;
	private InfoTag info;
	private SuccessTag success;
	private DangerTag danger;
	private CodeTag code;
	
	JLabel label;
	Highlighter h;
	ViewArea(Document source) throws BadLocationException{
		setFont(new Font("Arial", Font.BOLD, 20));
		setOpaque(true);
		setEditable(false);
		
        StyledDocument doc = getStyledDocument();
        title = new TitleTag(doc, "#", "\n");
        bold = new BoldTag(doc, "*", "*");
        italic = new ItalicTag(doc, "**", "**");
        strike = new StrikeTag(doc, "~~", "~~");
        image = new ImageTag(doc, "![", ")") ;
        //image = new ImageTag(doc, "![","]","(",")") ;
        font = new FontTag(doc, "$$", "$$");
        //warning = new WarningTag(doc, ":::warning", ":::");
        warning = new WarningTag(doc, ":::warning\n", "\n:::");
        info = new InfoTag(doc,":::info\n","\n:::");
        success = new SuccessTag(doc, ":::success\n", "\n:::");
        danger = new DangerTag(doc, ":::danger\n", "\n:::");
        code = new CodeTag(doc, "```\n", "\n```");
        
        addStylesToDocument(doc);
        
	}
	
	private void addStylesToDocument(StyledDocument doc) {
        Style def = StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setFontFamily(def, "SansSerif");
        StyleConstants.setBold(def, false);
        
		Style s = doc.addStyle("regular", def);
		StyleConstants.setBold(s, false);
	}

	public void setViewText(Document source) throws BadLocationException {
		String str = source.getText(0, source.getLength());
		StyledDocument doc = getStyledDocument();
		doc.remove(0, doc.getLength());
		doc.insertString(0, str, doc.getStyle("regular"));
		
		title.apply();
		italic.apply();
		bold.apply();
		strike.apply();
		image.apply();
		font.apply();
		warning.apply();
		info.apply();
		success.apply();
		danger.apply();
		code.apply();
		
	}
}
