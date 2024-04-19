package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FeedDto;
import com.designtartans.pigfarmingserver.exceptions.FarmNotFoundException;
import com.designtartans.pigfarmingserver.model.Feed;
import com.designtartans.pigfarmingserver.repository.FarmRepository;
import com.designtartans.pigfarmingserver.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FeedService implements FeedServiceInterface {
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private FarmRepository farmRepository;

    public BodyResponse createFeed(FeedDto feedDto) throws FarmNotFoundException {

        if (!farmExists(feedDto.getFarmId())) {
            throw new FarmNotFoundException("Farm not found");
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

    @Override
    public BodyResponse getFeedsByFarm(Long farmId) throws FarmNotFoundException {
        if (!farmExists(farmId)) {
            throw new FarmNotFoundException("Farm not found");
        }

        BodyResponse response = new BodyResponse();
        response.setProcessed(true);
        response.setStatusCode(HttpStatus.OK);
        response.setResult(feedRepository.findByFarmId(farmId));
        return response;
    }

    // check if farm exists
    private boolean farmExists(long id) {
        return farmRepository.existsById(id);
    }
}
