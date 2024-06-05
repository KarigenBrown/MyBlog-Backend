package me.myblog.framework.domain.meta;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import me.myblog.framework.domain.entity.Category;

import java.time.Instant;

@StaticMetamodel(Category.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Category_ {

	
	/**
	 * @see Category#createBy
	 **/
	public static volatile SingularAttribute<Category, Long> createBy;
	
	/**
	 * @see Category#createTime
	 **/
	public static volatile SingularAttribute<Category, Instant> createTime;
	
	/**
	 * @see Category#updateBy
	 **/
	public static volatile SingularAttribute<Category, Long> updateBy;
	
	/**
	 * @see Category#name
	 **/
	public static volatile SingularAttribute<Category, String> name;
	
	/**
	 * @see Category#description
	 **/
	public static volatile SingularAttribute<Category, String> description;
	
	/**
	 * @see Category#pid
	 **/
	public static volatile SingularAttribute<Category, Long> pid;
	
	/**
	 * @see Category#updateTime
	 **/
	public static volatile SingularAttribute<Category, Instant> updateTime;
	
	/**
	 * @see Category#id
	 **/
	public static volatile SingularAttribute<Category, Long> id;
	
	/**
	 * @see Category
	 **/
	public static volatile EntityType<Category> class_;
	
	/**
	 * @see Category#status
	 **/
	public static volatile SingularAttribute<Category, Character> status;

	public static final String CREATE_BY = "createBy";
	public static final String CREATE_TIME = "createTime";
	public static final String UPDATE_BY = "updateBy";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PID = "pid";
	public static final String UPDATE_TIME = "updateTime";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

