package View;

import javax.swing.*;

public class NewStaff {

    private JPanel rootPanel;
    private JTextField txtFullName;
    private JComboBox cbGender;

    String[] Gender = {"Nam", "Nữ"};

    public JPanel getRootPanel()
    {
        for (int i = 0; i < Gender.length; i++) {
            cbGender.addItem(Gender[i]);
        }
        return rootPanel;
    }

    public String getFullName(){
        if(txtFullName.getText().equals("")){
            return null;
        }
        else {
            return txtFullName.getText();
        }
    }

    public String getGender(){
        if(cbGender.getSelectedItem().toString().equals("")){
            return "";
        }
        else {
            return cbGender.getSelectedItem().toString();
        }
    }
}
