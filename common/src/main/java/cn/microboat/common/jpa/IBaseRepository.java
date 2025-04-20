package cn.microboat.common.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;

/**
 * 基础 REPO
 */
@NoRepositoryBean
public interface IBaseRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    /**
     * 根据 code 查询实体
     *
     * @param code 编码
     * @return 实体
     */
    @Query("select t from #{#entityName} t where t.code = ?1")
    T findByCode(String code);

    /**
     * 根据编码集合查询实体列表
     *
     * @param codes 编码集合
     * @return 实体列表
     */
    @Query("select t from #{#entityName} t where t.code in ?1")
    List<T> findByCodeIn(Collection<String> codes);

    /**
     * 多条件查询实体列表
     *
     * @param specification 条件参数
     * @param pageable      分页参数
     * @param sort          排序参数
     * @return 实体列表
     */
    default Page<T> findAll(Specification<T> specification, Pageable pageable, Sort sort) {
        if (pageable.isPaged()) {
            return findAll(specification, pageable);
        }
        return new PageImpl<>(findAll(specification, sort));
    }
}
