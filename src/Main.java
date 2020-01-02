import View.ViewStaff;
import model.StaffsModel;
import model.StaffsModelImpl;

public class Main{

    public static void main(String[] args) {
        StaffsModel model = new StaffsModelImpl();
        ViewStaff viewStaff = new ViewStaff(model);

    }
}
