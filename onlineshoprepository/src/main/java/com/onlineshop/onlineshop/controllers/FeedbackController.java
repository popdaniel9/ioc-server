package com.onlineshop.onlineshop.controllers;

import com.onlineshop.onlineshop.entity.FeedBackEntity;
import com.onlineshop.onlineshop.entity.ProducatorEntity;
import com.onlineshop.onlineshop.entity.ProdusEntity;
import com.onlineshop.onlineshop.entity.UserEntity;
import com.onlineshop.onlineshop.model.FeedbackRequest;
import com.onlineshop.onlineshop.model.FeedbackResponse;
import com.onlineshop.onlineshop.services.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFeedbackForProduct(@PathVariable Long id){
        var list = feedbackService.getFeedbackForProduct(id);
        List<FeedbackResponse> out = new ArrayList<>();
        for(FeedBackEntity f:list){
            out.add(new FeedbackResponse(f.getId(), f.getProdusEntity().getId(), f.getScor(), f.getMesaj(), f.getUserEntity().getUsername()));
        }
        if (out.size() == 0){
            return new ResponseEntity<String>("No feedback found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<FeedbackResponse>>(out, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public FeedbackResponse upload(@RequestBody FeedbackRequest request){
        ProdusEntity produsEntity = feedbackService.getProductFromFeedback(request.getIdProduct());
        UserEntity userEntity = feedbackService.getUserFromFeedback(request.getUsername());
        FeedBackEntity feedBackEntity = new FeedBackEntity();
        feedBackEntity.setMesaj(request.getMessage());
        feedBackEntity.setProdusEntity(produsEntity);
        feedBackEntity.setUserEntity(userEntity);
        feedBackEntity.setScor(request.getScore());
        FeedBackEntity feedback = feedbackService.uploadFeedback(feedBackEntity);
        return new FeedbackResponse(feedback.getId(), feedback.getProdusEntity().getId(), feedback.getScor(), feedback.getMesaj(), feedback.getUserEntity().getUsername());
    }


    @GetMapping
    public ResponseEntity reachEndpoint(){
        return new ResponseEntity("If your are reading this you reached a secure endpoint", HttpStatus.OK);
    }
}


