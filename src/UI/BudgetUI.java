package UI;
import ReportClasses.Budget;
import ReportClasses.ExpenseReport;
import ReportClasses.IncomeReport;
import ReportClasses.Report;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetUI extends javax.swing.JFrame {

    public BudgetUI() {initComponents();}

    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setTitle("Budget Program");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);

        refreshTable(new Tables().getIncomeTable(getIncomeReports()));

        jTable1.setCellSelectionEnabled(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        ListSelectionModel select= jTable1.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(e -> {
            int[] columns = jTable1.getSelectedColumns();
            int[] row = jTable1.getSelectedRows();
            jTable1.getSelectionModel().clearSelection();
            if (row.length == 1 && columns.length == 1){
                int tableType = 0;
                if ((columns[0] == 6)){
                    tableType = edit((int)jTable1.getValueAt(row[0], 0));
                }
                else if ((columns[0] == 7)){
                    tableType = delete((int)jTable1.getValueAt(row[0], 0));
                    newId = 0;
                    generateID();
                }

                if (tableType == 1){
                    refreshTable(new Tables().getIncomeTable(getIncomeReports()));
                }
                else if (tableType == 2){
                    refreshTable(new Tables().getExpenseTable(getExpenseReports()));
                }
                else return;

                refreshBudget();

            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Add Income Report");
        jButton1.addActionListener(e -> addIncomeReport());

        jLabel1.setText("File:                              ");

        jButton2.setText("Add Expense Report");
        jButton2.addActionListener(e -> addExpenseReport());

        jLabel3.setText("Budget: 0€                         ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(400, 400, 400)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3))
                                .addContainerGap())
        );

        jMenu1.setText("File");

        jMenuItem1.setText("New Budget");
        jMenu1.add(jMenuItem1);
        jMenuItem1.addActionListener(e -> {
            budget = new Budget();
            jLabel1.setText("File:                              ");
            refreshBudget();
            refreshTable(new Tables().getIncomeTable(getIncomeReports()));
            jMenuItem3.setEnabled(false);
        });

        jMenuItem2.setText("Open File");
        jMenu1.add(jMenuItem2);
        getFile();

        jMenuItem3.setText("Save File");
        jMenu1.add(jMenuItem3);
        jMenuItem3.setEnabled(false);
        jMenuItem3.addActionListener(e -> {
            try {
                saveFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        jMenuItem8.setText("Save As...");
        jMenu1.add(jMenuItem8);
        jMenuItem8.addActionListener(e -> saveAs());

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Menu");

        jMenuItem4.setText("Show Income");
        jMenu2.add(jMenuItem4);
        jMenuItem4.addActionListener(e -> refreshTable(new Tables().getIncomeTable(getIncomeReports())));

        jMenuItem5.setText("Show Expenses");
        jMenu2.add(jMenuItem5);
        jMenuItem5.addActionListener(e -> refreshTable(new Tables().getExpenseTable(getExpenseReports())));

        jMenuItem6.setText("Add Income Report");
        jMenu2.add(jMenuItem6);
        jMenuItem6.addActionListener(e -> addIncomeReport());

        jMenuItem7.setText("Add Expense Report");
        jMenu2.add(jMenuItem7);
        jMenuItem7.addActionListener(e -> addExpenseReport());

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    Budget budget = new Budget();
    private Report tempReport;
    File dataFile = null;
    private int newId = 0;
    // End of variables declaration

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BudgetUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new BudgetUI().setVisible(true));

    }
    public void getFile(){

        jMenuItem2.addActionListener(e -> {
            SearchUI search = new SearchUI();
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .csv files", "csv");
            search.jFileChooser1.removeChoosableFileFilter(search.jFileChooser1.getChoosableFileFilters()[0]);
            search.jFileChooser1.addChoosableFileFilter(restrict);
            int userSelection = search.jFileChooser1.showOpenDialog(search);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                dataFile = search.jFileChooser1.getSelectedFile();
                try {
                    openFile(dataFile);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                search.dispose();
            }
            else if (userSelection == JFileChooser.CANCEL_OPTION) {
                search.dispose();
            }
            jMenuItem3.setEnabled(true);
        });

    }
    public void openFile(File dataFile) throws FileNotFoundException {

        budget = new Budget();

       try{
           new Scanner(dataFile);
       }
       catch (Exception e){
           return;
       }

        Scanner scanner = new Scanner(dataFile);

        while(true){
            if (scanner.hasNextLine()){
                String[] dataLine = scanner.nextLine().split(",,");
                if (dataLine[5].equals("true") || dataLine[5].equals("false")){
                    budget.addReport(new ExpenseReport(Integer.parseInt(dataLine[0]), Float.parseFloat(dataLine[2]),
                            dataLine[3], dataLine[1], Boolean.parseBoolean(dataLine[5]), dataLine[4]));
                }
                else {
                    budget.addReport(new IncomeReport(Integer.parseInt(dataLine[0]), Float.parseFloat(dataLine[2]),
                            dataLine[3], dataLine[1], dataLine[5], dataLine[4]));
                }
            }
            else break;
        }

        jLabel1.setText("File: " + dataFile.getName());
        refreshBudget();
        refreshTable(new Tables().getIncomeTable(getIncomeReports()));
        newId = 0;
        generateID();

    }
    public void saveFile() throws IOException {

        FileOutputStream out = new FileOutputStream(dataFile);

        for (IncomeReport report:budget.getIncomeReports()){
            out.write((report.toString() + "\n").getBytes());
        }

        for (ExpenseReport report:budget.getExpenseReports()){
            out.write((report.toString() + "\n").getBytes());
        }

        out.close();

    }
    public void saveAs(){

        SearchUI saveUI = new SearchUI();
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .csv files", "csv");
        saveUI.jFileChooser1.removeChoosableFileFilter(saveUI.jFileChooser1.getChoosableFileFilters()[0]);
        saveUI.jFileChooser1.addChoosableFileFilter(restrict);
        saveUI.jFileChooser1.setDialogTitle("Specify a file to save");
        int userSelection = saveUI.jFileChooser1.showSaveDialog(saveUI);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            dataFile = saveUI.jFileChooser1.getSelectedFile();
            if (!dataFile.getName().endsWith(".csv")){
                dataFile = new File(dataFile.getPath() + ".csv");
            }
            try {saveFile();}
            catch (Exception e){ return; }
            jLabel1.setText("File: " + dataFile.getName());
            jMenuItem3.setEnabled(true);
        }

    }
    public void refreshTable(DefaultTableModel model){
        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
        }
    }
    public void refreshBudget(){
        jLabel3.setText(String.format("Balance: %.2f€", budget.balance()));
    }
    public Object[][] getIncomeReports(){

        ArrayList<IncomeReport> income = budget.getIncomeReports();
        Object[][] incomeTable = new Object[income.size()][8];

        for (int i=0; i<income.size(); i++){
            incomeTable[i][0] = income.get(i).getId();
            incomeTable[i][1] = income.get(i).getDate();
            incomeTable[i][2] = income.get(i).getSum();
            incomeTable[i][3] = income.get(i).getCategoryIndex();
            incomeTable[i][4] = income.get(i).getAdditionalInfo();
            incomeTable[i][5] = income.get(i).getPaymentType();
            incomeTable[i][6] = "Edit";
            incomeTable[i][7] = "Delete";
        }

        return incomeTable;

    }
    public Object[][] getExpenseReports(){

        ArrayList<ExpenseReport> expenses = budget.getExpenseReports();
        Object[][] expenseTable = new Object[expenses.size()][8];

        for (int i=0; i<expenses.size(); i++){
            expenseTable[i][0] = expenses.get(i).getId();
            expenseTable[i][1] = expenses.get(i).getDate();
            expenseTable[i][2] = expenses.get(i).getSum();
            expenseTable[i][3] = expenses.get(i).getCategoryIndex();
            expenseTable[i][4] = expenses.get(i).getAdditionalInfo();
            if (expenses.get(i).isTransferred()){
                expenseTable[i][5] = "Transferred";
            }
            else expenseTable[i][5] = "Not Transferred";
            expenseTable[i][6] = "Edit";
            expenseTable[i][7] = "Delete";
        }

        return expenseTable;

    }
    public void addIncomeReport(){

        IncomeReportUI reportUI = new IncomeReportUI();
        reportUI.setTitle("Add Income Report");
        reportUI.setVisible(true);
        reportUI.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        reportUI.cancelButton.addActionListener(e -> reportUI.dispose());

        reportUI.createButton.addActionListener(e -> {
            tempReport = reportUI.getReport(newId);
            if (tempReport != null){
                reportUI.dispose();
                budget.addReport(tempReport);
                refreshTable(new Tables().getIncomeTable(getIncomeReports()));
                refreshBudget();
                generateID();
            }
        });

    }
    public void addExpenseReport(){

        ExpenseReportUI reportUI = new ExpenseReportUI();
        reportUI.setTitle("Add Expense Report");
        reportUI.setVisible(true);
        reportUI.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        reportUI.cancelButton.addActionListener(e -> reportUI.dispose());

        reportUI.createButton.addActionListener(e -> {
            tempReport = reportUI.getReport(newId);
            if (tempReport != null){
                reportUI.dispose();
                budget.addReport(tempReport);
                refreshTable(new Tables().getExpenseTable(getExpenseReports()));
                refreshBudget();
                generateID();
            }
        });

    }
    public int edit(int id){
        for (IncomeReport report: budget.getIncomeReports()){
            if (report.getId()==id){
                IncomeReportUI reportUI = new IncomeReportUI();
                reportUI.setTitle("Edit Income Report");
                reportUI.editReport(report);
                reportUI.setVisible(true);
                reportUI.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                reportUI.cancelButton.addActionListener(e -> reportUI.dispose());

                reportUI.createButton.addActionListener(e -> {
                    tempReport = reportUI.getReport(id);
                    if (tempReport != null){
                        reportUI.dispose();
                        budget.editReport(tempReport);
                        refreshTable(new Tables().getIncomeTable(getIncomeReports()));
                        refreshBudget();
                    }
                });
                return 1;
            }
        }
        for (ExpenseReport report: budget.getExpenseReports()){
            if (report.getId()==id){
                ExpenseReportUI reportUI = new ExpenseReportUI();
                reportUI.setTitle("Edit Expense Report");
                reportUI.editReport(report);
                reportUI.setVisible(true);
                reportUI.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                reportUI.cancelButton.addActionListener(e -> reportUI.dispose());

                reportUI.createButton.addActionListener(e -> {
                    tempReport = reportUI.getReport(id);
                    if (tempReport != null){
                        reportUI.dispose();
                        budget.editReport(tempReport);
                        refreshTable(new Tables().getExpenseTable(getExpenseReports()));
                        refreshBudget();
                    }
                });
                return 2;
            }
        }
        return 0;
    }
    public int delete(int id){
        for (IncomeReport report: budget.getIncomeReports()){
            if (report.getId()==id){
                budget.removeReport(report);
                return 1;
            }
        }
        for (ExpenseReport report: budget.getExpenseReports()){
            if (report.getId()==id){
                budget.removeReport(report);
                return 2;
            }
        }
        return 0;
    }
    public void generateID(){
        for (ExpenseReport report: budget.getExpenseReports()){
            if(report.getId() == newId){
                newId++;
                generateID();
                return;
            }
        }
        for (IncomeReport report: budget.getIncomeReports()){
            if (report.getId() == newId){
                newId++;
                generateID();
                return;
            }
        }
    }

}
