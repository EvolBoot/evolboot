package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import projectPackage.core.data.jpa.JpaAbstractEntity;
import projectPackage.core.domain.AggregateRoot;
import projectPackage.core.domain.IdGenerate;
import projectPackage.core.domain.LocaleDomainPart;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.jpa.convert.XarvkgvvrllncLocaleListConverter;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.*;
import java.util.Set;
import java.util.List;


/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Table(name = "tableprefix_lrxxoiecygkjh_xarvkgvvrllnc")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class Xarvkgvvrllnc extends JpaAbstractEntity<Keya2Akk5iV3n> implements AggregateRoot<Xarvkgvvrllnc>, LocaleDomainPart<XarvkgvvrllncLocale> {

    @Id
    private Keya2Akk5iV3n id;

    @Convert(converter = XarvkgvvrllncLocaleListConverter.class)
    private List<XarvkgvvrllncLocale> locales;

    private void generateId() {
        //    this.id = IdGenerate.longId();
    }


    public Xarvkgvvrllnc(String name) {
        //   setLocales(locales);
        generateId();
    }

    @Override
    public Keya2Akk5iV3n id() {
        return id;
    }

    @Override
    public Xarvkgvvrllnc root() {
        return this;
    }
}
