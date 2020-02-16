package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import view.layout.Form;

@SuppressWarnings("serial")
public class RegisterForm extends Form {

	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnRegister;

	private void init() {
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();
		btnRegister = new JButton("Register");
		btnLogin = new JButton("Login");

		btnRegister.addMouseListener(this);
		btnLogin.addMouseListener(this);
	}

	public RegisterForm() {
		init();
		setSize(400, 150);
		setTitle("Register");
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		add(centerPanel(), BorderLayout.CENTER);
		add(southPanel(), BorderLayout.SOUTH);
		setVisible(true);
	}

	private JPanel centerPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(15, 15, 0, 15));
		panel.setLayout(new GridLayout(2, 2, 15, 15));
		panel.add(new JLabel("Username"));
		panel.add(txtUsername);
		panel.add(new JLabel("Password"));
		panel.add(txtPassword);
		return panel;
	}

	private JPanel southPanel() {
		JPanel panel = new JPanel();
		panel.add(btnRegister);
		panel.add(btnLogin);
		return panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnLogin) {
			dispose();
			new LoginForm();
		} else {
			String username = txtUsername.getText();
			String password = new String(txtPassword.getPassword());

			if (username.length() == 0 || password.length() == 0) {
				JOptionPane.showMessageDialog(null, "username and password must be filled");
				txtUsername.setText("");
				txtPassword.setText("");
				return;
			}

			if (!UserController.getInstance().register(username, password)) {
				JOptionPane.showMessageDialog(null, "username already exists");
				txtUsername.setText("");
				txtPassword.setText("");
				return;
			}

			dispose();
			new MainForm();
		}
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

}
