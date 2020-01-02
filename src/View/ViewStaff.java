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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

public class ViewStaff extends JFrame implements TableObserver {

    private JPanel rootPanel;
    private JTable staffTable;
    private JButton addButton;
    private JButton deleteButton;

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
        staffTable.setModel(staffTableModel);

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

        List<Staff> staff = this.staffsModel.getAllStaff();
        staffTableModel.updateStaffs(staff);

        staffTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onEmployeesTableClicked(e);
            }
        });
    }

    private void onAddStaff(ActionEvent e){
        NewStaffController controller = new NewStaffControllerImpl(this,staffsModel,new NewStaff());
        controller.newStaff();
    }

    private void onDeleteStaff(ActionEvent e){
        if (staffTable.getSelectedRow() != -1) {
            StaffsController staffController = new StaffsControllerImpl(this, staffsModel, new NewStaff());
            staffController.deleteStaff((Integer) staffTableModel.getValueAt(staffTable.getSelectedRow(), 0));
            System.out.println(staffTable.getSelectedRow());
        }else {
            JOptionPane.showConfirmDialog(null, "Chưa chọn bất kỳ nhân viên nào", "Xóa nhân viên", JOptionPane.OK_OPTION);
        }
    }

    private void onEmployeesTableClicked(MouseEvent e) {
        if (e.getClickCount() > 1) {
            if (staffTable.getSelectedRow() != -1) {
                StaffsController staffController = new StaffsControllerImpl(this, staffsModel, new NewStaff());
                staffController.updateStaff((Integer) staffTableModel.getValueAt(staffTable.getSelectedRow(), 0));
            }
        }
    }

    public JPanel getRootPanel(){
        return rootPanel;
    }

    @Override
    public void updateTable(List<Staff> staffs) {
        staffTableModel.updateStaffs(staffs);
    }

}
