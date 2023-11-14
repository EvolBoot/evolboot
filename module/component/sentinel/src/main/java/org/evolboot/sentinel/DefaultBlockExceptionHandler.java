package org.evolboot.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.Constant;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 触发流控时,默认返回
 *
 * @author evol
 */
@Service
@Slf4j
public class DefaultBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        response.setStatus(200);
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        outputStreamWrite(response, ResponseModel.error(Constant.ErrorStatusCode.ERROR_CODE, "flow limiting"));
    }

    protected void outputStreamWrite(HttpServletResponse response, Object data) {
        String msgText = JsonUtil.stringify(data);
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(msgText.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("ERROR:", e);
        }
    }
}
