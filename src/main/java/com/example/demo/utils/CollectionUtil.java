package com.example.demo.utils;

import java.util.*;

public class CollectionUtil {

    /**
     * 两个集合是否相等
     *
     * @param aCollection
     * @param bCollection
     * @param <T>
     * @return
     */
    public static <T> boolean isEquals(Collection<T> aCollection, Collection<T> bCollection) {
        if (isEmpty(aCollection) || isEmpty(bCollection)) {
            return false;
        }
        if (aCollection.size() != bCollection.size()) {
            return false;
        }
        return aCollection.containsAll(bCollection);
    }

    /**
     * 获得笛卡尔积
     * 比如[[a1,a2,a3],[b1,b2,b3],[c1,c2,c3]]计算结果为[[a1,b1,c1],[a1,b1,c2]....]
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> descartes(List<List<T>> list) {
        List<List<T>> result = new ArrayList<>();
        descartes(list, result, 0, new ArrayList<>());
        return result;
    }

    public static boolean exists(List<Integer> list, List<List<Integer>> listAll) {
        for (List<Integer> a : listAll) {
            if (CollectionUtil.isEquals(a, list)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获得笛卡尔积
     *
     * @param dimvalue
     * @param result
     * @param layer
     * @param curList
     */
    private static <T> void descartes(List<List<T>> dimvalue, List<List<T>> result, int layer, List<T> curList) {
        if (layer < dimvalue.size() - 1) {
            if (dimvalue.get(layer).size() == 0) {
                descartes(dimvalue, result, layer + 1, curList);
            } else {
                for (int i = 0; i < dimvalue.get(layer).size(); i++) {
                    List<T> list = new ArrayList<>(curList);
                    list.add(dimvalue.get(layer).get(i));
                    descartes(dimvalue, result, layer + 1, list);
                }
            }
        } else if (layer == dimvalue.size() - 1) {
            if (dimvalue.get(layer).size() == 0) {
                result.add(curList);
            } else {
                for (int i = 0; i < dimvalue.get(layer).size(); i++) {
                    List<T> list = new ArrayList<>(curList);
                    list.add(dimvalue.get(layer).get(i));
                    result.add(list);
                }
            }
        }
    }

    public static void main(String args[]) {
//        List<Integer> listA = Arrays.asList(1, 2);
//        List<Integer> listB = Arrays.asList(2, 1, 3);
//        boolean result = isEquals(listA, listB);
        List<List<String>> list = Arrays.asList(
                Arrays.asList("a1", "a2", "a3"),
                Arrays.asList("b1", "b2", "b3")
        );
        List<List<String>> result = descartes(list);
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return map != null && !map.isEmpty();
    }

}
