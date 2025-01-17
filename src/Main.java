
import model.Service;
import model.familyTree.FamilyTree;
import model.human.Gender;
import model.human.Human;
import model.save.FamilyTreeFileIO;
import model.save.FamilyTreeFileManager;
import view.Console;
import view.View;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FamilyTree<Human> tree = new FamilyTree<>();

        Human vasya = new Human("Василий", Gender.Male, LocalDate.of(1963, 12, 10));
        Human masha = new Human("Мария", Gender.Female, LocalDate.of(1965, 9, 15));
        Human christina = new Human("Кристина", Gender.Female, LocalDate.of(1988, 7, 5),
                vasya, masha);
        Human semyon = new Human("Семен", Gender.Male, LocalDate.of(1991, 1, 25),
                vasya, masha);

        tree.add(vasya);
        tree.add(masha);
        tree.add(christina);
        tree.add(semyon);

        Human grandMother = new Human("Лариса", Gender.Female, LocalDate.of(1945, 9, 1));
        grandMother.addChild(vasya);

        tree.add(grandMother);

        FamilyTreeFileIO fileIo = new FamilyTreeFileManager();
        Service service = new Service(tree);
        service.setFileIo(fileIo);
        
        tree = service.load();

        View view = new Console();
        view.setFileIo(fileIo);
        view.setFamilyTree(tree);
        view.start();
    }
}