package com.teja.classfieldsmap;

import java.lang.reflect.Field;
import java.util.*;

public class ClassFieldsMap {

    private static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS = new HashMap<>();

    static {
        PRIMITIVES_TO_WRAPPERS.put(boolean.class, Boolean.class);
        PRIMITIVES_TO_WRAPPERS.put(byte.class, Byte.class);
        PRIMITIVES_TO_WRAPPERS.put(char.class, Character.class);
        PRIMITIVES_TO_WRAPPERS.put(double.class, Double.class);
        PRIMITIVES_TO_WRAPPERS.put(float.class, Float.class);
        PRIMITIVES_TO_WRAPPERS.put(int.class, Integer.class);
        PRIMITIVES_TO_WRAPPERS.put(long.class, Long.class);
        PRIMITIVES_TO_WRAPPERS.put(short.class, Short.class);
        PRIMITIVES_TO_WRAPPERS.put(void.class, Void.class);
    }

    private Set<String> keys;
    private Map<String, Object> originalMap;
    private Map<String, Class> keyTypeMap;
    private Map<String, String> caseInsensitiveKeyToOriginalKeyMap;
    private boolean ignoreCase;

    public ClassFieldsMap(Class classType) {
        this(classType, false);
    }

    public ClassFieldsMap(Class classType, boolean caseInsensitiveKeys) {
        keys = new HashSet<>();
        originalMap = new HashMap<>();
        this.ignoreCase = caseInsensitiveKeys;
        keyTypeMap = new HashMap<>();
        if (caseInsensitiveKeys) {
            caseInsensitiveKeyToOriginalKeyMap = new HashMap<>();
        }
        addKeys(classType);
    }

    @Override
    public String toString() {
        return originalMap.toString();
    }

    private Class<?> getValueClass(Class<?> c) {
        return c.isPrimitive() ? PRIMITIVES_TO_WRAPPERS.get(c) : c;
    }

    private void addKeys(Class type) {
        Field[] declaredFields = type.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            Class<?> fieldType = declaredField.getType();
            if (ignoreCase) {
                caseInsensitiveKeyToOriginalKeyMap.put(fieldName.toLowerCase(),fieldName);
            }
            keyTypeMap.put(fieldName, fieldType);
            keys.add(fieldName);
        }
    }

    /******************************************
     ******************************************
     ******* Basic Methods of map ********
     ******************************************
     ******************************************/

    private String getOriginalKey(String key) {
        return ignoreCase ? caseInsensitiveKeyToOriginalKeyMap.getOrDefault(key.toLowerCase(), key) : key;
    }

    public int size() {
        return originalMap.size();
    }

    public boolean isEmpty() {
        return originalMap.isEmpty();
    }

    public boolean containsKey(String key) {
        return originalMap.containsKey(getOriginalKey(key));
    }

    public boolean containsValue(Object value) {
        return originalMap.containsValue(value);
    }

    public Object get(String key) {
        return originalMap.get(getOriginalKey(key));
    }

    public Object put(String key, Object value) throws KeyNotValidException, InvalidValueException {
        key = getOriginalKey(key);
        if (!keyIsValid(key)) {
            throw new KeyNotValidException(String.format("field [%s] is not part of class, available fields ", key) + keys);
        }
        isValidValueType(key, value);
        return originalMap.put(key, value);
    }

    /***********************************************************************************
     ************************** Validators Begin ***************************************
     ***********************************************************************************/

    private void isValidValueType(String key, Object value) throws InvalidValueException {
        Class<?> fieldType = keyTypeMap.get(key);
        Class<?> valueClass = value.getClass();
        if (fieldType.isPrimitive()) {
            fieldType = getValueClass(fieldType);
        }
        if (valueClass.isPrimitive()) {
            valueClass = getValueClass(valueClass);
        }

        if (valueClass != fieldType) {
            throw new InvalidValueException(String.format("value is not field class type expected type [%s] but found [%s]",
                    fieldType.getName(), valueClass.getName()));
        }
    }

    private boolean keyIsValid(String key) {
        return keys.contains(key);
    }

    /***********************************************************************************
     **************************** Validators End ***************************************
     ***********************************************************************************/

    public Object remove(String key) {
        return originalMap.remove(getOriginalKey(key));
    }

    public void putAll(Map<? extends String, ?> m) throws KeyNotValidException, InvalidValueException {
        for (Map.Entry<? extends String, ?> entry : m.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        originalMap.clear();
    }

    public Set<String> keySet() {
        return originalMap.keySet();
    }

    public Collection<Object> values() {
        return originalMap.values();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return originalMap.entrySet();
    }
}
