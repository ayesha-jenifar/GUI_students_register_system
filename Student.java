package com.company;

public class Student {
    private String SFirstName;
    private String SLastName;
    private int    SStudentId;
    private String SAddress;
    private int    SPostalCode;
    private String SCity;
    private String SEmail;
    private String SDegree;
    private String SDegreeProgram;
    private String SDepartment;
    private int    SCreditCompleted;
    private int    SCreditRemaing;
    private int    STotalCredit = 240;

    public Student(){
        this.SFirstName = null;
        this.SLastName = null;
        this.SStudentId = 0;
        this.SAddress = null;
        this.SPostalCode = 0;
        this.SCity = null;
        this.SEmail = null;
        this.SDegree = null;
        this.SDegreeProgram = null;
        this.SDepartment = null;
        this.SCreditCompleted = 0;
        setCreditRemaining();

    }

    public Student(String firstname, String lastname, int studentid, String address, int postalcode, String city, String email, String degree, String degreeprogram, String department, int creditcompleted){
        this.SFirstName = firstname;
        this.SLastName = lastname;
        this.SStudentId = studentid;
        this.SAddress = address;
        this.SPostalCode = postalcode;
        this.SCity = city;
        this.SEmail = email;
        this.SDegree = degree;
        this.SDegreeProgram = degreeprogram;
        this.SDepartment = department;
        this.SCreditCompleted = creditcompleted;
        setCreditRemaining();
    }

    //GETTERS FOR STUDENT INFORMATION
    public String getFirstName(){ return SFirstName; }
    public String getLastName(){ return SLastName; }
    public int getStudentId(){ return SStudentId; }
    public String getAddress(){ return SAddress; }
    public int getPostalCode(){ return SPostalCode; }
    public String getCity(){ return SCity; }
    public String getEmail(){ return SEmail; }
    public String getDegree(){ return SDegree; }
    public String getDegreeProgram(){ return SDegreeProgram; }
    public String getDepartment(){ return SDepartment; };
    public int getCreditCompleted(){    return SCreditCompleted;}
    public int getCreditRemaing() { return SCreditRemaing;}


    //SETTERS FOR STUDENT INFORMATION
    public void setFirstName(String firstname){ this.SFirstName = firstname; }
    public void setLastName(String lastname){
        this.SLastName = lastname;
    }
    public void setStudentId(int studentid){
        this.SStudentId = studentid;
    }
    public void setAddress(String address){
        this.SAddress = address;
    }
    public void setPostalCode(int postalcode){
        this.SPostalCode = postalcode;
    }
    public void setCity(String city){
        this.SCity = city;
    }
    public void setEmail(String email){
        this.SEmail = email;
    }
    public void setDegree(String degree){
        this.SDegree = degree;
    }
    public void setDegreeProgram(String degreeprogram){
        this.SDegreeProgram = degreeprogram;
    }
    public void setDepartment(String department){
        this.SDepartment = department;
    }
    public void setCreditCompleted(int creditcompleted){
        this.SCreditCompleted = creditcompleted;
    }
    public void setCreditRemaining(){ this.SCreditRemaing = STotalCredit - SCreditCompleted; }


    //RETURN STUDENT INFORMATION AS STRING
    public String toString(){
        return "FIRST NAME: " + SFirstName + " \nLAST NAME: " + SLastName + "\nSTUDENT ID: " + SStudentId +
                "\nADDRESS: " + SAddress + "\nPOSTAL CODE: " + SPostalCode + "\nCITY: " + SCity +
                "\nEMAIL: " + SEmail + "\nDEGREE: " + SDegree + "\nDEGREE PROGRAM: " + SDegreeProgram +
                "\nDEPARTMENT: " + SDepartment + "\nCREDIT COMPLETED: " + SCreditCompleted + "\nCREDIT REMAINING: " + SCreditRemaing + "\nTOTAL CREDIT: " + STotalCredit;
    }

}
