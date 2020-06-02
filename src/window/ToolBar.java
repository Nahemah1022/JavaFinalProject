package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import button.LoadButton;
import button.MenuButton;
import button.SaveButton;
import button.TagButton;

public class ToolBar extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int leftWidth = 240; 
	
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
	private MenuButton menuButton;
	
	ToolBar(EditArea source) throws IOException {
		setBackground(new Color(28, 28, 30));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createRaisedBevelBorder());
		setPreferredSize(new Dimension(Window.FRAME.getSize().width/2, 40));
		setMaximumSize(new Dimension(Window.FRAME.getSize().width*2, 40));
				
		fileSection = new JPanel();
		fileSection.setLayout(new GridLayout(0, 4));
		fileSection.setBackground(new Color(28, 28, 30));
		//fileSection.setBackground(new Color(10, 10, 10));
		//fileSection.setBorder(BorderFactory.createRaisedBevelBorder());
		fileSection.setPreferredSize(new Dimension(Window.FRAME.getSize().width/3, 40));
		fileSection.setSize(new Dimension(Window.WIDTH/6, 40));
		tagSection = new JPanel();
		tagSection.setLayout(new GridLayout(0, 6));
		tagSection.setBackground(new Color(28, 28, 30));
		tagSection.setPreferredSize(new Dimension(Window.FRAME.getSize().width*2/3, 40));
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
		menuButton = new MenuButton("/images/menu.png", source);
		
		fileSection.add(menuButton);
		fileSection.add(saver);
		fileSection.add(loader);
		
		tagSection.add(bold);
		tagSection.add(italic);
		tagSection.add(strike);
		tagSection.add(checkbox);
		tagSection.add(link);
		tagSection.add(image);
	}
	
	public void toggleFileSection() {
		this.saver.setVisible(!this.saver.isVisible());
		this.loader.setVisible(!this.loader.isVisible());
		this.menuButton.setVisible(!this.menuButton.isVisible());
	}
	
}

