package com.qwx.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qwx.bean.HttpResponse;
import com.qwx.bean.HttpResponseList;
import com.qwx.bean.HttpResponsePageList;
import com.qwx.bean.ResponseStatusCode;
import com.qwx.bean.SPStatParam;
import com.qwx.dao.ToolsLibDao;
import com.qwx.database.BasePagingAndSortingRepository;
import com.qwx.database.BaseService;
import com.qwx.entity.BaseEntity;
import com.qwx.util.Ansj;
import com.qwx.util.StatisticUtil;

public class BaseController<T extends BaseEntity> extends BaseService<T> {
	@Resource
	ToolsLibDao dao;
	@Override
	public void getBaseDao() {   
		if (baseDao == null)
			baseDao = (BasePagingAndSortingRepository<T, String>) dao;
	}

	/**
	 * 获取所有的信息
	 */
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public HttpResponseList<T> getAll() {
		try {
			return new HttpResponseList<T>(get());
		} catch (Exception e) {
			e.printStackTrace();
			return new HttpResponseList<T>(ResponseStatusCode.C400);
		}
	}
	

	/**
	 * 通过分页信息获取所有信息
	 * 
	 * @param pageIndex
	 *            页索引
	 * @param pageSize
	 *            页大小
	 * @return
	 */
	@RequestMapping(value = "/getall/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public HttpResponsePageList<T> getAll(@PathVariable("pageIndex") String pageIndex,
			@PathVariable("pageSize") String pageSize) {
		try {
			return new HttpResponsePageList<T>(get(pageIndex, pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			return new HttpResponsePageList<T>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 通过编号获取信息
	 * 
	 * @param id
	 *            编号
	 * @return
	 */
	@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
	public HttpResponse<T> getInfoById(@PathVariable("id") String id) {
		try {
			getBaseDao();
			return new HttpResponse<T>(baseDao.findOne(id));
		} catch (Exception e) {
			e.printStackTrace();
			return new HttpResponse<T>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 通过条件获取信息（GET）
	 * 
	 * @param where
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "/getbyfilter/{where}", method = RequestMethod.GET)
	public HttpResponseList<T> getInfoByFilter(@PathVariable("where") String where) {
		try {
			return new HttpResponseList<T>(getByFilter(where, null));
		} catch (Exception e) {
			return new HttpResponseList<T>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 通过条件获取信息（POST）
	 * 
	 * @param where
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "/getbyfilter", method = RequestMethod.POST)
	public HttpResponseList<T> getInfoByFilterOfPost(@RequestBody String where) {
		try {
			return new HttpResponseList<T>(getByFilter(where, null));
		} catch (Exception e) {
			return new HttpResponseList<T>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 通过查询条件和分页信息获取信息（GET）
	 * 
	 * @param pageIndex
	 *            页索引
	 * @param pageSize
	 *            页大小
	 * @param where
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "/getbyfilter/{pageIndex}/{pageSize}/{where}", method = RequestMethod.GET)
	public HttpResponsePageList<T> getInfoByFilter(@PathVariable("pageIndex") String pageIndex,
			@PathVariable("pageSize") String pageSize, @PathVariable("where") String where) {
		try {
			return new HttpResponsePageList<T>(getPageByFilter(pageIndex, pageSize, where, null));
		} catch (Exception e) {
			return new HttpResponsePageList<T>(ResponseStatusCode.C400);
		}
	}
	
	

	/**
	 * 通过查询条件和分页信息获取信息（POST）
	 * 
	 * @param pageIndex
	 *            页索引
	 * @param pageSize
	 *            页大小
	 * @param where
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "/getbyfilter/{pageIndex}/{pageSize}", method = RequestMethod.POST)
	public HttpResponsePageList<T> getInfoByFilterOfPost(@PathVariable("pageIndex") String pageIndex,
			@PathVariable("pageSize") String pageSize, @RequestBody String where) {
		try {
			return new HttpResponsePageList<T>(getPageByFilter(pageIndex, pageSize, where, null));
		} catch (Exception e) {
			return new HttpResponsePageList<T>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 新增单条数据
	 * 
	 * @param entity
	 *            新增的实体
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public HttpResponse<String> insert(@RequestBody T entity) {
		try {
			return new HttpResponse<String>(add(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 新增多条数据
	 * 
	 * @param entity
	 *            新增的实体集合
	 * @return
	 */
	@RequestMapping(value = "/adds", method = RequestMethod.POST)
	public HttpResponse<String> insert(@RequestBody List<T> entitys) {
		try {
			return new HttpResponse<String>(add(entitys));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 更新单条数据
	 * 
	 * @param entity
	 *            更新的实体
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public HttpResponse<String> updateInfo(@RequestBody T entity) {
		try {
			return new HttpResponse<String>(update(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 更新多条数据
	 * 
	 * @param entity
	 *            多新的实体
	 * @return
	 */
	@RequestMapping(value = "/updates", method = RequestMethod.POST)
	public HttpResponse<String> updateInfo(@RequestBody List<T> entitys) {
		try {
			return new HttpResponse<String>(update(entitys));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 根据编号删除数据
	 * 
	 * @param id
	 *            主键编号
	 * @return
	 */
	@RequestMapping(value = "/deletebyid/{id}", method = RequestMethod.GET)
	public HttpResponse<String> deleteByID(@PathVariable("id") String id) {

		try {
			getBaseDao();
			baseDao.delete(id);
			return new HttpResponse<String>("true");
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 删除数据
	 * 
	 * @param entity
	 *            实体对象
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public HttpResponse<String> deleteInfo(@RequestBody T entity) {
		try {
			return new HttpResponse<String>(delete(entity));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 删除多条数据
	 * 
	 * @param entitys
	 *            实体对象集合
	 * @return
	 */
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public HttpResponse<String> deleteInfo(@RequestBody List<T> entitys) {
		try {
			return new HttpResponse<String>(delete(entitys));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 根据条件删除数据
	 * 
	 * @param condition
	 *            删除条件
	 * @return
	 */
	@RequestMapping(value = "/deletebycondition/{condition}", method = RequestMethod.GET)
	public HttpResponse<String> deleteByCondition(@PathVariable("condition") String condition) {
		try {
			return new HttpResponse<String>(delete(condition));
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 获取所有的列信息
	 */
	@RequestMapping(value = "/getcolumuninf", method = RequestMethod.GET)
	public HttpResponse<String> getColumuInf() {
		try {
			String sql = "select fields_name,fields_type,fields_comment from table_msg('public',"
					+ tableName.replace("\"", "'") + ")";
			@SuppressWarnings("rawtypes")
			List list = getObjectBySql(sql, null);
			String[] fs = { "\"fields_name\"", "\"fields_type\"", "\"fields_comment\"" };
			return new HttpResponse<String>(StatisticUtil.getResult(list, fs));
		} catch (Exception e) {
			e.printStackTrace();
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}

	/**
	 * 分词
	 */
	@RequestMapping(value = "/getansj/{str}", method = RequestMethod.GET)
	public List<String> getansj(@PathVariable("str") String str) {
		return Ansj.getAnsj(str);
	}

	/**
	 * 区间统计
	 * 
	 * @param sp
	 * @return
	 */
	@RequestMapping(value = "/sectionstat", method = RequestMethod.POST)
	public HttpResponse<String> sectionstat(@RequestBody SPStatParam sp) {
		try {
			boolean hasFun = sp.funs != null && sp.funs.length > 0;
			boolean hasValueField = sp.valuefields != null && sp.valuefields.length > 0;
			boolean hasRegion = sp.groupregion != null && !sp.groupregion.isEmpty();
			if (sp.filter == null || sp.filter.isEmpty())
				sp.filter = "1=1";
			String fs = "";
			String orderby = " ORDER BY ";
			if (sp.orderby != null && !sp.orderby.isEmpty()) {
				orderby += " \"" + sp.orderby + "\",";
			}
			if (sp.fields != null && sp.fields.length > 0) {
				for (String f : sp.fields) {
					fs += "\"" + f + "\",";
				}
			}
			String groupby = " GROUP BY ";
			groupby = fs.equals("") ? "" : groupby + fs.substring(0, fs.length() - 1);
			String fs1 = fs;// 记录返回数组字段
			String sql = "";// 查询语句
			String str = "";
			if (hasValueField && hasFun) {
				for (String v : sp.valuefields) {
					for (String f : sp.funs) {
						str += "COALESCE(ROUND(" + f + "(\"" + v + "\"),2),0)  as \"" + v + "_" + f + "\",";
						fs1 += "\"" + v + "_" + f + "\",";
					}
				}
			}
			if (hasRegion) {
				orderby += "\"orderNum\",";
				int orderNum = 1;
				for (Map.Entry<String, String> e : sp.groupregion.entrySet()) {
					sql += " SELECT " + fs + str + "CAST('" + e.getKey() + "' as text) as \"REGION\"," + orderNum
							+ " as \"orderNum\"," + "Count(*) as \"COUNT\" FROM " + tableName + " WHERE " + sp.filter
							+ " AND " + e.getValue() + groupby + " UNION ";
					orderNum++;
				}
				sql = sql.substring(0, sql.length() - 6);
				fs1 += "\"REGION\",\"orderNum\",";
			} else {
				sql = " Select " + fs + str + "Count(*) \"COUNT\" FROM " + tableName + " WHERE " + sp.filter + groupby;
			}
			if (orderby.equals(" ORDER BY ")) {
				orderby = "";
			} else {
				orderby = orderby.substring(0, orderby.length() - 1);
			}
			sql += orderby;
			fs1 += "\"COUNT\"";
			@SuppressWarnings("rawtypes")
			List list = getBySql(sql, null);
			String[] rfs = fs1.split(",");
			String result = StatisticUtil.getResult(list, rfs);
			return new HttpResponse<String>(result);
		} catch (Exception e) {
			return new HttpResponse<String>(ResponseStatusCode.C400);
		}
	}
}
