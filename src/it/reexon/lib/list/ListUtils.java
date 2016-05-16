/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ListUtils
{
    /**
     * This function resolve the warning:
     *          The expression of type List needs unchecked conversion to conform to List
     * Example: 
     *          List<AnagSoggetto> findAllById(Long id)
     *          ...
     *          return castList(AnagSoggetto.class, query.list());
     *          
     * @param clazz     class type
     * @param c         list 
     * @return list
     * 
     */
    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c)
    {
        List<T> r = new ArrayList<T>(c.size());
        for (Object o : c)
            r.add(clazz.cast(o));
        return r;
    }

    /**
     * Find Average Element Integer from List
     * Example: 
     * <code>   List<Int> = {1, 2, 3}
     *          return 2
     * </code>        
     * @param list list to find average element
     * @return  <ul> Average Element. 
     *          <ul> Null if list is empty
     *          
     *          
     * @throws IllegalArgumentException if list param is null
     */
    public static Integer findAverageElementInt(List<? extends Integer> list)
    {
        if (list == null)
            throw new IllegalArgumentException("List is null");

        if (list.isEmpty())
            return null;

        Double averageDouble = list.stream().mapToInt(i -> i).average().getAsDouble();
        int average = averageDouble.intValue();

        return sortListByElement(new ArrayList<>(list.stream().map(p -> p.longValue()).collect(Collectors.toList())), new Long(average)).stream()
                                                                                                                                        .findFirst()
                                                                                                                                        .get()
                                                                                                                                        .intValue();
    }

    /**
    * Find Average Element Long from List
    * 
    * @param list list to find average element
    * @return  <ul>Average Element. 
    *          <ul>Null if list is empty
    *          
    * Example: 
    *          List<Long> = {1L, 2L, 3L}
    *          return 2L
    *          
    * @throws IllegalArgumentException if list param is null
    */
    public static Long findAverageElementLong(List<? extends Long> list)
    {
        if (list == null)
            throw new IllegalArgumentException("List cannot be null");

        if (list.isEmpty())
            return null;

        Double averageDouble = list.stream().mapToLong(i -> i).average().getAsDouble();
        long average = averageDouble.longValue();

        return sortListByElement(list, average).stream().findFirst().get();
    }

    /**
     * Order Long list by an element
     * Example: 
     * <br>    list = {1,2,3}
     * <br>    element = {2}
     * <br>    retrurn = {2,1,3}
     * 
     * @param list list to order
     * @param element element in which to sort
     * 
     * @return  sorted list;
     *          null if list is empty
     *          
     * @throws IllegalArgumentException if list is null or element is null
     */
    public static List<Long> orderByElementLong(final List<? extends Long> list, Long element)
    {
        if (list == null)
            throw new IllegalArgumentException("List cannot be null");

        if (element == null)
            throw new IllegalArgumentException("Element cannot be null");

        if (list.isEmpty())
            return null;

        return sortListByElement(list, element);
    }

    /**
     * It takes the list item that is closest to the value passed as a parameter.
     * 
     * @param list      list from which you find the value
     * @param element   element to look for
     * 
     * @return item that is closest to the value passed as a parameter.
     * @throws IllegalArgumentException - if list is null or element is null
     */
    public static Long nearestElement(List<? extends Long> list, Long element)
    {
        List<Long> sortedList = orderByElementLong(list, element);
        if (sortedList == null)
            return null;
        return sortedList.stream().findFirst().get();
    }

    /**
     * Create a List form elements
     * 
     * @param element array element to add
     * @return - list with elements
     */
    @SafeVarargs
    public static <T> List<T> createList(T... element)
    {
        return new LinkedList<>(Arrays.asList(element));
    }

    //TODO createSet

    private static final List<Long> sortListByElement(final List<? extends Long> list, Long element)
    {
        List<Long> listToSort = new ArrayList<Long>(list);
        listToSort.sort((p1, p2) -> {
            long distance1 = p1 - element;
            long distance2 = p2 - element;

            return Long.compare(distance1 < 0 ? distance1 * (-1) : distance1, distance2 < 0 ? distance2 * (-1) : distance2);
        });

        return listToSort;
    }
}
