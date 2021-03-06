package com.hyit.www.enums;

/**
 * @Author LiJun
 * @Date 2020/2/18 21:02
 * @Description: 用户可用状态信息
 */

public enum PersonInfoStatusEnum {

    ALLOW(1, "允许"), NOT_ALLOW(0, "不允许");
    private int state;
    private String stateInfo;

    private PersonInfoStatusEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    /**根据传入的state值返回相应的状态值*/
    public static PersonInfoStatusEnum stateOf(int index) {
        for (PersonInfoStatusEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}

