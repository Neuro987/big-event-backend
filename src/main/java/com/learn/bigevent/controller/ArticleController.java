package com.learn.bigevent.controller;

import com.learn.bigevent.pojo.Article;
import com.learn.bigevent.pojo.PageBean;
import com.learn.bigevent.pojo.Result;
import com.learn.bigevent.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) Integer categoryId
    ) {
        PageBean<Article> pb = articleService.list(pageNum, pageSize, state, categoryId);
        return Result.success(pb);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Article.Update.class) Article article) {
        articleService.update(article);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam Integer id) {
        Article article = articleService.findById(id);
        return Result.success(article);
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        articleService.delete(id);
        return Result.success();
    }

}
