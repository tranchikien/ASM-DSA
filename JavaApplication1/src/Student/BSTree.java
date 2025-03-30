/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author CHI KIEN
 */
public class BSTree {

    private BSTNode root;

    public BSTree() {
        root = null;
    }

    public void insert(Student data) {
        root = insertRec(root, data);
    }

    private BSTNode insertRec(BSTNode root, Student data) {
        if (root == null) { // Nếu nút hiện tại rỗng
            return new BSTNode(data); // Tạo nút mới chứa sinh viên
        }
        if (data.getId() < root.data.getId()) { // Nếu ID nhỏ hơn nút hiện tại
            root.left = insertRec(root.left, data); // Chèn vào cây con trái
        } else if (data.getId() > root.data.getId()) { // Nếu ID lớn hơn
            root.right = insertRec(root.right, data); // Chèn vào cây con phải
        }
        return root; // Trả về nút gốc đã cập nhật
    }

    public void search(int id) {
        long startTime = System.nanoTime(); // Bắt đầu đo thời gian
        Student student = searchRec(root, id); // Tìm kiếm đệ quy
        long endTime = System.nanoTime(); // Kết thúc đo thời gian
        System.out.println("BST Search Time: " + (endTime - startTime) + " ns");
        if (student != null) {
            System.out.println("Find: " + student); // Hiển thị sinh viên nếu tìm thấy
        } else {
            System.out.println("No students found!");
        }
    }

    private Student searchRec(BSTNode root, int id) {
        if (root == null || root.data.getId() == id) { // Nếu rỗng hoặc tìm thấy
            return (root != null) ? root.data : null; // Trả về sinh viên hoặc null
        }
        return (id < root.data.getId()) ? searchRec(root.left, id) : searchRec(root.right, id); // Đệ quy trái/phải
    }

    public void display(int orderType) {
        switch (orderType) {
            case 1:
                System.out.println("Browse Pre-order:");
                preOrder(root);
                break;
            case 2:
                System.out.println("Browse In-order:");
                inOrder(root);
                break;
            case 3:
                System.out.println("Browse Post-order:");
                postOrder(root);
                break;
            default:
                System.out.println("Invalid selection!");
        }
    }

    private void preOrder(BSTNode node) {
        if (node != null) {
            System.out.println(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void inOrder(BSTNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.data);
            inOrder(node.right);
        }
    }

    private void postOrder(BSTNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.data);
        }
    }

    public void update(int id, Scanner scanner) {
        Student student = searchRec(root, id); // Tìm sinh viên theo ID
        if (student != null) { // Nếu tìm thấy
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine().trim(); // Nhập tên mới
            System.out.print("Enter new score: ");
            double newMark = Double.parseDouble(scanner.nextLine().trim()); // Nhập điểm mới
            student.setName(newName); // Cập nhật tên
            student.setMark(newMark); // Cập nhật điểm
            System.out.println("Update successful!");
        } else {
            System.out.println("No students found to update!");
        }
    }

    public void delete(int id) {
        root = deleteRec(root, id);
    }

    private BSTNode deleteRec(BSTNode root, int id) {
        if (root == null) {
            return root;
        }
        if (id < root.data.getId()) {
            root.left = deleteRec(root.left, id);
        } else if (id > root.data.getId()) {
            root.right = deleteRec(root.right, id);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data.getId());
        }
        return root;
    }

    private Student minValue(BSTNode root) {
        Student minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }
}
