package window;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import tokenTag.BoldTag;
import tokenTag.CheckboxTag;
import tokenTag.CodeTag;
import tokenTag.Comment;
import tokenTag.DangerTag;
import tokenTag.DividerTag;
import tokenTag.FontTag;
import tokenTag.HyperLinkTag;
import tokenTag.ImageTag;
import tokenTag.InfoTag;
import tokenTag.Irr_Order;
import tokenTag.Irregular_Order;
import tokenTag.ItalicTag;
import tokenTag.MarkdownTag;
import tokenTag.Quote;
import tokenTag.StrikeTag;
import tokenTag.SuccessTag;
import tokenTag.TableTag;
import tokenTag.TitleTag;
import tokenTag.UnderLabel;
import tokenTag.UnderlineTag;
import tokenTag.WarningTag;

public class ViewArea extends JTextPane {
	
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

	private UnderlineTag underline;
	private Quote quote;
	private Comment comment;
	private Irregular_Order f_irregular;
	private Irr_Order s_irregular;
	private UnderLabel Ulabel;

	private StrikeTag strike;
	private ImageTag image;
	private FontTag font;
	private WarningTag warning;
	private InfoTag info;
	private SuccessTag success;
	private DangerTag danger;
	private CodeTag code;
	private DividerTag divider;

	ViewArea(EditArea editArea) throws BadLocationException{
		setFont(new Font("Arial", Font.BOLD, 20));
		setOpaque(true);
		setEditable(false);
		setContentType("tekst/html");
		
        StyledDocument doc = getStyledDocument();
        title = new TitleTag(doc, "#", "\n");
        bold = new BoldTag(doc, "**", "**");
        italic = new ItalicTag(doc, "*", "*");
        hyperlink = new HyperLinkTag(doc, "[", ")");
        markdown = new MarkdownTag(doc, "==", "==");
        checkbox = new CheckboxTag(doc, "- [ ]", "\n");
        table = new TableTag(doc, "|", "-|\n");
        underline = new UnderlineTag(doc, "_","_");
        quote = new Quote(doc, ">","\n");
        comment = new Comment(doc,"// ","\n");
        s_irregular = new Irr_Order(doc,"	- ","\n");
        f_irregular = new Irregular_Order(doc,"- ","\n");
        strike = new StrikeTag(doc, "~~", "~~");
        Ulabel = new UnderLabel(doc,"~","~");
        image = new ImageTag(doc, "![](",")") ;
        font = new FontTag(doc, "$$", "$$");
        warning = new WarningTag(doc, ":::warning\n", "\n:::");
        info = new InfoTag(doc,":::info\n","\n:::");
        success = new SuccessTag(doc, ":::success\n", "\n:::");
        danger = new DangerTag(doc, ":::danger\n", "\n:::");
        code = new CodeTag(doc, "```\n", "\n```");
        divider = new DividerTag(doc, "-", "=");
        
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
		//System.out.println(str.length() - doc.getLength());
		doc.remove(0, doc.getLength());
		doc.insertString(0, str, doc.getStyle("regular"));
		
		title.apply();
		bold.apply();
		italic.apply();
		checkbox.apply();
		
		image.apply();
		
		hyperlink.apply();
		markdown.apply();
		table.apply();
		underline.apply();
		quote.apply();
		comment.apply();
		s_irregular.apply();
		f_irregular.apply();
		strike.apply();
		Ulabel.apply();
		
		font.apply();
		warning.apply();
		info.apply();
		success.apply();
		danger.apply();
		code.apply();
		divider.apply();
		
	}
}
