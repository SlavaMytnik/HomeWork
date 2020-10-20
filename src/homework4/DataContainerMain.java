package homework4;

/**
 * Класс DataContainerMain работает с объектами типа DataContainer
 */
public class DataContainerMain {
    public static void main(String[] args) {

        // Экземпляр класса DataContainer в виде массива элементов Integer
        DataContainer<Integer> dataContainerInteger = new DataContainer<>();

        dataContainerInteger.add(5);
        dataContainerInteger.add(4);

        // К массиву [5, 1] добавляем элемент null => [5, 1]
        dataContainerInteger.add(null);
        dataContainerInteger.add(10);
        dataContainerInteger.add(1);
        dataContainerInteger.add(3);
        dataContainerInteger.add(1);

        /* Из массива [5, 4, 10, 1, 3, 1] удаляем элемент
        с индексом 1 => [5, 10, 1, 3, 1] */
        dataContainerInteger.delete(1);

        /* Из массива [5, 10, 1, 3, 1] удаляем первый элемент,
        совпадающий со значением 1 => [5, 10, 3, 1] */
        dataContainerInteger.delete((Integer) 1);

        // Сортируем элементы массива по возрастанию: [5, 10, 3, 1] => [1, 3, 5, 10]
        dataContainerInteger.sort(new Cmp<>());

        System.out.println(dataContainerInteger.toString());

        //---------------------------------------------------------------

        // Экземпляр класса DataContainer в виде массива элементов String
        DataContainer<String> dataContainerString = new DataContainer<>();

        dataContainerString.add("A");

        // К массиву [A] добавляем элемент null => [A]
        dataContainerString.add("E");
        dataContainerString.add("C");
        dataContainerString.add("C");
        dataContainerString.add("B");

        /* Из массива [A, E, C, C, B] удаляем элемент
        с индексом 1 => [A, C, C, B] */
        dataContainerString.delete(1);

        /* Из массива [A, C, C, B] удаляем первый элемент,
        совпадающий со значением C => [A, C, B] */
        dataContainerString.delete("C");

        // Сортируем элементы массива по убыванию: [A, C, B] => [C, B, A]
        dataContainerString.sort(new Cmp<String>().reversed());

        System.out.println(dataContainerString.toString());

        // Сортируем элементы массива по возрастанию: [C, B, A] => [A, B, C]
        DataContainer.sort(dataContainerString);

        System.out.println(dataContainerString.toString());

        // Сортируем элементы массива по убыванию: [A, B, C] => [C, B, A]
        DataContainer.sort(dataContainerString, new Cmp<String>().reversed());

        System.out.println(dataContainerString.toString());
    }
}
