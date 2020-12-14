package com.onlineshop.onlineshop.services;

import com.onlineshop.onlineshop.entity.FeedBackEntity;
import com.onlineshop.onlineshop.entity.ProdusEntity;
import com.onlineshop.onlineshop.entity.UserEntity;
import com.onlineshop.onlineshop.repository.FeedBackRepository;
import com.onlineshop.onlineshop.repository.ProdusRepository;
import com.onlineshop.onlineshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedBackRepository feedRepository;
    private final ProdusRepository produsRepository;
    private final UserRepository userRepository;

    public FeedbackService(FeedBackRepository feedRepository, ProdusRepository produsRepository, UserRepository userRepository) {
        this.feedRepository = feedRepository;
        this.produsRepository = produsRepository;
        this.userRepository = userRepository;
    }

    public FeedBackEntity uploadFeedback(FeedBackEntity feedback){
        return feedRepository.save(feedback);
    }

    public List<FeedBackEntity> getFeedbackForProduct(Long idProduct){
        List<FeedBackEntity> list = (List<FeedBackEntity>) feedRepository.getFeedBackEntitiesByProdusEntity_Id(idProduct);
        /*List<FeedBackEntity> out = new ArrayList<>();
        for(FeedBackEntity f:list){
            if(f.getProdusEntity().getId().equals(idProduct))
                out.add(f);
        }*/
        return list;
    }

    public UserEntity getUserFromFeedback(String username){
        return userRepository.getUserEntityByUsername(username);
    }

    public ProdusEntity getProductFromFeedback(Long id){
        return produsRepository.getById(id);
    }
}
