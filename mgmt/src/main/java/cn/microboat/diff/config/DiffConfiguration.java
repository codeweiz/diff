package cn.microboat.diff.config;

import cn.microboat.diff.service.ArticleService;
import cn.microboat.diff.service.DiffService;
import cn.microboat.diff.service.SnapShotService;
import cn.microboat.diff.service.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 配置类
 */
@EnableJpaRepositories(basePackages = "cn.microboat.diff.repo")
@EntityScan(basePackages = "cn.microboat.diff.entity")
@EnableJpaAuditing
@Configuration
public class DiffConfiguration {

    /**
     * 文章服务
     */
    @Bean
    public ArticleService articleService() {
        return new ArticleService();
    }

    /**
     * 差异服务
     */
    @Bean
    public DiffService diffService() {
        return new DiffService();
    }

    /**
     * 快照服务
     */
    @Bean
    public SnapShotService snapShotService() {
        return new SnapShotService();
    }

    /**
     * 用户服务
     */
    @Bean
    public UserService userService() {
        return new UserService();
    }

}
