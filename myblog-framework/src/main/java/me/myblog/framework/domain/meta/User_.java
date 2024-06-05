package me.myblog.framework.domain.meta;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.entity.User;

import java.time.Instant;

@StaticMetamodel(User.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class User_ {

	
	/**
	 * @see User#nickName
	 **/
	public static volatile SingularAttribute<User, String> nickName;
	
	/**
	 * @see User#sex
	 **/
	public static volatile SingularAttribute<User, Character> sex;
	
	/**
	 * @see User#roles
	 **/
	public static volatile ListAttribute<User, Role> roles;
	
	/**
	 * @see User#phonenumber
	 **/
	public static volatile SingularAttribute<User, String> phonenumber;
	
	/**
	 * @see User#updateTime
	 **/
	public static volatile SingularAttribute<User, Instant> updateTime;
	
	/**
	 * @see User#avatar
	 **/
	public static volatile SingularAttribute<User, String> avatar;
	
	/**
	 * @see User#userName
	 **/
	public static volatile SingularAttribute<User, String> userName;
	
	/**
	 * @see User#type
	 **/
	public static volatile SingularAttribute<User, Character> type;
	
	/**
	 * @see User#password
	 **/
	public static volatile SingularAttribute<User, String> password;
	
	/**
	 * @see User#createBy
	 **/
	public static volatile SingularAttribute<User, Long> createBy;
	
	/**
	 * @see User#createTime
	 **/
	public static volatile SingularAttribute<User, Instant> createTime;
	
	/**
	 * @see User#updateBy
	 **/
	public static volatile SingularAttribute<User, Long> updateBy;
	
	/**
	 * @see User#id
	 **/
	public static volatile SingularAttribute<User, Long> id;
	
	/**
	 * @see User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see User#email
	 **/
	public static volatile SingularAttribute<User, String> email;
	
	/**
	 * @see User#status
	 **/
	public static volatile SingularAttribute<User, Character> status;

	public static final String NICK_NAME = "nickName";
	public static final String SEX = "sex";
	public static final String ROLES = "roles";
	public static final String PHONENUMBER = "phonenumber";
	public static final String UPDATE_TIME = "updateTime";
	public static final String AVATAR = "avatar";
	public static final String USER_NAME = "userName";
	public static final String TYPE = "type";
	public static final String PASSWORD = "password";
	public static final String CREATE_BY = "createBy";
	public static final String CREATE_TIME = "createTime";
	public static final String UPDATE_BY = "updateBy";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String STATUS = "status";

}

