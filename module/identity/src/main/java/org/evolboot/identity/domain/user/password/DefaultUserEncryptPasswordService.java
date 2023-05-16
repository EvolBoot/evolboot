package org.evolboot.identity.domain.user.password;

import org.evolboot.core.service.crypto.rsa.RsaService;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class DefaultUserEncryptPasswordService implements UserEncryptPasswordService {

    private final RsaService rsaService;

    public DefaultUserEncryptPasswordService(RsaService rsaService) {
        this.rsaService = rsaService;
    }

    @Override
    public ReversiblePassword toReversiblePassword(String encodePassword) {
        String password = rsaService.privateDecryptStr(encodePassword);
        return new ReversiblePasswordImpl(password);
    }
}
