package com.url.shortener.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name ="url_shortener_access_detail")
public class UrlShortenerAccessDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date accessDateTime;

	public Date getAccessDateTime() {
		return accessDateTime;
	}

	public void setAccessDateTime(Date accessDateTime) {
		this.accessDateTime = accessDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "url_shortener_id")
	private UrlShortener urlShortener;

	public UrlShortener getUrlShortener() {
		return urlShortener;
	}

	public void setUrlShortener(UrlShortener urlShortener) {
		this.urlShortener = urlShortener;
	}
	
	
	
	
}
