package UI;

import javax.swing.table.DefaultTableModel;

public class Tables {

    public DefaultTableModel getIncomeTable(Object[][] incomeReports){
        return (new javax.swing.table.DefaultTableModel(
                incomeReports,
                new String [] {
                        "ID", "Date", "Sum", "Category Index", "Additional Information", "Payment Type", "", ""
                }
        ) {
            final Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            final boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

    }

    public DefaultTableModel getExpenseTable(Object[][] expenseReports){
        return (new javax.swing.table.DefaultTableModel(
                expenseReports,
                new String [] {
                        "ID", "Date", "Sum", "Category Index", "Additional Information", "Transfer Status", "", ""
                }
        ) {
            final Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Object.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            final boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

    }

}

