package Utility;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetConverter {
    public static DefaultTableModel resultSetToTableModel(ResultSet rs) throws SQLException {
        if (rs == null) {
            throw new IllegalArgumentException("ResultSet cannot be null");
        }

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        
        // Get column names
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            columnNames[columnIndex - 1] = metaData.getColumnLabel(columnIndex);
        }

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Populate data
        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                rowData[columnIndex - 1] = rs.getObject(columnIndex);
            }
            model.addRow(rowData);
        }

        return model;
    }
}
