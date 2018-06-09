package com.laiyy.springquartz.dto;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author wh
 * @date 2018/1/14
 */
//@ApiModel(value = "ajaxDto", description = "接口统一返回值")
public class AjaxDto {
//    @ApiModelProperty(name = "result", value = "操作编码， 200 成功")
    private int result;
//    @ApiModelProperty(name = "msg", value = "操作反馈描述")
    private String msg;
//    @ApiModelProperty(name = "obj", value = "操作反馈对象")
    private Object obj;

    @Override
    public String toString() {
        return "AjaxDto{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public AjaxDto() {
    }

    public AjaxDto(int result, String msg, Object obj) {
        this.result = result;
        this.msg = msg;
        this.obj = obj;
    }

    public AjaxDto(int result) {
        this.result = result;
    }

    public AjaxDto(int result, String msg) {
        this.result = result;
        this.msg = msg;
    }
}
