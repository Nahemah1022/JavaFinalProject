package Windows;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import tokenTag.*;

public class ViewArea extends JTextPane{
	
	private static final long serialVersionUID = 1L;

	public static final int Width = Window.WIDTH/2;
	public static final int HEIGHT = Window.HEIGHT;
	
	private TitleTag title;
	private BoldTag bold;
	private ItalicTag italic;
	private HyperLinkTag hyperlink;
	private MarkdownTag markdown;
	private CheckboxTag checkbox;
	private TableTag table;
	
	ViewArea(Document source){
		setFont(new Font("Arial", Font.BOLD, 20));
		setOpaque(true);
		setEditable(false);
		
        StyledDocument doc = getStyledDocument();
        title = new TitleTag(doc, "#", "\n");
        bold = new BoldTag(doc, "*", "*");
        italic = new ItalicTag(doc, "**", "**");
        hyperlink = new HyperLinkTag(doc, "[", ")");
        markdown = new MarkdownTag(doc, "==", "==");
        checkbox = new CheckboxTag(doc, "- [ ]", "\n");
        table = new TableTag(doc, "|", "\n");
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
		checkbox.apply();
		hyperlink.apply();
		markdown.apply();
		table.apply();
		
	}
}
