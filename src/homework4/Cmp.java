package homework4;

import java.util.Comparator;

/**
 * Класс Cmp выполняет сравнение объектов
 */
public class Cmp<T extends Comparable> implements Comparator<T> {

    /**
     * Метод compare выполняет сравнение объектов
     * @param o1 - первый объект сравнения
     * @param o2 - второй объект сравнения
     * @return возвращает результат сравнения
     */
    @Override
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
