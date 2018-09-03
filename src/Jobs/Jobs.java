package Jobs;

public class Jobs {
    private String jobDescription,jobName,datePlaced,dateFinished,customerName,handymanName,status;
    private int customerId,handymanId,duration,jobId;

    public Jobs() {
    }

    public Jobs(String jobDescription, String jobName, String datePlaced, String customerName, String handymanName, String status, int customerId, int handymanId, int duration,int jobId) {
        this.jobDescription = jobDescription;
        this.jobName = jobName;
        this.datePlaced = datePlaced;
        this.customerName = customerName;
        this.handymanName = handymanName;
        this.status = status;
        this.customerId = customerId;
        this.handymanId = handymanId;
        this.duration = duration;
        this.jobId=jobId;
    }

    public Jobs(String jobDescription, String jobName, String datePlaced, String dateFinished, String customerName, String handymanName, String status, int customerId, int handymanId, int duration, int jobId) {
        this.jobDescription = jobDescription;
        this.jobName = jobName;
        this.datePlaced = datePlaced;
        this.dateFinished = dateFinished;
        this.customerName = customerName;
        this.handymanName = handymanName;
        this.status = status;
        this.customerId = customerId;
        this.handymanId = handymanId;
        this.duration = duration;
        this.jobId=jobId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(String datePlaced) {
        this.datePlaced = datePlaced;
    }

    public String getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(String dateFinished) {
        this.dateFinished = dateFinished;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getHandymanName() {
        return handymanName;
    }

    public void setHandymanName(String handymanName) {
        this.handymanName = handymanName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getHandymanId() {
        return handymanId;
    }

    public void setHandymanId(int handymanId) {
        this.handymanId = handymanId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
