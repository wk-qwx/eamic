package com.qwx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qwx.bean.PageList;
import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.UserEntity;
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return entity.getId();
	}
}
