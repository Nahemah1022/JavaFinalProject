package window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import button.HighlightButton;
import button.TagButton;


public class Window extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private ViewArea viewArea;
	private JPanel leftArea;
	private JPanel rightArea;
	private EditArea editArea;
	private ToolBar toolBar;
	private JLayeredPane muneContainer;
	private Menu menu;
	private boolean menuShowing;
	private static final String TITLE = "Notability";
	
	public static JFrame FRAME;
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 800;

	public Window() throws BadLocationException, IOException {
		setLayout(new BorderLayout());
		
		muneContainer = new JLayeredPane();
		muneContainer.setLayout(new BorderLayout());
		
		leftArea = new JPanel();
		leftArea.setLayout(new BoxLayout(leftArea, BoxLayout.Y_AXIS));
		editArea = new EditArea(this);
		toolBar = new ToolBar(editArea);
		leftArea.add(toolBar);
		leftArea.add(new ScrollArea(editArea, "Source"));
		
		menu = new Menu(editArea);
		muneContainer.add(menu);
		muneContainer.add(leftArea);
		muneContainer.setLayer(leftArea, 100);
		menuShowing = true;
		
		viewArea = new ViewArea(editArea);
		rightArea = new JPanel();
		rightArea.setLayout(new BoxLayout(rightArea, BoxLayout.Y_AXIS));
		rightArea.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		rightArea.add(new ScrollArea(viewArea, "Styled"));
		JPanel footer = new JPanel();
		footer.add(new HighlightButton("/images/highlighter.png", editArea, viewArea));
		footer.setBackground(Color.white);
		footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		rightArea.add(footer);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        		new JScrollPane(muneContainer),
        		rightArea
        );
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);
        add(splitPane);
	}
	
	public void passSource(Document document) throws BadLocationException {
		viewArea.setViewText(document);
		//document.getText(0, document.getLength())
	}
	
    private static void createAndShowGUI() throws BadLocationException, IOException {
        //Create and set up the window.
    	FRAME = new JFrame(Window.TITLE);
        FRAME.setSize(Window.WIDTH, Window.HEIGHT);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        FRAME.add(new Window());

        //Display the window.
        FRAME.pack();
        FRAME.setVisible(true);
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
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
	}

	public void toggleMenu() {
		muneContainer.setLayer(leftArea, this.menuShowing ? -1 : 100);
		this.editArea.setEditable(!menuShowing);
		this.menuShowing = !this.menuShowing;
		this.menu.setVisible(!this.menuShowing);
		this.toolBar.toggleFileSection();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
