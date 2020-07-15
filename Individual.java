
/**
 *
 * @author
 */
public abstract class Individual {

    private String fName;
    private String lName;
    private String DOB;

    /**
     * default constructor
     */
    public Individual() {
        fName = "Unknown";
        lName = "Unknown";
        DOB = "7/4/2000";
    }

    /**
     * constructor
     *
     * @param fName first name
     * @param lName last name
     * @param DOB
     */
    public Individual(String fName, String lName, String DOB) {
        this.fName = fName;
        this.lName = lName;
        this.DOB = DOB;
    }

    /**
     * getter
     *
     * @return first name
     */
    public String getfName() {
        return fName;
    }

    /**
     * getter
     *
     * @return last name
     */
    public String getlName() {
        return lName;
    }

    /**
     * getter
     *
     * @return full name
     */
    public String getName() {
        return fName + " " + lName;
    }

    /**
     * getter
     *
     * @return
     */
    public String getDOB() {
        return DOB;
    }

    /**
     * setter
     *
     * @param fName first name
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * setter
     *
     * @param lName last name
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * setter
     *
     * @param DOB
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getFullName() {
        return fName + " " + lName;
    }

    /**
     * Handle individuals with the same first name or same last name (assume
     * that users will have either same first names or same last names, not
     * both)
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Individual individual = (Individual) obj;
        return this.getFullName().equals(individual.getFullName());
    }

    /**
     *
     * @return expression of this object
     */
    @Override
    public String toString() {
        String str = "First name: " + fName + System.lineSeparator()
                + "Last Name: " + lName + System.lineSeparator()
                + "DOB: " + DOB;
        return str;
    }
}
