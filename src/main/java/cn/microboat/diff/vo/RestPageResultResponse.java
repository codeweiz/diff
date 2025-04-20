package cn.microboat.diff.vo;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 带分页的响应
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestPageResultResponse<T> extends RestResponse {

    /**
     * 分页列表
     */
    private List<T> result;

    /**
     * 分页
     */
    private RestPageBean page;

    /**
     * 根据分页列表获取分页响应
     *
     * @param page 分页列表
     * @return 分页响应
     */
    public static <T> RestPageBean buildRestPageBean(Page<T> page) {
        return new RestPageBean(page.getNumber(), page.getSize(), page.getNumberOfElements(), page.getTotalPages(), page.getTotalElements());
    }

    /**
     * 根据 page 返回响应
     *
     * @param page 分页列表
     * @return 带分页的响应
     */
    public static <T> RestPageResultResponse<T> of(Page<T> page) {
        return new RestPageResultResponse<>(page.getContent(), buildRestPageBean(page));
    }

}
