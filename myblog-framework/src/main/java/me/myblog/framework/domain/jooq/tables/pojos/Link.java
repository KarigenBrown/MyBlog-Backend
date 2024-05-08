/*
 * This file is generated by jOOQ.
 */
package me.myblog.framework.domain.jooq.tables.pojos;


import java.time.LocalDateTime;

import me.myblog.framework.domain.jooq.tables.interfaces.ILink;


/**
 * 友链
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Link implements ILink {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String logo;
    private String description;
    private String address;
    private String status;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;

    public Link() {}

    public Link(ILink value) {
        this.id = value.getId();
        this.name = value.getName();
        this.logo = value.getLogo();
        this.description = value.getDescription();
        this.address = value.getAddress();
        this.status = value.getStatus();
        this.createBy = value.getCreateBy();
        this.createTime = value.getCreateTime();
        this.updateBy = value.getUpdateBy();
        this.updateTime = value.getUpdateTime();
    }

    public Link(
        Long id,
        String name,
        String logo,
        String description,
        String address,
        String status,
        Long createBy,
        LocalDateTime createTime,
        Long updateBy,
        LocalDateTime updateTime
    ) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.description = description;
        this.address = address;
        this.status = status;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    /**
     * Getter for <code>myblog.link.id</code>.
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>myblog.link.id</code>.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>myblog.link.name</code>.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>myblog.link.name</code>.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>myblog.link.logo</code>.
     */
    @Override
    public String getLogo() {
        return this.logo;
    }

    /**
     * Setter for <code>myblog.link.logo</code>.
     */
    @Override
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * Getter for <code>myblog.link.description</code>.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>myblog.link.description</code>.
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for <code>myblog.link.address</code>. 网站地址
     */
    @Override
    public String getAddress() {
        return this.address;
    }

    /**
     * Setter for <code>myblog.link.address</code>. 网站地址
     */
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for <code>myblog.link.status</code>. 审核状态
     * (0代表审核通过，1代表审核未通过，2代表未审核)
     */
    @Override
    public String getStatus() {
        return this.status;
    }

    /**
     * Setter for <code>myblog.link.status</code>. 审核状态
     * (0代表审核通过，1代表审核未通过，2代表未审核)
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter for <code>myblog.link.create_by</code>.
     */
    @Override
    public Long getCreateBy() {
        return this.createBy;
    }

    /**
     * Setter for <code>myblog.link.create_by</code>.
     */
    @Override
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * Getter for <code>myblog.link.create_time</code>.
     */
    @Override
    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    /**
     * Setter for <code>myblog.link.create_time</code>.
     */
    @Override
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * Getter for <code>myblog.link.update_by</code>.
     */
    @Override
    public Long getUpdateBy() {
        return this.updateBy;
    }

    /**
     * Setter for <code>myblog.link.update_by</code>.
     */
    @Override
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * Getter for <code>myblog.link.update_time</code>.
     */
    @Override
    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    /**
     * Setter for <code>myblog.link.update_time</code>.
     */
    @Override
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Link other = (Link) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.logo == null) {
            if (other.logo != null)
                return false;
        }
        else if (!this.logo.equals(other.logo))
            return false;
        if (this.description == null) {
            if (other.description != null)
                return false;
        }
        else if (!this.description.equals(other.description))
            return false;
        if (this.address == null) {
            if (other.address != null)
                return false;
        }
        else if (!this.address.equals(other.address))
            return false;
        if (this.status == null) {
            if (other.status != null)
                return false;
        }
        else if (!this.status.equals(other.status))
            return false;
        if (this.createBy == null) {
            if (other.createBy != null)
                return false;
        }
        else if (!this.createBy.equals(other.createBy))
            return false;
        if (this.createTime == null) {
            if (other.createTime != null)
                return false;
        }
        else if (!this.createTime.equals(other.createTime))
            return false;
        if (this.updateBy == null) {
            if (other.updateBy != null)
                return false;
        }
        else if (!this.updateBy.equals(other.updateBy))
            return false;
        if (this.updateTime == null) {
            if (other.updateTime != null)
                return false;
        }
        else if (!this.updateTime.equals(other.updateTime))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.logo == null) ? 0 : this.logo.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.address == null) ? 0 : this.address.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.createBy == null) ? 0 : this.createBy.hashCode());
        result = prime * result + ((this.createTime == null) ? 0 : this.createTime.hashCode());
        result = prime * result + ((this.updateBy == null) ? 0 : this.updateBy.hashCode());
        result = prime * result + ((this.updateTime == null) ? 0 : this.updateTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Link (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(logo);
        sb.append(", ").append(description);
        sb.append(", ").append(address);
        sb.append(", ").append(status);
        sb.append(", ").append(createBy);
        sb.append(", ").append(createTime);
        sb.append(", ").append(updateBy);
        sb.append(", ").append(updateTime);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ILink from) {
        setId(from.getId());
        setName(from.getName());
        setLogo(from.getLogo());
        setDescription(from.getDescription());
        setAddress(from.getAddress());
        setStatus(from.getStatus());
        setCreateBy(from.getCreateBy());
        setCreateTime(from.getCreateTime());
        setUpdateBy(from.getUpdateBy());
        setUpdateTime(from.getUpdateTime());
    }

    @Override
    public <E extends ILink> E into(E into) {
        into.from(this);
        return into;
    }
}
