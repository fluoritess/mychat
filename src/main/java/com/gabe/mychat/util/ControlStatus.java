package com.gabe.mychat.util;


public class ControlStatus {
    public Boolean turboActive;
    public Boolean  windowactive1;
    public Boolean    fanactive1;
    public Boolean    bulbactive3;
    public Boolean     fanactive2;
    public Boolean      bulbactive2;
    public Boolean      bulbactive1;
    public Boolean     windowactive2;
    public Boolean     fogActive;
    public Boolean   hatactive1;
    public Boolean   hatactive2;
    public Boolean   hatactive3;
    public Boolean    hatactive4;
    public  ControlStatus update;
    public ControlStatus getUpdate() {
        return update;
    }

    public void setUpdate(ControlStatus update) {
        this.update = update;
    }


    public Boolean getTurboActive() {
        return turboActive;
    }

    public void setTurboActive(Boolean turboActive) {
        this.turboActive = turboActive;
    }

    public Boolean getWindowactive1() {
        return windowactive1;
    }

    public void setWindowactive1(Boolean windowactive1) {
        this.windowactive1 = windowactive1;
    }

    public Boolean getFanactive1() {
        return fanactive1;
    }

    public void setFanactive1(Boolean fanactive1) {
        this.fanactive1 = fanactive1;
    }

    public Boolean getBulbactive3() {
        return bulbactive3;
    }

    public void setBulbactive3(Boolean bulbactive3) {
        this.bulbactive3 = bulbactive3;
    }

    public Boolean getFanactive2() {
        return fanactive2;
    }

    public void setFanactive2(Boolean fanactive2) {
        this.fanactive2 = fanactive2;
    }

    public Boolean getBulbactive2() {
        return bulbactive2;
    }

    public void setBulbactive2(Boolean bulbactive2) {
        this.bulbactive2 = bulbactive2;
    }

    public Boolean getBulbactive1() {
        return bulbactive1;
    }

    public void setBulbactive1(Boolean bulbactive1) {
        this.bulbactive1 = bulbactive1;
    }

    public Boolean getWindowactive2() {
        return windowactive2;
    }

    public void setWindowactive2(Boolean windowactive2) {
        this.windowactive2 = windowactive2;
    }

    public Boolean getFogActive() {
        return fogActive;
    }

    public void setFogActive(Boolean fogActive) {
        this.fogActive = fogActive;
    }

    public Boolean getHatactive1() {
        return hatactive1;
    }

    public void setHatactive1(Boolean hatactive1) {
        this.hatactive1 = hatactive1;
    }

    public Boolean getHatactive2() {
        return hatactive2;
    }

    public void setHatactive2(Boolean hatactive2) {
        this.hatactive2 = hatactive2;
    }

    public Boolean getHatactive3() {
        return hatactive3;
    }

    public void setHatactive3(Boolean hatactive3) {
        this.hatactive3 = hatactive3;
    }

    public Boolean getHatactive4() {
        return hatactive4;
    }

    public void setHatactive4(Boolean hatactive4) {
        this.hatactive4 = hatactive4;
    }

    @Override
    public String toString() {
        return "ControlStatus{" +
                "turboActive=" + turboActive +
                ", windowactive1=" + windowactive1 +
                ", fanactive1=" + fanactive1 +
                ", bulbactive3=" + bulbactive3 +
                ", fanactive2=" + fanactive2 +
                ", bulbactive2=" + bulbactive2 +
                ", bulbactive1=" + bulbactive1 +
                ", windowactive2=" + windowactive2 +
                ", fogActive=" + fogActive +
                ", hatactive1=" + hatactive1 +
                ", hatactive2=" + hatactive2 +
                ", hatactive3=" + hatactive3 +
                ", hatactive4=" + hatactive4 +
                '}';
    }
}
