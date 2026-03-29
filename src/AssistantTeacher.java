import java.util.ArrayList;
import java.util.List;

public class AssistantTeacher extends Employee implements Teacher {
  private List<DesignatedCourse> courses = new ArrayList<>();

  public AssistantTeacher(String lname, String fname){
    super(lname, fname);
  }

  @Override
  protected String getEmployeeIdString(){
    return "OY_ASSISTANT_";
  }

  @Override
  public String getCourses(){
    String str = "";

    for(DesignatedCourse course : courses){
      str += course + "\n\t";
    }
    
    return str;
  }

  public void setCourses(List<DesignatedCourse> courses){
    this.courses = courses;
  }

  @Override
  public String toString(){
    return String.format(
      """
      Teacher id: %s
      \tFirst name: %s, Last name: %s
      \tBirthdate: %s
      \tSalary: %.2f
      \tAssistant for courses:
      \t%s
      """,
      getIdString(), 
      getFirstName(), getLastName(), 
      getBirthDate(), 
      calculatePayment(),
      getCourses()
    );
  }
}
