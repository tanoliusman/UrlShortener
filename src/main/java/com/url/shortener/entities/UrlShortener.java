package com.url.shortener.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "url_shortener")
public class UrlShortener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String url;
    
    private String shortUrl;
    
    private Date expiryDate;
    
    public UrlShortener(Long id, String url, String shortUrl,Date expiryDate) {
    	this.id = id;
    	this.url = url;
    	this.shortUrl = shortUrl;
    	this.expiryDate = expiryDate;
    }
    
    @OneToMany(mappedBy = "urlShortener", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UrlShortenerAccessDetail> urlShortenerAccessDetails;
    
    

    public UrlShortener() {
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getShortUrl() {
		return shortUrl;
	}


	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


	public List<UrlShortenerAccessDetail> getUrlShortenerAccessDetails() {
		return urlShortenerAccessDetails;
	}


	public void setUrlShortenerAccessDetails(List<UrlShortenerAccessDetail> urlShortenerAccessDetails) {
		this.urlShortenerAccessDetails = urlShortenerAccessDetails;
	}
    
}
