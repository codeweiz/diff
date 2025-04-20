package cn.microboat.diff.ctrl;

import cn.microboat.common.rest.PageForm;
import cn.microboat.common.rest.RestPageResultResponse;
import cn.microboat.diff.entity.Article;
import cn.microboat.diff.form.ArticleSearchForm;
import cn.microboat.diff.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章 API 接口
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/page")
    public RestPageResultResponse<Article> pageArticle(@RequestBody ArticleSearchForm searchForm, PageForm pageForm) {
        return RestPageResultResponse.of(articleService.findAll(searchForm, pageForm.toPageable(), pageForm.toSort()));
    }

}
