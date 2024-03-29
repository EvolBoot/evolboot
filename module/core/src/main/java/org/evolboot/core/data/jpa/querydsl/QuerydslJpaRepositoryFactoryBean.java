package org.evolboot.core.data.jpa.querydsl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.lang.NonNull;

import jakarta.persistence.EntityManager;
import java.io.Serializable;

/**
 * QueryDSL 创建工厂，但同时也兼容了JPA
 *
 * @param <R>
 * @param <T>
 * @param <ID>
 */
public class QuerydslJpaRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends JpaRepositoryFactoryBean<R, T, ID> {

    public QuerydslJpaRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    @NonNull
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new QuerydslJpaRepositoryFactory(entityManager);
    }

    private static class QuerydslJpaRepositoryFactory extends JpaRepositoryFactory {
        QuerydslJpaRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }

        @Override
        @NonNull
        protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, @NonNull EntityManager entityManager) {
            if (isQueryDslRepository(information)) {
                return new QuerydslJpaBaseRepository<>(getEntityInformation(information.getDomainType()), entityManager);
            }
            return super.getTargetRepository(information, entityManager);
        }

        @Override
        @NonNull
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            if (isQueryDslRepository(metadata)) {
                return QuerydslJpaBaseRepository.class;
            }
            return super.getRepositoryBaseClass(metadata);
        }

        private boolean isQueryDslRepository(RepositoryMetadata metadata) {
            Class<?> repositoryInterface = metadata.getRepositoryInterface();
            return ExtendedQuerydslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
        }
    }
}
