package Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuFrame extends MyFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton startButton1;
	public JButton startButton2;
	public JButton closeButton;
	public MenuFrame()
	{
		super("Jegmezo - Menu");
		startButton1 = new JButton("Palya 1 Start");
		startButton2 = new JButton("Palya 2 Start");
		closeButton = new JButton("Bezaras");
		JLabel jl = new JLabel("Jegmezo");
		this.setSize(400,400);
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jl.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		jl.setBounds(0,0,400,50);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.cyan);
		jp.setBackground(Color.cyan);
		jl.setBackground(Color.cyan);
		jl.setOpaque(true);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
		startButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
		closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(jl, BorderLayout.NORTH);
		jp.add(Box.createRigidArea(new Dimension(0,20)));
		jp.add(startButton1);
		jp.add(Box.createRigidArea(new Dimension(0,20)));
		jp.add(startButton2);
		jp.add(Box.createRigidArea(new Dimension(0,20)));
		jp.add(closeButton);
		this.add(jp, BorderLayout.CENTER);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
	}

}
