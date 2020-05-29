import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Window extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private EditArea editArea;
	private ViewArea viewArea;
	private static final String TITLE = "Notability";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;

	public Window() {
		setLayout(new BorderLayout());
		
		editArea = new EditArea();
		viewArea = new ViewArea();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        		new ScrollArea(editArea, "Source"),
        		new ScrollArea(viewArea, "Styled")
        );
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);
        add(splitPane);
	}
	
    private static void createAndShowGUI() {
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
            	createAndShowGUI();
            }
        });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
