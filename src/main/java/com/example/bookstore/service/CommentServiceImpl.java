package com.example.bookstore.service;

import com.example.bookstore.entity.Comment;
import com.example.bookstore.entity.Product;
import com.example.bookstore.entity.User;
import com.example.bookstore.entity.UserProductsComments;
import com.example.bookstore.exceptions.BookStoreException;
import com.example.bookstore.repository.CommentRepository;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private ProductService productService;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,ProductService productService) {
        this.commentRepository = commentRepository;
        this.productService = productService;
    }

    @Override
    public Comment saveComments(long userid, long productid, Comment comment) {
        Product foundProduct = productService.findByIdProduct(productid);
        User foundUser = commentRepository.findByIdUserForComments(userid).orElseThrow(() -> new BookStoreException("User not Found", HttpStatus.BAD_REQUEST));


        UserProductsComments userComments = new UserProductsComments();
        userComments.setComment(comment);
        userComments.setUser(foundUser);
        userComments.setProduct(foundProduct);

        foundProduct.getCommentsList().add(userComments);
        foundUser.getCommentsList().add(userComments);
        comment.getCommentsList().add(userComments);

        return commentRepository.save(comment);
    }

    @Override
    public Comment deleteComments(long id) {
        Comment foundComment = commentRepository.findById(id).orElseThrow(() -> new BookStoreException("Comment not found",HttpStatus.BAD_REQUEST));
        commentRepository.delete(foundComment);
        return foundComment;
    }


}
