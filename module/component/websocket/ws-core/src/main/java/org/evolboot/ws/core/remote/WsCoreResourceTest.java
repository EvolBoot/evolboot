package org.evolboot.ws.core.remote;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.ws.core.WsMessageHandle;
import org.evolboot.ws.core.WsMessageSender;
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
    private final WsMessageSender wsMessageSender;

    public WsCoreResourceTest(WsMessageHandle wsMessageHandle, WsMessageSender wsMessageSender) {
        this.wsMessageHandle = wsMessageHandle;
        this.wsMessageSender = wsMessageSender;
    }

    /**
     * @return
     */
    @GetMapping("/send-test")
    public ResponseModel<?> sendTest(
            String principalId,
            String action,
            String message
    ) {
        wsMessageSender.send(principalId, action, message);
        wsMessageSender.printOnline();
        return ResponseModel.ok();
    }


}
