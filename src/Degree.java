import java.util.ArrayList;
import java.util.List;

public class Degree {
  private static final int MAX_COURSES = 50;
  private int count = 0;
  private String degreeTitle = ConstantValues.NO_TITLE;
  private String titleOfThesis = ConstantValues.NO_TITLE;
  private List<StudentCourse> myCourses = new ArrayList<StudentCourse>(MAX_COURSES);

  public List<StudentCourse> getCourses(){
    return myCourses;
  }

  public void addStudentCourses(List<StudentCourse> courses){
    if(courses != null){
      for(StudentCourse course : courses){
        addStudentCourse(course);
      }
    }
  }

  public boolean addStudentCourse(StudentCourse course){
    if(course != null && count < MAX_COURSES){
      myCourses.add(course);
      this.count++;
      return true;
    }
    return false;
  }

  public String getDegreeTitle() {
    return degreeTitle;
  }

  public void setDegreeTitle(String degreeTitle) {
    if(degreeTitle != null) {
      this.degreeTitle = degreeTitle;
    }
  }

  public String getTitleOfThesis() {
    return titleOfThesis;
  }

  public void setTitleOfThesis(String titleOfThesis) {
    if(titleOfThesis != null){
      this.titleOfThesis = titleOfThesis;
    }
  }

  public double getCreditsByBase(Character base){
    double credits = 0.0;

    for(StudentCourse course : getCourses()){
      if(course != null && isCourseCompleted(course)){
        Character courseBase = course.getCourse().getCourseBase();

        if(courseBase == base){
          credits += course.getCourse().getCredits();
        }
      }
    }

    return credits;
  }

  public double getCreditsByType(final int courseType){
    double credits = 0.0;

    for(StudentCourse course : getCourses()){
      if(course != null && isCourseCompleted(course)){
        int currentCourseType = course.getCourse().getCourseType();
        
        if(currentCourseType == courseType){
          credits += course.getCourse().getCredits();
        }
      }
    }

    return credits;
  }

  public double getCredits(){
    double credits = 0.0;

    for(StudentCourse course : getCourses()){
      if(course != null && isCourseCompleted(course)){
        credits += course.getCourse().getCredits();
      }
    }

    return credits;
  }

  private boolean isCourseCompleted(StudentCourse c){
    return c != null && c.isPassed();
  }

  public void printCourses(){
    List<StudentCourse> courses = getCourses();
    System.out.print("[");
    for(StudentCourse course : courses){
      if(course != null){
        System.out.println(course);
      }
    }
    System.out.print("]");
  }

  public List<Double> getGPA(int type){

    List<Double> values = new ArrayList<Double>(3);

    Double sum = 0.0;
    Double count = 0.0;
    Double avg = 0.0;

    for(StudentCourse course : getCourses()){
      if(type == ConstantValues.ALL && course.getCourse().isNumericGrade()){
        count += 1.0;
        sum += course.getGradeNum();

      } else if(course.getCourse().getCourseType() == type && course.getCourse().isNumericGrade()){
        count += 1.0;
        sum += course.getGradeNum();
      }
    }; 

    if(count > 0.0){
      avg = sum / count;
    }

    double finalSum = Math.round(sum * 100.0) / 100.0;
    double finalAvg = Math.round(avg * 100.0) / 100.0;

    values.add(finalSum);
    values.add(count);
    values.add(finalAvg);

    return values;
  }

  public int getCount() {
    return count;
  }

  @Override
  public String toString() {

    String s = String.format(
      "Degree [Title: \"%s\" (courses: %d)\n\tThesis title: \"%s\"", 
      getDegreeTitle(), getCount(), getTitleOfThesis()
    );

    int count  = 0;

    for(StudentCourse course : getCourses()){
      if(course != null){
        count++;
        s += String.format("\n\t%d. %s", count, course.toString());
      }
    }

    s += "]";
    
    return s;
  }
}
