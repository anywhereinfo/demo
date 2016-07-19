package com.example;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface ChannelMetadata {

    @Input("cars")
    SubscribableChannel readCars();

    @Output("cars")
    MessageChannel postCars();

}

