/*
 * This file is generated by jOOQ.
 */
package me.myblog.framework.domain.jooq.tables.records;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import me.myblog.framework.domain.jooq.tables.ArticleTag;
import me.myblog.framework.domain.jooq.tables.interfaces.IArticleTag;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * 文章标签关联表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
@Entity
@Table(
    name = "article_tag",
    schema = "myblog"
)
public class ArticleTagRecord extends UpdatableRecordImpl<ArticleTagRecord> implements Record2<Long, Long>, IArticleTag {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>myblog.article_tag.article_id</code>. 文章id
     */
    public ArticleTagRecord setArticleId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>myblog.article_tag.article_id</code>. 文章id
     */
    @Column(name = "article_id")
    @Override
    public Long getArticleId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>myblog.article_tag.tag_id</code>. 标签id
     */
    public ArticleTagRecord setTagId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>myblog.article_tag.tag_id</code>. 标签id
     */
    @Column(name = "tag_id")
    @Override
    public Long getTagId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return ArticleTag.ARTICLE_TAG.ARTICLE_ID;
    }

    @Override
    public Field<Long> field2() {
        return ArticleTag.ARTICLE_TAG.TAG_ID;
    }

    @Override
    public Long component1() {
        return getArticleId();
    }

    @Override
    public Long component2() {
        return getTagId();
    }

    @Override
    public Long value1() {
        return getArticleId();
    }

    @Override
    public Long value2() {
        return getTagId();
    }

    @Override
    public ArticleTagRecord value1(Long value) {
        setArticleId(value);
        return this;
    }

    @Override
    public ArticleTagRecord value2(Long value) {
        setTagId(value);
        return this;
    }

    @Override
    public ArticleTagRecord values(Long value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    public void from(IArticleTag from) {
        setArticleId(from.getArticleId());
        setTagId(from.getTagId());
        resetChangedOnNotNull();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ArticleTagRecord
     */
    public ArticleTagRecord() {
        super(ArticleTag.ARTICLE_TAG);
    }

    /**
     * Create a detached, initialised ArticleTagRecord
     */
    public ArticleTagRecord(Long articleId, Long tagId) {
        super(ArticleTag.ARTICLE_TAG);

        setArticleId(articleId);
        setTagId(tagId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ArticleTagRecord
     */
    public ArticleTagRecord(me.myblog.framework.domain.jooq.tables.pojos.ArticleTag value) {
        super(ArticleTag.ARTICLE_TAG);

        if (value != null) {
            setArticleId(value.getArticleId());
            setTagId(value.getTagId());
            resetChangedOnNotNull();
        }
    }
}
