package org.evolboot.locale.domain.language.repository;

import org.evolboot.core.data.Page;
import org.evolboot.locale.domain.language.Language;
import org.evolboot.locale.domain.language.LanguageQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface LanguageRepository {

    Language save(Language language);

    Optional<Language> findById(Long id);

    Page<Language> page(LanguageQuery query);

    void deleteById(Long id);

    List<Language> findAllByEnableIsTrueOrderBySortAsc();

    List<Language> findAll();
}
