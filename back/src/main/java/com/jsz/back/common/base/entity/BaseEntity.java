package com.jsz.back.common.base.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * BaseEntity
 * @Description:
 * @author: Yang 
 * @date:   2017年10月13日
 */
@Entity
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 30;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if (null != page && page > 0) {
			this.page = page;
		}
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		if (null != rows && rows > 0) {
			this.rows = rows;
		}
		
	}
}

