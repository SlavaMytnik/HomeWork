package homework4;

import java.util.*;

/**
 * Класс DataContainer выполняет Задания 1 - 12 ДЗ 4
 * (операции с данными обобщенного типа)
 */
public class DataContainer<T> {
    private T[] data = (T[]) new Object[]{};

    /**
     * Метод add добавляет элемент в массив
     * @param item - принимаемый элемент
     * @return возвращеает индекс добавленного элемента в массиве
     */
    int add(T item) {
        if (item == null) {
            return -1;
        }

        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == null) {
                this.data[i] = item;
                return i;
            }
        }

        this.data = Arrays.copyOf(data, data.length + 1);
        this.data[this.data.length - 1] = item;
        return data.length - 1;
    }

    /**
     * Метод delete удаляет элемент из массива по индексу
     * @param index - индекс удаляемого элемента массива
     * @return возвращает информацию об успешности удаления элемента массива
     */
    boolean delete(int index) {
        if (index < 0 || (this.data.length - 1) < index) {
            return false;
        }

        for (int i = 0; i < this.data.length - 1; i++) {
            if (i >= index) {
                this.data[i] = this.data[i + 1];
            }
        }

        this.data = Arrays.copyOf(this.data, this.data.length - 1);
        return true;
    }

    /**
     * Метод delete удаляет элемент из массива по значению
     * @param item - значение удаляемого элемента массива
     * @return возвращает информацию об успешности удаления элемента массива
     */
    boolean delete(T item) {
        boolean isFind = false;

        for (int i = 0; i < this.data.length; i++) {
            if (!isFind && this.data[i].equals(item)) {
                isFind = true;
            }

            if (isFind && i < this.data.length - 1) {
                this.data[i] = this.data[i + 1];
            }
        }

        if (isFind) {
            this.data = Arrays.copyOf(this.data, this.data.length - 1);
            return true;
        }
        return false;
    }

    /**
     * Метод get возвращает значение элемента массива по индексу
     * @param index - индекс элемента массива
     * @return возвращает значение элемента массива
     */
    T get(int index) {
        return ((this.data.length - 1) < index) ? null : this.data[index];
    }

    /**
     * Метод getItems возвращает массив элементов
     * @return возвращает массив элементов
     */
    T[] getItems() {
        return this.data;
    }

    /**
     * Метод sort сортирует элементы массива
     * @param comparator - объект реализации сравнения
     */
    void sort(Comparator<T> comparator) {
        if (comparator != null && this.data.length > 1) {
            int left = 0;
            int right = this.data.length - 1;

            do {
                for (int j = left; j < right; j++) {
                    if (comparator.compare(this.data[j],this.data[j + 1]) > 0) {
                        T temp = this.data[j];
                        this.data[j] = this.data[j + 1];
                        this.data[j + 1] = temp;
                    }
                }
                right--;

                for (int j = right; j > left; j--) {
                    if (comparator.compare(this.data[j], this.data[j - 1]) < 0) {
                        T temp = this.data[j];
                        this.data[j] = this.data[j - 1];
                        this.data[j - 1] = temp;
                    }
                }
                left++;
            } while (left < right);
        }
    }

    /**
     * Метод sort сортирует элементы массива
     * @param container - массив элементов
     */
    static void sort(DataContainer<? extends Comparable> container) {
        if (container != null) {
            DataContainer MyContainer = container;

            MyContainer.sort(new Cmp());
        }
    }

    /**
     * Метод sort сортирует элементы массива
     * @param container - массив элементов
     * @param comparator - объект реализации сравнения
     */
    static void sort(DataContainer<? extends Comparable> container, Comparator<? extends Comparable> comparator) {
        if (container != null && comparator != null) {
            DataContainer MyContainer = container;

            MyContainer.sort(comparator);
        }
    }

    /**
     * Метод toString возвращает масссив без элементов null в виде строки
     * @return возвращает масссив в виде строки
     */
    @Override
    public String toString() {
        T[] dataWithoutNull = this.data.clone();

        int count = 0;

        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == null) {
                count++;
            } else {
                dataWithoutNull[i - count] = this.data[i];
            }
        }

        dataWithoutNull = Arrays.copyOf(dataWithoutNull,
                this.data.length - count);
        return Arrays.toString(dataWithoutNull);
    }

}







