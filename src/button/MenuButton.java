package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import window.EditArea;

public class MenuButton extends Button {

	private static final long serialVersionUID = 1L;

	public MenuButton(String iconPath, EditArea source) throws IOException {
		super(iconPath, source);
				
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				source.window.toggleMenu();
			}
			
		});
	}

}
