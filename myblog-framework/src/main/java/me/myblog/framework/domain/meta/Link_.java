package me.myblog.framework.domain.meta;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import me.myblog.framework.domain.entity.Link;

import java.time.Instant;

@StaticMetamodel(Link.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Link_ {

	
	/**
	 * @see Link#createBy
	 **/
	public static volatile SingularAttribute<Link, Long> createBy;
	
	/**
	 * @see Link#address
	 **/
	public static volatile SingularAttribute<Link, String> address;
	
	/**
	 * @see Link#createTime
	 **/
	public static volatile SingularAttribute<Link, Instant> createTime;
	
	/**
	 * @see Link#updateBy
	 **/
	public static volatile SingularAttribute<Link, Long> updateBy;
	
	/**
	 * @see Link#name
	 **/
	public static volatile SingularAttribute<Link, String> name;
	
	/**
	 * @see Link#logo
	 **/
	public static volatile SingularAttribute<Link, String> logo;
	
	/**
	 * @see Link#description
	 **/
	public static volatile SingularAttribute<Link, String> description;
	
	/**
	 * @see Link#updateTime
	 **/
	public static volatile SingularAttribute<Link, Instant> updateTime;
	
	/**
	 * @see Link#id
	 **/
	public static volatile SingularAttribute<Link, Long> id;
	
	/**
	 * @see Link
	 **/
	public static volatile EntityType<Link> class_;
	
	/**
	 * @see Link#status
	 **/
	public static volatile SingularAttribute<Link, Character> status;

	public static final String CREATE_BY = "createBy";
	public static final String ADDRESS = "address";
	public static final String CREATE_TIME = "createTime";
	public static final String UPDATE_BY = "updateBy";
	public static final String NAME = "name";
	public static final String LOGO = "logo";
	public static final String DESCRIPTION = "description";
	public static final String UPDATE_TIME = "updateTime";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

