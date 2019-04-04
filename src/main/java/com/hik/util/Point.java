package com.hik.util;

import java.util.ArrayList;  

/**  
 *  <span style="font-family: Arial; font-size: 14px; line-height: 26px;">����ߵ�һЩ���÷���</span><br/>  
 *   
 * @author liuZhiwei  
 * 2016��8��6�� ����3:48:38  
 */  
public class Point {  
      
    /**  
     *  �Ƿ��� ���<br/>  
     *  ����Ϊ�ĸ��������  
     * @param px1  
     * @param py1  
     * @param px2  
     * @param py2  
     * @param px3  
     * @param py3  
     * @param px4  
     * @param py4  
     * @return    
     */  
    public boolean isIntersect ( double px1 , double py1 , double px2 , double py2 , double px3 , double py3 , double px4 ,    
            double py4 )    
    {    
        boolean flag = false;    
        double d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3);    
        if ( d != 0 )    
        {    
            double r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3)) / d;    
            double s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1)) / d;    
            if ( (r >= 0) && (r <= 1) && (s >= 0) && (s <= 1) )    
            {    
                flag = true;    
            }    
        }    
        return flag;    
    }   
    /**  
     *  Ŀ����Ƿ���Ŀ����ϱ���<br/>  
     *    
     * @param px0 Ŀ���ľ�������  
     * @param py0 Ŀ����γ������  
     * @param px1 Ŀ���ߵ����(�յ�)��������  
     * @param py1 Ŀ���ߵ����(�յ�)γ������  
     * @param px2 Ŀ���ߵ��յ�(���)��������  
     * @param py2 Ŀ���ߵ��յ�(���)γ������  
     * @return  
     */  
    public boolean isPointOnLine ( double px0 , double py0 , double px1 , double py1 , double px2 , double py2 )    
    {    
        boolean flag = false;    
        double ESP = 1e-9;//����С������  
        if ( (Math.abs(Multiply(px0, py0, px1, py1, px2, py2)) < ESP) && ((px0 - px1) * (px0 - px2) <= 0)    
                && ((py0 - py1) * (py0 - py2) <= 0) )    
        {    
            flag = true;    
        }    
        return flag;    
    }   
  
    public double Multiply ( double px0 , double py0 , double px1 , double py1 , double px2 , double py2 )    
    {    
        return ((px1 - px0) * (py2 - py0) - (px2 - px0) * (py1 - py0));    
    }  
  
    /**  
     * �ж�Ŀ����Ƿ��ڶ������(�ɶ�������)<br/>  
     *   
     * @param px Ŀ���ľ�������  
     * @param py Ŀ����γ������  
     * @param polygonXA ����εľ������꼯��  
     * @param polygonYA ����ε�γ�����꼯��  
     * @return  
     */  
    public boolean isPointInPolygon ( double px , double py , ArrayList<Double> polygonXA , ArrayList<Double> polygonYA )    
    {    
        boolean isInside = false;    
        double ESP = 1e-9;    
        int count = 0;    
        double linePoint1x;    
        double linePoint1y;    
        double linePoint2x = 180;    
        double linePoint2y;    
  
        linePoint1x = px;    
        linePoint1y = py;    
        linePoint2y = py;    
  
        for (int i = 0; i < polygonXA.size() - 1; i++)    
        {    
            double cx1 = polygonXA.get(i);    
            double cy1 = polygonYA.get(i);    
            double cx2 = polygonXA.get(i + 1);    
            double cy2 = polygonYA.get(i + 1);   
            //���Ŀ������κ�һ������  
            if ( isPointOnLine(px, py, cx1, cy1, cx2, cy2) )    
            {    
                return true;    
            }  
            //����߶εĳ�������С(������)��ô������ʵ�����غϵģ������Թ���һ���߶�  
            if ( Math.abs(cy2 - cy1) < ESP )    
            {    
                continue;    
            }    
            //��һ�����Ƿ�����Ŀ���Ϊ����������ƽ��γ����  
            if ( isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x, linePoint2y) )    
            {    
                //�ڶ������ڵ�һ�����·�,�������γ��Ϊ��(��Сγ��)  
                if ( cy1 > cy2 )    
                    count++;    
            }  
            //�ڶ������Ƿ�����Ŀ���Ϊ����������ƽ��γ����  
            else if ( isPointOnLine(cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y) )    
            {    
                //�ڶ������ڵ�һ�����Ϸ�,��������(�ϼ��򱱼�)γ��Ϊ90(���γ��)  
                if ( cy2 > cy1 )    
                    count++;    
            }  
            //��������ɵ��߶��Ƿ����Ŀ���Ϊ����������ƽ��γ�����ཻ  
            else if ( isIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y) )    
            {    
                count++;    
            }    
        }    
        if ( count % 2 == 1 )    
        {    
            isInside = true;    
        }    
  
        return isInside;    
    }    
}  