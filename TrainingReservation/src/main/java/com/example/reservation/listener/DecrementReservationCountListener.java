package com.example.reservation.listener;


import com.example.reservation.dto.DecrementReservationCountDto;
import com.example.reservation.dto.IncrementReservationCountDto;
import com.example.reservation.service.UserService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class DecrementReservationCountListener {

    private MessageHelper messageHelper;
    private UserService userService;

    public DecrementReservationCountListener(MessageHelper messageHelper, UserService userService) {
        this.messageHelper = messageHelper;
        this.userService = userService;
    }

    @JmsListener(destination = "${destination.decrementReservationCount}", concurrency = "5-10")
    public void decrementReservationCount(Message message) throws JMSException {
        DecrementReservationCountDto decrementReservationCountDto = messageHelper.getMessage(message, DecrementReservationCountDto.class);
        System.out.println(decrementReservationCountDto);
        userService.decrementReservationCount(decrementReservationCountDto);
    }

}
