package org.evolboot.locale.domain.language;

import org.evolboot.locale.domain.language.repository.LanguageRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class LanguageCreateFactory extends LanguageSupportService {
    protected LanguageCreateFactory(LanguageRepository repository) {
        super(repository);
    }

    public Long execute(Request request) {
        Language language = new Language(
                request.getRemark(),
                request.getLanguage(),
                request.getSort()
        );
        repository.save(language);
        return language.getId();
    }

    public static class Request extends LanguageRequestBase {
    }

}
