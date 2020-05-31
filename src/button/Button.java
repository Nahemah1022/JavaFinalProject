package button;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import window.EditArea;

public class Button extends JButton {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 36;
	private static final int HEIGHT = 24;
	Button(String iconPath, EditArea source) throws IOException {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder());
		/*setBorder(BorderFactory.createSoftBevelBorder(
				BevelBorder.RAISED, 
				new Color(81, 81, 81), 
				new Color(81, 81, 81),
				new Color(81, 81, 81),
				new Color(81, 81, 81)
			)
		);*/
		setOpaque(false);
		setContentAreaFilled(false);
		Image image = ImageIO.read(this.getClass().getResource(iconPath));
		setIcon(new ImageIcon(image.getScaledInstance(HEIGHT, HEIGHT, Image.SCALE_DEFAULT)));
	}

}
