package org.evolboot.identity.domain.user.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.service.crypto.rsa.RsaService;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.entity.Gender;
import org.evolboot.identity.domain.user.entity.User;
import org.evolboot.identity.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class UserUpdateService extends UserSupportService {

    private final RsaService rsaService;

    public UserUpdateService(UserRepository repository, RsaService rsaService) {
        super(repository);
        this.rsaService = rsaService;
    }


    public void execute(Long userId, Request request) {
        User user = repository.findById(userId).orElseThrow(() -> new DomainNotFoundException(
                IdentityI18nMessage.User.userNotFound()
        ));
        if (ExtendObjects.isNotBlank(request.avatar)) {
            user.setAvatar(request.avatar);
        }
        if (ExtendObjects.isNotBlank(request.nickname)) {
            user.setNickname(request.nickname);
        }
        if (ExtendObjects.nonNull(request.gender)) {
            user.setGender(request.gender);
        }

        if (ExtendObjects.isNotBlank(request.getLoginPassword())) {
            user.resetPassword(rsaService.privateDecryptStr(request.getLoginPassword()));
        }
        if (ExtendObjects.isNotBlank(request.getEmail())) {
            repository.findByEmail(request.getEmail().trim()).ifPresent(emailUser -> {
                Assert.isTrue(emailUser.id().equals(user.id()), "邮箱已被占用");
            });
            user.setEmail(request.getEmail().trim());
        }

        if (ExtendObjects.isNotBlank(request.getMobile())) {
            Assert.notBlank(request.getMobilePrefix(), "手机号区号不能为空");
            repository.findByMobile(request.getMobile().trim()).ifPresent(mobileUser -> {
                Assert.isTrue(mobileUser.id().equals(user.id()), "手机号已被占用");
            });
            user.setMobile(request.getMobile(), request.getMobilePrefix());
        }

        repository.save(user);

    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Request {
        private String nickname;
        private String avatar;
        private Gender gender;
        private String loginPassword;
        private String email;
        private String mobile;
        private String mobilePrefix;
    }


}
