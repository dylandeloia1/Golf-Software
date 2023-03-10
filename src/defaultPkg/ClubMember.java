package defaultPkg;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClubMember {
    private String email,password,name,gender,phoneNumber;
    private Integer age,memberNum;
    private ArrayList<ReceiptData> receipts;
    private DateFormat timeFormat = new SimpleDateFormat("h:mm a");
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
    TimeStringComparator comparator = new TimeStringComparator();

    public ClubMember(String email, String password, String name, Integer age, String gender, String phoneNumber, Integer memberNum) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.memberNum = memberNum;
    }

    public void setReceipts(ArrayList<ReceiptData> receipts) {
        this.receipts = receipts;
        Collections.sort(receipts,(receiptdata1,receiptdata2) ->{
            if(comparator.compare(receiptdata1.getDateOfGolf(),receiptdata2.getDateOfGolf()) == 0){
                return comparator.compare(receiptdata1.getTimeOfGolf(),receiptdata2.getTimeOfGolf());
            } else{
                return comparator.compare(receiptdata1.getDateOfGolf(),receiptdata2.getDateOfGolf());
            }
        });
    }

    public ArrayList<ReceiptData> getReceipts() {
        return receipts;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getMemberNum() {
        return memberNum;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return (email + "," + password + "," + name + "," + age + "," + gender + "," + phoneNumber + "," + memberNum);
    }
}
