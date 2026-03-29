import java.util.List;
import java.util.ArrayList;

public class Student extends Person {
  private int id;
  private int startYear = getCurrentYear();
  private int graduationYear;
  private int degreeCount = 3;
  private List<Degree> degrees = new ArrayList<Degree>(degreeCount);

  public Student(String lname, String fname){
    super(lname, fname);

    setId(getRandomId(ConstantValues.MIN_STUDENT_ID, ConstantValues.MAX_STUDENT_ID));

    for(int i = 0; i < degreeCount; i++){
      degrees.add(new Degree());
    }
  }

  public String getIdString(){
    return "Student id: " + Integer.toString(id);
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    if(id >= ConstantValues.MIN_STUDENT_ID && id <= ConstantValues.MAX_STUDENT_ID){
      this.id = id;
    }
  }

  public int getStartYear() {
    return startYear;
  }

  public void setStartYear(final int startYear) {
    int currentYear = getCurrentYear();
    if (startYear > 2000 && startYear <= currentYear) {
      this.startYear = startYear;
    }
  }

  public int getGraduationYear() {
    return graduationYear;
  }

  public String setGraduationYear(final int graduationYear) {
    int currentYear = getCurrentYear();
    int startYear = getStartYear();

    if(!canGraduate()){
      return "Check amount of required credits";
    } else if(graduationYear < startYear || graduationYear > currentYear){
      return "Check graduation year";
    }

    this.graduationYear = graduationYear;
    return "Ok";
  }

  public void setDegreeTitle(final int i, String dName){
    if(i >= 0 && i < degreeCount){
      degrees.get(i).setDegreeTitle(dName);
    }
  }

  public boolean addCourse(final int i, StudentCourse course){
    if(i >= 0 && i < degreeCount && course != null){
      degrees.get(i).addStudentCourse(course);
      return true;
    }

    return false;
  }

  public int addCourses(final int i, List<StudentCourse> courses){
    int count = 0;
    if(i >= 0 && i < degreeCount && courses != null){
      for(StudentCourse course : courses){
        if(course != null){
          degrees.get(i).addStudentCourse(course);
          count++;
        }
      }
    }

    return count;
  }

  public void printCourses(){
    for(Degree degree : degrees){
      if(degree != null){
        degree.printCourses();
      }
    }
  }

  public void printDegrees(){
    for(Degree degree : degrees){
      System.out.println();
      System.out.println(degree);
    }
  }

  public void setTitleOfThesis(final int i, String title){
    if(i >= 0 && i < degreeCount && title != null){
      degrees.get(i).setTitleOfThesis(title);
    }
  }

  public boolean hasGraduated() {
    return graduationYear != 0 && graduationYear <= getCurrentYear();
  }

  private boolean canGraduate(){
    if(degrees.size() < 2 || degrees.get(0) == null || degrees.get(1) == null){
      return false;
    }

    double bachelorCredits = 0.0;
    double masterCredits = 0.0;

    for(StudentCourse course : degrees.get(0).getCourses()){
      if(course != null && course.isPassed()){
        bachelorCredits += course.getCourse().getCredits();
      }
    }

    for(StudentCourse course : degrees.get(1).getCourses()){
      if(course != null && course.isPassed()){
        masterCredits += course.getCourse().getCredits();
      }
    }

    return (
      masterCredits >= ConstantValues.MASTER_CREDITS &&
      bachelorCredits >= ConstantValues.BACHELOR_CREDITS &&
      degrees.get(0).getTitleOfThesis() != ConstantValues.NO_TITLE &&
      degrees.get(1).getTitleOfThesis() != ConstantValues.NO_TITLE
    );
  }

  public int getStudyYears(){
    return hasGraduated() ? 
      getGraduationYear() - getStartYear()
      : getCurrentYear() - getStartYear();
  }

