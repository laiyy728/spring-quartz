package com.laiyy.springquartz.dto;

import java.util.Objects;

/**
 *
 * @author wh
 * @date 2018/1/14
 * @descritpion 全局统一返回值数据结构
 */
public class AjaxDto {

    private int code;

    private long count;

    private Object data;

    private String msg;

    private String uri;

    @Override
    public String toString() {
        return "AjaxDto{" +
                "code=" + code +
                ", count=" + count +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", uri='" + uri + '\'' +
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
                Objects.equals(msg, ajaxDto.msg) &&
                Objects.equals(uri, ajaxDto.uri);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, count, data, msg, uri);
    }

    public String getUri() {

        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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
