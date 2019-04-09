package com.db2md.bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 字段类
 * 
 *
 * <p>
 *
 * @author cs12110 2018年11月13日
 * @since 1.0
 */
@Data
public class FieldBean {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 长度
	 */
	private String length;

	/**
	 * 是否为空
	 */
	private String nullable;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 主键
	 */
	private String isKey;

	/**
	 * 自动增长
	 */
	private String autoIncre;

	/**
	 * 默认值
	 */
	private String defValue;



	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
