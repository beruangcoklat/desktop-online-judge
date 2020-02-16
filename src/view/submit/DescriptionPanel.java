package view.submit;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import model.Problem;

@SuppressWarnings("serial")
public class DescriptionPanel extends JPanel {

	public DescriptionPanel(final Problem activeProblem) {
		setLayout(new BorderLayout());
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JTextArea text = new JTextArea(activeProblem.getDescription());
		text.setEditable(false);
		text.setLineWrap(true);
		text.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel title = new JLabel(activeProblem.getTitle());
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setBorder(new EmptyBorder(0, 0, 5, 0));

		add((title), BorderLayout.NORTH);
		add(text, BorderLayout.CENTER);
	}

}
