package com.example.insurancequoute;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class Init {

    @EventListener(ApplicationReadyEvent.class)
    void init(){


    }

}
