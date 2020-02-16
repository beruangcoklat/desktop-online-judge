package view.submit;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.StatusController;
import controller.UserController;
import model.Problem;
import model.Submission;

@SuppressWarnings("serial")
public class StatisticPanel extends JPanel {

	private Problem activeProblem;
	private JTable table;
	private DefaultTableModel dtm;

	private void init() {
		String columnNames[] = { "Username", "Status" };
		dtm = new DefaultTableModel(null, columnNames){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dtm);
		for (Submission submission : activeProblem.submissions()) {
			String username = UserController.getInstance().find(submission.getUser_id()).getUsername();
			String status = StatusController.getInstance().find(submission.getStatus_id()).getName();
			Vector<Object> rowData = new Vector<>();
			rowData.add(username);
			rowData.add(status);
			dtm.addRow(rowData);
		}
	}

	public StatisticPanel(Problem activeProblem) {
		this.activeProblem = activeProblem;
		if(this.activeProblem == null){
			System.out.println("kosong");
		}
		init();
		add(new JScrollPane(table));
	}

}
