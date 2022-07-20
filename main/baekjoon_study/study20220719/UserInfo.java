package baekjoon_study.study20220719;


public class UserInfo {
    private String name;
    private String phoneNum;
    private Integer age;

    public UserInfo(String name, String phoneNum, Integer age) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.age = age;
    }

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
