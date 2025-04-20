package cn.microboat.diff.vo;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestPageBean {

    /**
     * 当前页
     */
    private int number;

    /**
     * 当前页数量
     */
    private int size;

    /**
     * 当前页数量
     */
    private int numberOfElements;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 总数量
     */
    private long totalElements;
}
