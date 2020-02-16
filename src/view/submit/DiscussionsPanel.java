package view.submit;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.PostController;
import controller.UserController;
import model.Post;
import model.Problem;
import model.User;
import utility.Util;

@SuppressWarnings("serial")
public class DiscussionsPanel extends JPanel implements ActionListener {

	private JTextField txtInput;
	private JButton btnSubmit;
	private Problem activeProblem;
	private JScrollPane centerPanel;

	private void init() {
		txtInput = new JTextField();
		btnSubmit = new JButton("Post");
		btnSubmit.addActionListener(this);
	}

	public DiscussionsPanel(Problem activeProblem) {
		this.activeProblem = activeProblem;
		init();
		setLayout(new BorderLayout());

		centerPanel = centerPanel();
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel(), BorderLayout.SOUTH);
	}

	private JScrollPane centerPanel() {
		JPanel panel = new JPanel();
		Vector<Post> posts = PostController.getInstance().getFromProblem(activeProblem.getId());
		int aaa = posts.size();
		if(aaa < 5) aaa = 5;
		panel.setLayout(new GridLayout(aaa, 1));
		for (Post post : posts)
			panel.add(dataPanel(post));

		JScrollPane comments = new JScrollPane(panel);
		comments.setPreferredSize(new Dimension(200, 200));
		comments.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			boolean flag = false;

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if (!flag) {
					flag = true;
					e.getAdjustable().setValue(e.getAdjustable().getMaximum());
				}
			}
		});

		return comments;
	}

	private JPanel dataPanel(Post post) {
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(100, 100));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(new BorderLayout());
		User u = UserController.getInstance().find(post.getUser_id());
		panel.add(new JLabel(u.getUsername()), BorderLayout.NORTH);

		JTextField content = new JTextField(post.getDescription());
		content.setEditable(false);
		panel.add(content, BorderLayout.CENTER);
		return panel;
	}

	private JPanel southPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(15, 15, 15, 15));
		panel.setLayout(new GridLayout(2, 1));
		panel.add(txtInput);
		panel.add(Util.wrap(btnSubmit));
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSubmit) {
			String post = txtInput.getText();
			PostController.getInstance().insert(post, activeProblem.getId());
			
			remove(centerPanel);
			centerPanel = centerPanel();
			add(centerPanel, BorderLayout.CENTER);
			revalidate();
			
			txtInput.setText("");
		}
	}

}
