package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import model.User;


public class UserManage implements Serializable {
	transient private User nowUser=new User();
	private	ArrayList<User> users=new ArrayList<User>();
private static UserManage instance=new UserManage();//单例对象
	private UserManage()
	{
		try {
			read();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static UserManage getInstance()
	{
		return instance;
	}
	public void read() throws IOException, ClassNotFoundException
	{
		File file = new File("data/user.txt");
		FileInputStream fis = new FileInputStream(file);  
		   ObjectInputStream ois = new ObjectInputStream(fis);   
		   this.users= (ArrayList<User>) ois.readObject();  
		   ois.close();  
		   fis.close(); 
		   
	}

	public void Save() throws IOException{
		File file = new File("data/user.txt");
		   //Student对象序列化过程  
		   FileOutputStream fos = new FileOutputStream(file);  
		   ObjectOutputStream oos = new ObjectOutputStream(fos);  
		   oos.writeObject(this.users);  
		   oos.flush();  
		   oos.close();  
		   fos.close();  

	}

	public boolean login(String username,String password)
	{
		 //Iterator<user> it = this.users.iterator();
		 for(int i=0;i<this.users.size();i++){
			System.out.println(this.users.get(i).getUsername());
			if(this.users.get(i).getUsername().equals(username)&& this.users.get(i).getPassword().equals(password))
			{
				this.nowUser.setUsername(username);
				this.nowUser.setPassword(password);
				this.nowUser.setPower(this.users.get(i).getPower());
				return true;
			}
			
		}	
		 return false;
	}

	public void logout()
	{
		this.nowUser.setUsername(null);
		this.nowUser.setPassword(null);
		this.nowUser.setPower(0);
	}

	public int addUser(String username,String password) throws IOException//0成功，1用户名重复，2密码不够6位，3权限不足
	{
		
		if(this.nowUser.getUsername().equals("Administrator"))
		{
			
			if(username.length()<6)
			{
				return 2;//密码不够6位
			}
			for(int i=0;i<this.users.size();i++){
				if(this.users.get(i).getUsername().equals(username))
				{
					
					return 1;//用户名重复
				}
			 }
			 User newUser=new User(username,password,0);
			 this.getUsers().add(newUser);
			 Save();
			 return 0;//成功
		}
		return 3;//权限不足
	}

	public boolean deleteUser(String username) throws IOException
	{
		if(this.nowUser.getUsername().equals("Administrator"))
		{
			for(int i=0;i<this.users.size();i++){
				if(this.users.get(i).getUsername().equals(username))
				{
					
					this.getUsers().remove(i);
					Save();
					return true;
				}
			 }
			 
			 return false;
		}
		return false;
	}

	public int changePassword(String username,String password) throws IOException//0成功，1未找到用户，2密码不够6位，3权限不足
	{
		if(this.nowUser.getUsername().equals("Administrator"))
		{
			if(username.length()<6)
			{
				return 2;//密码不够6位
			}
			for(int i=0;i<this.users.size();i++){
				if(this.users.get(i).getUsername().equals(username))
				{
					this.getUsers().remove(i);
					User newUser=new User(username,password,0);
					 this.users.add(newUser);
					 Save();
					 return 0;//0成功
				}
			 }
			 
			 return 1;//1未找到用户
		}
		return 3;//3权限不足
	}


	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public User getNowUser() {
		return nowUser;
	}

	public void setNowUser(User nowUser) {
		this.nowUser = nowUser;
	}
}
