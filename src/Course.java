public class Course {
  private String name = ConstantValues.NO_TITLE;
  private String courseCode = ConstantValues.NOT_AVAILABLE;
  private Character courseBase = ' ';
  private int courseType;
  private int period;
  private double credits;
  private boolean numericGrade;

  public Course(){}

  public Course(
    String name, final int code, 
    Character courseBase, final int type, 
    final int period, final double credits, 
    boolean numericGrade
  ) {
    setName(name);
    setCourseCode(code, courseBase);
    setCourseType(type);
    setPeriod(period);
    setCredits(credits);
    setNumericGrade(numericGrade);
  }

  public Course(Course course){
    this.name = course.getName();
    this.courseCode = course.getCourseCode();
    this.courseBase = course.getCourseBase();
    this.courseType = course.getCourseType();
    this.period = course.getPeriod();
    this.credits = course.getCredits();
    this.numericGrade = course.isNumericGrade();
  }

  public String getName(){
    return name;
  }

  public void setName(String name){
    if(name != null && !name.equals("")){
      this.name = name;
    }
  }

  public String getCourseTypeString(){
    return courseType == 0 ? "Optional" : "Mandatory";
  }

  public int getCourseType(){
    return courseType;
  }

  public void setCourseType(final int type) {
    if(type == 1 || type == 0){
      this.courseType = type;
    }
  }

  public String getCourseCode(){
    return courseCode;
  }

  public void setCourseCode(final int courseCode, Character courseBase){
    boolean codeValid = courseCode > 0 && courseCode < 1000000;
    Character baseUpper = Character.toUpperCase(courseBase);
    boolean baseValid = baseUpper == 'A' || baseUpper == 'P' || baseUpper == 'S';

    if(codeValid && baseValid){
      this.courseCode = Integer.toString(courseCode) + baseUpper;
      this.courseBase = baseUpper;
    }
  }

  public Character getCourseBase(){
    return courseBase;
  }

  public int getPeriod(){
    return period;
  }

  public void setPeriod(final int period){
    if(period <= ConstantValues.MAX_PERIOD && period >= ConstantValues.MIN_PERIOD){
      this.period = period;
    }
  }

  public double getCredits(){
    return credits;
  }

  private void setCredits(final double credits){
    if(credits <= ConstantValues.MAX_COURSE_CREDITS && credits >= ConstantValues.MIN_CREDITS){
      this.credits = credits;
    }
  }

  public boolean isNumericGrade(){
    return numericGrade;
  }

  public void setNumericGrade(boolean numericGrade){
    this.numericGrade = numericGrade;
  }

  @Override
  public String toString() {
    return String.format(
      "[%s (%5.2f cr), \"%s\". %s, period: %d.]", 
      getCourseCode(), getCredits(), getName(), 
      getCourseTypeString(), getPeriod()
    );
  }
}
