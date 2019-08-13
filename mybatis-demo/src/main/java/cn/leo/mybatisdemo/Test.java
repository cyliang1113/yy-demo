package cn.leo.mybatisdemo;

import cn.leo.mybatisdemo.dao.UserDao;
import cn.leo.mybatisdemo.po.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.leo.mybatisdemo.po.UserUser;
import cn.leo.mybatisdemo.service.UserUserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
	private static final Log logger = LogFactory.getLog(Test.class);
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) ac.getBean("userDao");

		for (int i = 0; i < 1; i++) {
			List<User> users = new ArrayList<>();
			for (int j = 0; j < 100; j++) {
				String name = getName();
				int age = getAge();
				String add = getMemo();
				String memo = getMemo();
				User user = new User();
				user.setName(name);
				user.setAddress(add);
				user.setAge(age);
				user.setMemo(memo);
				user.setCreateTime(new Date(System.currentTimeMillis() + j * 1000));
				users.add(user);
			}
			userDao.batchInsert(users);
			users = null;
		}

	}

	public static String getName(){
		long l = System.currentTimeMillis() % 2;
		if (l == 0) {
			return new StringBuilder(getRandomChar()).append(getRandomChar()).toString();
		}
		return new StringBuilder(getRandomChar()).append(getRandomChar()).append(getRandomChar()).toString();
	}

	public static int getAge(){
		return (int)(18 + Math.random() * (55 - 18 + 1));
	}

	public static String getMemo(){
		int leng = (int) (20 + Math.random() * (50 - 20 + 1));
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < leng; i++) {
			stringBuilder.append(getRandomChar());
		}
		return stringBuilder.toString();
	}

	public static String getRandomChar() {
		return String.valueOf((char)(0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))));
	}
}
