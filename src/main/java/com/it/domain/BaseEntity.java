package com.it.domain;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{


	private static final long serialVersionUID = 8882549236617919685L;
	
	protected Long id;
	protected Date updateDate;
	protected Date createDate;
	


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

	
	@PrePersist
	public void onPersist() {
		DateTime nowDt = new DateTime(DateTimeZone.UTC);
		Date current = new Date(nowDt.getMillis());
		setCreateDate(current);
		setUpdateDate(current);
	}
	
	@PreUpdate
	public void onUpdate() {
		setUpdateDate(new Date(new DateTime(DateTimeZone.UTC).getMillis()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", updateDate=" + updateDate + ", createDate=" + createDate + "]";
	}
	

}
