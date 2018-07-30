package com.url.shortener.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.url.shortener.config.HelloMessage;
import com.url.shortener.entities.UrlShortener;
import com.url.shortener.entities.UrlShortenerAccessDetail;
import com.url.shortener.repositories.UrlShortenerRepository;
import com.url.shortener.utils.ResponseClass;
import com.url.shortener.utils.StatusCodes;
import com.url.shortener.utils.UrlShortenerGenerator;


@Service
public class UrlShortenerService {

	@Value("${expiry.time.minutes}")
	private int expiryTimeInMinutes;
	@Autowired
	private UrlShortenerRepository urlRepository;
	
	@Autowired 
	private BroadcastService broadcastService;
	
	public ResponseClass getAllUrls(){
		try {
		List<UrlShortener> urls = urlRepository.getUrls();
		return ResponseClass.getResponse("Fetch Successfull", StatusCodes.SUCCESS, true, urls);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ResponseClass.getResponse("Internal Error: While fetching urls data", StatusCodes.INTERAL_ERROR, false, null);
	}

    public ResponseClass saveUrl(String urlData) {
		try{
			
			UrlShortener urlShortener = urlRepository.findByUrl(urlData);
			if(urlShortener != null) {
				return ResponseClass.getResponse("Url already Exists", StatusCodes.BAD_REQUEAT, false, null);
			} else {
				urlShortener = new UrlShortener();
				urlShortener.setUrl(urlData);
				urlShortener.setShortUrl(UrlShortenerGenerator.shortenURL(urlData));
				urlShortener.setExpiryDate(getDate(expiryTimeInMinutes));
			}
			
			return ResponseClass.getResponse("Url Saved Successfully",StatusCodes.SUCCESS, true, urlRepository.save(urlShortener));
		}catch(Exception ex){
			return ResponseClass.getResponse(ex.getMessage(), StatusCodes.INTERAL_ERROR, false, null);
		}
    }
    
    private Date getDate(int minutes) {
    	long ONE_MINUTE_IN_MILLIS=60000;//millisecs

    	Calendar date = Calendar.getInstance();
    	long t= date.getTimeInMillis();
    	Date afterAddingMins=new Date(t + (minutes * ONE_MINUTE_IN_MILLIS));
    	return afterAddingMins;
    }
    
    public ResponseClass addUrlAccess(String shortUrl) {
    	try {
    		UrlShortener urlShortener = urlRepository.findByShortUrl(shortUrl);
    		if(urlShortener == null) {
    			return ResponseClass.getResponse("No Short Url attached to any Url", StatusCodes.NOT_FOUND, false, null);
    		}
    		if(urlShortener.getExpiryDate() != null && new Date().compareTo(urlShortener.getExpiryDate())>0) {
				return ResponseClass.getResponse("Url has been expired", StatusCodes.BAD_REQUEAT, false, null);
			}else {
				UrlShortenerAccessDetail accessDetail = new UrlShortenerAccessDetail();
				accessDetail.setAccessDateTime(new Date());
				accessDetail.setUrlShortener(urlShortener);
				urlShortener.getUrlShortenerAccessDetails().add(accessDetail);
				urlRepository.save(urlShortener);
			}
    		HelloMessage hello = new HelloMessage();
    		hello.setName(urlShortener.getShortUrl());
    		broadcastService.broadcast(hello);
    		return ResponseClass.getResponse("Added Url Access Successfull",StatusCodes.SUCCESS, true, null);
    	} catch(Exception ex) {
    		return ResponseClass.getResponse("Unable to add Url Access",StatusCodes.INTERAL_ERROR, false, null);
    	}
    }
    
    public ResponseClass getShortUrlAccessData(String shortUrl) {
    	try {
    		UrlShortener shortenerUrl = urlRepository.findByShortUrl(shortUrl);
    		return ResponseClass.getResponse("Fetched Successfull", StatusCodes.SUCCESS, true, shortenerUrl);
    	} catch(Exception ex) {
    		return ResponseClass.getResponse("Internal Error Occured",StatusCodes.SUCCESS, false, null);
    	}
    }

	public String getGraphData() {
		List<UrlShortener> urlShorteners = urlRepository.findAll();
		StringBuilder stringBuilder = new StringBuilder("Server,Instance Url Load");
		stringBuilder.append("\n");
		
		for(UrlShortener url : urlShorteners) {
			stringBuilder.append(url.getShortUrl());
			stringBuilder.append(",").append((url.getUrlShortenerAccessDetails() == null)?0:url.getUrlShortenerAccessDetails().size());
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	/*public Boolean markCompleted(String[] ids) {
		try {
			for(String id: ids) {
				UrlShortener todo = urlRepository.findOne(Long.parseLong(id));
				if(todo !=null) {
					todo.setIsCompleted(true);
					urlRepository.save(todo);
				}
			}
		} catch(Exception ex) {
			return false;
		}
		return true;
	}*/

	/*public Boolean deleteTodo(String todoId) {
		
		try {
			urlRepository.delete(Long.parseLong(todoId));
		}catch(Exception ex) {
			return false;
		}
		return true;
	}*/


}
