package com.qwx.util;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.BaseAnalysis;

public class Ansj {
	// 过滤词数组
	private static String[] ansjDictionary = { "有限公司", "集团","湖南省","湖南","株洲市","株洲" };

	/**
	 * 
	 * @param str
	 * @return 分好的词集合
	 */
	public static List<String> getAnsj(String str) {
		for (int i = 0; i < ansjDictionary.length; i++) {
			if (str.indexOf(ansjDictionary[i]) > -1) {
				str = str.replace(ansjDictionary[i], "");
			}
		}
		Result result = BaseAnalysis.parse(str); // 分词结果的一个封装，主要是一个List<Term>的terms
		List<Term> terms = result.getTerms(); // 拿到terms
		List<String> words = new ArrayList<String>();
		for (int i = 0; i < terms.size(); i++) {
			if (!words.contains(terms.get(i).getName())){
				words.add(terms.get(i).getName()); // 拿到词
			}
		}
		words.remove(" ");
		return words;
	}
}
