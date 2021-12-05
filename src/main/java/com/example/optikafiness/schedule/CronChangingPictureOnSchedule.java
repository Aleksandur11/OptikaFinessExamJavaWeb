package com.example.optikafiness.schedule;

import com.example.optikafiness.model.entity.GlassesOfferEntity;
import com.example.optikafiness.repository.GlassesOfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CronChangingPictureOnSchedule {

    private GlassesOfferRepository glassesOfferRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CronChangingPictureOnSchedule.class);
    public CronChangingPictureOnSchedule(GlassesOfferRepository glassesOfferRepository) {
        this.glassesOfferRepository = glassesOfferRepository;
    }
@Scheduled(cron="* * * */43200 * *")
    public void changePicture(){
        GlassesOfferEntity newEntity=glassesOfferRepository.findById(1L).orElse(null);
        if(newEntity.getImageUrl().equals("/images/image/cool-sunglasses-isolated-white-background-top-view.jpg")) {
            newEntity.setImageUrl("/images/image/sunglasses.jpg");
        }else {
            newEntity.setImageUrl("/images/image/cool-sunglasses-isolated-white-background-top-view.jpg");
        }
        glassesOfferRepository.save(newEntity);

        LOGGER.info("Updated object",newEntity);

}
}
