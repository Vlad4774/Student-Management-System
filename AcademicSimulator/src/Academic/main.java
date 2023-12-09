package Academic;

import javax.swing.*;

import Academic.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class main {
	public static void main(String[] args) {
		
		Frame frame = new Frame("Login");
		
		initializateProgram();
		
		frame.addLabel(new Rectangle(50, 175, 100, 100), "User:");
        frame.addLabel(new Rectangle(50, 250, 150, 50), "Password:");
        
        JTextField userField = frame.addField(new Rectangle(175, 205, 150, 30));
        JTextField passwordField = frame.addField(new Rectangle(175, 255, 150, 30));
        
        frame.addButton(new Rectangle(130, 350, 130, 50), Color.green, Color.black, e -> Connecting(userField, passwordField, frame), "Connect");
        frame.addButton(new Rectangle(120, 425, 150, 50), Color.blue, Color.black, e -> addRequest(frame), "Request an account");
        
        frame.setMessageForNeedHelp("Here you need to add your user and password");
        
        frame.setVisible(true);
	}
	
	public static void Connecting(JTextField userField, JTextField passwordField, Frame frame)
	{
		String user = userField.getText();
        String password = passwordField.getText();
        
        userField.setText("");
        passwordField.setText("");
        
        Admin admin = Admin.checkUser(user, password);
        Student student = Student.checkStudent(user, password);
        
        if(admin != null)
        {
      	    adminGui.showAdminGUI(admin, frame);
        }
        else if(student != null)
        {
        	studentGui.showStudentGui(student, frame);
        }
        else
        {
      	    JOptionPane.showMessageDialog(null, "Invalid User", "Need Help Information", JOptionPane.INFORMATION_MESSAGE);
        }
	}
	
	private static void initializateProgram()
	{
		new Admin("admin", "admin");
		new Subject("mathematics", "6");
		new Subject("OOP", "9");
		new Student("Vlad", "1234");
		new Student("John", "1234");
		new Student("Christian", "1234");
		new Student("Smara", "1234");
	}
	
	public static void addRequest(Frame frame)
	{
		frame.dispose();
		
        Frame addRequestFrame = new Frame("Send Message to admin");
		
		addRequestFrame.addLabel(new Rectangle(165, 50, 200, 100), "MESSAGE ADMIN");
		
        addRequestFrame.addButton(new Rectangle(0, 50, 107, 50), new Color(216, 210, 225), Color.white, e -> {frame.setVisible(true); addRequestFrame.dispose();}, "<--");
        
        addRequestFrame.addLabel(new Rectangle(50, 175, 150, 100), "Your Name:");
        addRequestFrame.addLabel(new Rectangle(50, 225, 150, 100), "Message:");
        
        JTextField nameField = addRequestFrame.addField(new Rectangle(175, 210, 150, 30));
        JTextField messageField = addRequestFrame.addField(new Rectangle(175, 260, 150, 30));
        
        addRequestFrame.addButton(new Rectangle(130, 450, 130, 50), Color.green, Color.black, e ->{
        	Admin admin = Admin.getObject("admin");
        	
        	if(admin != null)
        	{
        		admin.receiveMessage(messageField.getText() + " from Student without account: " + nameField.getText());
        	}
        	
        	addRequestFrame.setVisible(false);
        	frame.setVisible(true);
        	
        	}, "Submit");
        
        addRequestFrame.setMessageForNeedHelp("Here you can send message to an admin");
        
        addRequestFrame.setVisible(true);
	}
}
