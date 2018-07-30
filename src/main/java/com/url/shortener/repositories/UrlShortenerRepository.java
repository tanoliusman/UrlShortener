package com.url.shortener.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.url.shortener.entities.UrlShortener;


@Repository
public interface UrlShortenerRepository extends JpaRepository<UrlShortener, Long>{

	@Query("select new UrlShortener(id,url,shortUrl,expiryDate) from UrlShortener")
	List<UrlShortener> getUrls();

	UrlShortener findByShortUrl(String shortUrl);

	UrlShortener findByUrl(String urlData);

}
