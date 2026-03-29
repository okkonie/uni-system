public class PersonID {
  private String birthDate = ConstantValues.NO_BIRTHDATE;

  public String getBirthDate() {
    return birthDate;
  }

  public String setPersonId(final String personID){
    if(personID == null || !checkPersonIdNumber(personID)){
      return ConstantValues.INVALID_BIRTHDAY;
    }

    String yearStart = 
      personID.charAt(6) == '+' ? "18" 
      : personID.charAt(6) == '-' ? "19" 
      : "20";

    String bd = 
      personID.substring(0, 2) + "." 
      + personID.substring(2, 4) + "."
      + yearStart + personID.substring(4, 6);

    if(!checkBirthDate(bd)){
      return ConstantValues.INVALID_BIRTHDAY;
    }

    if(!checkValidCharacter(personID)){
      return ConstantValues.INCORRECT_CHECKMARK;
    }

    return "Ok";
  }

  private boolean checkPersonIdNumber(final String personID){
    return (
      personID.length() == 11 &&
      (personID.charAt(6) == '+' 
      || personID.charAt(6) == '-' 
      || personID.charAt(6) == 'A')
    );
  }

  private boolean checkLeapYear(int year){
    return year % 400 == 0 ? true : year % 100 == 0 ? false : year % 4 == 0;
  }

  private boolean checkValidCharacter(final String personID){
    String nString = personID.substring(0, 6) + personID.substring(7, 10);
    double n = Double.parseDouble(nString) / 31;
    n -= (int)n;
    n *= 31;

    int correctNum = (int)Math.round(n);

    char[] chars = {
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 
      'B', 'C', 'D', 'E', 'F', 'H', 'J', 'K', 'L', 'M', 'N',
      'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
    };

    char correctChar = chars[correctNum];
    return personID.charAt(10) == correctChar;
  }

  private boolean checkBirthDate(final String date){

    if(date == null){
      return false;
    }

    String[] dates = date.split("\\.");
    int day = Integer.parseInt(dates[0]);
    int month = Integer.parseInt(dates[1]);
    int year = Integer.parseInt(dates[2]);
    boolean isLeap = checkLeapYear(year);
    int daysInFeb = isLeap ? 29 : 28;
    int[] daysInMonth = {31, daysInFeb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if(day < 1 || month < 1 || month > 12 || year < 1 || day > daysInMonth[month - 1]){
      return false;
    }

    this.birthDate = date;
    return true;
  }
}
