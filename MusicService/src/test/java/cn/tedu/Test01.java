package cn.tedu;

import java.util.Arrays;

/**
 *
 * Unit test for simple App.
 *
 */

public class Test01 {
    static String [] arr0 = new String[0];
    public static void main(String[] args) {
        String[] arr = new String[0];
        arr = arr_add(arr, "1");

    }

    private static String[] arr_add(String[] arr, String str) {
        arr0 = Arrays.copyOf(arr, arr.length + 1);
        arr0[arr0.length - 1] = str;
        return arr0;
    }

    private static String[] arr_remove(String[] arr, int index) {
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            } else {
                arr_add(arr0,arr[i]);
            }
        }
        return arr0;
    }
    private static String arr_get(String[] arr,int index){
        for (int i = 0 ;i<arr.length;i++){
            if (i==index){
                return arr[i];
            }
        }
        return null;
    }

    private static void arr_set(String [] arr,int index ,String str){
        arr[index] = str;
    }

    private static int arr_indexof(String [] arr,String str){
        for (int i = 0 ;i<arr.length;i++){
            if (arr[i].equals(str)){
                return i;
            }
            else return -1;
        }
        return -1;
    }
    private static boolean arr_isEmpty(String [] arr){
        if (arr.length==0){
            return true;
        }else
        return false;
    }
    private static int arr_size(String [] arr){
        return arr.length;
    }

    private static String arr_toString(String [] arr){
        return Arrays.toString(arr);
    }
}