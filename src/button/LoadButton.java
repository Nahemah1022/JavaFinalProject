package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;

import window.EditArea;
import window.Menu;

public class LoadButton extends Button {

	private static final long serialVersionUID = 1L;

	public LoadButton(String iconPath, EditArea source) throws IOException {
		super(iconPath, source);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File(Menu.workspacePath.equals("") ? "." : Menu.workspacePath));
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setDialogTitle("Import Note");
			    int returnVal = chooser.showOpenDialog(source);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	try {
			    		String content = new String ( Files.readAllBytes( Paths.get(chooser.getSelectedFile().getPath().replace("/", "\\")) ) );
			    		source.getDocument().remove(0, source.getDocument().getLength());
			    		source.getDocument().insertString(0, content, null);
			    	} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			}
			
		});

	}

}
