package com.example.shopapplication.Web;

import com.example.shopapplication.Payload.Request.ChatRequest;
import com.example.shopapplication.Payload.Request.ChatUpdate;
import com.example.shopapplication.Payload.Request.ReplyPostRequest;
import com.example.shopapplication.Services.ChatService;
import com.example.shopapplication.Services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/mobilePhone/chat")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    MapValidationErrorService mapValidationErrorService;

    @Autowired
    ChatService chatService;

    @PostMapping("/post")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> chatPost (@Valid @RequestBody  ChatRequest chatRequest, BindingResult bindingResult) {

        ResponseEntity<?> error = mapValidationErrorService.mapValidationService(bindingResult);

        if (error != null)
            return error;

        return chatService.postMessage(chatRequest);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updatePost (@Valid @RequestBody ChatUpdate chatUpdate, BindingResult bindingResult) {

        ResponseEntity<?> error = mapValidationErrorService.mapValidationService(bindingResult);

        if (error != null)
            return error;


        return chatService.updateMessage(chatUpdate);
    }

    @GetMapping("/{phoneId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> chatGet(@PathVariable String phoneId) {
        return chatService.getChatList(phoneId);
    }


    //  post reply message
    @PostMapping("/reply")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> postReply (@Valid @RequestBody ReplyPostRequest replyPostRequest, BindingResult bindingResult) {
        ResponseEntity<?> error = mapValidationErrorService.mapValidationService(bindingResult);

        if (error != null)
            return error;

        return chatService.postReplyMessage(replyPostRequest);
    }

    //  get reply using message id
    @GetMapping("/reply/{messageId}")
    @PreAuthorize("hasRole('USER')")
    public  ResponseEntity<?> getReplies (@PathVariable String messageId) {
        System.out.println("hello from getReplies");
        return chatService.getReplies(messageId);
    }
}
