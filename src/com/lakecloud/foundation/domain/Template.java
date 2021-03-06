package com.lakecloud.foundation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.lakecloud.core.constant.Globals;
import com.lakecloud.core.domain.IdEntity;

/**
 * @info 系统模板类，包括站内短信模板、邮件模板，手机短信模板,通过velocity模板合成数据
 * @since V1.0
 * @author 江苏太湖云计算信息技术股份有限公司 www.chinacloud.net erikchang
 * 
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = Globals.DEFAULT_TABLE_SUFFIX + "template")
public class Template extends IdEntity {
	private String info;// 模板描述
	private String type;// msg:站内短信模板，email:邮件模板，sms:手机短信模板
	private String title;// 模板标题，使用velocity标签管理
	/**
	 * @info 数据库移植兼容性修改
	 * @author chenyong
	 * @see
	 * 	修改@Column(columnDefinition = "LongText")
	 */
	@Lob
	@Column(length = 10485760)
	private String content;// 模板内容，使用${}velocity模板标签
	private String mark;// 模板代码，根据模板代码获取对应的模板
	private boolean open;// 是否开启，开启后方可使用

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
