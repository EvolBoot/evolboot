package org.evolboot.locale.domain.language;

import org.evolboot.core.data.Page;
import org.evolboot.locale.domain.language.repository.LanguageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author evol
 */
@Service
public class DefaultLanguageAppService extends LanguageSupportService implements LanguageAppService {


    private final LanguageCreateFactory factory;
    private final LanguageUpdateService updateService;

    protected DefaultLanguageAppService(LanguageRepository repository, LanguageCreateFactory factory, LanguageUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }


    @Override
    @Transactional
    public Long create(LanguageCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Long id, LanguageUpdateService.Request request) {
        updateService.execute(id, request);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public List<Language> findAllByEnableIsTrue() {
        return repository.findAllByEnableIsTrueOrderBySortAsc();
    }

    @Override
    public List<Language> list() {
        return repository.findAll();
    }

    @Override
    public Page<Language> page(LanguageQuery query) {
        return repository.page(query);
    }

    @Override
    @Transactional
    public void enable(Long id) {
        Language language = findById(id);
        language.enable();
        repository.save(language);

    }

    @Override
    public void disable(Long id) {
        Language language = findById(id);
        language.disable();
        repository.save(language);
    }

}
