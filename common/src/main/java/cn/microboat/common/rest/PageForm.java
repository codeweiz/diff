package cn.microboat.common.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageForm {

    /**
     * 页码(从0开始)
     */
    private int page = 0;

    /**
     * 页大小
     */
    private int size = 10;

    /**
     * 是否分页，默认分页
     */
    private boolean paged = true;

    /**
     * 排序，格式: 属性名,(asc|desc);属性名,(asc|desc)
     * 例如: "name,asc;createTime,desc"
     */
    private String sort = "";

    /**
     * 获取Spring Data的Pageable对象
     *
     * @return Pageable对象
     */
    public Pageable toPageable() {
        if (!paged) {
            return Pageable.unpaged();
        }

        Sort sort = toSort();
        if (sort != null) {
            return PageRequest.of(page, size, sort);
        } else {
            return PageRequest.of(page, size);
        }
    }

    /**
     * 获取Spring Data的Sort对象
     *
     * @return Sort对象，如果没有排序条件则返回不排序
     */
    public Sort toSort() {
        if (!StringUtils.hasText(sort)) {
            return Sort.unsorted();
        }

        List<Sort.Order> orders = new ArrayList<>();
        String[] sortArr = sort.split(";");

        for (String sortItem : sortArr) {
            if (!StringUtils.hasText(sortItem)) {
                continue;
            }

            String[] parts = sortItem.trim().split(",");
            if (parts.length == 0) {
                continue;
            }

            String property = parts[0].trim();
            if (!StringUtils.hasText(property)) {
                continue;
            }

            Sort.Direction direction = Sort.Direction.ASC;
            if (parts.length > 1 && "desc".equalsIgnoreCase(parts[1].trim())) {
                direction = Sort.Direction.DESC;
            }

            orders.add(new Sort.Order(direction, property));
        }

        if (orders.isEmpty()) {
            return Sort.unsorted();
        }

        return Sort.by(orders);
    }

    /**
     * 创建未分页的请求
     *
     * @return 未分页的PageForm实例
     */
    public static PageForm unpaged() {
        PageForm form = new PageForm();
        form.setPaged(false);
        return form;
    }

    /**
     * 设置排序
     *
     * @param property  属性名
     * @param direction 排序方向 ("asc" 或 "desc")
     * @return 当前PageForm实例
     */
    public PageForm addSort(String property, String direction) {
        if (!StringUtils.hasText(property)) {
            return this;
        }

        String sortItem = property + "," + (("desc".equalsIgnoreCase(direction)) ? "desc" : "asc");

        if (!StringUtils.hasText(this.sort)) {
            this.sort = sortItem;
        } else {
            this.sort = this.sort + ";" + sortItem;
        }

        return this;
    }

    /**
     * 使用Java API设置多字段排序
     *
     * @param orders 排序条件数组，格式为 ["field1,asc", "field2,desc"]
     * @return 当前PageForm实例
     */
    public PageForm withSort(String... orders) {
        if (orders == null || orders.length == 0) {
            return this;
        }

        this.sort = Arrays.stream(orders)
                .filter(StringUtils::hasText)
                .collect(Collectors.joining(";"));

        return this;
    }

    /**
     * 创建基本分页请求
     *
     * @param page 页码(从0开始)
     * @param size 页大小
     * @return 分页的PageForm实例
     */
    public static PageForm of(int page, int size) {
        PageForm form = new PageForm();
        form.setPage(Math.max(0, page));
        form.setSize(Math.max(1, size));
        form.setPaged(true);
        return form;
    }

    /**
     * 创建带排序的分页请求
     *
     * @param page 页码(从0开始)
     * @param size 页大小
     * @param sort 排序字符串
     * @return 分页的PageForm实例
     */
    public static PageForm of(int page, int size, String sort) {
        PageForm form = of(page, size);
        form.setSort(sort);
        return form;
    }
}