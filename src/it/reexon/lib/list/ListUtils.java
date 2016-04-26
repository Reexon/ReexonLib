/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
     * @param list list to find average element
     * @return  Average Element. 
     *          Null if list is empty
     *          
     * Example: 
     *          List<Int> = {1, 2, 3}
     *          return 2
     *          
     * @throws NullPointerException if list param is null
     */
    public static Integer findAverageElementInt(List<? extends Integer> list)
    {
        if (list == null)
            throw new NullPointerException("List is null");

        if (list.isEmpty())
            return null;

        Double averageDouble = list.stream().mapToInt(i -> i).average().getAsDouble();
        int average = averageDouble.intValue();

        list.sort((p1, p2) -> {
            int compare1 = p1.compareTo(average) < 0 ? p1.compareTo(average) * -1 : p1.compareTo(average);
            int compare2 = p2.compareTo(average) < 0 ? p2.compareTo(average) * -1 : p2.compareTo(average);
            return compare1 - compare2;
        });
        return list.stream().findFirst().get();
    }

    /**
     * @param list list to find average element
     * @return  Average Element. 
     *          Null if list is empty
     *          
     * Example: 
     *          List<Long> = {1L, 2L, 3L}
     *          return 2L
     *          
     * @throws NullPointerException if list param is null
     */
    public static Long findAverageElementLong(List<? extends Long> list)
    {
        if (list == null)
            throw new NullPointerException("List is null");

        if (list.isEmpty())
            return null;

        Double averageDouble = list.stream().mapToLong(i -> i).average().getAsDouble();
        long average = averageDouble.longValue();

        list.sort((p1, p2) -> {
            int compare1 = p1.compareTo(average) < 0 ? p1.compareTo(average) * -1 : p1.compareTo(average);
            int compare2 = p2.compareTo(average) < 0 ? p2.compareTo(average) * -1 : p2.compareTo(average);
            return compare1 - compare2;
        });
        return list.stream().findFirst().get();
    }
}
