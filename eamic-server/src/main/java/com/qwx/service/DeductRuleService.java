package com.qwx.service;

import java.util.List;

import com.qwx.entity.DeductRuleEntity;

/**
 * 扣分导则信息接口
 * @author kal02
 *
 */
public interface DeductRuleService {

	public List<DeductRuleEntity> Guideline(String jsonstr);
}
