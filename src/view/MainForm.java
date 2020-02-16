package view;

import java.awt.event.MouseEvent;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import utility.Auth;
import view.layout.Form;
import view.layout.InternalForm;

@SuppressWarnings("serial")
public class MainForm extends Form {

	private JMenu menuProblem, menuProfile, menuLogout;
	private JDesktopPane desktopPane = new JDesktopPane();
	private InternalForm activeForm;

	public MainForm() {
		init();
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		add(desktopPane);
		setJMenuBar(menuBar());
		setVisible(true);
	}

	private void init() {
		menuProblem = new JMenu("Problem");
		menuProfile = new JMenu("Profile");
		menuLogout = new JMenu("Logout");
		desktopPane = new JDesktopPane();
		menuProblem.addMouseListener(this);
		menuProfile.addMouseListener(this);
		menuLogout.addMouseListener(this);
	}

	private JMenuBar menuBar() {
		JMenuBar mb = new JMenuBar();
		mb.add(menuProblem);
		mb.add(menuProfile);
		mb.add(menuLogout);
		return mb;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public InternalForm getActiveForm() {
		return activeForm;
	}

	public void setActiveForm(InternalForm activeForm) {
		this.activeForm = activeForm;
		activeForm.setSize(1000, 600);
		activeForm.setResizable(false);
		activeForm.setMaximizable(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == menuLogout) {
			dispose();
			new LoginForm();
			Auth.getInstance().logout();
			return;
		}

		InternalForm form = null;
		if (e.getSource() == menuProblem)
			form = new ProblemForm(this);
		else if (e.getSource() == menuProfile)
			form = new ProfileForm(this);
		form.setLocation(this.getWidth() / 2 - form.getWidth() / 2, this.getHeight() / 2 - form.getHeight() / 2);

		if (activeForm == null) {
			activeForm = form;
			desktopPane.add(form);
		} else {
			activeForm.dispose();
			activeForm = form;
			desktopPane.add(activeForm);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}
