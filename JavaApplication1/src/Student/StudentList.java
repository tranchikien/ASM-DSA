/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

/**
 *
 * @author CHI KIEN
 */
public class StudentList {

    private Student[] students;
    private int size;

    public StudentList() {
        students = new Student[100]; // Giả sử tối đa 100 sinh viên
        size = 0;
    }

    public void add(Student student) {
        if (size < students.length) { // Kiểm tra mảng còn chỗ không
            students[size++] = student; // Thêm sinh viên vào cuối, tăng size
        } else {
            System.out.println("List is full!"); // Báo lỗi nếu đầy
        }
    }

    public void remove(int id) {
        for (int i = 0; i < size; i++) { // Duyệt qua mảng
            if (students[i].getId() == id) { // Nếu tìm thấy ID
                for (int j = i; j < size - 1; j++) { // Dịch các phần tử sau lên
                    students[j] = students[j + 1];
                }
                students[--size] = null; // Xóa phần tử cuối, giảm size
                return;
            }
        }
        System.out.println("No student found with ID: " + id);
    }

    public void linearSearch(int id) {
        long startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == id) {
                long endTime = System.nanoTime();
                System.out.println("LinkedList Search Time: " + (endTime - startTime) + " ns");
                System.out.println("Find: " + students[i]);
                return;
            }
        }
        long endTime = System.nanoTime();
        System.out.println("LinkedList Search Time: " + (endTime - startTime) + " ns");
        System.out.println("No students found!");
    }

    public void quickSort(int low, int high) {
        if (low < high) { // Nếu còn phần tử để sắp xếp
            int pi = partition(low, high); // Phân vùng mảng
            quickSort(low, pi - 1); // Sắp xếp nửa trái
            quickSort(pi + 1, high); // Sắp xếp nửa phải
        }
    }

    private int partition(int low, int high) {
        double pivot = students[high].getMark(); // Chọn điểm cuối làm pivot
        int i = low - 1; // Chỉ số phần tử nhỏ hơn pivot
        for (int j = low; j < high; j++) { // Duyệt qua mảng
            if (students[j].getMark() < pivot) { // Nếu nhỏ hơn pivot
                i++; // Tăng chỉ số
                swap(i, j); // Hoán đổi
            }
        }
        swap(i + 1, high); // Đặt pivot vào vị trí đúng
        return i + 1; // Trả về vị trí pivot
    }

    public void bubbleSort() {
        long startTime = System.nanoTime(); // Đo thời gian bắt đầu
        for (int i = 0; i < size - 1; i++) { // Lặp n-1 lần
            for (int j = 0; j < size - i - 1; j++) { // Duyệt các cặp còn lại
                if (students[j].getMark() < students[j + 1].getMark()) {
                    swap(j, j + 1); // Hoán đổi
                }
            }
        }
        long endTime = System.nanoTime(); // Đo thời gian kết thúc
        System.out.println("BubbleSort Time: " + (endTime - startTime) + " ns");
    }

    private void swap(int i, int j) {
        Student temp = students[i];
        students[i] = students[j];
        students[j] = temp;
    }

    public int size() {
        return size;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
        }
    }
}
