package com.dcstudio.flutter_push_plugin;

public enum PushIntent {
    TOKEN_INTENT_ACTION("TOKEN_INTENT_ACTION"),
    TOKEN("TOKEN"),
    TOKEN_ERROR("TOKEN_ERROR"),
    TOKEN_ERROR_DESC("TOKEN_ERROR_DESC"),
    UN_INIT("UN_INIT"),

    REMOTE_MESSAGE_NOTIFICATION_INTENT_ACTION("REMOTE_MESSAGE_NOTIFICATION_INTENT_ACTION"),
    NOTIFICATION_OPEN("NOTIFICATION_OPEN");

    PushIntent(String id) {
        this.id = id;
    }
    private String id;
    public String id() {
        return id;
    }
}
