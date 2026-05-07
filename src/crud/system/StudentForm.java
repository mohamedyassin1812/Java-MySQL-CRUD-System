
package crud.system;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.sql.ResultSet;

public class StudentForm extends JFrame {

    JTextField nameField, ageField, gradeField, searchField;
    JTable table;
    DefaultTableModel model;
    JButton addBtn, updateBtn, deleteBtn, clearBtn;

    public StudentForm() {

        setTitle("Student Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 30);
        add(nameField);

        // Age
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(20, 60, 100, 30);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(120, 60, 200, 30);
        add(ageField);

        // Grade
        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setBounds(20, 100, 100, 30);
        add(gradeLabel);

        gradeField = new JTextField();
        gradeField.setBounds(120, 100, 200, 30);
        add(gradeField);

        // Buttons
        addBtn = new JButton("Add");
        addBtn.setBounds(20, 150, 80, 30);
        add(addBtn);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(120, 150, 100, 30);
        add(updateBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(240, 150, 100, 30);
        add(deleteBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(360, 150, 80, 30);
        add(clearBtn);

        // Search
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(20, 190, 80, 20);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(100, 190, 200, 25);
        add(searchField);

        // Table
        String[] columns = {"ID", "Name", "Age", "Grade"};
        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 220, 450, 200);
        add(scroll);

        // Sorter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        loadStudents();

        // 🔍 Search
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                String text = searchField.getText();

                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });

        // ➕ Add
        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();
                String grade = gradeField.getText().trim();

                if (name.isEmpty() || ageText.isEmpty() || grade.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields required!");
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageText);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Age must be number!");
                    return;
                }

                Student s = new Student(name, age, grade);
                StudentDAO.addStudent(s);

                loadStudents();
                clearFields();

                JOptionPane.showMessageDialog(null, "Added ✔");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        });

        // 🖱️ Click Row
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = table.getSelectedRow();
                int row = table.convertRowIndexToModel(selectedRow);

                nameField.setText(model.getValueAt(row, 1).toString());
                ageField.setText(model.getValueAt(row, 2).toString());
                gradeField.setText(model.getValueAt(row, 3).toString());
            }
        });

        // ✏️ Update
        updateBtn.addActionListener(e -> {
            try {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Select row first!");
                    return;
                }

                int row = table.convertRowIndexToModel(selectedRow);

                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();
                String grade = gradeField.getText().trim();

                if (name.isEmpty() || ageText.isEmpty() || grade.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields required!");
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageText);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Age must be number!");
                    return;
                }

                int id = Integer.parseInt(model.getValueAt(row, 0).toString());

                Student s = new Student(id, name, age, grade);
                StudentDAO.updateStudent(s);

                loadStudents();

                JOptionPane.showMessageDialog(null, "Updated ✔");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        });

        // ❌ Delete Button
        deleteBtn.addActionListener(e -> deleteSelected());

        // ⌨️ Delete من الكيبورد
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {

                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
                    deleteSelected();
                }
            }
        });

        // 🧼 Clear
        clearBtn.addActionListener(e -> clearFields());

        // 🔥 Enter Navigation
        nameField.addActionListener(e -> ageField.requestFocus());
        ageField.addActionListener(e -> gradeField.requestFocus());
        gradeField.addActionListener(e -> addBtn.doClick());

        // 🔥 Enter يعمل Add من أي مكان
        getRootPane().setDefaultButton(addBtn);

        // 🎯 Autofocus
        nameField.requestFocus();

        setVisible(true);
    }

    void loadStudents() {
        try {
            model.setRowCount(0);

            ResultSet rs = StudentDAO.getStudents();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("grade")
                });
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void clearFields() {
        nameField.setText("");
        ageField.setText("");
        gradeField.setText("");
    }

    void deleteSelected() {
        try {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Select row first!");
                return;
            }

            int row = table.convertRowIndexToModel(selectedRow);
            int id = Integer.parseInt(model.getValueAt(row, 0).toString());

            StudentDAO.deleteStudent(id);
            loadStudents();

            JOptionPane.showMessageDialog(null, "Deleted ✔");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}