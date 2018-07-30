package com.url.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortener.services.UrlShortenerService;
import com.url.shortener.utils.ResponseClass;

@RestController
@RequestMapping("/url")
public class UrlShortenerRestController {

    @Autowired
    private UrlShortenerService urlService;


    @GetMapping
    public ResponseClass getAllUrls(){
        return urlService.getAllUrls();
    }

    @PostMapping("/shortener")
    public ResponseClass generateShortUrl(@RequestBody String url){
        return urlService.saveUrl(url);
    }
    
    @PostMapping("/access")
    public ResponseClass accessUrl(@RequestBody String shortUrl) {
    	return urlService.addUrlAccess(shortUrl);
    }
    
    
    @PostMapping("/get/access")
    public ResponseClass getShortUrlAccessData(@RequestBody String shortUrl) {
    	return urlService.getShortUrlAccessData(shortUrl);
    }
    
    @GetMapping("/get/graph/data")
    public String getGraphData() {
    	return urlService.getGraphData();
    }
    

    /*@PutMapping
    public Boolean markCompleted(@RequestBody String[] ids) {
    	return urlService.markCompleted(ids);
    }
    
    @DeleteMapping
    public Boolean deleteTodo(@RequestBody String todoId){
       return urlService.deleteTodo(todoId);
    	
    }*/

}
