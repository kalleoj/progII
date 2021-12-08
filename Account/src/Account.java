public class Account {

    String name;
    int age;

    public static void main(String [] args ) {

        Account a = new Account();
        a.setName("Olivia");
        a.setAge(22);
        System.out.println(a.getName());
        System.out.println(a.getAge());

        a.printDetails();


    }
    public void setName(String name) {
        this.name = name;

    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public void printDetails() {
        System.out.println(name + ", " + age);

    }


}
