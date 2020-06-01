package Windows;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class ScrollArea extends JScrollPane{
	
	private static final long serialVersionUID = 1L;
	private Dimension minSize;
	
	ScrollArea(Component view, String title){
		super(view);
		minSize = new Dimension(100, 10);
        setPreferredSize(new Dimension(Window.WIDTH/2, Window.HEIGHT));
        setMinimumSize(minSize);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(title),
                BorderFactory.createEmptyBorder(0,0,0,0))
        );
	}
}
