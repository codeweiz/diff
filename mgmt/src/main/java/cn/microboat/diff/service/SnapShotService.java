package cn.microboat.diff.service;

import cn.microboat.diff.entity.SnapShot;
import cn.microboat.diff.repo.SnapShotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * 快照服务
 */
public class SnapShotService {

    @Autowired
    private SnapShotRepository snapShotRepository;

    /**
     * 多条件分页排序查询快照列表
     *
     * @param specification 条件
     * @param pageable      分页参数
     * @param sort          排序参数
     * @return 快照列表
     */
    public Page<SnapShot> findAll(Specification<SnapShot> specification, Pageable pageable, Sort sort) {
        return snapShotRepository.findAll(specification, pageable, sort);
    }
}
