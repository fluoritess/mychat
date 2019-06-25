package com.gabe.mychat.util;



import java.util.*;


public class FormatData {
    //格式化给前端的数据
    public  static Map<String, Boolean> webFormat(String a ){
        Map<String,Boolean> map = new HashMap<>();
        List<Map<String, Boolean>> list = new ArrayList<>();
        for (int i=0;i<a.length();i++) {
            String c = String.valueOf(a.charAt(i));
            if ("1".equals(c)) {
                if (i == 0) {
                    map.put(Constant.bulbactive1, true);
                }
                if (i == 1) {
                    map.put(Constant.bulbactive2, true);
                }
                if (i == 2) {
                    map.put(Constant.bulbactive3, true);
                }
                if (i == 3) {
                    map.put(Constant.hatactive1, true);
                }
                if (i == 4) {
                    map.put(Constant.hatactive2, true);
                }
                if (i == 5) {
                    map.put(Constant.hatactive3, true);
                }
                if (i == 6) {
                    map.put(Constant.hatactive4, true);

                }
                if (i == 7) {
                    map.put(Constant.windowactive1, true);

                }
                if (i == 8) {
                    map.put(Constant.windowactive2, true);

                }
                if (i == 9) {
                    map.put(Constant.fanactive1, true);

                }
                if (i == 10) {
                    map.put(Constant.fanactive2, true);

                }
                if (i == 11) {
                    map.put(Constant.fogActive, true);
                }
                if (i == 12) {
                    map.put(Constant.turboActive, true);

                }
            }
            if ("0".equals(c)) {
                if (i == 0) {
                    map.put(Constant.bulbactive1, false);

                }
                if (i == 1) {
                    map.put(Constant.bulbactive2, false);

                }
                if (i == 2) {
                    map.put(Constant.bulbactive3, false);

                }
                if (i == 3) {
                    map.put(Constant.hatactive1, false);

                }
                if (i == 4) {
                    map.put(Constant.hatactive2, false);

                }
                if (i == 5) {
                    map.put(Constant.hatactive3, false);

                }
                if (i == 6) {
                    map.put(Constant.hatactive4, false);

                }
                if (i == 7) {
                    map.put(Constant.windowactive1, false);

                }
                if (i == 8) {
                    map.put(Constant.windowactive2, false);

                }
                if (i == 9) {
                    map.put(Constant.fanactive1, false);

                }
                if (i == 10) {
                    map.put(Constant.fanactive2, false);

                }
                if (i == 11) {
                    map.put(Constant.fogActive, false);

                }
                if (i == 12) {
                    map.put(Constant.turboActive, false);

                }
            }
        }


        return map;
    }
    //格式化前端来给数据库的数据

