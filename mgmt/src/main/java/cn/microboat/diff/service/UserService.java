package cn.microboat.diff.service;

import cn.microboat.diff.entity.User;
import cn.microboat.diff.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * 用户服务
 */
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 多条件分页排序查询用户列表
     *
     * @param specification 条件
     * @param pageable      分页参数
     * @param sort          排序参数
     * @return 用户列表
     */
    public Page<User> findAll(Specification<User> specification, Pageable pageable, Sort sort) {
        return userRepository.findAll(specification, pageable, sort);
    }
}
