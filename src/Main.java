package prac10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) throws IOException {

        for (String line : Exercise1("file1.txt")) {
            System.out.println(line);
        }
        Exercise2("Передаем строку в фаил...");
        Exercise3();
        Exercise4("FormatFile.txt");
    }
    private static List<String> Exercise1(String fileName) {
        List<String> stringLine = new ArrayList<>();
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                // Записываем строки в список
                stringLine.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage()); // Вывести сообщение с ошибкой, если она появится
        }
        return stringLine;

    }
    private static void Exercise2(String string) {
        File file = new File("file1.txt");
        try (FileWriter writer = new FileWriter(file, true)) { // Запись в конец файла (append === true)
            writer.write(string);
        }
        catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }
    private static void Exercise3() {
        File file = new File("Summaryfile.txt");
        System.out.println("\nИдет склеивание...");
        try (FileWriter writer = new FileWriter(file)) {
            //Передаем фаил для считывания в метод упражнения 1
            for (String line: Exercise1("file1.txt")) {
                // Записываем в новый файл пришедшие строки
                writer.write(line + "\n");
                System.out.print(".");
            }
            for (String line: Exercise1("file2.txt")) {
                writer.write(line + "\n");
                System.out.print(".");
            }
            System.out.println("\nСклеивание файлов успешно завершено.");
        }
        catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }
    private static void Exercise4(String filename) throws IOException {
        List<String> arrayList = new ArrayList<>();
        File file = new File(filename);
        for (String line: Exercise1("FormatFile.txt")) {
            arrayList.add(line.replaceAll("[^A-Za-zА-Яа-я0-9]", Matcher.quoteReplacement("$")));
            // Заполняет список строками, в которых заменяется все, что не соответствует паттерну, на $
        }
        try (FileWriter writer = new FileWriter(file)) {
            for (String s : arrayList) {
                writer.write(s);
            }
        }
    }
}
