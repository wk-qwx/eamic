package com.qwx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qwx.bean.PageList;
import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.ActionEntity;
import com.qwx.entity.RoleActionEntity;
import com.qwx.service.ActionService;

/**
 * 权限动作信息服务
 * @author kal02
 *
 */
@Service
public class ActionServiceImpl extends BaseService<ActionEntity> implements ActionService{
	@Resource
	BasePagingAndSortingRepository<ActionEntity, String> actionDao;
	@Resource	
	BasePagingAndSortingRepository<RoleActionEntity, String> roleactionDao;
	public ActionServiceImpl() {
		tableName = "action";
	}
	
	/**
	 *获取权限动作列表
	 */
	public PageList<ActionEntity> getActionList(String page, String limit) {
		String sql = "select * from action";
		return getPageBySql(page,limit,sql);
	}
	/**
	 * 权限动作信息筛选查询分页列表
	 */
	public PageList<ActionEntity> getActionListByFileter(String page, String limit, String whereStr){
		String sql = "";
		if(whereStr.equals("")){
			sql = "select * from action";			
		}else{
			sql = "select * from action where " + whereStr + "";
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
	 * 删除权限动作
	 */
	public String delAction(ActionEntity entity){
		try {
			actionDao.delete(entity);
			String sql = "select * from role_action where actionid = '"+entity.getId()+"'";
			List<RoleActionEntity> rows = getBySql(sql,RoleActionEntity.class);
			roleactionDao.delete(rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return entity.getId();
	}
}
