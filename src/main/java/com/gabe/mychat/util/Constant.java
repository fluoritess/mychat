package com.gabe.mychat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface Constant {
    Map<String,Object> aotoControlMap=new ConcurrentHashMap<>();
    Map<String,Object> map=new HashMap<>();
    //设备名称
    public static final String windowactive1="windowactive1";
    public static final String windowactive2="windowactive2";
    public static final String bulbactive1="bulbactive1";
    public static final String bulbactive2 = "bulbactive2";
    public static final String bulbactive3 = "bulbactive3";
    public static final String fanactive1 = "fanactive1";
    public static final String fanactive2 = "fanactive2";
    public static final String turboActive = "turboActive";
    public static final String hatactive1 = "hatactive1";
    public static final String hatactive2 = "hatactive2";
    public static final String hatactive3 = "hatactive3";
    public static final String hatactive4 = "hatactive4";
    public static final String fogActive = "fogActive";
    //协议数据段开始的地方
    public static final int start_data = 4;
    //实时数据名称
    public static final String  rootT="rootT";
    public static final String   rootH="rootH";
    public static final String soilSurFaceT="soilSurFaceT";
    public static final String soilSurFaceH="soilSurFaceH";
    public static final String  greenHouseT="greenHouseT";
    public static final String  greenHouseH="greenHouseH";
    public static final String  ventilationV="ventilationV";
    public static final String  co2C="co2C";
    public static final String  greenhousePressure="greenhousePressure";


}
