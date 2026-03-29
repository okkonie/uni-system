import java.time.Year;

public abstract class Person {
  private String firstName = ConstantValues.NO_NAME;
  private String lastName = ConstantValues.NO_NAME;
  private String birthDate = ConstantValues.NO_BIRTHDATE;

  public int getCurrentYear(){
    return Year.now().getValue();
  }

  public Person(String lname, String fname){
    if(lname != null){
      this.lastName = lname;
    }
    if(fname != null){
      this.firstName = fname;
    }
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    if(firstName != null){
      this.firstName = firstName;
    }
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    if(lastName != null){
      this.lastName = lastName;
    }
  }

  public String getBirthDate(){
    return birthDate;
  }

  public String setBirthDate(String personId){
    if(personId == null){
      return "No change";
    }
    
    PersonID id = new PersonID();
    String check = id.setPersonId(personId);

    if(check.equals("Ok")){
      this.birthDate = id.getBirthDate();
      return this.birthDate;
    }

    return "No change";
  }

  
  protected int getRandomId(final int min, final int max){
    return (int)((Math.random() * (max - min)) + min);
  }

  public abstract String getIdString();
}
