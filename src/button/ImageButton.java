package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

import window.EditArea;
import window.Menu;

public class ImageButton extends Button {

	private static final long serialVersionUID = 1L;

	public ImageButton(String iconPath, EditArea source) throws IOException {
		super(iconPath, source);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    JFileChooser chooser = new JFileChooser();
			    chooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setCurrentDirectory(new java.io.File(Menu.workspacePath.equals("") ? "" : Menu.workspacePath));
			    chooser.setDialogTitle("Import image");
			    int returnVal = chooser.showOpenDialog(source);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	try {
			    		String img = "![](" + chooser.getSelectedFile().getPath() + ")";
			    		source.getDocument().insertString(source.getCaretPosition(), img, null);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			}
			
		});

	}

}
