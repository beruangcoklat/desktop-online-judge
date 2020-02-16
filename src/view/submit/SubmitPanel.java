package view.submit;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.SubmissionController;
import model.Problem;
import utility.Auth;
import utility.Compiler;
import utility.CompilerProxy;
import utility.Util;

@SuppressWarnings("serial")
public class SubmitPanel extends JPanel implements MouseListener {

	private JTextArea txtCodingan;
	private JButton btnSubmit;
	private Problem activeProblem;

	private void init() {
		txtCodingan = new JTextArea();
		btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(this);

		final String templateCodingan = "class Main{\n" + "\tpublic static void main(String[] args){\n\n" + "\t}\n"
				+ "}";
		txtCodingan.setText(templateCodingan);
	}

	public SubmitPanel(Problem activeProblem) {
		init();
		this.activeProblem = activeProblem;
		setLayout(new BorderLayout());
		JLabel title = new JLabel("Submit solution for problem: " + activeProblem.getTitle());
		title.setFont(new Font("Arial", Font.BOLD, 20));
		txtCodingan.setLineWrap(true);

		add(Util.wrap(title), BorderLayout.NORTH);
		add(txtCodingan, BorderLayout.CENTER);
		add(Util.wrap(btnSubmit), BorderLayout.SOUTH);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnSubmit) {
			String status = "";
			CompilerProxy.getInstance().setup(txtCodingan.getText(), activeProblem.getInput());
			String[] compileResult = CompilerProxy.getInstance().compile();
			String resultStatus = compileResult[0];
			String result = compileResult[1];

			if (resultStatus.equals(Compiler.SUCCESS)) {
				String answer = activeProblem.getAnswer();
				status = result.equals(answer) ? "Accepted" : "Wrong Answer";
				JOptionPane.showMessageDialog(null, status);
			} else if (resultStatus.equals(Compiler.COMPILER_ERROR)) {
				status = "Compile Error";
			} else if (resultStatus.equals(Compiler.TLE)) {
				status = "TLE";
			} else if (resultStatus.equals(Compiler.RUNTIME_ERROR)) {
				status = "Runtime Error";
			}

			int status_id = -1;
			if (status.equals("Accepted"))
				status_id = 1;
			else if (status.equals("Wrong Answer"))
				status_id = 2;
			else if (status.equals("Compile Error"))
				status_id = 3;
			else if (status.equals("TLE"))
				status_id = 4;
			else if (status.equals("Runtime Error"))
				status_id = 5;

			int user_id = Auth.getInstance().user().getId();
			int problem_id = activeProblem.getId();
			String submission = txtCodingan.getText();
			SubmissionController.getInstance().insert(user_id, problem_id, status_id, submission);
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
