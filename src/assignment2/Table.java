package assignment2;

import javax.swing.table.AbstractTableModel;

public class Table extends AbstractTableModel {
    String[] columns = {"ISBN", "Title", "Edition", "Author","Year", "Price", "Publisher", "Pages" };
    Object[][] data = {{" ", " ", " ", " ", " ", " ", " ", " "}};

    boolean[] columnEditable = {false, false, false, false, false, false, false, false};

    public Table(Object[][] data) {
        this.data = data;
    }


    @Override
    public int getRowCount() {
    return data.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public boolean isCellEditable(int row, int column) {
        return columnEditable[column];
    }

    public void setValueAt(Object value, int row, int column) {
        data[row][column] = value;
        fireTableCellUpdated(row, column);
    }

}
