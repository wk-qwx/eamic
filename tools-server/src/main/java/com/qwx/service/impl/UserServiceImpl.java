package com.qwx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qwx.bean.PageList;
import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.RoleUserEntity;
import com.qwx.entity.UserEntity;
import com.qwx.entity.UserViewEntity;
import com.qwx.service.UserService;

/**
 * 用户信息服务
 * @author kal02
 *
 */
@Service
public class UserServiceImpl extends BaseService<UserEntity> implements UserService{
	@Resource
	BasePagingAndSortingRepository<UserEntity, String> userDao;
	@Resource
	BasePagingAndSortingRepository<RoleUserEntity, String> roleuserDao;
	public UserServiceImpl() {
		tableName = "tl_user";
	}
	
	/**
	 *获取用户列表
	 */
	public PageList<UserEntity> getUserList(String page, String limit) {
		String sql = "select * from tl_user order by createtime desc";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 用户信息筛选查询分页列表
	 */
	public PageList<UserEntity> getUserListByFileter(String page, String limit, String whereStr){
		String sql = "";
		if(whereStr.equals("")){
			sql = "select * from tl_user ORDER BY createtime desc";			
		}else{
			sql = "select * from tl_user where " + whereStr + " ORDER BY createtime desc";
		}
		try{
			//返回分页列表
			return getPageBySql(page,limit,sql);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	/**
	 * 删除用户
	 */
	public String delUser(UserEntity entity){
		try {
			userDao.delete(entity);
			String sql = "select * from role_user where userid = '"+entity.getId()+"'";
			List<RoleUserEntity> rows = getBySql(sql,RoleUserEntity.class);
			roleuserDao.delete(rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return entity.getId();
	}
	/**
	 * 用户验证
	 * @param username 用户名
	 * @param pwd 密码
	 * @return 返回数据行和token验证码
	 */
	public List<UserViewEntity> checkUser(String username,String pwd){
		try {
			String sql="select * from user_v where username = '"+username+"' and pwd = '"+pwd+"'";
			List<UserViewEntity> user = getBySql(sql,UserViewEntity.class);	
				
			return user;
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;
	}
}
