package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import button.DirButton;
import button.FileButton;
import button.MenuButton;
import button.NewFileButton;

public class Menu extends JPanel {

	public boolean hasWorkspace;
	public static String workspacePath = "";
	public static String editingFileName = "";
	
	private static final long serialVersionUID = 1L;
	private JButton[] baseButtons;
	private JPanel warning;
	private ArrayList<File> files;
	private ArrayList<JButton> buttons;
	private GridBagConstraints c;
	private EditArea source;
	private Menu m;

	public Menu(EditArea source) throws IOException {
		this.source = source;
		setBounds(new Rectangle(0, 0, Window.FRAME.getSize().width/6, 900));
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createRaisedBevelBorder());
		setBackground(new Color(50, 50, 50));
		setForeground(Color.white);
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		files = new ArrayList<File>();
		buttons = new ArrayList<JButton>();
		baseButtons = new JButton[6];

		c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;
		add(new MenuButton("/images/menu.png", source), c);
		c.gridx = 2;
		c.gridwidth = 3;
		JLabel title = new JLabel("Explorer");
		title.setForeground(new Color(200, 200, 200));
		title.setFont(new Font("Arial", Font.BOLD, 20));
		add(title, c);
		setGridWidth();
		m = this;
		hasWorkspace = false;
		if(!hasWorkspace) {
			JButton open = new JButton("Open Workspace");
			setWarning(open);
			open.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				    JFileChooser chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new java.io.File(Menu.workspacePath.equals("") ? "." : Menu.workspacePath));
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    chooser.setDialogTitle("Open Workspace");
				    int returnVal = chooser.showOpenDialog(open);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	c.weighty = 0;
						c.gridwidth = 6;
						c.gridx = 0;
						c.ipady = 2;
						workspacePath = chooser.getSelectedFile().getPath().replace("/", "\\");
						try {
							add(new DirButton(workspacePath), c);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						c.gridwidth = 5;
						c.gridx = 1;
						File folder = new File(chooser.getSelectedFile().getPath().replace("/", "\\"));
				    	File[] listOfFiles = folder.listFiles();
				    	for (int i = 0; i < listOfFiles.length; i++) {
							if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".txt")) {
								files.add(listOfFiles[i]);
								try {
									buttons.add(new FileButton(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-4), source, listOfFiles[i]));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								c.gridy = i+2;
								add(buttons.get(buttons.size()-1), c);
							}

				    	}
				    	try {
				    		c.gridy++;
							add(new NewFileButton("New File", source, m), c);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    	warning.setVisible(false);
				    	open.setVisible(false);
				    }

				}
			});
		}
	}
	
	public void addOneFile() {
    	try {
    		c.gridy++;
			add(new NewFileButton("New File", source, m), c);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	private void setWarning(JButton open) {
		warning = new JPanel();
		warning.setLayout(new BoxLayout(warning, BoxLayout.Y_AXIS));
		warning.setBackground(new Color(50, 50, 50));
		JLabel label = new JLabel("<html><center> There's <br>  no current <br>  workspace!</center></html>");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		label.setForeground(new Color(200, 200, 200));
		label.setBackground(new Color(50, 50, 50));
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 1;
		c.ipady = 0;
		c.weightx = 0;
		warning.add(label);
		warning.add(open);
		add(warning, c);
	}
	
	private void setGridWidth() {
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(10,0,0,0);
		c.gridy = 100;
		c.gridwidth = 1;

		for(int i=0; i<6; ++i) {
			baseButtons[i] = new JButton(i+"");
			c.gridx = i;
			add(baseButtons[i], c);
		}
	}
}
