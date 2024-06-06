package me.myblog.framework.domain.meta;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import me.myblog.framework.domain.entity.Menu;

import java.time.Instant;

@StaticMetamodel(Menu.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Menu_ {

	
	/**
	 * @see Menu#visible
	 **/
	public static volatile SingularAttribute<Menu, Character> visible;
	
	/**
	 * @see Menu#icon
	 **/
	public static volatile SingularAttribute<Menu, String> icon;
	
	/**
	 * @see Menu#orderNum
	 **/
	public static volatile SingularAttribute<Menu, Integer> orderNum;
	
	/**
	 * @see Menu#menuName
	 **/
	public static volatile SingularAttribute<Menu, String> menuName;
	
	/**
	 * @see Menu#updateTime
	 **/
	public static volatile SingularAttribute<Menu, Instant> updateTime;
	
	/**
	 * @see Menu#remark
	 **/
	public static volatile SingularAttribute<Menu, String> remark;
	
	/**
	 * @see Menu#parentId
	 **/
	public static volatile SingularAttribute<Menu, Long> parentId;
	
	/**
	 * @see Menu#path
	 **/
	public static volatile SingularAttribute<Menu, String> path;
	
	/**
	 * @see Menu#component
	 **/
	public static volatile SingularAttribute<Menu, String> component;
	
	/**
	 * @see Menu#createBy
	 **/
	public static volatile SingularAttribute<Menu, Long> createBy;
	
	/**
	 * @see Menu#createTime
	 **/
	public static volatile SingularAttribute<Menu, Instant> createTime;
	
	/**
	 * @see Menu#updateBy
	 **/
	public static volatile SingularAttribute<Menu, Long> updateBy;
	
	/**
	 * @see Menu#isFrame
	 **/
	public static volatile SingularAttribute<Menu, Integer> isFrame;
	
	/**
	 * @see Menu#menuType
	 **/
	public static volatile SingularAttribute<Menu, Character> menuType;
	
	/**
	 * @see Menu#perms
	 **/
	public static volatile SingularAttribute<Menu, String> perms;
	
	/**
	 * @see Menu#id
	 **/
	public static volatile SingularAttribute<Menu, Long> id;
	
	/**
	 * @see Menu
	 **/
	public static volatile EntityType<Menu> class_;
	
	/**
	 * @see Menu#status
	 **/
	public static volatile SingularAttribute<Menu, Character> status;

	public static final String VISIBLE = "visible";
	public static final String ICON = "icon";
	public static final String ORDER_NUM = "orderNum";
	public static final String MENU_NAME = "menuName";
	public static final String UPDATE_TIME = "updateTime";
	public static final String REMARK = "remark";
	public static final String PARENT_ID = "parentId";
	public static final String PATH = "path";
	public static final String COMPONENT = "component";
	public static final String CREATE_BY = "createBy";
	public static final String CREATE_TIME = "createTime";
	public static final String UPDATE_BY = "updateBy";
	public static final String IS_FRAME = "isFrame";
	public static final String MENU_TYPE = "menuType";
	public static final String PERMS = "perms";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

