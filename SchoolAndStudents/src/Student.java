public class Student 
{
    private String name;
    private String course;

    private double weight;
    private double height;

    public Student() 
    {
        name = "";
        course = "";
    }

    public Student(String name, String course) 
    {
        this.name = name;
        this.course = course;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public String toString()
    {
        return "Name: " + name + " Course: " + course;
    }
    
    public boolean equals(Student student)
    {
        if (name.equals(student.name) && course.equals(student.course))
        {
            return true;
        }
    
        return false;
    }
}