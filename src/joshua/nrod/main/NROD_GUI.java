package joshua.nrod.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JDesktopPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class NROD_GUI extends JFrame {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NROD_GUI frame = new NROD_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NROD_GUI() {
		setTitle("JPLogics - NROD Connection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextArea GUIConsole = new JTextArea();
		GUIConsole.setText("GUI-Console");
		contentPane.add(GUIConsole, BorderLayout.CENTER);
		JLabel lblS = new JLabel("JPLogics - NROD Connection");
		contentPane.add(lblS, BorderLayout.NORTH);
	}

}
