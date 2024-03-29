package com.stylefeng.guns.sharecore.common.persistence.model;

/**
 * 
 * <P>
 * 小程序信息模板类 *号为必须字段
 * </P>
 * 
 * @author Alan.huang
 */
public class WxAppTemplate extends WxAppBaseResult {

	private String touser; // *接收者（用户）的 openid

	private String template_id; // *所需下发的模板消息的id

	private String page; // 点击模板卡片后的跳转页面，仅限本小程序内的页面

	private String form_id; // *表单提交场景下，为 submit 事件带上的 formId；

	private Object data; // *模板内容，不填则下发空模板

	private String color; // 模板内容字体的颜色，不填默认黑色

	private String emphasis_keyword; // 模板需要放大的关键词，不填则默认无放大

	private String prepay_id; // 支付场景下，为本次支付的 prepay_id

	@Override
	public String toString() {
		return "WxAppTemplate [touser=" + touser + ", template_id=" + template_id + ", page=" + page + ", form_id="
				+ form_id + ", data=" + data + ", color=" + color + ", emphasis_keyword=" + emphasis_keyword + "]";
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}

	public String getEmphasis_keyword() {
		return emphasis_keyword;
	}

	public void setEmphasis_keyword(String emphasis_keyword) {
		this.emphasis_keyword = emphasis_keyword;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
}
