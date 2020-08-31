package com.nowcoder.community.utils;

public interface CommunityConstant {

    int ACTIVATION_SUCCESS = 0;

    int ACTIVATION_REPEAT = 1;

    int ACTIVATION_FAILURE = 2;

    //默认状态的登录凭证的超时时间
    int DEFAULT_EXPIRED_SECONDS = 3600*12;

    //记住状态下的登录凭证的超时时间
    int REMEMBER_EXPIRED_SECOND = 3600*24*100;

    int ENTITY_TYPE_POST = 1;

    int ENTITY_TYPE_COMMENT = 2;

    int ENTITY_TYPE_USER = 3;

    String TOPIC_COMMENT = "comment";

    String TOPIC_LIKE = "like";

    String TOPIC_FOLLOW  = "follow";

    int SYSTEM_USER_ID = 1;

    String TOPIC_PUBLISH = "publish";

    String AUTHORITY_USER = "user";

    String AUTHORITY_ADMIN = "admin";

    String AUTHORITY_MODERATOR = "moderator";

    String TOPIC_DELETE = "delete";
}
