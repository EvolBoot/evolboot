package org.evolboot.locale.domain.language;

import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.locale.LocaleI18nMessage;
import org.evolboot.locale.domain.language.repository.LanguageRepository;

/**
 * @author evol
 */
public abstract class LanguageSupportService {

    final LanguageRepository repository;

    protected LanguageSupportService(LanguageRepository repository) {
        this.repository = repository;
    }

    public Language findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(LocaleI18nMessage.Language.notFound()));
    }

}
