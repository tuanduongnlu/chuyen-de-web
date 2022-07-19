package com.example.chuyendeweb.controller;

import com.example.chuyendeweb.DTO.CommentDTO;
import com.example.chuyendeweb.DTO.findPost.FindPostWriteDTO;
import com.example.chuyendeweb.entities.Comment;
import com.example.chuyendeweb.entities.FindPost;
import com.example.chuyendeweb.service.CommentService;
import com.example.chuyendeweb.service.RoomTypeService;
import com.example.chuyendeweb.service.SearcherPostService;
import com.example.chuyendeweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SearcherPostController {
    @Autowired
    SearcherPostService searcherPostService;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/findRoom")
    public String getPostPage(Model model) {
        List<FindPost> listAll = searcherPostService.getAllFindPost();
        model.addAttribute("listAllFindPost", listAll);
        return "findRoom";

    }
    @GetMapping(value = "/postFindRoom/{id}")
    public String getPostDetail(@PathVariable("id")int id, Model model){
        FindPost findPost = searcherPostService.findById(id);
        List<Comment> comments = findPost.getComments();
        model.addAttribute("findPost", findPost);
        model.addAttribute("comments", comments);
        return "postDetail";
    }

    @DeleteMapping(value = "/postFindRoom/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        searcherPostService.deleteById(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @PostMapping("/postFindRoom")
    public String saveFindPost(@RequestParam("roomType") int roomType, @RequestParam("title") String title,
                               @RequestParam("detail") String detail) {
        FindPostWriteDTO findPostWriteDTO = new FindPostWriteDTO(roomType, title, detail);
        FindPost findPost = FindPostWriteDTO.trantToPiFindPost(findPostWriteDTO);
        findPost.setRoomType(roomTypeService.getById(findPostWriteDTO.getRoomType()));


        final String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        findPost.setUser(userService.findByPhone(phone));
        searcherPostService.saveOrUpdate(findPost);
        return "redirect:/home";
    }

    @PostMapping("/postFindRoom/comment/{id}")
    public String UpdateComemnt(@PathVariable("id")int id, @RequestParam("comment")String subComment){
        Comment comment = commentService.findById(id);
        comment.setComment(subComment);
        commentService.saveOrUpdate(comment);
        return "postDetail";
    }
    @PostMapping("/postFindRoom/{id}/comment")
    public String saveComemnt(@PathVariable("id")int id, @RequestParam("comment")String subComment){
        FindPost findPost = searcherPostService.findById(id);

        List<Comment> comments = findPost.getComments();
        Comment comment = new Comment();
        comment.setComment(subComment);
        comment.setFind_post(findPost);
        final String phone = SecurityContextHolder.getContext().getAuthentication().getName();

        comment.setUser(userService.findByPhone(phone));
        comments.add(comment);
        findPost.setComments(comments);
        searcherPostService.saveOrUpdate(findPost);
        return "redirect:/postFindRoom/"+id;
    }


}
