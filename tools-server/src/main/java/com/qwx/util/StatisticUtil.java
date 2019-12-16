package com.qwx.util;

import java.util.List;

@SuppressWarnings("rawtypes")
public class StatisticUtil {
	/**
	 * 
	 * @param list
	 *            查询出来的集合
	 * @param fs
	 *            字段名数组
	 * @return String[{},{}...] json字符串
	 */
	public static String getResult(List list, String[] fs) {
		String result = "[";
		if (fs.length == 1) {
			int length = list.size();
			for (int i = 0; i < length; i++) {
				Object o = list.get(i);
				if (o == null)
					result += "{" + fs[0] + ":\"\"}";
				else if (o.getClass().getSimpleName().equals("String")) {
					result += "{" + fs[0] + ":\"" + o + "\"}";
				} else
					result += "{" + fs[0] + ":" + o + "}";
				if (i < length - 1)
					result += ",";
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				result += "{";
				Object[] ob = (Object[]) list.get(i);
				for (int j = 0; j < ob.length; j++) {
					if (ob[j] == null) {
						result += fs[j] + ":\"\"";
					} else if (ob[j].getClass().getSimpleName().equals("String")
							|| ob[j].getClass().getSimpleName().equals("Timestamp")) {
						result += fs[j] + ":\"" + ob[j] + "\"";
					} else
						result += fs[j] + ":" + ob[j];
					if (j < ob.length - 1)
						result += ",";
				}
				result += "}";
				if (i < list.size() - 1)
					result += ",";
			}
		}
		result += "]";
		return result;
	}
}