package cn.microboat.diff.service;

import cn.microboat.diff.entity.Diff;
import cn.microboat.diff.repo.DiffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * 差异服务
 */
public class DiffService {

    @Autowired
    private DiffRepository diffRepository;

    /**
     * 多条件分页排序查询差异列表
     *
     * @param specification 条件
     * @param pageable      分页参数
     * @param sort          排序参数
     * @return 差异列表
     */
    public Page<Diff> findAll(Specification<Diff> specification, Pageable pageable, Sort sort) {
        return diffRepository.findAll(specification, pageable, sort);
    }
}
