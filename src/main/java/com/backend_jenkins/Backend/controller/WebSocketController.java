//package com.backend_jenkins.Backend.controller;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class WebSocketController {
//
//    private final SimpMessagingTemplate messagingTemplate;
//
//    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    // Method to programmatically send notifications
//    public void notifyUserDeleted(String email) {
//        messagingTemplate.convertAndSend("/topic/user-deleted", email);
//    }
//
//    @MessageMapping("/bonjour")
//    @SendTo("/topic/bonjour")
//    public String handleBonjourRequest() {
//        return "bonjour";
//    }
//
//}
