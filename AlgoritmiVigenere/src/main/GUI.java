package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jdesktop.swingx.prompt.PromptSupport;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import java.awt.Label;
import main.FocusTraversalOnArray;
import java.awt.Component;

public class GUI
{
	private JFrame frmVigenereAlgorithm;
	private JTextField txtText;
	private JTextField txtEnkriptuar;
	private JTextField txtDekriptuar;
	private JTextField txtKey;
	private JButton btnClean;
	private JTextField txtKeyLength;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try {
					GUI window = new GUI();
					window.frmVigenereAlgorithm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public GUI() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		int width = tk.getScreenSize().width;
		int height = tk.getScreenSize().height;
		frmVigenereAlgorithm = new JFrame();
		frmVigenereAlgorithm.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("")));
		frmVigenereAlgorithm.setAutoRequestFocus(false);
		frmVigenereAlgorithm.setResizable(false);
		frmVigenereAlgorithm.setTitle("Vigenere Algorithm");
		frmVigenereAlgorithm.setBounds(0, 0, width, height);
		frmVigenereAlgorithm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVigenereAlgorithm.getContentPane().setLayout(null);
		
		txtText = new JTextField();
		txtText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar = e.getKeyChar();
				if(Character.isLetter(vchar) || Character.isDigit(vchar)){}
				else{
					JOptionPane.showMessageDialog(null, "Ju lutem shtypni vetem numra dhe shkronja");
					e.consume();
				}
			}
		});
		txtText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtText.setBounds(601, 397, 707, 42);
		frmVigenereAlgorithm.getContentPane().add(txtText);
		txtText.setColumns(10);
		PromptSupport.setPrompt("Shkruani tekstin tuaj ", txtText);
		
		txtEnkriptuar = new JTextField();
		txtEnkriptuar.setEditable(false);
		txtEnkriptuar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEnkriptuar.setColumns(10);
		txtEnkriptuar.setBounds(601, 541, 707, 47);
		frmVigenereAlgorithm.getContentPane().add(txtEnkriptuar);
		PromptSupport.setPrompt("Teksti i Enkriptuar ", txtEnkriptuar);
		
		txtDekriptuar = new JTextField();
		txtDekriptuar.setEditable(false);
		txtDekriptuar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDekriptuar.setColumns(10);
		txtDekriptuar.setBounds(601, 690, 707, 42);
		frmVigenereAlgorithm.getContentPane().add(txtDekriptuar);
		PromptSupport.setPrompt("Teksti i Dekriptuar", txtDekriptuar);
		
		JLabel lblGjatesiaEQelesit_1 = new JLabel("Gjatesia e qelesit te gjenerohet ne menyre :");
		lblGjatesiaEQelesit_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblGjatesiaEQelesit_1.setBounds(601, 186, 440, 25);
		frmVigenereAlgorithm.getContentPane().add(lblGjatesiaEQelesit_1);
		
		txtKeyLength = new JTextField();
		txtKeyLength.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtKeyLength.setBounds(822, 245, 202, 36);
		frmVigenereAlgorithm.getContentPane().add(txtKeyLength);
		txtKeyLength.setColumns(10);
		PromptSupport.setPrompt("#", txtKeyLength);
		
		final JComboBox cmbMenyra = new JComboBox();
		cmbMenyra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbMenyra.getSelectedItem().toString().equals("Automatike")){
					txtKeyLength.setEditable(false);
					Random ran = new Random();
					int n = ran.nextInt(200);
					txtKeyLength.setText(String.valueOf(n));
				}
				else if(cmbMenyra.getSelectedItem().toString().equals("Manuale")){
					txtKeyLength.setText(null);
					txtKeyLength.setEditable(true);
					txtKeyLength.addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent e)
						{
							char c = e.getKeyChar();
							if (!Character.isDigit(c))
							{
								JOptionPane.showMessageDialog(null, "Ju lutem shkruani numer te plote!");
							    e.consume();
							}
						}
					});
				}
			}
		});
		cmbMenyra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbMenyra.setModel(new DefaultComboBoxModel(new String[] {"Automatike", "Manuale"}));
		cmbMenyra.setSelectedIndex(0);
		cmbMenyra.setToolTipText(" ");
		cmbMenyra.setBounds(1061, 183, 247, 36);
		frmVigenereAlgorithm.getContentPane().add(cmbMenyra);
		
		txtKey = new JTextField();
		txtKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e)
			{
				 int c = e.getKeyChar();
				 if (Character.isDigit(c))
				 	{
					 e.setKeyChar((char)(c+17));	
				    }
				 char c1 = e.getKeyChar();
				 if(Character.isLowerCase(c))
				 {
					e.setKeyChar(Character.toUpperCase(c1));
				 }
			}
		});
		txtKey.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtKey.setBounds(601, 308, 423, 44);
		frmVigenereAlgorithm.getContentPane().add(txtKey);
		txtKey.setColumns(10);
		PromptSupport.setPrompt("Qelesi", txtKey);
		
		JButton btnGjeneroQelesin = new JButton("Gjenero Qelesin");
		btnGjeneroQelesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try{
					int length = new Integer(txtKeyLength.getText());
					String key = Vigenere.generateString(length);
					txtKey.setText(key);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Caktoni Gjatesin e Qelesit ne fillim.");
				}
			}
		});
		btnGjeneroQelesin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnGjeneroQelesin.setBounds(1061, 304, 247, 48);
		frmVigenereAlgorithm.getContentPane().add(btnGjeneroQelesin);
		
		JButton btnEnkripto = new JButton("Enkripto");
		btnEnkripto.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnEnkripto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try{
					String text = txtText.getText();
					String key = txtKey.getText();
					txtEnkriptuar.setText(Vigenere.encrypt(text, key));
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Caktoni ose Gjeneroni Qelesin ne Fillim.");
				}
			}
		});
		btnEnkripto.setBounds(1061, 463, 247, 53);
		frmVigenereAlgorithm.getContentPane().add(btnEnkripto);
		
		JButton btnDekripto = new JButton("Dekripto");
		btnDekripto.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnDekripto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try{
					String text = txtEnkriptuar.getText();
					String key = txtKey.getText();
					txtDekriptuar.setText(Vigenere.decrypt(text, key));
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Caktoni ose Gjeneroni Qelesin ne Fillim.");
				}
			}
		});
		btnDekripto.setBounds(1061, 613, 247, 53);
		frmVigenereAlgorithm.getContentPane().add(btnDekripto);
		
		btnClean = new JButton("Clean");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				txtKey.setText(null);
				txtText.setText(null);
				txtEnkriptuar.setText(null);
				txtDekriptuar.setText(null);
				txtKeyLength.setText(null);
				if(cmbMenyra.getSelectedItem().toString().equals("Automatike"))
				{
					Random ran = new Random();
					int n = ran.nextInt(200);
					txtKeyLength.setText(String.valueOf(n));
				}
			}
		});
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnClean.setBounds(1061, 760, 247, 47);
		frmVigenereAlgorithm.getContentPane().add(btnClean);
		
		JLabel lblGjatesiaEQelesit = new JLabel("Gjatesia e Qelesit : ");
		lblGjatesiaEQelesit.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblGjatesiaEQelesit.setBounds(601, 245, 206, 25);
		frmVigenereAlgorithm.getContentPane().add(lblGjatesiaEQelesit);
		frmVigenereAlgorithm.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{cmbMenyra, txtKeyLength, txtKey, btnGjeneroQelesin, txtText, btnEnkripto, txtEnkriptuar, btnDekripto, txtDekriptuar, btnClean, lblGjatesiaEQelesit_1, lblGjatesiaEQelesit}));
		
	}
}
