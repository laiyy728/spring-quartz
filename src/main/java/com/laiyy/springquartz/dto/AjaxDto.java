package com.laiyy.springquartz.dto;

import java.util.Objects;

/**
 *
 * @author wh
 * @date 2018/1/14
 */
public class AjaxDto {

    private int code;

    private long count;

    private Object data;

    private String msg;

    @Override
    public String toString() {
        return "AjaxDto{" +
                "code=" + code +
                ", count=" + count +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AjaxDto ajaxDto = (AjaxDto) o;
        return code == ajaxDto.code &&
                count == ajaxDto.count &&
                Objects.equals(data, ajaxDto.data) &&
                Objects.equals(msg, ajaxDto.msg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, count, data, msg);
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
