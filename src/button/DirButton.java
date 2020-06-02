package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class DirButton extends JButton {

	private static final long serialVersionUID = 1L;

	public DirButton(String name) throws IOException {
		super(name);
		setBackground(new Color(50, 50, 50));
		setBorder(BorderFactory.createEmptyBorder());
		setHorizontalAlignment(SwingConstants.LEFT);
		setForeground(new Color(200, 200, 200));
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		Image image = ImageIO.read(this.getClass().getResource("/images/folder.png"));
		setIcon(new ImageIcon(image.getScaledInstance(24, 24, Image.SCALE_DEFAULT)));
		
	}
}
