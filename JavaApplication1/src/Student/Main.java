package Student;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        BSTree studentTree = new BSTree();
        StudentList studentList = new StudentList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Management =====");
            System.out.println("1. Enter student");
            System.out.println("2. Search students by ID (Binary Search)");
            System.out.println("3. Search students by ID (Linear Search)");
            System.out.println("4. Show student list (Pre-order, In-order, Post-order)");
            System.out.println("5. Update student");
            System.out.println("6. Delete student");
            System.out.println("7. Sort student list by score");
            System.out.println("   7.1. QuickSort (ascending)");
            System.out.println("   7.2. BubbleSort (descending)");
            System.out.println("8. Show student list");
            System.out.println("9. Exit");
            System.out.print("Select function: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Enter number of students: ");
                        int n = Integer.parseInt(scanner.nextLine().trim());
                        for (int i = 0; i < n; i++) {
                            System.out.println("Enter student information " + (i + 1) + ":");

                            // Xác thực ID
                            String id;
                            while (true) {
                                System.out.print("Enter ID: ");
                                id = scanner.nextLine().trim();
                                if (onlyDigits(id)) {
                                    break;
                                } else {
                                    System.out.println("ID must be a number!.");
                                }
                            }

                            // Xác thực tên
                            String name;
                            while (true) {
                                try {
                                    System.out.print("Enter name: ");
                                    name = scanner.nextLine().trim();
                                    if (!isValidName(name)) {
                                        throw new Exception("Name must contain only letters and cannot exceed 50 characters!.");
                                    }
                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            // Xác thực điểm
                            double mark;
                            while (true) {
                                try {
                                    System.out.print("Enter score (0-10): ");
                                    mark = Double.parseDouble(scanner.nextLine().trim());
                                    if (mark < 0 || mark > 10) {
                                        throw new Exception("Score must be between 0 and 10.");
                                    }
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input! Please enter a valid number for the score.");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            Student student = new Student(Integer.parseInt(id), name, mark);
                            studentTree.insert(student);
                            studentList.add(student);
                            System.out.println("Student added successfully!"); // Thông báo thêm sinh viên thành công
                        }
                        break;
                    case 2:
                        System.out.print("Search students by ID (Binary Search): ");
                        int searchIdBST = Integer.parseInt(scanner.nextLine().trim());
                        studentTree.search(searchIdBST);
                        System.out.println("Search completed successfully!"); // Thông báo tìm kiếm thành công
                        break;
                    case 3:
                        System.out.print("Search students by ID (Linear Search): ");
                        int searchIdLL = Integer.parseInt(scanner.nextLine().trim());
                        studentList.linearSearch(searchIdLL);
                        System.out.println("Search completed successfully!"); // Thông báo tìm kiếm thành công
                        break;
                    case 4:
                        System.out.println("Select browsing type: 1-Pre-order, 2-In-order, 3-Post-order");
                        int orderType = Integer.parseInt(scanner.nextLine().trim());
                        studentTree.display(orderType);
                        break;
                    case 5:
                        System.out.print("Enter the student ID to be updated: ");
                        int updateId = Integer.parseInt(scanner.nextLine().trim());
                        studentTree.update(updateId, scanner);
                        System.out.println("Student updated successfully!"); // Thông báo cập nhật sinh viên thành công
                        break;
                    case 6:
                        System.out.print("Enter the student ID to delete: ");
                        int deleteId = Integer.parseInt(scanner.nextLine().trim());

                        studentList.remove(deleteId);
                        System.out.println("Student deleted successfully!"); // Thông báo xóa sinh viên thành công
                        break;
                    case 7:
                        System.out.println("Select sorting method:");
                        System.out.println("1. QuickSort (ascending)");
                        System.out.println("2. BubbleSort (descending)");
                        int sortChoice = Integer.parseInt(scanner.nextLine().trim());
                        switch (sortChoice) {
                            case 1:
                                studentList.quickSort(0, studentList.size() - 1);
                                System.out.println("Student list sorted successfully using QuickSort!"); // Thông báo sắp xếp thành công
                                break;
                            case 2:
                                studentList.bubbleSort();
                                System.out.println("Student list sorted successfully using BubbleSort!"); // Thông báo sắp xếp thành công
                                break;
                            default:
                                System.out.println("Invalid selection!");
                                break;
                        }
                        // Hiển thị danh sách sau khi sắp xếp
                        System.out.println("Student list after sorting:");
                        studentList.display();
                        break;
                    case 8:
                        System.out.println("Show student list:");
                        studentList.display();
                        break;
                    case 9:
                        System.out.println("Exiting the program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid selection!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public static boolean isValidName(String name) {
        if (name.length() > 50) {
            return false;
        }
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean onlyDigits(String s) {
        return s.matches("\\d+");
    }
}
