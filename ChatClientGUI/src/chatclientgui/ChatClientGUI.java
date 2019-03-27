// Dillon Cain - 3/24/19 (CS3000)
// Chat GUI Client
// - Connects to a chat server, (Google Cloud - VM Instance was used (Linux Build))
// 
// 
package chatclientgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatClientGUI {

	private JFrame frame;
	private JTextField tfServer;
	private JTextField tfPort;
	private JTextField tfDisplayName;
	private JTextField tfMsg;
	private JTextArea taMsg;
	
	private ChatClient client;
	private JButton btnBye;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClientGUI window = new ChatClientGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChatClientGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 677, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblServer = new JLabel("Server");
		lblServer.setBounds(55, 55, 56, 16);
		frame.getContentPane().add(lblServer);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(55, 120, 56, 16);
		frame.getContentPane().add(lblPort);
		
		JLabel lblDisplayName = new JLabel("Display Name");
		lblDisplayName.setBounds(55, 183, 118, 16);
		frame.getContentPane().add(lblDisplayName);
		
		tfServer = new JTextField();
		tfServer.setBounds(198, 52, 268, 22);
		frame.getContentPane().add(tfServer);
		tfServer.setColumns(10);
		
		tfPort = new JTextField();
		tfPort.setBounds(198, 117, 268, 22);
		frame.getContentPane().add(tfPort);
		tfPort.setColumns(10);
		
		tfDisplayName = new JTextField();
		tfDisplayName.setBounds(198, 180, 268, 22);
		frame.getContentPane().add(tfDisplayName);
		tfDisplayName.setColumns(10);
		
		tfMsg = new JTextField();
		tfMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send();
				tfMsg.requestFocus();
			}
		});
		tfMsg.setBounds(55, 254, 563, 22);
		frame.getContentPane().add(tfMsg);
		tfMsg.setColumns(10);
		
		JButton btnJoin = new JButton("Join");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String server = tfServer.getText();
				int	   port	  = Integer.parseInt(tfPort.getText());
				String displayName = tfDisplayName.getText();
				
				// Chat client running...
				taMsg.setText("");
				client =
					new ChatClient(server, port, displayName, taMsg, tfMsg);
				
				// Now bye and chat enabled
				btnJoin.setEnabled(false);
				btnBye.setEnabled(true);
				tfMsg.setEditable(true);
			}
		});
		btnJoin.setBounds(521, 51, 97, 25);
		frame.getContentPane().add(btnJoin);
		
		JButton button = new JButton("Bye");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// send .bye
				tfMsg.setText(".bye");
				client.send();
				
				// Disable or enable buttons
				btnJoin.setEnabled(true);
				btnBye.setEnabled(false);
				tfMsg.setEditable(false);
				
			}
		});
		btnBye = button;
		btnBye.setBounds(521, 116, 97, 25);
		frame.getContentPane().add(btnBye);
		
		taMsg = new JTextArea();
		taMsg.setBounds(55, 306, 563, 154);
		frame.getContentPane().add(taMsg);
		
		btnJoin.setEnabled(true);
		btnBye.setEnabled(false);
		tfMsg.setEditable(false);
	}
}
