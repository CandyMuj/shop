package cn.itcast.shop.user;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.product.Product;
import cn.itcast.shop.utils.MailUtils;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.UUIDUtils;

/**
 * 用户模块:业务层代码
 * 
 *
 */
@Transactional
public class UserService {
	// 注入Dao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 业务层注册用户的代码
	 * @param user
	 */
	public void regist(User user) {
		// 保存用户:
		user.setState(0);// 0 未激活  1已经激活.
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();//生成激活码
		user.setCode(code);
		userDao.save(user);
		// 发送邮件:
		try {
			MailUtils.sendMail(user.getEmail(), code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 业务层根据激活码查询用户 的方法
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	// 业务层修改用户的方法
	public void update(User existUser) {
		userDao.update(existUser);
	}

	// 业务层登录的方法
	public User login(User user) {
		return userDao.login(user);
	}

	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}
	//验证码

	

	public PageBean<User> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<User> pageBean= new PageBean<User>();
		Integer limit=10;
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		//查询总页数
		Integer totalCount=0;
		totalCount=userDao.findAll();
		pageBean.setTotalCount(totalCount);
		Integer totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<User> list=userDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}


	
}
