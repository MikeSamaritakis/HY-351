package mainClasses;

public class Employee {
    int ID;
    String EmployeeName;
    int Age;
    int Salary;
    int HierarchicalPosition;

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID = id;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String name) {
        EmployeeName = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public int getHierarchicalPosition() {
        return HierarchicalPosition;
    }

    public void setHierarchicalPosition(int hierarchicalPosition) {
        HierarchicalPosition = hierarchicalPosition;
    }
}
