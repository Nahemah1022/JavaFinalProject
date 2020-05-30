import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class Window extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private EditArea editArea;
	private ViewArea viewArea;
	private static final String TITLE = "Notability";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;

	public Window() throws BadLocationException {
		setLayout(new BorderLayout());
		
		editArea = new EditArea(this);
		viewArea = new ViewArea(editArea.getDocument());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        		new ScrollArea(editArea, "Source"),
        		new ScrollArea(viewArea, "Styled")
        );
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);
        add(splitPane);
	}
	
	public void passSource(Document document) throws BadLocationException {
		viewArea.setViewText(document);
		//document.getText(0, document.getLength())
	}
	
    private static void createAndShowGUI() throws BadLocationException {
        //Create and set up the window.
        JFrame frame = new JFrame(Window.TITLE);
        frame.setSize(Window.WIDTH, Window.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Window());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
            	UIManager.put("swing.boldMetal", Boolean.FALSE);
            	try {
					createAndShowGUI();
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
