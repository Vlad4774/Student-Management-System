package Academic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    private String message;

    public Frame(String title) {
        this.setTitle(title);
        this.setSize(400, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        getContentPane().setBackground(new Color(22, 16, 50));
        
        JLabel textLabel = new JLabel("Student Management System");
        textLabel.setForeground(Color.WHITE);
        textLabel.setBackground(new Color(22, 16, 50));
        textLabel.setOpaque(true);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setVerticalAlignment(JLabel.CENTER);
        textLabel.setFont(new Font("Arial", Font.BOLD, 24));
        textLabel.setBounds(0, 0, 400, 50);
        this.add(textLabel);
        
        JButton needHelpButton = new JButton("Need Help?");
        needHelpButton.setBounds(130, 500, 130, 40);
        needHelpButton.setBackground(new Color(208, 214, 179));
        needHelpButton.addActionListener(e -> showMessageForNeedHelp());
        this.add(needHelpButton);
    }
    
    private void showMessageForNeedHelp()
    {        JOptionPane.showMessageDialog(null, message, "Need Help Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setMessageForNeedHelp(String text) {
        message = text;
    }
    
    public void addButton(Rectangle r, Color background, Color foreground, ActionListener action, String text)
    {
    	JButton button = new JButton(text);
    	button.setBounds(r);
    	button.setBackground(background);
    	button.setForeground(foreground);
    	button.addActionListener(action);
    	this.add(button);
    }
    
    public JTextField addField(Rectangle r)
    {
    	JTextField field = new JTextField();
    	field.setBounds(r);
    	this.add(field);
    	return field;
    }
    
    public void addLabel(Rectangle r, String text)
    {
    	JLabel userLabel = new JLabel(text);
        userLabel.setFont(new Font("Georgia", Font.BOLD, 20));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(r);
        this.add(userLabel);
    }
}
