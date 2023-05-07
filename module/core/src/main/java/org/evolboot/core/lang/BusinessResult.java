package org.evolboot.core.lang;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务结果,当业务虽然成功,但有不同的业务状态时，可使用
 * 比如添加好友，添加成功，或者 发送请求成功 都算是成功，但不同的成功，会让客户端有不同的下一步操作，所以需要有不同的subCode区分。
 *
 * @author evol
 */
@Getter
@Setter
public class BusinessResult<T> {

    private int subCode;

    private String msg;

    private T data;

    public BusinessResult(Integer subCode, String msg, T data) {
        this.subCode = subCode;
        this.msg = msg;
        this.data = data;
    }

    public BusinessResult(Integer subCode, String msg) {
        this.subCode = subCode;
        this.msg = msg;
    }

}
