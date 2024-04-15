package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FeedDto;
import com.designtartans.pigfarmingserver.model.Feed;
import com.designtartans.pigfarmingserver.repository.FarmRepository;
import com.designtartans.pigfarmingserver.repository.FeedRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FeedService implements  FeedServiceInterface{

    private FeedRepository feedRepository;
    private FarmRepository farmRepository;

    public BodyResponse createFeed(FeedDto feedDto) {

        if(!farmExists(feedDto.getFarmId())) {
            BodyResponse response = new BodyResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setProcessed(false);
            response.setResult("Farm not found");
            return response;
        }

        Feed feed = new Feed();
        feed.setType(feedDto.getType());
        feed.setWeight(feedDto.getWeight());
        if (feedDto.getPricePerKg() != null) {
            feed.setPricePerKg(feedDto.getPricePerKg());
            feed.setTotalPrice(feedDto.getPricePerKg() * feedDto.getWeight());
        } else {
            feed.setTotalPrice(feedDto.getTotalPrice());
        }
        feed.setFarm(farmRepository.findById(feedDto.getFarmId()).get());
        feedRepository.save(feed);

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.CREATED);
        response.setProcessed(true);
        response.setResult(feed);
        return response;

    }


    //check if farm exists
    private boolean farmExists(long id) {
        return farmRepository.existsById(id);
    }
}
