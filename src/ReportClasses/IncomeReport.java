package ReportClasses;

public class IncomeReport extends Report {

    protected String paymentType;
    protected String additionalInfo;

    public IncomeReport(int id, float sum, String categoryIndex, String date, String paymentType, String additionalInfo){
        super(id);
        this.sum = sum;
        this.categoryIndex = categoryIndex;
        this.date = date;
        this.paymentType = paymentType;
        this.additionalInfo = additionalInfo;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @Override
    public String toString() {
        return String.format("%s,,%s,,%s,,%s,,%s,,%s",id,date,sum,categoryIndex,additionalInfo,paymentType);
    }

}
