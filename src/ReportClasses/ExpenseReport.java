package ReportClasses;

public class ExpenseReport extends Report {

    protected boolean isTransferred;
    protected String additionalInfo;

    public ExpenseReport(int id, float sum, String categoryIndex, String date, boolean isTransferred, String additionalInfo){
        super(id);
        this.sum = sum;
        this.categoryIndex = categoryIndex;
        this.date = date;
        this.isTransferred = isTransferred;
        this.additionalInfo = additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setTransferred(boolean transferred) {
        isTransferred = transferred;
    }

    public boolean isTransferred() {
        return isTransferred;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @Override
    public String toString() {
        return String.format("%s,,%s,,%s,,%s,,%s,,%s",id,date,sum,categoryIndex,additionalInfo,isTransferred);
    }

}
