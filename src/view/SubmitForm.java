package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import controller.ProblemController;
import model.Problem;
import view.layout.InternalForm;
import view.submit.DescriptionPanel;
import view.submit.DiscussionsPanel;
import view.submit.StatisticPanel;
import view.submit.SubmitPanel;

@SuppressWarnings("serial")
public class SubmitForm extends InternalForm {

	public SubmitForm(int problem_id) {
		Problem activeProblem = ProblemController.getInstance().find(problem_id);

		setTitle("Submit");
		setSize(800, 500);
		setLayout(new BorderLayout());

		JTabbedPane tab = new JTabbedPane();
		tab.add("Description", new DescriptionPanel(activeProblem));
		tab.add("Submit Solution", new SubmitPanel(activeProblem));
		tab.add("Submissions and Statistic", new StatisticPanel(activeProblem));
		tab.add("Discussions", new DiscussionsPanel(activeProblem));

		add(new JScrollPane(tab));
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
