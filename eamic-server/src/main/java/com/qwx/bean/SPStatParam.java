package com.qwx.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 对K_SAMPLEPOINT表统计的参数(SamplingPoint)
 * 
 * @author zzy
 *
 */
public class SPStatParam implements Serializable {
	private static final long serialVersionUID = 1L;

	// 统计的区间集合
	// 例如：
	// {
	// "0＜CD≤0.2":"0＜\"CD\" AND \"CD\"≤0.2" ,
	// "0.2＜CD≤0.4":"0.2＜\"CD\" AND \"CD\"≤0.4",
	// "0.4＜CD≤1.6":"0.4＜\"CD\" AND \"CD\"≤1.6" 
	// }
	public Map<String, String> groupregion;

	// 统计的字段集合["TOWN","TYPE"]
	public String[] fields;

	// 过滤条件,例如： (\"TYPE\" ="1" AND \"BATCH\"="T1" AND \"YEAR\" =2014)
	public String filter;

	// 统计的值字段集合["CD","CR"]
	public String[] valuefields;

	// 统计聚合函数集合["COUNT","SUM","AVG","MAX","MIN"]
	public String[] funs;
	
	// 排序字段
	public String orderby;
	
	// 统计字段（重金属）
	public String statfield;

	// 分组字段 可为空
	public String groupfield;
	
	// 对象数组， 组内分区集合 ，为空即为查询所有，不为空则分区间段查询
	// 例如：
	// [
	// ["0＜CD≤0.2" , "0＜\"CD\" AND \"CD\"≤0.2" ],
	// ["0.2＜CD≤0.4","0.2＜\"CD\" AND \"CD\"≤0.4" ],
	// ["0.4＜CD≤1.6" ,"0.4＜\"CD\" AND \"CD\"≤1.6" ]
	// ]
	public String[][] groupfilters;
	
	// 二维数组
	public String[][] statcases;
	//治理措施的使用技术及过滤条件集合
	//例如：
    //{ "VIP": "\"ISCD\"=1 AND \"ISOW\"=1 AND \"ISCA\"=1", "土壤调理剂": "\"ISSC\"=1",
    //  "叶面阻控剂": "\"ISRCA\"=1", "有机肥": "\"ISOF\"=1"}
	public Map<String,String> groupdata;

}
