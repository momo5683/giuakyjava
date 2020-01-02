package controller;

import View.NewStaff;
import model.Staff;
import model.StaffsModel;

import javax.swing.*;
import java.awt.*;

public class StaffsControllerImpl implements StaffsController {
    private Component parent;
    private StaffsModel staffsModel;
    private NewStaff view;

    public StaffsControllerImpl(Component parent, StaffsModel model, NewStaff view) {
        this.parent = parent;
        this.staffsModel = model;
        this.view = view;
    }

    @Override
    public void deleteStaff(int id) {
        int option = JOptionPane.showConfirmDialog(parent, "Bạn có muốn xóa nhân viên này ???", "Xóa nhân viên", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            staffsModel.deleteStaff(id);
        }
    }

    @Override
    public void updateStaff(int id) {
        int option = JOptionPane.showConfirmDialog(parent, view.getRootPanel(), "Chỉnh sử nhân viên", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            if(view.getFullName() == null){
                JOptionPane.showConfirmDialog(null, "Chưa nhập tên nhân viên", "Lỗi !!!", JOptionPane.OK_OPTION);
            }else {
                String fullName = view.getFullName();
                String gender = view.getGender();
                Staff staff = new Staff();
                staff.setFullName(fullName);
                staff.setGender(gender);
                staffsModel.updateStaff(staff, id);
            }
        }
    }
}
