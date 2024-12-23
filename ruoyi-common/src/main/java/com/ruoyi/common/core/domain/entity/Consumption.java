package com.ruoyi.common.core.domain.entity;

public class Consumption {
    private String openid;
    private String consumption;
    private String step;
    private String running;
    private String hiking;
    private String cycling;
    private String jumping;
    private String swimming;
    private String others;
    private String timestamp;

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getHiking() {
        return hiking;
    }

    public void setHiking(String hiking) {
        this.hiking = hiking;
    }

    public String getRunning() {
        return running;
    }

    public void setRunning(String running) {
        this.running = running;
    }

    public String getCycling() {
        return cycling;
    }

    public void setCycling(String cycling) {
        this.cycling = cycling;
    }

    public String getSwimming() {
        return swimming;
    }

    public void setSwimming(String swimming) {
        this.swimming = swimming;
    }

    public String getJumping() {
        return jumping;
    }

    public void setJumping(String jumping) {
        this.jumping = jumping;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
