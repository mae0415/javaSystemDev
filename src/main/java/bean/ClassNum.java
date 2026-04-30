// 前田春太
package bean;
 
import java.io.Serializable;
 
public class ClassNum implements Serializable {
 
    // クラス名
    private String class_num;
 
    // 学校
    private School school;
 
    // getter・setter
    public School getSchool() {
        return school;
    }
 
    public void setSchool(School school) {
        this.school = school;
    }
 
    public String getClass_num() {
        return class_num;
    }
 
    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }
}
 
 