    public  static String webtoMysqlFormat(ControlStatus controlStatus ){
        String s="";
        if(controlStatus.getBulbactive1()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if (controlStatus.getBulbactive2()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if(controlStatus.getBulbactive3()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if(controlStatus.getHatactive1()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if(controlStatus.getHatactive2()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if(controlStatus.getHatactive3()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if(controlStatus.getHatactive4()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if(controlStatus.getWindowactive1()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if(controlStatus.getWindowactive2()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if(controlStatus.getFanactive1()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if(controlStatus.getFanactive2()==true){
            s+="1";
        }
        else {
            s+="0";
        }
        if(controlStatus.getFogActive()==true){
            s+="1";
        }
        else {
            s+="0";
        }

        if(controlStatus.getTurboActive()==true){
            s+="1";
        }
        else {
            s+="0";
        }

        return s;
    }

    public  static String webtoDianPianJi(String a){
        String s="33#S02D#0123456789#";
        for (int i=0;i<a.length();i++) {
            String c = String.valueOf(a.charAt(i));
            s=s+"#"+c;
        }
        return s;
    }
    //给前端实时数据格式化
    public static Map<String,Object> serviceToWeb(String s, Date date){
        Map<String,Object> map= new HashMap<>();
        String[] value = s.split("#");
        for (int i=0;i<value.length;i++){
            if (i==4){
                map.put(Constant.rootT,value[i]);
            }
            if (i==5){
                map.put(Constant.soilSurFaceT,value[i]);
            }
            if (i==6){
                map.put(Constant.rootH,value[i]);
            }
            if (i==7){
                map.put(Constant.soilSurFaceH,value[i]);
            }
            if (i==8){
                map.put(Constant.greenHouseT,value[i]);
            }
            if (i==9){
                map.put(Constant.greenHouseH,value[i]);
            }
            if (i==12){
                map.put(Constant.ventilationV,value[i]);
            }
            if(i==11){
                map.put(Constant.co2C,value[i]);
            }
            if(i==10){
                map.put(Constant.greenhousePressure,value[i]);
            }
        }
        map.put("time",date);
        return map;
    }

    public static Map<String,Integer> getUpdateAddress(ControlStatus controlStatus){
        Map<String,Integer> map=new HashMap<>();
        if(controlStatus.getBulbactive1()!=null){
            if(controlStatus.getBulbactive1()==true){
                map.put("typeId",1);
                map.put("stateId",1);
            }
            else if(controlStatus.getBulbactive1()==false){
                map.put("typeId",1);
                map.put("stateId",2);

            }
        }
        if(controlStatus.getBulbactive2()!=null) {
            if (controlStatus.getBulbactive2() == true) {
                map.put("typeId", 1);
                map.put("stateId", 1);
            } else if (controlStatus.getBulbactive2() == false) {

                map.put("typeId", 1);
                map.put("stateId", 2);
            }
        }
        if(controlStatus.getBulbactive3()!=null) {
            if (controlStatus.getBulbactive3() == true) {
                map.put("typeId", 1);
                map.put("stateId", 1);
            } else if (controlStatus.getBulbactive3() == false) {

                map.put("typeId", 1);
                map.put("stateId", 2);
            }
        }
        if(controlStatus.getHatactive1()!=null) {
            if (controlStatus.getHatactive1() == true) {
                map.put("typeId", 2);
                map.put("stateId", 1);
            } else if (controlStatus.getHatactive1() == false) {

                map.put("typeId", 2);
                map.put("stateId", 2);
            }
        }
        if(controlStatus.getHatactive2()!=null) {
            if (controlStatus.getHatactive2() == true) {
                map.put("typeId", 2);
                map.put("stateId", 1);

            } else if (controlStatus.getHatactive2() == false) {

                map.put("typeId", 2);
                map.put("stateId", 2);
            }
        }
        if(controlStatus.getHatactive3()!=null) {
            if (controlStatus.getHatactive3() == true) {
                map.put("typeId", 2);
                map.put("stateId", 1);

            } else if (controlStatus.getHatactive3() == false) {

                map.put("typeId", 2);
                map.put("stateId", 2);
            }
        }
        if(controlStatus.getHatactive4()!=null) {
            if (controlStatus.getHatactive4() == true) {
                map.put("typeId", 2);
                map.put("stateId", 1);

            } else if (controlStatus.getHatactive4() == false) {

                map.put("typeId", 2);
                map.put("stateId", 2);
            }
        }
        if(controlStatus.getWindowactive1()!=null) {
            if (controlStatus.getWindowactive1() == true) {
                map.put("typeId", 3);
                map.put("stateId", 1);

            } else if (controlStatus.getWindowactive1() == false) {
                map.put("typeId", 3);
                map.put("stateId", 2);

            }
        }
        if(controlStatus.getWindowactive2()!=null) {
            if (controlStatus.getWindowactive2() == true) {
                map.put("typeId", 3);
                map.put("stateId", 1);

            } else if (controlStatus.getWindowactive2() == false) {
                map.put("typeId", 3);
                map.put("stateId", 2);

            }
        }
        if(controlStatus.getFanactive1()!=null) {
            if (controlStatus.getFanactive1() == true) {
                map.put("typeId", 3);
                map.put("stateId", 1);

            } else if (controlStatus.getFanactive1() == false) {
                map.put("typeId", 3);
                map.put("stateId", 2);

            }
        }
        if(controlStatus.getFanactive2()!=null) {
            if (controlStatus.getFanactive2() == true) {
                map.put("typeId", 3);
                map.put("stateId", 1);

            } else if (controlStatus.getFanactive2() == false) {
                map.put("typeId", 3);
                map.put("stateId", 2);

            }
        }
        if(controlStatus.getFogActive()!=null) {
            if (controlStatus.getFogActive() == true) {
                map.put("typeId", 4);
                map.put("stateId", 1);

            } else if (controlStatus.getFogActive() == false) {
                map.put("typeId", 4);
                map.put("stateId", 2);

            }

        }
        if(controlStatus.getTurboActive()!=null) {
            if (controlStatus.getTurboActive() == true) {
                map.put("typeId", 5);
                map.put("stateId", 1);

            } else if (controlStatus.getTurboActive() == false) {
                map.put("typeId", 5);
                map.put("stateId", 2);
            }
        }
        return map;
    }
}
