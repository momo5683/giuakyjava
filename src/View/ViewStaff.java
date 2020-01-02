package View;

import controller.NewStaffController;
import controller.NewStaffControllerImpl;
import controller.StaffsController;
import controller.StaffsControllerImpl;
import model.Staff;
import model.StaffsModel;
import model.TableObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class ViewStaff extends JFrame implements TableObserver {

    private JPanel rootPanel;
    private JTable studentTable;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    private StaffTableModel staffTableModel;
    private StaffsModel staffsModel;

    private List<TableObserver> tableObserver = new Vector<>();

    private List<Staff> staffs = new Vector<>();

    String[] Gender = {"Nam", "Nữ"};

    public ViewStaff(StaffsModel model){
        this.staffsModel = model;
        this.staffsModel.registerObserver(this);

        setTitle("Staff Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(600,400));
        setLocation(500,200);
        pack();
        setVisible(true);

        staffTableModel = new StaffTableModel();
        studentTable.setModel(staffTableModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddStaff(e);
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDeleteStaff(e);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onUpdateStaff(e);
            }
        });

        List<Staff> staff = this.staffsModel.getAllStaff();
        staffTableModel.updateStudents(staff);

    }

    private void onAddStaff(ActionEvent e){
        NewStaffController controller = new NewStaffControllerImpl(this,staffsModel,new NewStaff());
        controller.newStaff();
    }

    private void onDeleteStaff(ActionEvent e){
        if (studentTable.getSelectedRow() != -1) {
            StaffsController staffController = new StaffsControllerImpl(this, staffsModel, new NewStaff());
            staffController.deleteStaff((Integer) staffTableModel.getValueAt(studentTable.getSelectedRow(), 0));
            System.out.println(studentTable.getSelectedRow());
        }else {
            JOptionPane.showConfirmDialog(null, "Chưa chọn bất kỳ nhân viên nào", "Xóa nhân viên", JOptionPane.OK_OPTION);
        }
    }

    private void onUpdateStaff(ActionEvent e){
        if (studentTable.getSelectedRow() != -1) {
            StaffsController staffController = new StaffsControllerImpl(this, staffsModel, new NewStaff());
            staffController.updateStaff((Integer) staffTableModel.getValueAt(studentTable.getSelectedRow(), 0));
        }else {
            JOptionPane.showConfirmDialog(null, "Chưa chọn bất kỳ nhân viên nào", "Xóa nhân viên", JOptionPane.OK_OPTION);
        }
    }

    public JPanel getRootPanel(){
        return rootPanel;
    }

    @Override
    public void updateTable(List<Staff> students) {
        staffTableModel.updateStudents(students);
    }

}
