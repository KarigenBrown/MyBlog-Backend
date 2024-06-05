package me.myblog.framework.domain.meta;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import me.myblog.framework.domain.entity.Menu;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.entity.User;

import java.time.Instant;

@StaticMetamodel(Role.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Role_ {

	
	/**
	 * @see Role#updateTime
	 **/
	public static volatile SingularAttribute<Role, Instant> updateTime;
	
	/**
	 * @see Role#remark
	 **/
	public static volatile SingularAttribute<Role, String> remark;
	
	/**
	 * @see Role#users
	 **/
	public static volatile ListAttribute<Role, User> users;
	
	/**
	 * @see Role#roleSort
	 **/
	public static volatile SingularAttribute<Role, Integer> roleSort;
	
	/**
	 * @see Role#createBy
	 **/
	public static volatile SingularAttribute<Role, Long> createBy;
	
	/**
	 * @see Role#createTime
	 **/
	public static volatile SingularAttribute<Role, Instant> createTime;
	
	/**
	 * @see Role#updateBy
	 **/
	public static volatile SingularAttribute<Role, Long> updateBy;
	
	/**
	 * @see Role#roleName
	 **/
	public static volatile SingularAttribute<Role, String> roleName;
	
	/**
	 * @see Role#roleKey
	 **/
	public static volatile SingularAttribute<Role, String> roleKey;
	
	/**
	 * @see Role#id
	 **/
	public static volatile SingularAttribute<Role, Long> id;
	
	/**
	 * @see Role#menus
	 **/
	public static volatile ListAttribute<Role, Menu> menus;
	
	/**
	 * @see Role
	 **/
	public static volatile EntityType<Role> class_;
	
	/**
	 * @see Role#status
	 **/
	public static volatile SingularAttribute<Role, Character> status;

	public static final String UPDATE_TIME = "updateTime";
	public static final String REMARK = "remark";
	public static final String USERS = "users";
	public static final String ROLE_SORT = "roleSort";
	public static final String CREATE_BY = "createBy";
	public static final String CREATE_TIME = "createTime";
	public static final String UPDATE_BY = "updateBy";
	public static final String ROLE_NAME = "roleName";
	public static final String ROLE_KEY = "roleKey";
	public static final String ID = "id";
	public static final String MENUS = "menus";
	public static final String STATUS = "status";

}

