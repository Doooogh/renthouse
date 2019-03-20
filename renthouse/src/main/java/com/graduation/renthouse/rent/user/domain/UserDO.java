package com.graduation.renthouse.rent.user.domain;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-11 15:01:29
 */
public class UserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer userId;
	//用户昵称
	private String name;
	//用户名
	private String username;
	//密码
	private String password;
	//手机号
	private String phone;
	//性别
	private String sex;
	//年龄
	private Integer age;
	//状态
	private Integer status;

	//角色
	private List<Integer> roleIds;


	public UserDO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDO{" +
				"userId=" + userId +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				", sex='" + sex + '\'' +
				", age=" + age +
				", status=" + status +
				", roleIds=" + roleIds +
				", createtime=" + createtime +
				", updatetime=" + updatetime +
				'}';
	}

	//创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
	private Date createtime;

	//修改时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
	private Date updatetime;


	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * 设置：id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：用户昵称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：用户昵称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
}
