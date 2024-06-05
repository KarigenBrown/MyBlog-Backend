package me.myblog.framework.domain.meta;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import me.myblog.framework.domain.entity.Article;
import me.myblog.framework.domain.entity.Tag;

import java.time.Instant;

@StaticMetamodel(Tag.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Tag_ {

	
	/**
	 * @see Tag#createBy
	 **/
	public static volatile SingularAttribute<Tag, Long> createBy;
	
	/**
	 * @see Tag#createTime
	 **/
	public static volatile SingularAttribute<Tag, Instant> createTime;
	
	/**
	 * @see Tag#updateBy
	 **/
	public static volatile SingularAttribute<Tag, Long> updateBy;
	
	/**
	 * @see Tag#name
	 **/
	public static volatile SingularAttribute<Tag, String> name;
	
	/**
	 * @see Tag#updateTime
	 **/
	public static volatile SingularAttribute<Tag, Instant> updateTime;
	
	/**
	 * @see Tag#remark
	 **/
	public static volatile SingularAttribute<Tag, String> remark;
	
	/**
	 * @see Tag#id
	 **/
	public static volatile SingularAttribute<Tag, Long> id;
	
	/**
	 * @see Tag
	 **/
	public static volatile EntityType<Tag> class_;
	
	/**
	 * @see Tag#articles
	 **/
	public static volatile ListAttribute<Tag, Article> articles;

	public static final String CREATE_BY = "createBy";
	public static final String CREATE_TIME = "createTime";
	public static final String UPDATE_BY = "updateBy";
	public static final String NAME = "name";
	public static final String UPDATE_TIME = "updateTime";
	public static final String REMARK = "remark";
	public static final String ID = "id";
	public static final String ARTICLES = "articles";

}

