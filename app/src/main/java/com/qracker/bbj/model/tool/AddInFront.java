package com.qracker.bbj.model.tool;

/**
 * @program: BBJ
 * @description: 实现在arraylist首位添加元素
 * @author: HeMu-qracker
 * @create: 2020-01-13 01:16
 **/
import java.util.ArrayList;

public interface AddInFront<T> {
    public ArrayList<T> addInFront(ArrayList<T> arrayList,T t);
}