package org.evolboot.core.remote;

import org.evolboot.core.service.crypto.rsa.RsaService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用来测试，发布时需要删除
 *
 * @author evol
 */
@Hidden
@Slf4j
//@RestController
//@RequestMapping("/v1/api/rsa")
@Tag(name = "RSA", description = "RSA")
public class RsaResourceV1 {

    public final RsaService service;

    public RsaResourceV1(RsaService service) {
        this.service = service;
    }

    @Operation(summary = "私钥加密")
    @GetMapping("/private-encrypt")
    public ResponseModel<?> privateEncrypt(
            String str
    ) {
        return ResponseModel.ok(service.privateEncrypt(str));
    }

    @Operation(summary = "私钥解密")
    @GetMapping("/private-decryptStr")
    public ResponseModel<?> privateDecryptStr(
            String str
    ) {
        return ResponseModel.ok(service.privateDecryptStr(str));
    }

    @Operation(summary = "公钥加密")
    @GetMapping("/public-encrypt")
    public ResponseModel<?> publicEncrypt(
            String str
    ) {
        return ResponseModel.ok(service.publicEncrypt(str));
    }

    @Operation(summary = "公钥解密")
    @GetMapping("/public-decryptStr")
    public ResponseModel<?> publicDecryptStr(
            String str
    ) {
        return ResponseModel.ok(service.publicDecryptStr(str));
    }
}
