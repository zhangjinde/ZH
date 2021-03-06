package com.lakecloud.foundation.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.lakecloud.core.constant.Globals;
import com.lakecloud.core.domain.IdEntity;

/**
 * @info 用户商品分类,用在卖家店铺内部使用
 * @since V1.0
 * @author 江苏太湖云计算信息技术股份有限公司 www.chinacloud.net erikchang
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = Globals.DEFAULT_TABLE_SUFFIX + "usergoodsclass")
public class UserGoodsClass extends IdEntity {
	private String className;//
	private int sequence;
	private boolean display;
	private int level;
	@ManyToOne(fetch = FetchType.LAZY)
	private UserGoodsClass parent;
	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	private List<UserGoodsClass> childs = new ArrayList<UserGoodsClass>();
	@ManyToMany(mappedBy = "goods_ugcs")
	private List<Goods> goods_list = new ArrayList<Goods>();// 店铺中商品所在分类
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public List<Goods> getGoods_list() {
		return goods_list;
	}

	public void setGoods_list(List<Goods> goods_list) {
		this.goods_list = goods_list;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public UserGoodsClass getParent() {
		return parent;
	}

	public void setParent(UserGoodsClass parent) {
		this.parent = parent;
	}

	public List<UserGoodsClass> getChilds() {
		return childs;
	}

	public void setChilds(List<UserGoodsClass> childs) {
		this.childs = childs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
