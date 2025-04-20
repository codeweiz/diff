package cn.microboat.diff.form;

import cn.microboat.diff.entity.Diff;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiffSearchForm implements Specification<Diff> {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;


    @Override
    public Predicate toPredicate(Root<Diff> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        // 根据编码精确查询
        if (StringUtils.hasText(code)) {
            predicates.add(criteriaBuilder.equal(root.get("code"), code));
        }

        // 根据名称模糊查询
        if (StringUtils.hasText(name)) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        // 如果没有查询条件，返回null（查询全部）
        if (predicates.isEmpty()) {
            return null;
        }

        // 将所有条件用and连接
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
