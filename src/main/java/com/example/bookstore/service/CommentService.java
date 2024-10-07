package com.example.bookstore.service;


import com.example.bookstore.entity.Comment;

public interface CommentService{
    Comment saveComments(long userid,long productid,Comment comment);

    Comment deleteComments(long id);
}
