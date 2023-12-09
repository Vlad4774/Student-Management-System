package Academic;

import java.util.*;

public class Admin {
	
	private static List<Admin> allAdmins = new ArrayList<>();
	private static int nextId = 1;
	String name, password;
	List<String> messages;
	int id;
	
	public Admin(String name, String password)
	{
		this.name = name;
		this.password = password;
		this.id = nextId;
		this.messages = new ArrayList<>();
		nextId++;
		allAdmins.add(this);
	}
	
	public static Admin checkUser(String name, String password)
	{
		for(Admin admin : allAdmins)
		{
			if(admin.getName().equals(name) && admin.getPassword().equals(password))
			{
				return admin;
			}
		}
		
		return null;
	}
	
	public static Admin getObject(String name)
	{
		for(Admin admin : allAdmins)
		{
			if(admin.getName().equals(name))
			{
				return admin;
			}
		}
		
		return null;
	}
	
	public void receiveMessage(String message)
	{
		this.messages.add(message);
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
	
	public static List<Admin> getAdmins()
	{
		return allAdmins;
	}
	
	public List<String> getMessages() {
        return messages;
    }
	
	public void deleteMessages()
	{
		this.messages = new ArrayList<>();
	}
}
