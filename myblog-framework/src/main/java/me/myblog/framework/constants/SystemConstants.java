package me.myblog.framework.constants;

public class SystemConstants {
    private SystemConstants() {
    }

    public static final Integer HOT_ARTICLE_PAGE_NUMBER = 1;

    public static final Integer HOT_ARTICLE_PAGE_SIZE = 10;

    /**
     * 文章是草稿
     */
    public static final Integer ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常分布状态
     */
    public static final String ARTICLE_STATUS_NORMAL = "0";

    public static final String CATEGORY_STATUS_NORMAL = "0";

    public static final String MENU_STATUS_NORMAL = "0";

    /**
     * 链接审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";

    public static final Long ROOT_COMMENT = -1L;

    public static final String ARTICLE_COMMENT = "0";
    public static final String LINK_COMMENT = "1";

    public static final String USER_LOGIN_KEY_PREFIX = "loginUser:";

    public static final String ADMINISTRATOR_LOGIN_KEY_PREFIX = "loginAdministrator:";

    public static final String VIEW_COUNT_KEY = "article:viewCount";

    public static final String MENU = "C";

    public static final String BUTTON = "F";

    public static final Long ADMINISTRATOR = 1L;

    public static final Long MENU_TREE_ROOT = 0L;

    public static final String NORMAL_ROLE_STATUS = "0";
}
