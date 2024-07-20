/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testit;

/**
 *
 * @author GAIUS
 */
public class Testing {
    
    public static void main(String[] args) {
        int end;
        int begin;
        String block3;
        String str="{1:F01FBNINGLAGCSF0066139469}{2:O940190225102251020FBNINGLAGCS885532N}{3:108PH147100218}{4:";
        
       begin = str.indexOf("{3:");
      if (begin >= 0)
      {
        end = str.indexOf("}}", begin + 3);
        block3 = str.substring(begin + 3, end + 2);
          System.out.println(block3);
      }
      
      
    }
    
}
