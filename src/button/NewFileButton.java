package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import window.EditArea;
import window.Menu;

public class NewFileButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	private boolean isSet;
	private Menu menu;
	
	public NewFileButton(String name, EditArea source, Menu menu) throws IOException {
		super(name);
		setBackground(new Color(50, 50, 50));
		setBorder(BorderFactory.createEmptyBorder());
		setHorizontalAlignment(SwingConstants.LEFT);
		setForeground(new Color(200, 200, 200));
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		Image image = ImageIO.read(this.getClass().getResource("/images/plus.png"));
		setIcon(new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
		isSet = false;
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!isSet) {
				    JFileChooser chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new java.io.File(Menu.workspacePath.equals("") ? "." : Menu.workspacePath));
				    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				    chooser.setDialogTitle("Save Note");
				    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
				    int returnVal = chooser.showSaveDialog(source);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	try {
				    		File file = new File(chooser.getSelectedFile().getPath().replace("/", "\\") + ".txt");
				    		Menu.editingFileName = chooser.getSelectedFile().getName() + ".txt";
				    		System.out.println(Menu.editingFileName);
				    		file.createNewFile();
				    		source.getDocument().remove(0, source.getDocument().getLength());
				    		Image image = ImageIO.read(this.getClass().getResource("/images/file.png"));
				    		setIcon(new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
				    		setText(chooser.getSelectedFile().getName());
				    		isSet = true;
				    		menu.addOneFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }					
				}
				else {
					Menu.editingFileName = getText() + ".txt";
					String content;
					try {
						content = new String ( Files.readAllBytes( Paths.get(Menu.workspacePath + "\\" + Menu.editingFileName) ));
						source.getDocument().remove(0, source.getDocument().getLength());
						source.getDocument().insertString(0, content, null);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					source.window.toggleMenu();
				}
			}
			
		});
	}

}
