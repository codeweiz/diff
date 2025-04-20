package cn.microboat.diff.service;

import cn.microboat.diff.entity.Article;
import cn.microboat.diff.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * 文章服务
 */
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 多条件分页排序查询文章列表
     *
     * @param specification 条件
     * @param pageable      分页参数
     * @param sort          排序参数
     * @return 文章列表
     */
    public Page<Article> findAll(Specification<Article> specification, Pageable pageable, Sort sort) {
        return articleRepository.findAll(specification, pageable, sort);
    }
}
