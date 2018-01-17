package com.packt.webstore.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "SHIPPING_DETAIL")
public class ShippingDetail implements Serializable{

	private static final long serialVersionUID = 6350930334140807514L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SHIPPING_DETAIL_ID")
	private Integer shippingDetailId;
	@Column(name="NAME")
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="SHIPPING_DATE")
	private Date shippingDate;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	private Address shippingAddress;
	
	
		public ShippingDetail() {
		this.shippingAddress = new Address();
	}
		
		public Integer getShippingDetailId() {
			return shippingDetailId;
		}


		public void setShippingDetailId(Integer shippingDetailId) {
			this.shippingDetailId = shippingDetailId;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public Date getShippingDate() {
			return shippingDate;
		}


		public void setShippingDate(Date shippingDate) {
			this.shippingDate = shippingDate;
		}


		public Address getShippingAddress() {
			return shippingAddress;
		}


		public void setShippingAddress(Address shippingAddress) {
			this.shippingAddress = shippingAddress;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
}
