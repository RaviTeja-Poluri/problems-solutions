package com.teja.classfieldmap.test;

import com.teja.classfieldsmap.ClassFieldsMap;
import com.teja.classfieldsmap.InvalidValueException;
import com.teja.classfieldsmap.KeyNotValidException;

public class Main {

    public static void main(String[] args) throws KeyNotValidException, InvalidValueException {
        ClassFieldsMap m = new ClassFieldsMap(Model.class);
        System.out.println(m.put("fullName","teja"));
        System.out.println(m.put("age",15L));
        System.out.println(m);

        ClassFieldsMap m2 = new ClassFieldsMap(Model.class,true);
        System.out.println(m2.put("LlnAmE","teja"));
        System.out.println(m2.put("aGe",15L));
        System.out.println(m2);
        System.out.println(m2.containsKey("name"));
    }
}
