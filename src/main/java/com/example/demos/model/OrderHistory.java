
package com.example.demos.model;

import java.util.*;

import com.example.demos.dto.OrderHistoryDTO;
import com.example.demos.dto.PlayedDTO;
import com.example.demos.exceptions.NoUserFoundException;
import com.example.demos.repository.AdvertismentOrderRepository;
import com.example.demos.repository.AdvertismentRepository;
import com.example.demos.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;

/**
 * This class is called when there is a need to return a orderhistory to the
 * calle. This class will structure the object so it resemples the JSON format
 * that the callé sent orders with <a
 * href=https://app.lucidchart.com/invitations/accept/8d8dba05-8882-49a7-9768-ae0803b03259
 * > Json format </a>. Thus allowing the calee to see all orders made with that
 * specific username.
 * 
 * @author Netanel Avraham Eklind
 * @version 0.0.1
 */

 @Service
public class OrderHistory {

    @Autowired
    private AdvertismentOrderRepository advRepoOrder;

    @Autowired
    private AdvertismentRepository advRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private PlayedVideo playedVideo;

    /**
     * This methods builds the DTO from the given username by calling the crud
     * repositories and using it to get all the information needed.
     * 
     * @param username contains the unsername to get history from
     * @return a list of DTO's contaning all orders made by user.
     * @throws Exception
     */
    public List<OrderHistoryDTO> getHistory(String username) throws NoUserFoundException {
        List<OrderHistoryDTO> returnList = new ArrayList<>();
            List<Order> list = orderRepo.findByuser(username);
        if(list.size() == 0) throw new NoUserFoundException("No user with name "+username+" found");
        for(Order o: list){
            OrderHistoryDTO oHDTO = new OrderHistoryDTO();
            oHDTO.setOrderId(o.getID());
            oHDTO.setUser(o.getUser());
            oHDTO.setCredits(o.getCredits());
            oHDTO.setAdvertisement_videos(findVideo(o));
            oHDTO.setStartDate(setDate(o,true));
            oHDTO.setEndDate(setDate(o,false));
            returnList.add(oHDTO);
        }

        return returnList;
    }

    private String setDate(Order o,boolean flag) {
        String returnDate = "";
        List<Advertisement_order> list = advRepoOrder.findDistinctByOrders(o.getID());
        for(Advertisement_order ao: list){
        if(flag)
            returnDate = convert(ao.getStart_time_epoch());
        else
            returnDate = convert(ao.getEnd_time_epoch());
        }
        return returnDate;
    }

    private String convert(long time_epoch) {
        long res = time_epoch * 1000;
       return Instant.ofEpochMilli(res).toString();
    }

    private List<PlayedDTO> findVideo(Order o) {
        List<PlayedDTO> returnList = new ArrayList<>();
        List<Advertisement_order> list = advRepoOrder.findByOrders(o.getID());
            for(Advertisement_order ao: list){
                Optional<Advertisement_video> video = advRepo.findById(ao.getVideo());
                Integer count;
                try {
                     count = playedVideo.retriveVideoIsPlaying(video.get().getID());
                } catch (Exception e) {
                    count = 0;
                }
                boolean played = count > 0;
                returnList.add(new PlayedDTO(video,count,played));
            }
        return returnList;
    }



}