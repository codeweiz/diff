package cn.microboat.diff.vo;

import lombok.*;

/**
 * 基础响应
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse {

    public static final int CODE_SUCCESS = 200;
    public static final String MSG_SUCCESS = "ok";

    /**
     * 业务码
     */
    protected int code;

    /**
     * 错误信息
     */
    protected String msg;

    /**
     * 成功
     *
     * @return 响应
     */
    public static RestResponse ok() {
        return RestResponse.builder().code(CODE_SUCCESS).msg(MSG_SUCCESS).build();
    }

    /**
     * 失败
     *
     * @param code 业务码
     * @param msg  消息
     * @return 响应
     */
    public static RestResponse fail(int code, String msg) {
        return RestResponse.builder().code(code).msg(msg).build();
    }
}
