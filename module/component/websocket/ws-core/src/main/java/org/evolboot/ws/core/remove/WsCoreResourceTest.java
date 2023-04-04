package org.evolboot.ws.core.remove;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.event.mq.TestMessage;
import org.evolboot.ws.core.WsMessageHandle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author evol
 */
@RestController
@RequestMapping("/v1/admin/ws")
@Tag(name = "ws", description = "ws")
@AdminClient
public class WsCoreResourceTest {


    private final WsMessageHandle wsMessageHandle;

    public WsCoreResourceTest(WsMessageHandle wsMessageHandle) {
        this.wsMessageHandle = wsMessageHandle;
    }

    /**
     * @return
     */
    @GetMapping("/send-test")
    public ResponseModel<?> sendTest(
            String action,
            String message
    ) {
        String s = wsMessageHandle.handleMessage(action, message);
        return ResponseModel.ok(s);
    }

}
