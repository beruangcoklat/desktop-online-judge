package view.layout;

import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;

@SuppressWarnings("serial")
public abstract class InternalForm extends JInternalFrame implements MouseListener {

	public InternalForm() {
		setSize(500, 500);
		setClosable(true);
		setMaximizable(true);
		setResizable(true);
	}

}
