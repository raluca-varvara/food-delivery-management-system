package presentation;

import businessLayer.BaseProduct;
import businessLayer.MenuItem;
import presentation.tables.AbstractTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class EmployeePage extends AbstractTable<MenuItem> implements Observer {
    private final JFrame frame = new JFrame("MenuItems");
    private final JPanel content = new JPanel();
    private JTable table;

    public EmployeePage() {
        List<MenuItem> l = new ArrayList<>();
        l.add(new BaseProduct("nume",0,0,0,0,0,0) );
        Object[] cn =  columnsNames(l);
        int rowCount = 0;
        DefaultTableModel model = new DefaultTableModel(cn, rowCount);
        table = new JTable(model);

        table.setBackground(new Color(255,200,200));
        frame.setBounds(0, 0, 600, 600);
        content.setBounds(0, 0, 600, 600);
        content.setLayout(new BorderLayout());
        content.add(table.getTableHeader(), BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        content.add(scrollPane, BorderLayout.CENTER);

        frame.setContentPane(content);
        frame.setVisible(true);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void update(Observable o, Object arg) {
        List<MenuItem> list = (List<MenuItem>) arg;
        Iterator it = list.iterator();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int r = model.getRowCount();
        model.setRowCount(r+list.size());

        int i = 0;
        while (it.hasNext()) {
            MenuItem pair = (MenuItem) it.next();
            model.setValueAt(pair.getTitle(), r+i, 0);
            model.setValueAt(pair.getRating(), r+i, 1);
            model.setValueAt(pair.getCalories(), r+i, 2);
            model.setValueAt(pair.getProtein(), r+i, 3);
            model.setValueAt(pair.getFat(), r+i, 4);
            model.setValueAt(pair.getSodium(), r+i, 5);
            model.setValueAt(pair.getPrice(), r+i, 6);
            i++;
        }
        table=new JTable(model);
    }
}
