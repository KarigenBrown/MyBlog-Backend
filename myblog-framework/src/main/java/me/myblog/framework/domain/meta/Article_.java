package me.myblog.framework.domain.meta;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import me.myblog.framework.domain.entity.Article;

import java.time.Instant;

@StaticMetamodel(Article.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Article_ {

	
	/**
	 * @see Article#summary
	 **/
	public static volatile SingularAttribute<Article, String> summary;
	
	/**
	 * @see Article#thumbnail
	 **/
	public static volatile SingularAttribute<Article, String> thumbnail;
	
	/**
	 * @see Article#isComment
	 **/
	public static volatile SingularAttribute<Article, Character> isComment;
	
	/**
	 * @see Article#updateTime
	 **/
	public static volatile SingularAttribute<Article, Instant> updateTime;
	
	/**
	 * @see Article#title
	 **/
	public static volatile SingularAttribute<Article, String> title;
	
	/**
	 * @see Article#content
	 **/
	public static volatile SingularAttribute<Article, String> content;
	
	/**
	 * @see Article#createBy
	 **/
	public static volatile SingularAttribute<Article, Long> createBy;
	
	/**
	 * @see Article#createTime
	 **/
	public static volatile SingularAttribute<Article, Instant> createTime;
	
	/**
	 * @see Article#updateBy
	 **/
	public static volatile SingularAttribute<Article, Long> updateBy;
	
	/**
	 * @see Article#isTop
	 **/
	public static volatile SingularAttribute<Article, Character> isTop;
	
	/**
	 * @see Article#id
	 **/
	public static volatile SingularAttribute<Article, Long> id;
	
	/**
	 * @see Article#viewCount
	 **/
	public static volatile SingularAttribute<Article, Long> viewCount;
	
	/**
	 * @see Article
	 **/
	public static volatile EntityType<Article> class_;
	
	/**
	 * @see Article#categoryId
	 **/
	public static volatile SingularAttribute<Article, Long> categoryId;
	
	/**
	 * @see Article#status
	 **/
	public static volatile SingularAttribute<Article, Character> status;

	public static final String SUMMARY = "summary";
	public static final String THUMBNAIL = "thumbnail";
	public static final String IS_COMMENT = "isComment";
	public static final String UPDATE_TIME = "updateTime";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String CREATE_BY = "createBy";
	public static final String CREATE_TIME = "createTime";
	public static final String UPDATE_BY = "updateBy";
	public static final String IS_TOP = "isTop";
	public static final String ID = "id";
	public static final String VIEW_COUNT = "viewCount";
	public static final String CATEGORY_ID = "categoryId";
	public static final String STATUS = "status";

}

