package org.evolboot.locale.domain.language;

import org.evolboot.core.util.ExtendObjects;
import org.evolboot.locale.domain.language.repository.LanguageRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class LanguageUpdateService extends LanguageSupportService {
    protected LanguageUpdateService(LanguageRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        Language language = findById(id);

        if (ExtendObjects.isNotBlank(request.getRemark())) {
            language.setRemark(request.getRemark());
        }

        if (ExtendObjects.isNotBlank(request.getLanguage())) {
            language.setLanguage(request.getLanguage());
        }
        if (ExtendObjects.nonNull(request.getSort())) {
            language.setSort(request.getSort());
        }
        repository.save(language);
    }

    public static class Request extends LanguageRequestBase {
    }

}
