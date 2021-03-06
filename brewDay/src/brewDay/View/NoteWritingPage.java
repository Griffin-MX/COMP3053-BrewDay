package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import brewDay.Note;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class NoteWritingPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoteWritingPage frame = new NoteWritingPage(0,null,0,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param bid 
	 */
	public NoteWritingPage(int bid, String btnvalue, int nid, String oldtext) {
		setTitle("Note");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(93, 44, 256, 171);
		contentPane.add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setText(oldtext);
		scrollPane.setColumnHeaderView(editorPane);
		
		JLabel lblNewLabel = new JLabel("<html>The note will be attached to the Brew "+bid+"</httml>");
		lblNewLabel.setBounds(93, 25, 273, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(30, 144, 255));
		btnBack.setBounds(6, 6, 75, 36);
		contentPane.add(btnBack);
		
		btnBack.addActionListener(new ActionListener(){
			
        	public void actionPerformed(ActionEvent e) {
        	dispose();

        	JFrame main;
			try {
				main = new NoteMain();
			
        	main.setLocation(100,50);
        	main.setSize(600, 500);
        	main.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	}

        	});
		
		JButton btnFinish = new JButton(btnvalue);
		btnFinish.setForeground(new Color(255, 105, 180));
		btnFinish.setBounds(6, 214, 75, 36);
		contentPane.add(btnFinish);
		btnFinish.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		if(btnvalue == "Add") {
        			if(editorPane.getContentType().trim().equals("")) {
    					String messege="You must write something!";
    					JFrame win = new PromptWindow(messege);
    					win.setLocation(500, 80);
    					win.setSize(400, 200);
    					win.setVisible(true);}
        			else {
        		String content = editorPane.getText();
        		Note n = new Note();
        		n.addNote(content, bid);
        		dispose();
        		JFrame main;
    			
    				try {
						main = new NoteMain();
					
    			
            	main.setLocation(100,50);
            	main.setSize(600, 500);
            	main.setVisible(true);
    				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		String messege="Your note have added.";
				JFrame win = new PromptWindow(messege);
				win.setLocation(500, 80);
				win.setSize(400, 200);
				win.setVisible(true);
        		}

        		}
        		if(btnvalue == "Edit") {
        			if(editorPane.getContentType().trim().equals("")) {
    					String messege="You must write something!";
    					JFrame win = new PromptWindow(messege);
    					win.setLocation(500, 80);
    					win.setSize(400, 200);
    					win.setVisible(true);}
        			else {
        		String content = editorPane.getText();
        		Note.UIedit(content, nid);
        		dispose();
        		JFrame main;
    			
    				try {
						main = new NoteMain();
					
    			
            	main.setLocation(100,50);
            	main.setSize(600, 500);
            	main.setVisible(true);
    				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		String messege="Your note have updated.";
				JFrame win = new PromptWindow(messege);
				win.setLocation(500, 80);
				win.setSize(400, 200);
				win.setVisible(true);
        		}
        		}
        	}

        	});
	}
}
