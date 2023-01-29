package org.evolboot.core.remote;

import org.evolboot.core.Constant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 返回数据
 *
 * @author evol
 */
@Data
public class ResponseModel<T> {
    private static final long serialVersionUID = 1L;

    public final static int ERROR_CODE = Constant.ErrorStatusCode.ERROR_CODE;
    public final static int SUCCESS_CODE = Constant.ErrorStatusCode.SUCCESS_CODE;

    private final static String SUCCESS = "success";
    private final static String ERROR = "error";

    @Schema(example = SUCCESS_CODE + "", description = "错误码")
    private Integer code;
    @Schema(example = SUCCESS, description = "消息")
    private String msg;
    @Schema(example = "返回数据")
    private T data;

    public ResponseModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseModel(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseModel() {
        this(SUCCESS_CODE, SUCCESS);
    }

    public static ResponseModel<String> error() {
        return error(ERROR_CODE, ERROR);
    }

    public static ResponseModel<String> error(String msg) {
        return error(ERROR_CODE, msg);
    }

    public static ResponseModel<String> error(int code, String msg) {
        return new ResponseModel<String>(code, msg);
    }

    public static ResponseModel<String> ok(String msg) {
        return new ResponseModel<String>(SUCCESS_CODE, SUCCESS, msg);
    }

    public static <T> ResponseModel<T> ok(T data) {
        return new ResponseModel<T>(
                0, SUCCESS, data
        );
    }

    public static ResponseModel<String> ok() {
        return new ResponseModel<String>();
    }


}
