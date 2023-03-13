package ReportClasses;

import java.util.ArrayList;

public class Budget {

    private final ArrayList<Report> reports = new ArrayList<>();

    public void addReport(Report report){
        reports.add(report);
    }

    public ArrayList<IncomeReport> getIncomeReports(){
        ArrayList<IncomeReport> income = new ArrayList<>();
        for (Report report:reports){
            if (report instanceof IncomeReport) income.add((IncomeReport)report);
        }
        return income;
    }

    public ArrayList<ExpenseReport> getExpenseReports(){
        ArrayList<ExpenseReport> expenses = new ArrayList<>();
        for (Report report:reports){
            if (report instanceof ExpenseReport) expenses.add((ExpenseReport)report);
        }
        return expenses;
    }

    public double balance(){
        double sum = 0;
        for (Report report: reports){
            if (report instanceof ExpenseReport) sum -= report.sum;
            else sum += report.sum;
        }
        return sum;
    }

    public void removeReport(Report report){
        reports.remove(report);
    }

    public void editReport(final Report editedReport){
        for (Report report: reports) {
            if (report.getId() == editedReport.getId()){
                reports.set(reports.indexOf(report), editedReport);
                return;
            }
        }
    }

}
