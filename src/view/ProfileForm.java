package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProblemController;
import controller.StatusController;
import controller.SubmissionController;
import model.Problem;
import model.Status;
import model.Submission;
import utility.Auth;
import view.layout.InternalForm;

@SuppressWarnings("serial")
public class ProfileForm extends InternalForm {

	private JTable table;
	private DefaultTableModel dtm;
	private JTextArea txtCodingan;

	private void init() {
		String columnNames[] = { "ID", "Problem", "Status" };
		dtm = new DefaultTableModel(null, columnNames){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dtm);

		for (Submission sub : Auth.getInstance().user().getSubmissions()) {
			int id = sub.getId();
			int problem_id = sub.getProblem_id();
			int status_id = sub.getStatus_id();
			Problem problem = ProblemController.getInstance().find(problem_id);
			Status status = StatusController.getInstance().find(status_id);
			Vector<Object> rowData = new Vector<>();
			rowData.add(id);
			rowData.add(problem.getTitle());
			rowData.add(status.getName());
			dtm.addRow(rowData);
		}

		table.addMouseListener(this);
		txtCodingan = new JTextArea();
		txtCodingan.setEditable(false);
	}

	public ProfileForm(MainForm mainForm) {
		init();
		setSize(1000, 500);
		setTitle("Profile");
		setLayout(new BorderLayout());
		add(northPanel(), BorderLayout.NORTH);
		add(centerPanel(), BorderLayout.CENTER);
		setVisible(true);
	}

	private JPanel centerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(table), BorderLayout.WEST);
		panel.add(new JScrollPane(txtCodingan), BorderLayout.CENTER);
		panel.setBorder(new EmptyBorder(5, 10, 10, 10));
		return panel;
	}

	private JPanel northPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		int totalSubmission = Auth.getInstance().user().getSubmissions().size();
		panel.add(new JLabel("Username: " + Auth.getInstance().user().getUsername()));
		panel.add(new JLabel("Total Submission: " + totalSubmission));
		panel.setBorder(new EmptyBorder(10, 10, 5, 10));
		return panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			int row = table.getSelectedRow();
			int id = Integer.parseInt(table.getValueAt(row, 0).toString());
			Submission s = SubmissionController.getInstance().find(id);
			txtCodingan.setText(s.getSubmission());
		} catch (Exception ee) {

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
