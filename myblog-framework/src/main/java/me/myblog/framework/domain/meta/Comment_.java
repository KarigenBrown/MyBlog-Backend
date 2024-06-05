package me.myblog.framework.domain.meta;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import me.myblog.framework.domain.entity.Comment;

import java.time.Instant;

@StaticMetamodel(Comment.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Comment_ {

	
	/**
	 * @see Comment#toCommentId
	 **/
	public static volatile SingularAttribute<Comment, Long> toCommentId;
	
	/**
	 * @see Comment#createBy
	 **/
	public static volatile SingularAttribute<Comment, Long> createBy;
	
	/**
	 * @see Comment#rootId
	 **/
	public static volatile SingularAttribute<Comment, Long> rootId;
	
	/**
	 * @see Comment#createTime
	 **/
	public static volatile SingularAttribute<Comment, Instant> createTime;
	
	/**
	 * @see Comment#updateBy
	 **/
	public static volatile SingularAttribute<Comment, Long> updateBy;
	
	/**
	 * @see Comment#articleId
	 **/
	public static volatile SingularAttribute<Comment, Long> articleId;
	
	/**
	 * @see Comment#updateTime
	 **/
	public static volatile SingularAttribute<Comment, Instant> updateTime;
	
	/**
	 * @see Comment#id
	 **/
	public static volatile SingularAttribute<Comment, Long> id;
	
	/**
	 * @see Comment#toCommentUserId
	 **/
	public static volatile SingularAttribute<Comment, Long> toCommentUserId;
	
	/**
	 * @see Comment#type
	 **/
	public static volatile SingularAttribute<Comment, Character> type;
	
	/**
	 * @see Comment
	 **/
	public static volatile EntityType<Comment> class_;
	
	/**
	 * @see Comment#content
	 **/
	public static volatile SingularAttribute<Comment, String> content;

	public static final String TO_COMMENT_ID = "toCommentId";
	public static final String CREATE_BY = "createBy";
	public static final String ROOT_ID = "rootId";
	public static final String CREATE_TIME = "createTime";
	public static final String UPDATE_BY = "updateBy";
	public static final String ARTICLE_ID = "articleId";
	public static final String UPDATE_TIME = "updateTime";
	public static final String ID = "id";
	public static final String TO_COMMENT_USER_ID = "toCommentUserId";
	public static final String TYPE = "type";
	public static final String CONTENT = "content";

}

