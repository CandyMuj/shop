package cn.itcast.shop.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import cn.itcast.shop.utils.SHAUtil;

/**
 * 用户的Action
 * @author 13054
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	// Struts2中模型驱动使用的类
	/*
	 * 实现modeldriven的作用 接收数据，并且后台修改时 ，后台都是增删改，后台都是查到对象，直接给你传过来，你就用模型接收，这样很方便
	 * 所以如果不接受页面的请求数据，并且后台也不更改这个对象的响应数据就不需要实现这个，因为后台也是发送请求数据过来，只不过都是封装好的数据，你就需要接收
	 */
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	// 注入userService
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//获取请求中的验证码 接收验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	


	/**
	 *  编写一个挑转到注册页面的方法:
	 * @return
	 */
	public String registPage(){
		
		return "registPageSuccess";
	}
	
	/**
	 * 前台:注册用户的方法:
	 * @throws Exception 
	 */
	@InputConfig(resultName="registInput")
	/*
	 * 具体
	 */
	public String regist() throws Exception{//针对方法的校验 会在执行这个方法之前先校验 也就是执行一大堆拦截器才会执行目标方法 其中一个就是校验的 有误就会跳转到input视图
		//获取session中存入的验证码
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (checkcode == null || !checkcode.equalsIgnoreCase(checkcode1)) {//不区分大小写的比较 验证码不正确就是说比较返回的是false就说明验证码错误 但是if中药true才会执行所以要加个非
			this.addActionError("验证码错误");
			return "registInput"; //如果有误直接返回结果 一个函数只要返回了结果后面的就不会执行了
		}
		//对密码进行sha加密
		String password = user.getPassword();
		//加密算法
		password = SHAUtil.shaEncode(password);
		user.setPassword(password);
		userService.regist(user);
		this.addActionMessage("注册成功!请去邮箱激活!");
		return "registSuccess";
	}
	
	/**
	 * 前台:激活用户的方法:
	 */
	public String active(){
		// 根据激活码查询用户
		User existUser = userService.findByCode(user.getCode());
		/*
		 * 因为激活的链接后面带的有属性，这个是封装在request请求中的，而请求中的 数据都会被Struts自动封装到bean中
		 * 只不过封装的只有code 只要不保存是不会有错的，如果数据表运行为空，那也没问题，没有值的都是空，当然没人会报错这个数据
		 * 是用来根据这个查询一个用户，得到完整的数据，所以请求都是封装了数据的，都是被封装到了bean中
		 */
		if(existUser != null){
			// 根据激活码查询到这个用户.
			existUser.setState(1);
			// 修改用户的状态
			userService.update(existUser);
			// 添加信息:
			this.addActionMessage("激活成功!请去登录!");
			return "activeSuccess";
		}else{
			this.addActionMessage("激活失败!激活码有误!");
			return "activeSuccess";
		}
	}
	
	/**
	 * 前台:跳转到登录页面的方法
	 */
	public String loginPage(){
		return "loginPageSuccess";
	}
	
	/**
	 * 前台:登录功能:
	 * @throws Exception 
	 */
	@InputConfig(resultName="loginInput")
	public String login() throws Exception{
		//获取session中存入的验证码
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (checkcode == null || !checkcode.equalsIgnoreCase(checkcode1)) {//不区分大小写的比较 验证码不正确就是说比较返回的是false就说明验证码错误 但是if中药true才会执行所以要加个非
			this.addActionError("验证码错误");
			return "loginInput"; //如果有误直接返回结果 一个函数只要返回了结果后面的就不会执行了
		}
		//根据用户名查数据库，查询此用户是否存在
		User is_user = userService.findByUserName(user.getUsername());
		if (is_user == null) {
			this.addActionError("用户不存在");
			return "loginInput";
		}
		//对用户输入的密码进行md5加密 数据库中也是加密后存入的，那么我这里加密一下，根据加密后的密码和用户名查数据库
		String password = user.getPassword();
		//加密算法
		password = SHAUtil.shaEncode(password);
		user.setPassword(password);
		User existUser = userService.login(user);
		if(existUser == null){
			// 登录失败
			this.addActionError("用户名或密码错误");
			return "loginInput";
		}else if (existUser.getState() == 0) {
			this.addActionError("用户未激活 请先前去激活");
			return "loginInput";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}

	
	/**
	 * 前台:注册AJAX校验用户名.
	 * @throws IOException 
	 */
	public String checkUserName() throws IOException{
		/*
		 * 接收ajax传过来的用户名 因为传他是在请求中的 由ajax访问这个action 就会把数据请求带过来 只要字段和这里面的模型驱动中的字段相同 就会自动把请求中的参数封装进来 接收数据
		 */
		User existUser = userService.findByUserName(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existUser == null){
			// 用户名可以使用的
			response.getWriter().print("<font color='green'>用户名可以使用</font>");
		}else{
			// 用户名已经存在
			response.getWriter().print("<font color='red'>用户名已经存在</font>");
		}
		return NONE;
	}
	
	/**
	 * 前台：退出登录模块
	 * @return 
	 */
	public String exit() {
		//清除session完成退出 因为index页面如果检查到有session就会显示登录的标签 否则就会显示登录注册 只要没有用户的那个session就好了
		ServletActionContext.getRequest().getSession().invalidate();
		//用户用户退出后 这个用户在服务器的所有的session都不需要存在了 清除所有的session 只清除用户的session会导致其他的缓存在服务器这就是垃圾 会不好 全部清除就好些
		return "exitSuccess";
	}
}
