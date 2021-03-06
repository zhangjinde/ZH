package com.lakecloud.manage.admin.tools;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lakecloud.foundation.domain.Bargain;
import com.lakecloud.foundation.domain.BargainGoods;
import com.lakecloud.foundation.service.IBargainGoodsService;
import com.lakecloud.foundation.service.IBargainService;
import com.lakecloud.foundation.service.ISysConfigService;
@SuppressWarnings("all")
@Component
public class BargainManageTools {
	@Autowired
	private IBargainGoodsService bargainGoodsService;
	@Autowired
	private IBargainService bargainServicve;
	@Autowired
	private ISysConfigService configService;

	/**
	 * 查询商品折扣率
	 * 
	 * @param bargain_time
	 */
	public BigDecimal query_bargain_rebate(Object bargain_time) {
		Map params = new HashMap();
		params.put("bt", bargain_time);
		List<Bargain> bargain = this.bargainServicve.query(
				"select obj from Bargain obj where obj.bargain_time =:bt",
				params, 0, 1);
		BigDecimal bd = null;
		if (bargain.size() > 0) {
			bd = bargain.get(0).getRebate();
		} else {
			bd = this.configService.getSysConfig().getBargain_rebate();
		}
		return bd;
	}

	/**
	 * 查询特价最多商品数
	 * 
	 * @param bargain_time
	 * @return
	 */
	public int query_bargain_maximum(Object bargain_time) {
		Map params = new HashMap();
		params.put("bt", bargain_time);
		List<Bargain> bargain = this.bargainServicve.query(
				"select obj from Bargain obj where obj.bargain_time =:bt",
				params, 0, 1);
		int bd = 0;
		if (bargain.size() > 0) {
			bd = bargain.get(0).getMaximum();
		} else {
			bd = this.configService.getSysConfig().getBargain_maximum();
		}
		return bd;
	}

	/**
	 * 查询审核通过数
	 * 
	 * @param bargain_time
	 * @return
	 */
	public int query_bargain_audit(Object bargain_time) {
		Map params = new HashMap();
		params.put("bt", bargain_time);
		List<BargainGoods> bargainGoods = this.bargainGoodsService.query(
				"select obj from BargainGoods obj where obj.bg_time =:bt",
				params, -1, -1);
		int bd = 0;
		for (BargainGoods bg : bargainGoods) {
			if (bg.getBg_status() == 1) {
				bd = bd + 1;
			}
		}
		return bd;
	}
	/**
	 * 查询申请数
	 * 
	 * @param bargain_time
	 * @return
	 */
	public int query_bargain_apply(Object bargain_time) {
		Map params = new HashMap();
		params.put("bt", bargain_time);
		List<BargainGoods> bargainGoods = this.bargainGoodsService.query(
				"select obj from BargainGoods obj where obj.bg_time =:bt",
				params, -1, -1);
		return bargainGoods.size();
	}
}
