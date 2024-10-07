package com.example.bookstore.controller;

import com.example.bookstore.dto.CommentDto;
import com.example.bookstore.entity.Comment;
import com.example.bookstore.entity.Product;
import com.example.bookstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userid}/products/{productid}")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentDto saveComments(@PathVariable(value = "userid") long userid, @PathVariable(value = "productid") long productid, @RequestBody Comment comment){
         commentService.saveComments(userid,productid,comment);
         return new CommentDto(comment.getId(),comment.getDescription());
    }

    @DeleteMapping("/{commentid}")
    public CommentDto deleteComment(@PathVariable long commentid){
        return new CommentDto(commentService.deleteComments(commentid).getId(),commentService.deleteComments(commentid).getDescription());
    }
}
