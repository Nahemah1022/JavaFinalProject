package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import window.EditArea;
import window.Menu;

public class FileButton extends JButton {

	private static final long serialVersionUID = 1L;
	public FileButton(String name, EditArea source, File file) throws IOException {
		super(name);
		setBackground(new Color(50, 50, 50));
		setBorder(BorderFactory.createEmptyBorder());
		setHorizontalAlignment(SwingConstants.LEFT);
		setForeground(new Color(200, 200, 200));
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		Image image = ImageIO.read(this.getClass().getResource("/images/file.png"));
		setIcon(new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					String content = new String ( Files.readAllBytes( Paths.get(Menu.workspacePath + "\\" + file.getName()) ));
					source.getDocument().remove(0, source.getDocument().getLength());
					source.getDocument().insertString(0, content, null);
					source.window.toggleMenu();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
	}
}
