/**
 * Created by ALEX on 16.10.2014.
 */
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class AddressBook {
    private Address[] list = new Address[100];
    private int count = 0;

    // создание файла и сохранение текста в файл
    public static void save (AddressBook book) throws IOException {
        try {
            Path path = Paths.get("C:\\tmp\\AddressBook.txt");
            ArrayList <String> list = new ArrayList <String> ();
            for (int i = 0; i < book.count; i++) {
                Address address = book.list[i];
                list.add(address.name);
                list.add(address.phone);
                list.add(address.email);
                list.add(address.year);
            }
            Charset charset = Charset.forName("UTF-8");
            Files.write(path, list, charset);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // вывод всех записей из файла
    public static void load (AddressBook book) throws IOException {
        Path path = Paths.get("C:\\tmp\\AddressBook.txt");
        Charset charset = Charset.forName("UTF-8");
        List <String> lines = Files.readAllLines(path, charset);
        int i = 0;
        while (i < lines.size()) {
            String name = lines.get(i);
            String phone = lines.get(i+1);
            String email = lines.get(i+2);
            String year = lines.get(i+3);
            add(book, name, phone, email, year);
            i += 4;
        }
        System.out.println (lines);
    }

    //Добавление записи в книгу
    public static void add (AddressBook book, String name, String phone, String email, String year) {
        Address newAddress = new Address(name, phone, email, year);
        book.list[book.count] = newAddress;
        book.count++;
    }

    //Поиск записи по имени человека
    public static int[] find(AddressBook book, String name) {
        // подсчитываем количество совпадений введенного имени и записей в книге
        int count = 0;
        for (int i = 0; i < book.count; i++) {
            if (!(book.list[i].name == null)) {
                Address a = book.list[i];
                if (a.name.equals(name)) {
                    count++;
                }
            }
        }
        // создаем новый массив размером, подсчитанным выше
        // проверяем на совпадения, в случае совпадения выводим запись
        int[] poisk = new int[count];
        count = 0;
        for (int i = 0; i < book.count; i++) {
            if (!(book.list[i].name == null)) {
                Address a = book.list[i];
                if (a.name.equals(name)) {
                    poisk[count] = i;
                    count++;
                }
            }
        }
        return poisk;
    }

    //Поиск записи по индексу записи в книге
    public static void get (AddressBook book, int i) {
        Address b = book.list[i];
        System.out.println(i + "." + b.name + " / " + b.phone + " / " + b.email+ " / " + b.year);
    }


    // Вывод всех записей на экран
    public static void print (AddressBook book) {
        System.out.println(" Имя  /   Телефон  /  email / Год рождения");
        for (int i=0; i<book.count; i++) {
            get(book, i);
        }
    }

    // Удаление записи из книги по индексу
    public static void delete(AddressBook book, int index) {
        // присваиваем выбранной пользователем записи тип null
        book.list[index].name = null;
        book.list[index].phone = null;
        book.list[index].email = null;
        book.list[index].year = null;
        // перемещаем записи адресной книги влево на пустое место
        for (int i = index; i < book.count - 1; i++) {
            book.list[i].name = book.list[i + 1].name;
            book.list[i].phone = book.list[i + 1].phone;
            book.list[i].email = book.list[i + 1].email;
            book.list[i].year = book.list[i + 1].year;
        }
        book.count --;
    }

    // Изменение записи в книге по индексу
    public static void edit (AddressBook book, int index, String name, String phone, String email, String year){
        book.list [index].name=name;
        book.list [index].phone=phone;
        book.list [index].email=email;
        book.list [index].year=year;
    }

    // Получение количества записей в книге
    public static int getCount(AddressBook book){

        return book.count;
    }
}