  @Override
  public String toString() {
    Degree bachelor = degrees.get(0);
    Degree master = degrees.get(1);

    double bachelorMandatoryCredits = bachelor.getCreditsByType(ConstantValues.MANDATORY);
    double masterMandatoryCredits = master.getCreditsByType(ConstantValues.MANDATORY);

    String mandatoryBachelorCredits = bachelorMandatoryCredits >= ConstantValues.BACHELOR_MANDATORY
      ? String.format(
          "All mandatory bachelor credits completed (%.1f/%.1f)", 
          bachelorMandatoryCredits, ConstantValues.BACHELOR_MANDATORY
        )
      : String.format(
          "Missing mandatory bachelor credits %.1f (%.1f/%.1f)",
          ConstantValues.BACHELOR_MANDATORY - bachelorMandatoryCredits, bachelorMandatoryCredits, ConstantValues.BACHELOR_MANDATORY
        );

    String mandatoryMasterCredits = masterMandatoryCredits >= ConstantValues.MASTER_MANDATORY
      ? String.format(
          "All mandatory master's credits completed (%.1f/%.1f)", 
          masterMandatoryCredits, ConstantValues.MASTER_MANDATORY
        )
      : String.format(
          "Missing mandatory bachelor credits %.1f (%.1f/%.1f)",
          ConstantValues.MASTER_MANDATORY - masterMandatoryCredits, masterMandatoryCredits, ConstantValues.MASTER_MANDATORY
        );

    String status = hasGraduated() 
      ? String.format("The student has graduated in %d", getGraduationYear())
      : "The student has not graduated, yet";


    String last = hasGraduated()
      ? String.format("(Studies lasted for %d years)", getStudyYears())
      : String.format("(Studies have lasted for %d years)", getStudyYears());
    
    double bachelorCredits = bachelor.getCredits();
    double masterCredits = master.getCredits();
    
    String bachelorCreditsStr = bachelorCredits >= ConstantValues.BACHELOR_CREDITS
      ? String.format(
          "Total bachelor credits completed (%.1f/%.1f)", 
          bachelorCredits, ConstantValues.BACHELOR_CREDITS
        ) 
      : String.format(
          "Missing bachelor credits: %.1f (%.1f/%.1f)", 
          ConstantValues.BACHELOR_CREDITS - bachelorCredits, bachelorCredits, ConstantValues.BACHELOR_CREDITS
        );

    String masterCreditsStr = masterCredits >= ConstantValues.MASTER_CREDITS
      ? String.format(
          "Total master's credits completed (%.1f/%.1f)", 
          masterCredits, ConstantValues.MASTER_CREDITS
        ) 
      : String.format(
          "Missing master's credits: %.1f (%.1f/%.1f)", 
          ConstantValues.MASTER_CREDITS - masterCredits, masterCredits, ConstantValues.MASTER_CREDITS
        );
    
    String bd = getBirthDate() == ConstantValues.NO_BIRTHDATE 
    ? String.format("\"%s\"", ConstantValues.NO_BIRTHDATE) 
    : getBirthDate();

    Double gpa = 0.0;

    for(Degree d : degrees) gpa += d.getGPA(2).get(2);

    gpa /= 2;
    
    return String.format(
      """
      \n\nStudent id: %d
      \tFirst name: %s, Last name: %s
      \tDate of birth: %s
      \tStatus: %s
      \tStart year: %d %s
      \tTotal credits: %.1f (GPA = %.2f)
      \tBachelor credits: %.1f
      \t\t%s
      \t\t%s
      \t\tGPA of Bachelor studies: %.2f
      \t\tTitle of BSc Thesis: \"%s\"
      \tMaster credits: %.1f
      \t\t%s
      \t\t%s
      \t\tGPA of Master studies: %.2f
      \t\tTitle of MSc Thesis: \"%s\"
      """, 
      getId(), 
      getFirstName(), getLastName(), 
      bd, 
      status, 
      getStartYear(), last,
      bachelor.getCredits() + master.getCredits(), gpa,
      bachelor.getCredits(), 
      bachelorCreditsStr, 
      mandatoryBachelorCredits,
      bachelor.getGPA(2).get(2), 
      bachelor.getTitleOfThesis(),
      master.getCredits(), 
      masterCreditsStr,
      mandatoryMasterCredits, 
      master.getGPA(2).get(2), 
      master.getTitleOfThesis()
    );
  }
}