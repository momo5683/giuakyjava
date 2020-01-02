package model;

import java.util.List;
import java.util.Vector;

public class StaffsModelImpl implements StaffsModel {

    private List<TableObserver> tableObserver = new Vector<>();

    @Override
    public List<Staff> getAllStaff() {
        StaffDao dao = new StaffDaoImpl();

        return dao.getAllStaff();
    }

    @Override
    public void addStaff(Staff staff) {
        StaffDao dao = new StaffDaoImpl();
        dao.insertStaff(staff);
        notifyObserver();
    }

    @Override
    public void deleteStaff(int id) {
        StaffDao dao = new StaffDaoImpl();
        dao.delete(id);
        notifyObserver();
    }

    @Override
    public void updateStaff(Staff staff, int id) {
        StaffDao dao = new StaffDaoImpl();
        dao.update(staff,id);
        notifyObserver();
    }

    @Override
    public void registerObserver(TableObserver observer) {
        if(!tableObserver.contains(observer)){
            tableObserver.add(observer);
        }
    }

    private void notifyObserver(){
        List<Staff> staff = getAllStaff();
        for (TableObserver observer: tableObserver){
            observer.updateTable(staff);
        }
    }
}
