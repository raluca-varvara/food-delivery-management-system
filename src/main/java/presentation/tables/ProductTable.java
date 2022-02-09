package presentation.tables;

import businessLayer.MenuItem;
import presentation.tables.AbstractTable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductTable extends AbstractTable<MenuItem> {
    private final JFrame frame = new JFrame("MenuItems");
    private final JPanel content = new JPanel();
    private final JTable table;

    public ProductTable(List<MenuItem> l) {
        Object[] columns = this.columnsNames(l);
        Object[][] tableData = this.tableInput(l);
        table = new JTable(tableData, columns);

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
}
