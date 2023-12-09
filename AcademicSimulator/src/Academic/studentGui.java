package Academic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class studentGui {

	public static void showStudentGui(Student student, Frame loginFrame)
	{
		Frame studentFrame = new Frame("Student Account");
		
		loginFrame.dispose();
		
		JLabel userLabel = new JLabel("User: " + student.getName() + " with ID: " + student.getId());
		userLabel.setFont(new Font("Georgia", Font.BOLD, 15));
		userLabel.setBounds(130, 25, 250, 100);
		userLabel.setForeground(Color.white);
		studentFrame.add(userLabel);
		
		Color color = new Color(146, 180, 244);
		studentFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); studentFrame.dispose();} , "End Session");
		studentFrame.addButton(new Rectangle(105, 200, 180, 50), color, Color.black, e -> {checkScholarSituation(student, loginFrame); studentFrame.dispose();}, "Check Scholar Situation");
		studentFrame.addButton(new Rectangle(105, 250, 180, 50), color, Color.black, e -> {messageToAdmin(student, loginFrame); studentFrame.dispose();}, "Request to admin");
		
		studentFrame.setVisible(true);
	}
	
	public static void checkScholarSituation(Student student, Frame loginFrame)
	{
		Frame checkFrame = new Frame("Check Scholar Situation");
		
		checkFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); checkFrame.dispose();}, "End Session");
        checkFrame.addButton(new Rectangle(0, 100, 107, 50), new Color(216, 210, 225), Color.white, e -> {studentGui.showStudentGui(student, loginFrame); checkFrame.dispose();}, "<--");
		
		List<Subject> subjectsList = Subject.getAllSubjects();
		String[] subjectNames = new String[subjectsList.size()];
		int i = 0;
		for (Subject subject : subjectsList) 
		{
		    subjectNames[i++] = subject.getName();
		}

		JComboBox<String> subjectCombo = new JComboBox<>(subjectNames);
		subjectCombo.setBounds(250, 200, 130, 30);
		checkFrame.add(subjectCombo);
		
		JComboBox<String> situationCombo = new JComboBox<>();
		situationCombo.addItem("Notes");
		situationCombo.addItem("Absences");
		situationCombo.setBounds(250, 250, 130, 30);
		checkFrame.add(situationCombo);
		
		checkFrame.addLabel(new Rectangle(50, 200, 200, 30), "Choose subject:");
		checkFrame.addLabel(new Rectangle(50, 250, 200, 30), "Choose situation:");
		
		JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.green);
        submitButton.setForeground(Color.black);
        submitButton.setBounds(new Rectangle(250, 300, 130, 30));
    	
    	submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	Subject selectedSubject = Subject.getObject((String) subjectCombo.getSelectedItem());
            	String situation = (String) situationCombo.getSelectedItem();
            	
            	String[] columnNames = {situation};
            	DefaultTableModel model = new DefaultTableModel(null, columnNames);
            	
            	if (situation.equals("Notes"))
            	{
            		List<String> notes = student.getNotes(selectedSubject);
            		
            		for (String note : notes) {
                        model.addRow(new Object[]{"Note: " + note});
                    }
                    
                    JTable tabel = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(tabel);
                    scrollPane.setBounds(0, 350, 400, 130);
                    checkFrame.add(scrollPane);
            	}
            	else
            	{
            		List<String> absences = student.getAbsences(selectedSubject);
            		for (String absence : absences) {
                        model.addRow(new Object[]{ absence});
                    }
            		
                    JTable tabel = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(tabel);
                    scrollPane.setBounds(0, 350, 400, 130);
                    checkFrame.add(scrollPane);
            	}
            	
            		
                checkFrame.revalidate();
            	
            }
        }); 
        
        checkFrame.add(submitButton);
		
        checkFrame.setMessageForNeedHelp("Here you see the scholar situation");
        
		checkFrame.setVisible(true);
	}
	
	public static void messageToAdmin(Student student, Frame loginFrame)
	{
		Frame messageToAdminFrame = new Frame("Send Message to admin");
		
		messageToAdminFrame.addLabel(new Rectangle(175, 50, 200, 100), "MESSAGE ADMIN");
		
		messageToAdminFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); messageToAdminFrame.dispose();}, "End Session");
        messageToAdminFrame.addButton(new Rectangle(0, 100, 107, 50), new Color(216, 210, 225), Color.white, e -> {studentGui.showStudentGui(student, loginFrame); messageToAdminFrame.dispose();}, "<--");
        
        messageToAdminFrame.addLabel(new Rectangle(50, 175, 150, 100), "Name:");
        messageToAdminFrame.addLabel(new Rectangle(50, 225, 150, 100), "Message:");
        
        JTextField nameField = messageToAdminFrame.addField(new Rectangle(175, 205, 150, 30));
        JTextField messageField = messageToAdminFrame.addField(new Rectangle(175, 255, 150, 30));
        
        messageToAdminFrame.addButton(new Rectangle(130, 450, 130, 50), Color.green, Color.black, e ->{
        	Admin admin = Admin.getObject(nameField.getText());
        	
        	if(admin != null)
        	{
        		admin.receiveMessage(messageField.getText() + " from Student: " + student.getName());
        	}
        	
        	messageToAdminFrame.setVisible(false);
        	studentGui.showStudentGui(student, loginFrame);
        	
        	}, "Submit");
        
        messageToAdminFrame.setMessageForNeedHelp("Here you sent message to admin");
        
        messageToAdminFrame.setVisible(true);
	}
}
