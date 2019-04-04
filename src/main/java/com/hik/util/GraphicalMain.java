package com.hik.util;

import java.util.ArrayList;
import java.util.List;

public class GraphicalMain {  
    public static void main(String[] args) {  
        //(113.944421,22.528841) (113.94629,22.529208)  
        //isPointInPolygon(area.createDouble(),area.createDouble()); (114.082402,22.550271) 114.075323,22.543528)
        Area a1=new Area(113.941853,22.530777);  
        Area a3=new Area(113.94788,22.527597);  
        Area a2=new Area(113.940487,22.527789);  
        Area a4=new Area(113.947925,22.530618);  
        Area a5=new Area(113.941772,22.530727);  
        List<Area> areas=new ArrayList<Area>();  
        areas.add(a1);  
        areas.add(a2);  
        areas.add(a3);
        areas.add(a4);  
        areas.add(a5); 
        boolean str = isPointInPolygon(113.941853,21.530777,areas);  
        System.out.println("==>:"+str);
          
    }  
    public static Boolean isPointInPolygon( double px , double py,List<Area> areas){
 
        ArrayList<Double> polygonXA = new ArrayList<Double>();    
        ArrayList<Double> polygonYA = new ArrayList<Double>();   
        for(int i=0;i<areas.size();i++){  
            Area area=areas.get(i);  
            polygonXA.add(area.getPx());  
            polygonYA.add(area.getPy());  
        }  
        Point point=new Point();  
        Boolean flag= point.isPointInPolygon(px, py, polygonXA, polygonYA);  
        StringBuffer buffer=new StringBuffer();  
        buffer.append("目标点").append("(").append(px).append(",").append(py).append(")").append("\n");  
        buffer.append(flag?"在":"不在").append("\t").append("由\n");  
        for(int i=0;i<areas.size();i++){  
            Area area=areas.get(i);  
            buffer.append(area.getPoint()).append("; ");  
            //buffer.append("第"+i+"个点"+area.getPoint()).append("\n");  
            System.out.println("第"+(i+1)+"个点"+area.getPoint());  
        }  
        StringBuffer sb=new StringBuffer();  
        sb.append("目标点:").append("(").append(px).append(",").append(py).append(")").append("\n");  
        System.out.println(sb);  
        buffer.append(areas.size()).append("个点组成的").append(areas.size()).append("边行内");  
        System.out.println(buffer.toString());  
        return  flag;  
    }  
} 