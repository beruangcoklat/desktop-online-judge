package view;

import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ProblemController;
import model.Problem;
import view.layout.InternalForm;

@SuppressWarnings("serial")
public class ProblemForm extends InternalForm {

	private JTable table;
	private DefaultTableModel dtm;
	private MainForm mainForm;

	private void initTable() {
		String columnNames[] = { "ID", "Title", "Total Submissions" };
		dtm = new DefaultTableModel(null, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		table = new JTable(dtm);

		for (Problem prob : ProblemController.getInstance().all()) {
			Vector<Object> rowData = new Vector<>();
			rowData.add(prob.getId());
			rowData.add(prob.getTitle());
			rowData.add(prob.submissions().size());
			dtm.addRow(rowData);
		}
		add(new JScrollPane(table));
		table.addMouseListener(this);
	}

	public ProblemForm(MainForm mainForm) {
		this.mainForm = mainForm;
		initTable();
		setTitle("Problem");
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			int row = table.getSelectedRow();
			int problem_id = (int) table.getValueAt(row, 0);

			mainForm.getActiveForm().dispose();
			InternalForm form = new SubmitForm(problem_id);
			mainForm.setActiveForm(form);
			form.setLocation(mainForm.getWidth() / 2 - form.getWidth() / 2,
					mainForm.getHeight() / 2 - form.getHeight() / 2);
			mainForm.getDesktopPane().add(form);
		} catch (Exception ee) {
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
