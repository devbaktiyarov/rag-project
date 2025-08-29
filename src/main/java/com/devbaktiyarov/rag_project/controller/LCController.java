package com.devbaktiyarov.rag_project.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LCController {
    
    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    
    public LCController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder.defaultAdvisors(
            new QuestionAnswerAdvisor(vectorStore)
        ).build();
        this.vectorStore = vectorStore;
    }


    @GetMapping("/lc")
    public String lcQuestion(@RequestParam String q) {
        return chatClient
                .prompt()
                .user(q)
                .call()
                .content();
    }

}
