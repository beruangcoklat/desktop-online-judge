package view.layout;

import java.awt.event.MouseListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class Form extends JFrame implements MouseListener {

	public Form(int width, int height) {
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Form() {
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
