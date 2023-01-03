package services;

import bean.*;
import util.InputUtility;
import util.StringUtility;

public class TeacherService extends ManagementService {
    public static Teacher[] teachers;

    @Override
    public void process() {
        int teacherMenu = InputUtility.askInt("Choose operation number you want to realize: \n" +
                "1. Registration \n" +
                "2. Search \n" +
                "3. Delete \n" +
                "4. Change information \n" +
                "5. Show all \n");
        if (teacherMenu == 1) {
            register();
        } else if (teacherMenu == 2) {
            search();
        } else if (teacherMenu == 3) {
            delete();
        } else if (teacherMenu == 4) {
            update();
        } else if (teacherMenu == 5) {
            showAll();
        }

    }

    @Override
    public void register() {
        int regisrtrationCount = InputUtility.askInt("How many teacher do you want to register? ");
        teachers = new Teacher[regisrtrationCount];
        for (int i = 0; i < regisrtrationCount; i++) {
            teachers[i] = prepareTeacher();
        }
    }

    public static Teacher prepareTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName(InputUtility.askString("Please, include the name of teacher: "));
        teacher.setSurname(InputUtility.askString("Please, include the surname of teacher: "));
        teacher.setAge(InputUtility.askInt("Please, include the age of teacher: "));
        teacher.setSalary(InputUtility.askDouble("Please, include the salary of teacher: "));
        University university = new University();
        university.setName(InputUtility.askString("Please, include the university of teacher: "));
        teacher.setUniversity(university);
        int studentCount = InputUtility.askInt("Pleasee, include the count of teacher's students: ");
        Student[] newStudents = new Student[studentCount];
        int j = 0;
        for (int a = 0; a < studentCount; a++) {
            String studentNameOrSurname = InputUtility.askString("Please, include students' name or surname: ");
            for (int i = 0; i < StudentService.students.length; i++) {
                Student student = StudentService.students[i];
                if (StringUtility.containsIgnoreCase(student.getName(), studentNameOrSurname) ||
                        StringUtility.containsIgnoreCase(student.getSurname(), studentNameOrSurname)) {
                    newStudents[j] = student;
                    j++;
                }
            }
        }
        teacher.setStudents(newStudents);
        return teacher;
    }

    @Override
    public void search() {
        String search = InputUtility.askString("Please, include whether name or surname: ");
        for (int i = 0; i < teachers.length; i++) {
            Teacher teacher = teachers[i];
            if (StringUtility.containsIgnoreCase(teacher.getName(), search) ||
                    StringUtility.containsIgnoreCase(teacher.getSurname(), search)) {
                System.out.println(i + ". " + teacher);
            }
        }
    }

    @Override
    public void delete() {
        search();
        int teacherNumber = InputUtility.askInt("Please, include the number: ");
        teachers[teacherNumber] = null;
        Teacher[] newTeachers = new Teacher[teachers.length - 1];
        int j = 0;
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] != null) {
                newTeachers[i] = teachers[i];
                j++;
            }
        }
        teachers = newTeachers;
    }

    @Override
    public void update() {
        search();
        int teacherNumber = InputUtility.askInt("Which number of teacher do you want to change: ");
        Teacher teacher = teachers[teacherNumber];
        while (true) {
            String fieldString = InputUtility.askString("Which (String) cell do you want to change: ");
            if (fieldString.equalsIgnoreCase("name")) {
                teacher.setName(InputUtility.askString("Please, include the name that you prefer (changed): "));
            } else if (fieldString.equalsIgnoreCase("surname")) {
                teacher.setSurname(InputUtility.askString("Please, include the surname that you prefer (changed): "));
            } else if (fieldString.equalsIgnoreCase("age")) {
                teacher.setAge(InputUtility.askInt("Please, include the age that you prefer (changed): "));
            } else if (fieldString.equalsIgnoreCase("salary")) {
                teacher.setSalary(InputUtility.askDouble("Please, include the salary that you prefer (changed): "));
            } else if (fieldString.equalsIgnoreCase("university")) {
                University university = new University();
                university.setName(InputUtility.askString("Please, include the university that you prefer (changed): "));
                teacher.setUniversity(university);
            } else if (fieldString.equalsIgnoreCase("Done")) {
                break;
            } else {
                System.out.println("The cell name included is invalid ");
            }
        }
    }

    @Override
    public void showAll() {
        for (int i = 0; i < teachers.length; i++) {
            System.out.println(teachers[i]);
        }
    }
}
