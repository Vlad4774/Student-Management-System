package Academic;

import java.util.*;

public class Subject {

	private static List<Subject> allSubjects= new ArrayList<>();
	private String name, credits;
	
	public Subject(String name, String credits)
	{
	    this.name = name;
		this.credits = credits;
	    allSubjects.add(this);
	}
	
	public static List<Subject> getAllSubjects()
	{
		return allSubjects;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public static Subject getObject(String name)
	{
		for(Subject subject : allSubjects)
		{
			if (subject.getName().equals(name))
			{
				return subject;
			}
		}
		
		return null;
	}
}
