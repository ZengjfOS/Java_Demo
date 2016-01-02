package demo;

import java.lang.reflect.Field;
import java.util.Arrays;
 
class Person{
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString(){
        return "["+this.name+"  "+this.age+"]";
    }
    private String name;
    private int age;
}
 
public class Test{
    public static void main(String[] args) {
        Class<?> demo=null;
        try{
            demo=Class.forName("demo.Person");
        }catch (Exception e) {
            e.printStackTrace();
        }
        Person per=null;
        Field[] field=demo.getDeclaredFields();
        System.out.println(Arrays.toString(field));
    }
}