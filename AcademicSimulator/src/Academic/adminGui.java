package Academic;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class adminGui {

	public static void showAdminGUI(Admin admin, Frame loginFrame)
	{
		Frame adminFrame = new Frame("Admin Account");
		
		loginFrame.dispose();
		
		JLabel userLabel = new JLabel("User: " + admin.getName() + " with ID: " + admin.getId());
		userLabel.setFont(new Font("Georgia", Font.BOLD, 15));
		userLabel.setBounds(130, 25, 250, 100);
		userLabel.setForeground(Color.white);
		adminFrame.add(userLabel);
		
		Color color = new Color(146, 180, 244);
		
		adminFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); adminFrame.dispose();} , "End Session");
		adminFrame.addButton(new Rectangle(105, 100, 180, 50), color, Color.black, e -> AddAdmin(adminFrame, loginFrame), "Add Admin/Proffesor");
		adminFrame.addButton(new Rectangle(105, 150, 180, 50), color, Color.black, e -> AddStudent(adminFrame, loginFrame), "Add Student");
		adminFrame.addButton(new Rectangle(105, 200, 180, 50), color, Color.black, e -> UpdateStudent(adminFrame, loginFrame), "Update Student");
		adminFrame.addButton(new Rectangle(105, 250, 180, 50), color, Color.black, e -> addSubject(adminFrame, loginFrame), "Add subject");
		adminFrame.addButton(new Rectangle(105, 300, 180, 50), color, Color.black, e -> expolseStudent(adminFrame, loginFrame), "Student expulsion");
		adminFrame.addButton(new Rectangle(105, 350, 180, 50), color, Color.black, e -> seeRequests(adminFrame, loginFrame, admin), "Requests");
		adminFrame.addButton(new Rectangle(105, 400, 180, 50), color, Color.black, e -> printStudents(adminFrame, loginFrame), "List of Students");
		adminFrame.addButton(new Rectangle(105, 450, 180, 50), color, Color.black, e -> printAdmins(adminFrame, loginFrame), "List of Admins");
		
		adminFrame.setMessageForNeedHelp("Here you need to choose an option");
		
		adminFrame.setVisible(true);
	} 
	
	public static void AddAdmin(Frame adminFrame, Frame loginFrame)
	{
		adminFrame.dispose();
		
		Frame addAdminFrame = new Frame("Add Admin");
      
		addAdminFrame.addLabel(new Rectangle(175, 50, 200, 100), "ADD ADMIN");
        addAdminFrame.addLabel(new Rectangle(50, 175, 150, 100), "Name:");
        addAdminFrame.addLabel(new Rectangle(50, 250, 150, 50), "Password:");
        
        JTextField nameField = addAdminFrame.addField(new Rectangle(175, 205, 150, 30));
        JTextField passwordField = addAdminFrame.addField(new Rectangle(175, 260, 150, 30));
        
        addAdminFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); addAdminFrame.dispose();}, "End Session");
        addAdminFrame.addButton(new Rectangle(0, 100, 107, 50), new Color(216, 210, 225), Color.white, e -> {adminFrame.setVisible(true); addAdminFrame.dispose();}, "<--");
        addAdminFrame.addButton(new Rectangle(130, 350, 130, 50), Color.green, Color.black, e ->{new Admin(nameField.getText(), passwordField.getText()); adminFrame.setVisible(true); addAdminFrame.dispose();}, "Submit");
        
        addAdminFrame.setMessageForNeedHelp("Here you need to add the name and password for the new admin/proffesor");
        
        addAdminFrame.setVisible(true);
	}
	
	public static void AddStudent(Frame adminFrame, Frame loginFrame)
	{
		adminFrame.dispose();
		
		Frame addStudentFrame = new Frame("Add Student");
		
		addStudentFrame.addLabel(new Rectangle(175, 50, 200, 100), "ADD STUDENT");
        addStudentFrame.addLabel(new Rectangle(50, 175, 150, 100), "Name:");
        addStudentFrame.addLabel(new Rectangle(50, 250, 150, 50), "Password:");
		
        JTextField nameField = addStudentFrame.addField(new Rectangle(175, 205, 150, 30));
        JTextField passwordField = addStudentFrame.addField(new Rectangle(175, 255, 150, 30));
        
        addStudentFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); addStudentFrame.dispose();}, "End Session");
        addStudentFrame.addButton(new Rectangle(0, 100, 107, 50),new Color(216, 210, 225), Color.white, e -> {adminFrame.setVisible(true); addStudentFrame.dispose();}, "<--");
        addStudentFrame.addButton(new Rectangle(130, 350, 130, 50), Color.green, Color.black, e ->{new Student(nameField.getText(),
        		passwordField.getText()); 
                adminFrame.setVisible(true); 
                addStudentFrame.dispose();}, "Submit");
        
        addStudentFrame.setMessageForNeedHelp("Here you need to add the name and password for the new student");
        
        addStudentFrame.setVisible(true);
	}
	
	public static void UpdateStudent(Frame adminFrame, Frame loginFrame)
	{
		adminFrame.dispose();
		
		Frame updateStudentFrame = new Frame("Update Student");
		
		updateStudentFrame.addLabel(new Rectangle(175, 50, 300, 100), "UPDATE STUDENT");
        updateStudentFrame.addLabel(new Rectangle(20, 175, 250, 100), "Choose student:");
        updateStudentFrame.addLabel(new Rectangle(20, 225, 250, 100), "Choose subject:");
        updateStudentFrame.addLabel(new Rectangle(20, 300, 250, 100), "Note(if any):");
        updateStudentFrame.addLabel(new Rectangle(20, 350, 250, 100), "Date of absent(if any):");
    	
        JTextField noteField = updateStudentFrame.addField(new Rectangle(260, 332, 50, 30));
    	JTextField absentField = updateStudentFrame.addField(new Rectangle(260, 385, 80, 30));
        
        List<Student> allStudents = Student.getStudents();
        JComboBox<String> comboBoxStudent = new JComboBox<>();
        
        for(Student student : allStudents)
        {
        	comboBoxStudent.addItem(student.getName());
        }
        
        comboBoxStudent.setBounds(210, 210, 130, 30);
        updateStudentFrame.add(comboBoxStudent);
        
        JComboBox<String> comboBoxSubjects = new JComboBox<>();
        List<Subject> allSubjects = Subject.getAllSubjects();
        
        for(Subject subject : allSubjects)
        {
        	comboBoxSubjects.addItem(subject.getName());
        }
        
        comboBoxSubjects.setBounds(210, 260, 130, 30);
        updateStudentFrame.add(comboBoxSubjects);
        
        updateStudentFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); updateStudentFrame.dispose();}, "End Session");
        updateStudentFrame.addButton(new Rectangle(0, 100, 107, 50), new Color(216, 210, 225), Color.white, e -> {adminFrame.setVisible(true); updateStudentFrame.dispose();}, "<--");
        
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.green);
        submitButton.setForeground(Color.black);
        submitButton.setBounds(new Rectangle(130, 450, 130, 50));
    	
    	submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	Subject selectedSubject = Subject.getObject((String) comboBoxSubjects.getSelectedItem());
                Student choosenStudent = Student.getStudent((String) comboBoxStudent.getSelectedItem());
            	String note = noteField.getText();
            	String absent = absentField.getText();
            	
            	if(!note.isEmpty())
                {
                	choosenStudent.addNote(selectedSubject, note);
                }
                
                if(!absent.isEmpty())
                {
                	choosenStudent.addAbsent(selectedSubject, "Absent on date:" + absent);
                }
                
                adminFrame.setVisible(true); 
                updateStudentFrame.dispose();
            }
        });
        
    	
    	updateStudentFrame.add(submitButton);
    	
    	updateStudentFrame.setMessageForNeedHelp("Here you need to choose the student and the subject and update his \n scholar sitaution(note or absence or both)");
    	
        updateStudentFrame.setVisible(true);
	}
	
	public static void addSubject(Frame adminFrame, Frame loginFrame)
	{
		adminFrame.dispose();
		
		Frame addSubjectFrame = new Frame("Add subject");
	    
		addSubjectFrame.addLabel(new Rectangle(175, 50, 300, 100), "ADD SUBJECT");
        addSubjectFrame.addLabel(new Rectangle(20, 175, 250, 100), "Name of subject:");
        addSubjectFrame.addLabel(new Rectangle(20, 225, 250, 100), "Credits:");
        
        JTextField nameField = addSubjectFrame.addField(new Rectangle(200, 205, 150, 30));
        JTextField creditsField = addSubjectFrame.addField(new Rectangle(200, 260, 150, 30));
        
        addSubjectFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); addSubjectFrame.dispose();}, "End Session");
        addSubjectFrame.addButton(new Rectangle(0, 100, 107, 50), new Color(216, 210, 225), Color.white, e -> {adminFrame.setVisible(true); addSubjectFrame.dispose();}, "<--");
        addSubjectFrame.addButton(new Rectangle(130, 350, 130, 50), Color.green, Color.black, e ->{new Subject(nameField.getText(),
        		creditsField.getText()); 
                adminFrame.setVisible(true); 
                addSubjectFrame.dispose();}, "Submit");
        
        addSubjectFrame.setMessageForNeedHelp("Here you need to add the name and credits for the new subject");
        
        addSubjectFrame.setVisible(true);		
	}
	
	public static void expolseStudent(Frame adminFrame, Frame loginFrame)
	{
		adminFrame.dispose();
		
		Frame expolseStudentFrame = new Frame("Expolse Student");
	    
		expolseStudentFrame.addLabel(new Rectangle(150, 50, 300, 100), "EXPOLSE STUDENT");
        expolseStudentFrame.addLabel(new Rectangle(20, 175, 250, 100), "Name:");
        expolseStudentFrame.addLabel(new Rectangle(20, 225, 250, 100), "ID:");
        
        JTextField nameField = expolseStudentFrame.addField(new Rectangle(200, 205, 150, 30));
        JTextField idField = expolseStudentFrame.addField(new Rectangle(200, 260, 150, 30));
        
        expolseStudentFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); expolseStudentFrame.dispose();}, "End Session");
        expolseStudentFrame.addButton(new Rectangle(0, 100, 107, 50), new Color(216, 210, 225), Color.white, e -> {adminFrame.setVisible(true); expolseStudentFrame.dispose();}, "<--");
        expolseStudentFrame.addButton(new Rectangle(130, 350, 130, 50), Color.green, Color.black, e ->{Student.deleteStudent(nameField.getText(), Integer.parseInt(idField.getText())); 
                adminFrame.setVisible(true); 
                expolseStudentFrame.dispose();}, "Submit");
        
        expolseStudentFrame.setMessageForNeedHelp("Here you need to add the name and the id for the student you want to expolse");
        
        expolseStudentFrame.setVisible(true);		
	}
	
	public static void printStudents(Frame adminFrame, Frame loginFrame)
	{
        adminFrame.dispose();
		
		Frame printStudentFrame = new Frame("Expolse Student");
	    
		printStudentFrame.addLabel(new Rectangle(150, 50, 300, 100), "LIST OF STUDENTS");
        
        printStudentFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); printStudentFrame.dispose();}, "End Session");
        printStudentFrame.addButton(new Rectangle(0, 100, 107, 50), new Color(216, 210, 225), Color.white, e -> {adminFrame.setVisible(true); printStudentFrame.dispose();}, "<--");
        
        List<Student> students = Student.getStudents();
        
        String[] columnNames = {"Name", "ID"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        
        for(Student student : students)
        {
        	model.addRow(new Object[]{student.getName(), student.getId()});
        }
        
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setBounds(0, 350, 400, 130);
        printStudentFrame.add(scrollPane);
        
        printStudentFrame.setMessageForNeedHelp("Here is a list of students");
        
        printStudentFrame.setVisible(true);	
	}
	
	public static void printAdmins(Frame adminFrame, Frame loginFrame)
	{
        adminFrame.dispose();
		
		Frame printAdminFrame = new Frame("Expolse Student");
	    
		printAdminFrame.addLabel(new Rectangle(160, 50, 300, 100), "LIST OF ADMINS");
        
        printAdminFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); printAdminFrame.dispose();}, "End Session");
        printAdminFrame.addButton(new Rectangle(0, 100, 107, 50), new Color(216, 210, 225), Color.white, e -> {adminFrame.setVisible(true); printAdminFrame.dispose();}, "<--");
        
        List<Admin> admins = Admin.getAdmins();
        
        String[] columnNames = {"Name", "ID"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        
        for(Admin admin : admins)
        {
        	model.addRow(new Object[]{admin.getName(), admin.getId()});
        }
        
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setBounds(0, 250, 400, 130);
        printAdminFrame.add(scrollPane);
        
        printAdminFrame.setMessageForNeedHelp("Here is a list of students");
        
        printAdminFrame.setVisible(true);	
	}
	
	public static void seeRequests(Frame adminFrame, Frame loginFrame, Admin admin)
	{
		adminFrame.dispose();
		
        Frame seeRequestsFrame = new Frame("Expolse Student");
	    
		seeRequestsFrame.addLabel(new Rectangle(160, 50, 300, 100), "SEE REQUESTS");
        
        seeRequestsFrame.addButton(new Rectangle(0, 50, 107, 50), Color.red, Color.white, e -> {loginFrame.setVisible(true); seeRequestsFrame.dispose();}, "End Session");
        seeRequestsFrame.addButton(new Rectangle(0, 100, 107, 50), new Color(216, 210, 225), Color.white, e -> {adminFrame.setVisible(true); seeRequestsFrame.dispose();}, "<--");
        
        List<String> messages = admin.getMessages();
        
        String[] columnNames = {"Messages"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        
        for (String message : messages) {
            model.addRow(new Object[] {message});
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setBounds(0, 250, 400, 130);
        seeRequestsFrame.add(scrollPane);
        
        seeRequestsFrame.addButton(new Rectangle(130, 450, 130, 50), Color.red, Color.white, e -> {
        	
        	adminFrame.setVisible(true); 
            seeRequestsFrame.dispose();
            admin.deleteMessages();
        }, "Delete");
        
        seeRequestsFrame.setMessageForNeedHelp("Here you see the requests from students");
        
        seeRequestsFrame.setVisible(true);
	}
}
	