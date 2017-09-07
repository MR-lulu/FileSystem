import java.io.IOException;
import java.util.Scanner;

import model.User;

import util.UserManage;

public class Start {

	public static void main(String[] args) throws IOException, ClassNotFoundException  {
		// TODO Auto-generated method stub
		System.out.println("---------");
		UserManage um=new UserManage();
		/*User a=new User("aaaa", "1111", 0);
		
		User a1=new User("bbbb", "1111", 0);
		User a2=new User("cccc", "1111", 0);
		User a3=new User("Administrator", "2222", 0);
		User a4=new User("ssw", "2222", 0);
		um.getUsers().add(a);
		um.getUsers().add(a2);
		um.getUsers().add(a1);
		um.getUsers().add(a3);
		um.getUsers().add(a4);
		um.Save();*/
		
		um.read();
		for(int i=0;i<um.getUsers().size();i++)
		{
			System.out.println("username:"+um.getUsers().get(i).getUsername());
			System.out.println("password:"+um.getUsers().get(i).getPassword());
			System.out.println("power:"+um.getUsers().get(i).getPower());
		}
		
		while(true)
		{
			Scanner s = new Scanner(System.in);
			System.out.println("1.登录   2.添加用户   3.删除  4.更改密码");
			int in= s.nextInt();
			switch (in) {
			case 1://登录
				 
				 System.out.println("请输入账号");
				String username=s.next();
				System.out.println("请输入密码");
				String password=s.next();
				um.login(username, password);
				System.out.println("当前账号"+um.getNowUser().getUsername());
				break;
            case 2:
            	System.out.println("请输入账号");
				String username1=s.next();
				System.out.println("请输入密码");
				String password1=s.next();
				um.addUser(username1, password1);
				for(int i=0;i<um.getUsers().size();i++)
				{
					System.out.println("username:"+um.getUsers().get(i).getUsername());
					System.out.println("password:"+um.getUsers().get(i).getPassword());
					System.out.println("power:"+um.getUsers().get(i).getPower());
				}
				break;
            case 3:
            	System.out.println("请输入账号");
				String username2=s.next();
				um.deleteUser(username2);
				for(int i=0;i<um.getUsers().size();i++)
				{
					System.out.println("username:"+um.getUsers().get(i).getUsername());
					System.out.println("password:"+um.getUsers().get(i).getPassword());
					System.out.println("power:"+um.getUsers().get(i).getPower());
				}
            	break;
            case 4:
            	System.out.println("请输入账号");
				String username3=s.next();
				System.out.println("请输入密码");
				String password3=s.next();
				um.changePassword(username3, password3);
				for(int i=0;i<um.getUsers().size();i++)
				{
					System.out.println("username:"+um.getUsers().get(i).getUsername());
					System.out.println("password:"+um.getUsers().get(i).getPassword());
					System.out.println("power:"+um.getUsers().get(i).getPower());
				}
				break;
				case 5:
					break;
			}
		}
		
	}
}
