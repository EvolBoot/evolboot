package org.evolboot.system.domain.dictkey.service;


import org.springframework.stereotype.Service;
import org.evolboot.system.domain.dictkey.repository.DictKeyRepository;
import org.evolboot.system.domain.dictkey.DictKey;
import org.evolboot.system.domain.dictkey.
        DictKeyRequestBase;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Slf4j
@Service
public class DictKeyCreateFactory extends DictKeySupportService {
    protected DictKeyCreateFactory(DictKeyRepository repository) {
        super(repository);
    }

    public DictKey execute(Request request) {
        keyMustNotFound(request.getKey());
        DictKey dictKey = new DictKey(
                request.getDisplayName(),
                request.getKey(),
                request.getSort(),
                request.getRemark()
        );
        repository.save(dictKey);
        return dictKey;
    }

    public static class Request extends DictKeyRequestBase {

    }

}
