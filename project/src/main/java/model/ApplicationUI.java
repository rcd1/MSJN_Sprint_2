package model;
import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationUI {
    private Scanner scanner;
    private DegreeFacade application;
    private User user;

    public static void main(String[] args) {
        ApplicationUI test = new ApplicationUI();
        test.run();
    }

    public ApplicationUI() {
        scanner = new Scanner(System.in);
        application = new DegreeFacade();
    }

    public void run() {
        boolean running = true;
        while (running) {
            if (user == null) {
                displayMainMenu();
                String option = getUserOption().toLowerCase();
                clear();
                switch (option) {

                    case "login":
                        login();
                        break;
                    case "create account":
                        createAccount();
                        break;
                    case "exit":
                        running = false;
                        application.exit();
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            } else {
                displayLoggedInMenu();
                String option = getUserOption().toLowerCase();
                clear();
                switch (option) {
                    case "view your progress":
                        Student student = (Student) user;
                        student.displayProgress();
                        break;
                    case "view semester plan":
                        displaySemesterPlan(user);
                        break;
                    case "save semester plan to text file":
                        saveSemesterPlanToFile(user);
                        break;
                    case "choose application area":
                        selectApplicationArea();
                        break;
                    case "view courses":
                        viewCourses();
                        break;
                    case "fill requirement":
                        fillCourses();
                        break;
                    case "logout":
                        logout();
                        break;
                    case "manage your students":
                        manageYourStudents();
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        }
        scanner.close();
    }

    private void viewCourses() {
        System.out.print("Enter a keyword to view: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayList<Course> courses = CourseList.getInstance().findCourses(input);
        for(Course course : courses) {
            System.out.print(course.getDesignator());
            System.out.print(" ");
            System.out.println(course.getNumber());
        }
    }

    private void fillCourses() {
        Student student = (Student)user;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter name of course: ");
        String name = input.nextLine();
        System.out.print("Enter number of course: ");
        String number = input.nextLine();
        student.fillCourse(CourseList.getInstance().getCourse(name, number));
    }

    private void selectApplicationArea() {
        Student student = (Student) user;
        while (true) {
            for (ApplicationID applicationID : ApplicationID.values()) {
                System.out.println(applicationID.getName());
            }
            System.out.println("Which Area would you like?: ");
            String option = getUserOption().toLowerCase();
            switch (option) {
                // Ideally won't be hard code later
                case "undeclared":
                    student.setApplicationID(ApplicationID.UNDECLARED);
                    return;
                case "science":
                    student.setApplicationID(ApplicationID.SCIENCE);
                    return;
                case "math":
                    student.setApplicationID(ApplicationID.MATH);
                    return;
                case "robotics":
                    student.setApplicationID(ApplicationID.ROBOTICS);
                    return;
                case "speech":
                    student.setApplicationID(ApplicationID.SPEECH);
                    return;
                case "digital design":
                    student.setApplicationID(ApplicationID.DIGITALDESIGN);
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }

    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void manageYourStudents() {
        while (true) {
            Advisor advisor = (Advisor) user;
            displayManageStudentOptions(advisor);
            String option = getUserOption().toLowerCase();
            clear();
            switch (option) {
                case "add a student":
                    addStudentToAdvisorList();
                    break;
                case "select a student":
                    selectStudentToView();
                    break;
                case "go back":
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void selectStudentToView() {
        Advisor advisor = (Advisor) user;
        System.out.println("Your Students");
        advisor.displayStudents();
        System.out.println(
                "Which student would you like to view? (Enter a number 1 to " + advisor.getStudents().size() + ")");
        int studentIndex = getAdvisorStudentIndex(advisor);
        Student selectedStudent = advisor.getStudents().get(studentIndex);
        while (true) {
            System.out.println("What would you like to do?: ");
            System.out.println("View Student profile");
            System.out.println("Add a note");
            System.out.println("go back");
            String option = getUserOption().toLowerCase();
            clear();
            switch (option) {
                case "view student profile":
                    System.out.println(selectedStudent.getApplicationID().getName());
                    selectedStudent.displaySemesterPlan();
                    // application.viewStudentProgress(selectedStudent);
                    break;
                case "add a note":
                    addStudentNote(selectedStudent);
                    break;
                case "go back":
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        // application.viewStudentProgress(advisor.getStudents().get(studentIndex));

    }

    private void addStudentNote(Student selectedStudent) {
        System.out.println("Please enter your note to " + selectedStudent.getFirstName() + " "
                + selectedStudent.getLastName());
        String newNote = scanner.nextLine();
        application.addNote(selectedStudent, newNote);
    }

    private int getAdvisorStudentIndex(Advisor advisor) {
        while (true) {
            try {
                int index = Integer.parseInt(scanner.nextLine());

                if (index < 1 || index > advisor.getStudents().size()) {
                    System.out.println("Enter a number 1 to " + advisor.getStudents().size());
                    continue;
                }
                return index - 1;

            } catch (Exception e) {
                System.out.println("Enter a number 1 to " + advisor.getStudents().size());
            }

        }
    }

    private void addStudentToAdvisorList() {
        System.out.println("Enter the student's first name: ");
        String firstName = getUserOption().toLowerCase();
        System.out.println("Enter the student's last name: ");
        String lastName = getUserOption().toLowerCase();
        application.addStudentToAdvisorList((Advisor) user, firstName, lastName);

    }

    private void displayManageStudentOptions(Advisor advisor) {
        System.out.println("Would you like to:");
        System.out.println("Add a student");
        System.out.println("Select a student");
        System.out.println("Go Back");
    }

    // Display details
    private void displayMainMenu() {
        System.out.println("Welcome to DegreeWorks");
        System.out.println("Login");
        System.out.println("Create Account");
        System.out.println("Exit");
        System.out.print("Enter your choice: ");
    }

    private void displayLoggedInMenu() {
        System.out.println("Logged in as " + user.getFirstName() + " " + user.getLastName());
        if (user instanceof Advisor) {
            System.out.println("Manage Your Students");
        } else if (user instanceof Student) {
            System.out.println("View your progress");
            System.out.println("View Semester Plan");
            System.out.println("Save Semester Plan to Text File");
            System.out.println("Choose Application Area");
            System.out.println("View Courses");
            System.out.println("Fill Requirement");
        }
        System.out.println("Logout");
        System.out.print("Enter your choice: ");
    }

    private String getUserOption() {
        return scanner.nextLine();
    }

    private void saveSemesterPlanToFile(User user) {
        System.out.println("Enter the file name to save the semester plan:");
        String fileName = scanner.nextLine();

        application.saveSemesterPlanToFile(user, fileName);
        System.out.println("Semester plan saved to " + fileName + ".");
    }

    // Login
    private void login() {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        user = application.login(email, password);
        if (user != null) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed. Please check your username and password.");
        }
    }

    // Signup/CreateAcc
    private void createAccount() {
        System.out.println("Are you a student or advisor?");
        boolean isValid = false;
        UserType type = null;
        do {
            String typeInput = scanner.nextLine();
            if (typeInput.equalsIgnoreCase(UserType.STUDENT.getPhrase())) {
                type = UserType.STUDENT;
                isValid = true;
            } else if (typeInput.equalsIgnoreCase(UserType.ADVISOR.getPhrase())) {
                type = UserType.ADVISOR;
                isValid = true;
            }
        } while (!isValid);
        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        user = application.createAccount(firstName, lastName, email, password, type);
        if (user != null) {
            System.out.println("Account successfully made.");
        } else {
            System.out.println("Failed to create account. Please check your information again");
        }
    }

    // logout
    private void logout() {
        user = null;
        application.logout();
        System.out.println("Logout successful.");
    }

    private void displaySemesterPlan(User user) {
        if (user instanceof Student) {
            ((Student) user).displaySemesterPlan();
            // edit semsterplan code
        } else if (user instanceof Advisor) {
            Advisor advisor = (Advisor) user;
            ArrayList<Student> advisedStudents = advisor.getStudents();
            System.out.println("Select a student to view their semester plan: ");
            for (int i = 0; i < advisedStudents.size(); i++) {
                Student student = advisedStudents.get(i);
                String fullName = student.getFirstName() + " " + student.getLastName();
                System.out.println((i + 1) + ". " + fullName);
            }
            String option = getUserOption();
            int choice = Integer.parseInt(option);
            if (choice >= 1 && choice <= advisedStudents.size()) {
                Student selectedStudent = advisedStudents.get(choice - 1);
                selectedStudent.displaySemesterPlan();
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
