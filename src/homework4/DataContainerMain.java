package homework4;

/**
 * Класс DataContainerMain работает с объектами типа DataContainer
 */
public class DataContainerMain {
    public static void main(String[] args) {
        DataContainer<Integer> dataContainerInteger = new DataContainer<>();

        dataContainerInteger.add(1);
        dataContainerInteger.add(5);
        dataContainerInteger.add(10);
        dataContainerInteger.add(3);

        DataContainer<String> dataContainerString = new DataContainer<>();

        dataContainerString.add("A");
        dataContainerString.add("C");
        dataContainerString.add("B");

        DataContainer.sort(dataContainerInteger, new Cmp().reversed());
        DataContainer.sort(dataContainerString);

        System.out.println(dataContainerInteger.toString());
        System.out.println(dataContainerString.toString());
    }
}
