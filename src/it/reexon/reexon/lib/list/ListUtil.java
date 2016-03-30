package it.reexon.reexon.lib.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author marco.velluto
 */
public class ListUtil
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
}
