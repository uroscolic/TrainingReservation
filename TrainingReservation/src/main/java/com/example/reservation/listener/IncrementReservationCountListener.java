package com.example.reservation.listener;


import com.example.reservation.dto.IncrementReservationCountDto;
import com.example.reservation.service.UserService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


//@Component
public class IncrementReservationCountListener {

    private MessageHelper messageHelper;
    private UserService userService;

    public IncrementReservationCountListener(MessageHelper messageHelper, UserService userService) {
        this.messageHelper = messageHelper;
        this.userService = userService;
    }

    @JmsListener(destination = "${destination.incrementReservationCount}", concurrency = "5-10")
    public void incrementReservationCount(Message message) throws JMSException {
        IncrementReservationCountDto incrementReservationCountDto = messageHelper.getMessage(message, IncrementReservationCountDto.class);
        System.out.println(incrementReservationCountDto);
        userService.incrementReservationCount(incrementReservationCountDto);
    }

}
