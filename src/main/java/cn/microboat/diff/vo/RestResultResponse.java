package cn.microboat.diff.vo;

import lombok.*;

/**
 * 带结果的响应
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestResultResponse<T> extends RestResponse {

    /**
     * 结果
     */
    private T result;

    public RestResultResponse(RestResponse restResponse) {
        super(restResponse.getCode(), restResponse.getMsg());
    }

    /**
     * 成功
     */
    public static <T> RestResultResponse<T> ok(T result) {
        RestResultResponse<T> response = new RestResultResponse<>(RestResultResponse.ok());
        response.setResult(result);
        return response;
    }

    /**
     * 失败
     */
    public static <T> RestResultResponse<T> error(int code, String msg) {
        return new RestResultResponse<>(RestResponse.fail(code, msg));
    }
}
