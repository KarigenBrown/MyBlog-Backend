/*
 * This file is generated by jOOQ.
 */
package me.myblog.framework.domain.jooq.tables.interfaces;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 用户表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IUser extends Serializable {

    /**
     * Setter for <code>myblog.user.id</code>. 主键
     */
    public void setId(Long value);

    /**
     * Getter for <code>myblog.user.id</code>. 主键
     */
    public Long getId();

    /**
     * Setter for <code>myblog.user.user_name</code>. 用户名
     */
    public void setUserName(String value);

    /**
     * Getter for <code>myblog.user.user_name</code>. 用户名
     */
    public String getUserName();

    /**
     * Setter for <code>myblog.user.nick_name</code>. 昵称
     */
    public void setNickName(String value);

    /**
     * Getter for <code>myblog.user.nick_name</code>. 昵称
     */
    public String getNickName();

    /**
     * Setter for <code>myblog.user.password</code>. 密码
     */
    public void setPassword(String value);

    /**
     * Getter for <code>myblog.user.password</code>. 密码
     */
    public String getPassword();

    /**
     * Setter for <code>myblog.user.type</code>. 用户类型（0代表普通用户，1代表管理员）
     */
    public void setType(String value);

    /**
     * Getter for <code>myblog.user.type</code>. 用户类型（0代表普通用户，1代表管理员）
     */
    public String getType();

    /**
     * Setter for <code>myblog.user.status</code>. 账号状态（0正常，1停用）
     */
    public void setStatus(String value);

    /**
     * Getter for <code>myblog.user.status</code>. 账号状态（0正常，1停用）
     */
    public String getStatus();

    /**
     * Setter for <code>myblog.user.email</code>. 邮箱
     */
    public void setEmail(String value);

    /**
     * Getter for <code>myblog.user.email</code>. 邮箱
     */
    public String getEmail();

    /**
     * Setter for <code>myblog.user.phonenumber</code>. 手机号
     */
    public void setPhonenumber(String value);

    /**
     * Getter for <code>myblog.user.phonenumber</code>. 手机号
     */
    public String getPhonenumber();

    /**
     * Setter for <code>myblog.user.sex</code>. 用户性别（0男，1女，2未知）
     */
    public void setSex(String value);

    /**
     * Getter for <code>myblog.user.sex</code>. 用户性别（0男，1女，2未知）
     */
    public String getSex();

    /**
     * Setter for <code>myblog.user.avatar</code>. 头像
     */
    public void setAvatar(String value);

    /**
     * Getter for <code>myblog.user.avatar</code>. 头像
     */
    public String getAvatar();

    /**
     * Setter for <code>myblog.user.create_by</code>. 创建人的用户id
     */
    public void setCreateBy(Long value);

    /**
     * Getter for <code>myblog.user.create_by</code>. 创建人的用户id
     */
    public Long getCreateBy();

    /**
     * Setter for <code>myblog.user.create_time</code>. 创建时间
     */
    public void setCreateTime(LocalDateTime value);

    /**
     * Getter for <code>myblog.user.create_time</code>. 创建时间
     */
    public LocalDateTime getCreateTime();

    /**
     * Setter for <code>myblog.user.update_by</code>. 更新人
     */
    public void setUpdateBy(Long value);

    /**
     * Getter for <code>myblog.user.update_by</code>. 更新人
     */
    public Long getUpdateBy();

    /**
     * Setter for <code>myblog.user.update_time</code>. 更新时间
     */
    public void setUpdateTime(LocalDateTime value);

    /**
     * Getter for <code>myblog.user.update_time</code>. 更新时间
     */
    public LocalDateTime getUpdateTime();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IUser
     */
    public void from(IUser from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IUser
     */
    public <E extends IUser> E into(E into);
}
