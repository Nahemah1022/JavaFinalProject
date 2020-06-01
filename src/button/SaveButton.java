package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;

import window.EditArea;

public class SaveButton extends Button {

	private static final long serialVersionUID = 1L;
	
	public SaveButton(String iconPath, EditArea source) throws IOException {
		super(iconPath, source);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			    chooser.setDialogTitle("Save Note");
			    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
			    int returnVal = chooser.showSaveDialog(source);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	try {
			    		File file = new File(chooser.getSelectedFile().getPath().replace("/", "\\") + ".txt");
			    		file.createNewFile();
			    		FileWriter writer = new FileWriter(file);
			    		writer.write(source.getDocument().getText(0, source.getDocument().getLength()));
			    	    writer.flush();
			    	    writer.close();
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
