package services;

import bean.Student;
import bean.University;
import util.InputUtility;
import util.StringUtility;

public class StudentService extends ManagementService {
    public static Student[] students;

    @Override
    public void process() {
        int studentMenu = InputUtility.askInt("Choose operation number you want to realize: \n" +
                "1. Registration \n" +
                "2. Search \n" +
                "3. Delete \n" +
                "4. Change information \n" +
                "5. Show all \n");
        if (studentMenu == 1) {
            register();
        } else if (studentMenu == 2) {
            search();
        } else if (studentMenu == 3) {
            delete();
        } else if (studentMenu == 4) {
            update();
        } else if (studentMenu == 5) {
            showAll();
        }
    }

    @Override
    public void register() {
        int regisrtrationCount = InputUtility.askInt("How many students do you want to register? ");
        students = new Student[regisrtrationCount];
        for (int i = 0; i < regisrtrationCount; i++) {
            students[i] = prepareStudent();
        }
    }

    public Student prepareStudent() {
        Student student = new Student();
        student.setName(InputUtility.askString("Please, include the name of student: "));
        student.setSurname(InputUtility.askString("Please, include the surname of student: "));
        student.setAge(InputUtility.askInt("Please, include the age of student:: "));
        student.setScholarship(InputUtility.askDouble("Please, include the salary of student:: "));
        University university = new University();
        university.setName(InputUtility.askString("Please, include the university of student: "));
        student.setUniversity(university);
        return student;
    }

    @Override
    public void search() {

        String search = InputUtility.askString("Please, include whether name or surname:  ");
        for (int i = 0; i < students.length; i++) {
            Student student = students[i];
            if (StringUtility.containsIgnoreCase(student.getName(), search) ||
                    StringUtility.containsIgnoreCase(student.getSurname(), search)) {
                System.out.println(i + ". " + student);
            }
        }
    }

    @Override
    public void delete() {
        search();
        int studentNumber = InputUtility.askInt("Please, include the number: ");
        students[studentNumber] = null;
        Student[] newStudents = new Student[students.length - 1];
        int j = 0;
        for (
                int i = 0;
                i < students.length; i++) {
            if (students[i] != null) {
                newStudents[i] = students[i];
                j++;
            }
        }

        students = newStudents;
    }

    @Override
    public void update() {
        search();
        int studentNumber = InputUtility.askInt("Which number of student do you want to change: ");
        Student student = students[studentNumber];
        while (true) {
            String fieldString = InputUtility.askString("Which (String) cell do you want to change: ");
            if (fieldString.equalsIgnoreCase("name")) {
                student.setName(InputUtility.askString("Please, include the name that you prefer (changed): "));
            } else if (fieldString.equalsIgnoreCase("surname")) {
                student.setSurname(InputUtility.askString("Please, include the surname that you prefer (changed):  "));
            } else if (fieldString.equalsIgnoreCase("age")) {
                student.setAge(InputUtility.askInt("Please, include the age that you prefer (changed): "));
            } else if (fieldString.equalsIgnoreCase("scholarship")) {
                student.setScholarship(InputUtility.askDouble("Please, include the scholarship that you prefer (changed): "));
            } else if (fieldString.equalsIgnoreCase("university")) {
                University university = new University();
                university.setName(InputUtility.askString("Please, include the university that you prefer (changed): "));
                student.setUniversity(university);
            } else if (fieldString.equalsIgnoreCase("Done")) {
                break;
            } else {
                System.out.println("The cell name included is invalid. ");
            }
        }
    }

    @Override
    public void showAll() {
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }
    }
}

