import services.ManagementService;
import services.StudentService;
import services.TeacherService;
import util.InputUtility;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Integer menu = InputUtility.askInt(
                    "Telebeler yoxsa Muellimler haqqinda emeliyyat icra etmek isteyirsiniz: \n" +
                            "1. Muellimler \n" +
                            "2. Telebeler\n" +
                            "3. Exit \n");
            ManagementService service = null;
            if (menu == 1) {
                service = new TeacherService();
            } else if (menu == 2) {
                service = new StudentService();
            } else if (menu == 3) {
                System.exit(0);
            } else {
                System.out.println("Emeliyyat duzgun secilmeyib.");
            }
            if (menu > 0 && menu < 3) {
                service.process();
            }
        }
    }
}
