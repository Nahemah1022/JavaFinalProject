package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import button.LoadButton;
import button.SaveButton;
import button.TagButton;

public class ToolBar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel fileSection;
	private JPanel tagSection;
	
	private JButton bold;
	private JButton italic;
	private JButton strike;
	private JButton checkbox;
	private JButton link;
	private JButton image;
	private SaveButton saver;
	private LoadButton loader;
	
	ToolBar(EditArea source) throws IOException {
		setBackground(new Color(28, 28, 30));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createRaisedBevelBorder());
		setPreferredSize(new Dimension(Window.FRAME.getSize().width/2, 40));
		setMaximumSize(new Dimension(Window.FRAME.getSize().width*2, 40));
		fileSection = new JPanel();
		fileSection.setLayout(new FlowLayout(FlowLayout.CENTER));
		fileSection.setBackground(new Color(28, 28, 30));
		//fileSection.setPreferredSize(new Dimension(Window.FRAME.getSize().width/6, 40));
		tagSection = new JPanel();
		tagSection.setLayout(new FlowLayout(FlowLayout.CENTER));
		tagSection.setBackground(new Color(28, 28, 30));
		//tagSection.setPreferredSize(new Dimension(Window.FRAME.getSize().width*5/6, 40));
		add(fileSection);
		add(tagSection);
		
		bold = new TagButton("/images/bold.png", source, "*", "*");
		italic = new TagButton("/images/italic.png", source, "**", "**");
		strike = new TagButton("/images/strike.png", source, "~~", "~~");
		checkbox = new TagButton("/images/checkbox.png", source, "- [ ]", "\n");
		link = new TagButton("/images/link.png", source, "[](", ")");
		image = new TagButton("/images/image.png", source, "![](", ")");
		saver = new SaveButton("/images/save.png", source);
		loader = new LoadButton("/images/load.png", source);
		
		fileSection.add(saver);
		fileSection.add(loader);
		tagSection.add(bold);
		tagSection.add(italic);
		tagSection.add(strike);
		tagSection.add(checkbox);
		tagSection.add(link);
		tagSection.add(image);
	}
	
}

