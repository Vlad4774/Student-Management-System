package Academic;

import java.util.*;

public class Student {

	private static List<Student> allStudents = new ArrayList<>();
	private String name, password;
	int id;
	static int nextId = 1;
	private Map<Subject, List<String>> subjectsAndNotes = new HashMap<>();
	private Map<Subject, List<String>> subjectsAndAbsences = new HashMap<>();
	
	public Student(String name, String password)
	{
		this.name = name;
		this.password = password;
		this.id = nextId;
		allStudents.add(this);
		nextId++;
	}
	
	public static List<Student> getStudents()
	{
		return allStudents;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	private String getPassword()
	{
		return this.password;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public static Student checkStudent(String name, String password)
	{
		for(Student student : allStudents)
		{
			if(student.getName().equals(name) && student.getPassword().equals(password))
			{
				return student;
			}
		}
		
		
		return null;
	}
	
	public static Student getStudent(String name)
	{
		for(Student student : allStudents)
		{
			if(student.getName().equals(name))
			{
				return student;
			}
		}
		
		return null;
	}
	
	public static void deleteStudent(String name, int id) {
	    Iterator<Student> iterator = allStudents.iterator();

	    while (iterator.hasNext()) {
	        Student student = iterator.next();

	        if (student.getName().equals(name) && student.getId() == id) {
	            iterator.remove();
	        }
	    }
	}

	
    public List<Subject> getSubjects() {
        
        return new ArrayList<>(subjectsAndNotes.keySet());
    }
	
	public void addNote(Subject subject, String note) {
	    subjectsAndNotes.computeIfAbsent(subject, k -> new ArrayList<>()).add(note);
	}

	public void addAbsent(Subject subject, String absent) {
	    subjectsAndAbsences.computeIfAbsent(subject, k -> new ArrayList<>()).add(absent);
	}

	public List<String> getNotes(Subject subject) {
		
	    return subjectsAndNotes.getOrDefault(subject, Collections.emptyList());
	}
	
public List<String> getAbsences(Subject subject) {
		
	    return subjectsAndAbsences.getOrDefault(subject, Collections.emptyList());
	}
}