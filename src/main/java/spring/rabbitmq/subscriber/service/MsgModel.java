package spring.rabbitmq.subscriber.service;

public class MsgModel {
    private String msg;

    public MsgModel() {
    }

    public MsgModel(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
