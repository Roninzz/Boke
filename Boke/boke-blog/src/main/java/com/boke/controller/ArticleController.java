package com.boke.controller;

import com.boke.domain.ResponseResult;
import com.boke.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> test(){
//        return articleService.list();
//    }
    @GetMapping("/hotArticleList")
     public ResponseResult hotArticleList(){
        //热门文章查询  逢装成ResponseResult返回，要调用service
        return articleService.getHotArticleList();
     }

     @GetMapping("/articleList")
     public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId,Long tagId){
        //所有文章查询
         return articleService.getArticleList(pageNum,pageSize,categoryId,tagId);
     }

     @GetMapping("/{id}")
     public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
     }

    @GetMapping("/archivesList")
    public ResponseResult getArchivesList(Integer pageNum,Integer pageSize,Long categoryId){
        //所有文章查询
        return articleService.getArchivesList(pageNum,pageSize,categoryId);
    }
}
