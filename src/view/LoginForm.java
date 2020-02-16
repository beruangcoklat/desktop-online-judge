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

import utility.Auth;
import view.layout.Form;

@SuppressWarnings("serial")
public class LoginForm extends Form {

	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnRegister;

	private void init() {
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();
		btnLogin = new JButton("Login");
		btnRegister = new JButton("Register");

		btnLogin.addMouseListener(this);
		btnRegister.addMouseListener(this);
	}

	public LoginForm() {
		init();
		setSize(350, 150);
		setTitle("Login");
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
		panel.add(btnLogin);
		panel.add(btnRegister);
		return panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnLogin) {
			String username = txtUsername.getText();
			String password = new String(txtPassword.getPassword());
			boolean valid = Auth.getInstance().attempt(username, password);
			if (!valid) {
				JOptionPane.showMessageDialog(null, "invalid username or password");
				return;
			}
			dispose();
			new MainForm();
		} else if (e.getSource() == btnRegister) {
			dispose();
			new RegisterForm();
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
