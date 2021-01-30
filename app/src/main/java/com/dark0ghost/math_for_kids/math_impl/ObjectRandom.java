package com.dark0ghost.math_for_kids.math_impl;

import android.util.Log;

import java.util.Objects;

public class ObjectRandom {

    private int getAddress(){
        int hash = Objects.hash(new Object());
        Log.d( "Hash", String.valueOf(hash));
        return hash;
    }

    public int randomNumber(int begin, int end){
        int hash = getAddress();
        int difference = end - begin;
        int result = begin + (hash % difference);
        Log.d( "randomNumber", String.valueOf(result));
        return result;
    }

    public long randomNumber(long begin, long  end){
        int  hash = getAddress();
        long  difference = end - begin;
        long result = begin + (hash%difference);
        Log.d( "randomNumber", String.valueOf(result));
        return result;
    }

    public float randomNumber(float begin, float  end){
        int  hash = getAddress();
        float  difference = end - begin;
        return begin + (hash%difference);
    }

    public double randomNumber(double  begin, double   end){
        int hash = getAddress();
        double  difference =  (end - begin);
        return begin + (hash%difference);
    }


}
