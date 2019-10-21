package com.qwx.util;

import org.springframework.stereotype.Component;
  
  
/** 
 *  
 * @author KAL
 * 字典查询工具类
 */  
@Component
public class DictionaryUtil{  
  
    /*@Autowired(required =false)//(自动注入redisTemplet)
    private BaseService<DictionaryEntity> bs;  
    
    //=============================common============================  
    
    public String getDisplay(String name,String code){
    	String sql = "select display from Dictionary";
    	List<DictionaryEntity> list = bs.getBySql(sql);
    	return null;
    }
    
    
    
	public void test(){
		String sql = "select * from dictionary where name = '缺陷类型'";
		List<DictionaryEntity> list = getBySql(sql);
		for(int i = 0; i<list.size(); i++){
			String code = list.get(i).getCode();
			String name = list.get(i).getDisplay();
			test2(code,name);
		}
	}
	public void test2(String code,String name){
		String sql = "select defectlevel from ea_deduct_rule where defecttype = '"+code+"' group by defectlevel";
		List<String> list = getFieldBySql(sql);
		for(int i = 0; i<list.size(); i++){
			int inx = 1;
			String acode = list.get(i);
			String adispaly="";			
			String sql2 = "select * from dictionary where name ='缺陷程度' and level = '1' and code = '"+acode+"'";
			List<DictionaryEntity> list2 = getBySql(sql2);
			for(int j = 0; j<list2.size(); j++){
				adispaly = list2.get(j).getDisplay();
				DictionaryEntity dic = new DictionaryEntity();
				dic.setCode(String.valueOf(inx));
				dic.setDisplay(adispaly);
				dic.setId(UUID.randomUUID().toString());
				dic.setName(name);
				dic.setLevel("2");
				dic.setOrder(String.valueOf(inx));
				dic.setPid(acode);
				add(dic);
				inx++;
			}
		}
	}
      */
}