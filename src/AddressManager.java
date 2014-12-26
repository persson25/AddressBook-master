/**
 * Created by ALEX on 16.10.2014.
 */
 
import java.io.IOException;
import java.util.Scanner;

public class AddressManager {
    public static void main(String[] args) throws IOException {
        AddressBook addressBook = new AddressBook();
        AddressBook.load(addressBook);
        while (true) {
            System.out.println("Доступные операции:");
            System.out.println("1. Добавить запись в книгу");
            System.out.println("2. Поиск записи по имени человека");
            System.out.println("3. Поиск записи по индексу записи в книге");
            System.out.println("4. Вывод всех записей на экран");
            System.out.println("5. Удаление записи из книги по индексу");
            System.out.println("6. Изменение записи");
            System.out.println("7. Сохранение добавленных записей в файл");
            System.out.println("8. Выход");
            System.out.println("Введите нужный пункт меню: ");
            Scanner sc = new Scanner(System.in);
            String menu = sc.nextLine();
            int command = Integer.parseInt (menu);
            switch (command) {
                case 1: {
                    System.out.println("Введите имя:");
                    String name = sc.nextLine();
                    System.out.println("Введите телефон (в случае отстутствия введите \"нет\"):");
                    String phone = sc.nextLine();
                    System.out.println("Введите емайл (в случае отстутствия введите \"нет\"):");
                    String email = sc.nextLine();
                    System.out.println("Введите год рождения:");
                    String year = sc.nextLine();
                    AddressBook.add(addressBook, name, phone, email, year);
                    System.out.println("Запись успешно добавлена!");
                    break;
                }
                case 2: {
                    System.out.println("Введите имя для поиска:");
                    String name = sc.nextLine();
                    int [] poisk = AddressBook.find(addressBook, name);
                    if (poisk.length == 0) {
                        System.out.println("Ничего не найдено!");
                    } else {
                        for (int i =0; i < poisk.length; i++){
                            AddressBook.get(addressBook, poisk[i]);
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("Введите индекс для поиска:");
                    int index = sc.nextInt();
                    if (index < AddressBook.getCount(addressBook)) {
                        AddressBook.get(addressBook, index);
                    } else {
                        System.out.println("Неверно указан индекс!");
                    }
                    break;
                }
                case 4:
                    AddressBook.print(addressBook);
                    break;
                case 5: {
                    System.out.println("Введите индекс для удаления записи");
                    int index = sc.nextInt();
                    if (index < AddressBook.getCount(addressBook)) {
                        AddressBook.delete(addressBook, index);
                        System.out.println("Запись успешно удалена!");
                    } else {
                        System.out.println("Неверно указан индекс!");
                    }
                    break;
                }
                case 6:
                    System.out.println("Введите индекс для изменения записи");
                    String a = sc.nextLine();
                    int index = Integer.parseInt(a);
                    if (index < AddressBook.getCount(addressBook)) {
                        System.out.println("Введите имя");
                        String name = sc.nextLine();
                        System.out.println("Введите телефон (в случае отстутствия введите \"нет\")");
                        String phone = sc.nextLine();
                        System.out.println("Введите емайл (в случае отстутствия введите \"нет\")");
                        String email = sc.nextLine();
                        System.out.println("Введите год рождения");
                        String year = sc.nextLine();
                        AddressBook.edit(addressBook, index, name, phone, email, year);
                        System.out.println("Запись успешно изменена!");
                    } else {
                        System.out.println("Неверно указан индекс!");
                    }
                    break;
                case 7:
                    AddressBook.save(addressBook);
                    System.out.println("Записи успешно сохранены!");
                    break;
                case 8:
                    System.out.println("Спасибо за использование!");
                    return;
                default:

            }

        }
    }
}
