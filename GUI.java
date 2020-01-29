package com.company;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class GUI implements ActionListener{
    //VARIABLES FOR JFRAME MAIN WINDOW, JPANEL HEADER, JLABEL TEXT HEADER AND PROGRAM INFO
    private JFrame mainWindow;
    private JPanel HeaderLabel;
    private JLabel HeaderText;

    //VARIABLES FOR JBUTTONS FOR ADDING, VIEWING, EDITING, DELETING AND CLEAR STUDENT INFORMATION
    private JButton Add;
    private JButton View;
    private JButton Edit;
    private JButton Delete;
    private JButton Clear;

    //VARIABLES TABLE PROPERTIES: TEXT AREA AND CLEAR TEXT INFORMATION
    private JTextArea Table;
    private JButton ClearTableInformation;

    //VARIABLES FOR FILE SECTION JBUTTONS: CREATE, READ,WRITE TEXT FROM/TO A FILE; ALSO READ FROM JSON TEST FILE
    private JButton CreateTextFile;
    private JButton ReadTextFile;
    private JButton WriteToFile;
    private JButton DataFromWEB;

    // VARIABLES FOE LABEL AND TEXT INPUTS THE STUDENT INFORMATION;
    private JLabel FirstNameLabel;
    private JTextField FirstNameTextField;
    private JLabel LastNameLabel;
    private JTextField LastNameTextField;
    private JLabel StudentIdLabel;
    private  JTextField StudentIdTextField;
    private JLabel AddressLabel;
    private JTextField AddressTextField;
    private JLabel PostalCodeLabel;
    private JTextField PostalCodeTextField;
    private JLabel CityLabel;
    private JTextField CityTextField;
    private JLabel EmailLabel;
    private JTextField EmailTextField;
    private JLabel DegreeLabel;
    private JTextField DegreeTextField;
    private JLabel DegreeProgramLabel;
    private JTextField DegreeProgramTextField;
    private JLabel DepartmentLabel;
    private JTextField DepartmentTextField;
    private JLabel CreditCompletedLabel;
    private JTextField CreditCompletedTextField;

    //VARIABLES FOR USER INPUTS FOR STUDENT INFORMATION
    private String FirstName;
    private String LastName;
    private int    StudentId;
    private String Address;
    private int    PostalCode;
    private String City;
    private String Email;
    private String Degree;
    private String DegreeProgram;
    private String Department;
    private int    CreditCompleted;


    StudentLists studentlist = new StudentLists();


    public void actionPerformed(ActionEvent e) {
        //IF USER CHOOSE THE ADD BUTTON THEN INPUTS FROM THE CORRESPONDING TEXT FIELD TO THE STUDENT INFORMATION VARIABLE
        if (e.getSource() == Add) {

            FirstName = FirstNameTextField.getText();
            LastName = LastNameTextField.getText();
            Address = AddressTextField.getText();
            City = CityTextField.getText();
            Email = EmailTextField.getText();
            Degree = DegreeTextField.getText();
            DegreeProgram = DegreeProgramTextField.getText();
            Department = DepartmentTextField.getText();

            //IF USER PUT BLANK FIELD THEN IT WILL SHOW THE FOLLOWING MESSAGES
            if (FirstName.equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "FIRST NAME IS MANDATORY");
            }
            if (LastName.equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "LAST NAME IS MANDATORY");
            }
            if (StudentIdTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "STUDENT ID IS MANDATORY");
            }
            if (Address.equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "ADDRESS IS MANDATORY");
            }
            if (PostalCodeTextField.equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "POSTAL CODE IS MANDATORY");
            }
            if (City.equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "CITY IS MANDATORY");
            }
            if (Email.equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "EMAIL IS MANDATORY");
            }
            if (Degree.equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "DEGREE IS MANDATORY");
            }
            if (DegreeProgram.equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "DEGREE PROGRAM IS MANDATORY");
            }
            if (Department.equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "DEPARTMENT IS MANDATORY");
            }
            if (CreditCompletedTextField.equals("")) {
                JOptionPane.showMessageDialog(mainWindow, "CREDIT COMPLETED IS MANDATORY");
            }

            try {
                StudentId = Integer.parseInt(StudentIdTextField.getText());
                // IF STUDENT ID ALREADY EXISTS THEN IT WILL POP THE FOLLOWING MESSAGE ELSE IT WILL SAVE TO THE STUDENT LIST ARRAY
                if (studentlist.searchstudentInfobyID(StudentId) != null) {
                    JOptionPane.showMessageDialog(mainWindow, "ENTRY ALREADY EXISTS");
                } else {
                    PostalCode = Integer.parseInt(PostalCodeTextField.getText());
                    CreditCompleted = Integer.parseInt(CreditCompletedTextField.getText());
                    studentlist.addStudent(new Student(FirstName, LastName, StudentId, Address, PostalCode, City, Email, Degree, DegreeProgram, Department, CreditCompleted));
                }
            } catch (NumberFormatException e1) { //IF THE NUMBER FORMAT IS DIFFERENT THAN INT THEN IT WILL SHOW FOLLOWING MESSAGE
                JOptionPane.showMessageDialog(mainWindow, "Please Provide Integer value in Student id, Postal Code or Credit Completed Field");
            }

        }

        //IF USER CHOOSE VIEW BUTTON THEN IT WILL SHOW THE STUDENT INFORMATION TO THE TEXT AREA
        if (e.getSource() == View) {
            try {
                int index = 1;
                Table.setText("\t\tWelcome To Student Register System");
                //FILL UP THE TEXT AREA BY PRINTING THE STUDENT INFORMATION
                for (int i = 0; i < studentlist.arrayLength(); i++) {
                    String[] s = studentlist.printStudentInfo();
                    Table.append("\nSTUDENT ENTRY NUMBER: " + index);
                    Table.append("\n" + s[i]);
                    Table.append("\n");
                    index++;
                }
            } catch (NullPointerException e1) {//IF NULL POINTER THEN THIS MESSAGE WILL POP UP
                JOptionPane.showMessageDialog(mainWindow, "NullPointerException");
            } catch (ArrayIndexOutOfBoundsException e1) {//IF Array Index Out Of Bounds Exception OCCURS
                JOptionPane.showMessageDialog(mainWindow, "ArrayIndexOutOfBoundsException");
            }

        }

        //IF USER CHOOSE EDIT BUTTON THEN USER WILL BE ASKED TO PROVIDE TO THE STUDENT ID. THE CORRESPONDING STUDENT ID WILL BE SEARCHED AND THE USER WILL BE ASKED TO WHICH INFORMATION NEED TO BE EDITED.
        if (e.getSource() == Edit) {
            try {
                int SearchByStudentId = Integer.parseInt(JOptionPane.showInputDialog(mainWindow, "Search by Student Id"));//GET THE STUDENT ID
                Student InformationToEdit = studentlist.searchstudentInfobyID(SearchByStudentId);//SAVE TO THE InformationToEdit

                if (InformationToEdit == null) {//IF InformationToEdit IS NULL THE FOLLOWING MESSAGE WILL POP UP
                    JOptionPane.showMessageDialog(mainWindow, "PLEASE PROVIDE VALID ID e.g. 123456");
                } else {// USER WILL BE ASKED FOR CHOOSING THE OPTION TO SELECT WHICH INFORMATION HE/SHE WANTS EDIT
                    Table.setText(String.valueOf(studentlist.searchstudentInfobyID(SearchByStudentId)));
                    int EditElement = Integer.parseInt(JOptionPane.showInputDialog(mainWindow, "PLEASE CHOOSE FOLLOWING OPTIONS (1-8)\n1. All\n2. FIRST NAME \n3.LAST NAME" +
                            "\n4.ADDRESS\n5.POSTAL CODE\n6.CITY\n7.EMAIL ID\n8.DEGREE\n9.DEGREE PROGRAM\n10.DEPARTMENT\n11.CREDIT COMPLETED"));

                    //TO EDIT ALL INFORMATION
                    if (EditElement == 1) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT ALL ELEMENT" );
                        String FirstName = JOptionPane.showInputDialog(mainWindow, "FIRST NAME");
                        InformationToEdit.setFirstName(FirstName);
                        String LastName = JOptionPane.showInputDialog(mainWindow, "LAST NAME");
                        InformationToEdit.setLastName(LastName);
                        String Address = JOptionPane.showInputDialog(mainWindow, "ADDRESS");
                        InformationToEdit.setAddress(Address);
                        int PostalCode = Integer.parseInt(JOptionPane.showInputDialog(mainWindow, "POSTAL CODE"));
                        InformationToEdit.setPostalCode(PostalCode);
                        String City = JOptionPane.showInputDialog(mainWindow, "CITY");
                        InformationToEdit.setCity(City);
                        String EmailId = JOptionPane.showInputDialog(mainWindow, "EMAIL ID");
                        InformationToEdit.setEmail(EmailId);
                        String Degree = JOptionPane.showInputDialog(mainWindow, "DEGREE");
                        InformationToEdit.setDegree(Degree);
                        String DegreeProgram = JOptionPane.showInputDialog(mainWindow, "DEGREE PROGRAM");
                        InformationToEdit.setDegreeProgram(DegreeProgram);
                        String Department = JOptionPane.showInputDialog(mainWindow, "DEPARTMENT");
                        InformationToEdit.setDepartment(Department);
                        int CreditCompleted = Integer.parseInt(JOptionPane.showInputDialog(mainWindow, "CREDIT COMPLETED"));
                        InformationToEdit.setCreditCompleted(CreditCompleted);
                        JOptionPane.showMessageDialog(mainWindow, "ENTRY SUCCESSFUL CHECK THE TEXT TABLE");
                        InformationToEdit.setCreditRemaining();
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                    //TO EDIT FIRST NAME ONLY
                    if (EditElement == 2) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT FIRST NAME" );
                        String FirstName = JOptionPane.showInputDialog(mainWindow, "FIRST NAME");
                        InformationToEdit.setFirstName(FirstName);
                        JOptionPane.showMessageDialog(mainWindow, InformationToEdit.getFirstName());
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                    //TO EDIT LAST NAME ONLY
                    else if (EditElement == 3) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT LAST NAME" );
                        String LastName = JOptionPane.showInputDialog(mainWindow, "LAST NAME");
                        InformationToEdit.setLastName(LastName);
                        JOptionPane.showMessageDialog(mainWindow, InformationToEdit.getLastName());
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                    //TO EDIT ADDRESS ONLY
                    else if (EditElement == 4) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT ADDRESS" );
                        String Address = JOptionPane.showInputDialog(mainWindow, "ADDRESS");
                        InformationToEdit.setAddress(Address);
                        JOptionPane.showMessageDialog(mainWindow, InformationToEdit.getAddress());
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                    //TO EDIT POSTAL CODE ONLY
                    else if (EditElement == 5) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT POSTAL CODE" );
                        int PostalCode = Integer.parseInt(JOptionPane.showInputDialog(mainWindow, "POSTAL CODE"));
                        InformationToEdit.setPostalCode(PostalCode);
                        JOptionPane.showMessageDialog(mainWindow, InformationToEdit.getPostalCode());
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                    //TO EDIT CITY ONLY
                    else if (EditElement == 6) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT CITY" );
                        String City = JOptionPane.showInputDialog(mainWindow, "CITY");
                        InformationToEdit.setCity(City);
                        JOptionPane.showMessageDialog(mainWindow, InformationToEdit.getCity());
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                    //TO EDIT EMAIL ID ONLY
                    else if (EditElement == 7) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT EMAIL ID" );
                        String EmailId = JOptionPane.showInputDialog(mainWindow, "EMAIL ID");
                        InformationToEdit.setEmail(EmailId);
                        JOptionPane.showMessageDialog(mainWindow, InformationToEdit.getEmail());
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                    //TO EDIT DEGREE ONLY
                    else if (EditElement == 8) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT DEGREE" );
                        String Degree = JOptionPane.showInputDialog(mainWindow, "DEGREE");
                        InformationToEdit.setDegree(Degree);
                        JOptionPane.showMessageDialog(mainWindow, InformationToEdit.getDegree());
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                    //TO EDIT DEGREE PROGRAM ONLY
                    else if (EditElement == 9) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT DEGREE PROGRAM" );
                        String DegreeProgram = JOptionPane.showInputDialog(mainWindow, "DEGREE PROGRAM");
                        InformationToEdit.setDegreeProgram(DegreeProgram);
                        JOptionPane.showMessageDialog(mainWindow, InformationToEdit.getDegreeProgram());
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                    //TO EDIT DEPARTMENT ONLY
                    else if (EditElement == 10) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT DEPARTMENT" );
                        String Department = JOptionPane.showInputDialog(mainWindow, "DEPARTMENT");
                        InformationToEdit.setDepartment(Department);
                        JOptionPane.showMessageDialog(mainWindow, InformationToEdit.getDepartment());
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                    //TO EDIT CREDIT COMPLETED ONLY
                    else if (EditElement == 11) {
                        JOptionPane.showMessageDialog(mainWindow, "YOU CHOOSE TO EDIT CREDIT COMPLETED\"" );
                        int CreditCompleted = Integer.parseInt(JOptionPane.showInputDialog(mainWindow, "CREDIT COMPLETED"));
                        InformationToEdit.setCreditCompleted(CreditCompleted);
                        JOptionPane.showMessageDialog(mainWindow, InformationToEdit.getCreditCompleted());
                        InformationToEdit.setCreditRemaining();
                        Table.setText(String.valueOf(InformationToEdit));
                    }
                }
            } catch (NumberFormatException e1) {//IF STUDENT NUMBER IS GIVEN AS WRONG FORMAT
                JOptionPane.showMessageDialog(mainWindow, "PLEASE PROVIDE CORRECT FORMAT NUMBER e.g. 123456");
            }
        }

        //IF USER CHOOSE DELETE BUTTON THEN USER WILL BE ASKED TO PROVIDE TO STUDENT ID. THE CORRESPONDING STUDENT ID WILL BE SEARCHED AND DELETED.
        if (e.getSource() == Delete) {
            try {
                int SearchByStudentId = Integer.parseInt(JOptionPane.showInputDialog(mainWindow, "SEARCH BY STUDENT ID"));
                Student InformationToEdit = studentlist.searchstudentInfobyID(SearchByStudentId);
                //IF NULL IT WILL SHOW THE FOLLOWING MESSAGE
                if (InformationToEdit == null) {
                    JOptionPane.showMessageDialog(mainWindow, "PLEASE PROVIDE VALID ID e.g. 123456");
                }
                studentlist.deleteStudentInfobyID(SearchByStudentId);//DELETE FROM THE STUDENT LIST OBJECT ARRAY
            } catch (NumberFormatException e1) {//IF THE NUMBER FORMAT IS DIFFERENT THEN IT WILL SHOW THE FOLLOWING MESSAGE
                JOptionPane.showMessageDialog(mainWindow, "PLEASE PROVIDE CORRECT FORMAT NUMBER e.g. 123456");
            }
        }

        //IF USER CHOOSE CLEAR BUTTON THEN EACH TEXT FIELD WILL BE CLEARED
        if (e.getSource() == Clear) {
            //SET ALL TEXT FIELD TO NULL/CLEARED
            FirstNameTextField.setText(null);
            LastNameTextField.setText(null);
            StudentIdTextField.setText(null);
            AddressTextField.setText(null);
            PostalCodeTextField.setText(null);
            CityTextField.setText(null);
            EmailTextField.setText(null);
            DegreeTextField.setText(null);
            DegreeProgramTextField.setText(null);
            DepartmentTextField.setText(null);
            CreditCompletedTextField.setText(null);
        }

        //IF USER CHOOSE ClearTableInformation BUTTON THEN THE TEXT AREA WILL BE CLEARED
        if (e.getSource() == ClearTableInformation) {
            Table.setText(null);
        }

        //CreateTextFile BUTTON WILL ASKED THE USER TO PROVIDE A FILE NAME TO BE CREATED. IF THE FILE NAME IS ALREADY EXISTED OR EMPTY FIELD THEN IT WILL PROVIDE A WARNING MESSAGE
        if (e.getSource() == CreateTextFile) {
            try {
                String FileName = JOptionPane.showInputDialog(mainWindow, "TYPE FILENAME FOR A FILE TO BE CREATED: ");

                if (FileName.equals("")) {
                    JOptionPane.showMessageDialog(mainWindow, "FILE NAME CANNOT BE EMPTY STRING");
                } else {
                    File myFile = new File(FileName + ".txt");
                    if (myFile.exists()) {
                        JOptionPane.showMessageDialog(mainWindow, "FILE ALREADY EXISTS");
                    } else {
                        System.out.println(FileName);
                        JOptionPane.showMessageDialog(mainWindow, myFile.getAbsolutePath());//PROVIDE THE PATH OF THE FILE
                        myFile.createNewFile();
                    }
                }
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(mainWindow, "IOException");
            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(mainWindow, "NULL POINTER");
            }
        }
        // WriteToFile BUTTON WILL ASKED THE USER TO PROVIDE A FILE NAME(WHICH ALREADY EXISTS) TO WRITE THE STUDENT INFORMATION ON IT.
        if (e.getSource() == WriteToFile) {
            try {
                String FileName = JOptionPane.showInputDialog(mainWindow, "TYPE FILENAME FOR A FILE TO WRITE THE STUDENT INFORMATION: ");
                if (FileName.equals("")) {
                    JOptionPane.showMessageDialog(mainWindow, "FILE NAME CANNOT BE EMPTY STRING");
                } else {
                    File myFile = new File(FileName + ".txt");
                    if (myFile.exists()) {//IF myFile EXISTS THEN WILL SAVE THE STUDENT INFORMATION
                        JOptionPane.showMessageDialog(mainWindow, myFile.getAbsolutePath());
                        PrintWriter printWriter = new PrintWriter(myFile);
                        int index = 1;
                        printWriter.print("");
                        for (int i = 0; i < studentlist.arrayLength(); i++) {
                            String[] s = studentlist.printStudentInfo(); //TAKE STUDENT INFORMATION AS STRING AND SAVE TO THE STRING ARRAY s
                            printWriter.print("\nSTUDENT ENTRY NUMBER: " + index);
                            printWriter.print("\n" + s[i]);// PRINT/WRITE TO THE GIVEN FILE
                            printWriter.print("\n");
                            index++;
                        }
                        printWriter.close();//CLOSE THE FILE
                    } else {//IF FILE DOES NOT EXIST
                        JOptionPane.showMessageDialog(mainWindow, "FILE DOES NOT EXISTS");
                    }
                }
            } catch (FileNotFoundException e1) {//HANDLE FileNotFoundException
                JOptionPane.showMessageDialog(mainWindow, "FILE NOT FOUND");
            } catch (NullPointerException e1) {//HANDLE NullPointerException
                JOptionPane.showMessageDialog(mainWindow, "NULL POINTER");
            }
        }

        // ReadTextFile BUTTON WILL ASKED THE USER TO PROVIDE A FILE NAME(WHICH ALREADY EXISTS) TO READ THE STUDENT INFORMATION ON IT.
        if (e.getSource() == ReadTextFile) {
            try {
                String FileName = JOptionPane.showInputDialog(mainWindow, "TYPE FILENAME FOR A FILE TO READ THE STUDENT INFORMATION:");
                if (FileName.equals("")) {
                    JOptionPane.showMessageDialog(mainWindow, "FILE NAME CANNOT BE EMPTY STRING");
                } else {
                    File myFile = new File(FileName + ".txt");
                    String TextLine;
                    if (myFile.exists()) {//IF FILE EXISTS
                        FileReader fileReader = new FileReader(myFile);
                        BufferedReader myReader = new BufferedReader(fileReader);
                        Table.setText("\t\tREAD FROM " + FileName + " .txt FILE");
                        while ((TextLine = myReader.readLine()) != null) {//READ LINE
                            if (!TextLine.startsWith(">")) {
                                Table.append(TextLine + "\n");//PRINT ON TEXT AREA
                            }
                        }
                        myReader.close();//CLOSE THE FILE
                    } else {//IF FILE DOES NOT EXIST
                        JOptionPane.showMessageDialog(mainWindow, "FILE DOES NOT EXISTS");
                    }
                }
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(mainWindow, "IOException");
            } catch (NullPointerException e1) {//HANDLE NullPointerException
                JOptionPane.showMessageDialog(mainWindow, "NULL POINTER");

            }
        }

        // DataFromWEB BUTTON WILL READ TEST.JSON FILE TO READ THE STUDENT INFORMATION ON IT. THE PRIMARY GOAL WAS TO READ FROM THE WEBSITE BUT DUE UNAVAILABILITY
        // OF WEBSITE THIS FILE WAS IMPLEMENTED ON JSON FILES
        if (e.getSource() == DataFromWEB) {
            try {
                int JsonReadingOption = Integer.parseInt(JOptionPane.showInputDialog(mainWindow, "READ JSON FILE FROM \n1.NETWORK(SAMPLE DATA)\n2.LOCAL FILE(test.json)"));

                if (JsonReadingOption == 1) {
                    try {
                        String website = "https://jsonplaceholder.typicode.com/todos/1";
                        URL UrlObj = new URL(website);
                        HttpURLConnection Connection = (HttpURLConnection) UrlObj.openConnection();
                        BufferedReader FromWebJson = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
                        String FromWebJsonInputLine;
                        StringBuffer FromWebJsonInputStream = new StringBuffer();
                        while ((FromWebJsonInputLine = FromWebJson.readLine()) != null) {
                            FromWebJsonInputStream.append(FromWebJsonInputLine);
                        }
                        FromWebJson.close();

                        //Read JSON response and Print in Table
                        Table.setText("\tREAD SAMPLE JSON DATA FROM THE WEBSITE");
                        JSONObject jsonObject = new JSONObject(FromWebJsonInputStream.toString());
                        Table.append("\nTITLE: " + jsonObject.getString("title"));
                        Table.append("\nUSER ID: " + (jsonObject.getInt("userId")));
                    } catch (MalformedURLException e1) {
                        JOptionPane.showMessageDialog(mainWindow, "MalformedURLException");
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(mainWindow, "IOException");
                    }
                } else if (JsonReadingOption == 2) {
                    try {
                        File myFile = new File("test" + ".json");
                        FileReader fileReader = new FileReader(myFile);
                        BufferedReader myReader = new BufferedReader(fileReader);
                        StringBuffer response = new StringBuffer();
                        String inputLine;
                        while ((inputLine = (myReader.readLine())) != null) {
                            response.append(inputLine);
                        }
                        myReader.close();

                        //READ test.json FILE AND SAVE TO THE STUDENT LIST ARRAY AND ALSO PRINT ON THE TABLE
                        JSONObject jsonObject = new JSONObject(response.toString());
                        FirstName = jsonObject.getString("FIRST NAME");
                        LastName = jsonObject.getString("LAST NAME");
                        StudentId = jsonObject.getInt("STUDENT ID");
                        Address = jsonObject.getString("ADDRESS");
                        PostalCode = jsonObject.getInt("POSTAL CODE");
                        City = jsonObject.getString("CITY");
                        Email = jsonObject.getString("EMAIL ID");
                        Degree = jsonObject.getString("DEGREE");
                        DegreeProgram = jsonObject.getString("DEGREE PROGRAM");
                        Department = jsonObject.getString("DEPARTMENT");
                        CreditCompleted = jsonObject.getInt("CREDIT COMPLETED");

                        if (studentlist.searchstudentInfobyID(StudentId) != null) {
                            JOptionPane.showMessageDialog(mainWindow, "ID ALREADY EXISTS");
                        }
                        else {
                            studentlist.addStudent(new Student(FirstName, LastName, StudentId, Address, PostalCode, City, Email, Degree, DegreeProgram, Department, CreditCompleted));
                            try {
                                int index = 1;
                                Table.setText("\t\tREAD FROM JSON FILE");
                                //FILL UP THE TEXT AREA BY PRINTING THE STUDENT INFORMATION
                                for (int i = 0; i < studentlist.arrayLength(); i++) {
                                    String[] s = studentlist.printStudentInfo();
                                    Table.append("\nSTUDENT ENTRY NUMBER: " + index);
                                    Table.append("\n" + s[i]);
                                    Table.append("\n");
                                    index++;
                                }
                            } catch (NullPointerException e1) {//IF NULL POINTER THEN THIS MESSAGE WILL POP UP
                                JOptionPane.showMessageDialog(mainWindow, "Null");
                            } catch (ArrayIndexOutOfBoundsException e1) {
                                JOptionPane.showMessageDialog(mainWindow, "ArrayIndexOutOfBoundsException");
                            }

                        }
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(mainWindow, "IOException");
                    }catch (org.json.JSONException e1){
                        JOptionPane.showMessageDialog(mainWindow, "JSONException");
                    }
                } else {
                    JOptionPane.showMessageDialog(mainWindow, "CHOOSE 1-2");
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(mainWindow, "Number Format Exception");
            }

        }

    }

    public GUI(){

        mainWindow = new JFrame("STUDENT INFORMATION");
        HeaderLabel = new JPanel();
        HeaderText = new JLabel(" STUDENT INFORMATION  ");


        mainWindow.setLayout(null);
        mainWindow.setSize(800,1100);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

        //BUTTONS FOR DATA ENTRY
        Add = new JButton("ADD");
        View = new JButton("VIEW");
        Edit = new JButton("EDIT");
        Delete = new JButton("DELETE");
        Clear = new JButton("CLEAR");

        //Table
        Table = new JTextArea("\t\tWelcome To Student Register System");
        JScrollPane sp = new JScrollPane(Table);
        ClearTableInformation = new JButton("CLEAR TABLE");

        //BUTTON FOR READ/WRITE TO/FROM TEXT FILE
        CreateTextFile = new JButton("CREATE FILE");
        ReadTextFile = new JButton("READ FILE");
        WriteToFile = new JButton("WRITE FILE");
        DataFromWEB = new JButton("FROM JSON");

        //BUTTON CALLBACK
        Add.addActionListener( this );
        Edit.addActionListener( this );
        View.addActionListener( this );
        Delete.addActionListener( this );
        Clear.addActionListener( this );
        CreateTextFile.addActionListener(this);
        ReadTextFile.addActionListener(this);
        ClearTableInformation.addActionListener(this);
        WriteToFile.addActionListener(this);
        DataFromWEB.addActionListener(this);



        //STUDENT INFORMATION BUTTON INITIALIZATION
        FirstNameLabel = new JLabel("FIRST NAME ");
        FirstNameTextField = new JTextField();
        LastNameLabel = new JLabel("LAST NAME ");
        LastNameTextField = new JTextField();
        StudentIdLabel = new JLabel("STUDENT ID (e.g. 123456) ");
        StudentIdTextField = new JTextField();
        AddressLabel = new JLabel("ADDRESS ");
        AddressTextField = new JTextField();
        CityLabel = new JLabel("CITY ");
        CityTextField = new JTextField();
        PostalCodeLabel = new JLabel("POSTAL CODE (e.g. 33100)");
        PostalCodeTextField = new JTextField();
        EmailLabel = new JLabel("EMAIL ID ");
        EmailTextField = new JTextField();
        DegreeLabel = new JLabel("DEGREE ");
        DegreeTextField = new JTextField();
        DegreeProgramLabel = new JLabel("DEGREE PROGRAM  ");
        DegreeProgramTextField = new JTextField();
        DepartmentLabel = new JLabel("DEPARTMENT ");
        DepartmentTextField = new JTextField();
        CreditCompletedLabel = new JLabel("CREDIT COMPLETED (0-240)");
        CreditCompletedTextField = new JTextField();

        //GUI LAYOUT FOR MAINWINDOW, HEADER AND BUTTONS
        mainWindow.add(HeaderText);
        HeaderText.setBounds(200,40,600,25);
        mainWindow.add(HeaderLabel);
        HeaderLabel.setBounds(0,0,800,80);



        mainWindow.add(Add);
        Add.setBounds(40,400,100,25);
        mainWindow.add(View);
        View.setBounds(150,400,100,25);
        mainWindow.add(Edit);
        Edit.setBounds(260,400,100,25);
        mainWindow.add(Delete);
        Delete.setBounds(370,400,120,25);
        mainWindow.add(Clear);
        Clear.setBounds(500,400,100,25);

        //Table Layout Properties
        mainWindow.add(sp);
        sp.setBounds(90,450,500,300);
        mainWindow.add(ClearTableInformation);
        ClearTableInformation.setBounds(630,700,130,25);

        //FILE LAYOUT PROPERTIES
        mainWindow.add(CreateTextFile);
        CreateTextFile.setBounds(630,450,130,25);
        mainWindow.add(ReadTextFile);
        ReadTextFile.setBounds(630,485,130,25);
        mainWindow.add(WriteToFile);
        WriteToFile.setBounds(630,520,130,25);
        mainWindow.add(DataFromWEB);
        DataFromWEB.setBounds(630,600,130,45);

        //GUI LAYOUT FOR STUDENT INFORMATION
        mainWindow.add(FirstNameLabel);
        FirstNameLabel.setBounds(10,100,220,25);
        mainWindow.add(FirstNameTextField);
        FirstNameTextField.setBounds(260,100,300,25);
        mainWindow.add(LastNameLabel);
        LastNameLabel.setBounds(10,125,220,25);
        mainWindow.add(LastNameTextField);
        LastNameTextField.setBounds(260,125,300,25);
        mainWindow.add(StudentIdLabel);
        StudentIdLabel.setBounds(10,150,220,25);
        mainWindow.add(StudentIdTextField);
        StudentIdTextField.setBounds(260,150,70,25);

        mainWindow.add(AddressLabel);
        AddressLabel.setBounds(10,175,220,25);
        mainWindow.add(AddressTextField);
        AddressTextField.setBounds(260,175,300,25);
        mainWindow.add(PostalCodeLabel);
        PostalCodeLabel.setBounds(10,200,220,25);
        mainWindow.add(PostalCodeTextField);
        PostalCodeTextField.setBounds(260,200,70,25);
        mainWindow.add(CityLabel);
        CityLabel.setBounds(10,225,220,25);
        mainWindow.add(CityTextField);
        CityTextField.setBounds(260,225,150,25);
        mainWindow.add(EmailLabel);
        EmailLabel.setBounds(10,250,220,25);
        mainWindow.add(EmailTextField);
        EmailTextField.setBounds(260,250,150,25);

        mainWindow.add(DegreeLabel);
        DegreeLabel.setBounds(10,275,220,25);
        mainWindow.add(DegreeTextField);
        DegreeTextField.setBounds(260,275,200,25);
        mainWindow.add(DegreeProgramLabel);
        DegreeProgramLabel.setBounds(10,300,220,25);
        mainWindow.add(DegreeProgramTextField);
        DegreeProgramTextField.setBounds(260,300,200,25);
        mainWindow.add(DepartmentLabel);
        DepartmentLabel.setBounds(10,325,220,25);
        mainWindow.add(DepartmentTextField);
        DepartmentTextField.setBounds(260,325,200,25);

        mainWindow.add(CreditCompletedLabel);
        CreditCompletedLabel.setBounds(10,350,220,25);
        mainWindow.add(CreditCompletedTextField);
        CreditCompletedTextField.setBounds(260,350,70,25);


        //SET GUI COLOR AND FONT FOR MAINWINDOW, HEADER AND BUTTONS
        mainWindow.getContentPane().setBackground(Color.PINK);
        HeaderLabel.setBackground(Color.DARK_GRAY);
        HeaderText.setForeground(Color.PINK);
        HeaderText.setFont(new Font("Arial Black", Font.ITALIC, 26));

        Add.setForeground(Color.GRAY);
        Add.setFont(new Font("Arial Black", Font.ITALIC, 14));
        View.setForeground(Color.GRAY);
        View.setFont(new Font("Arial Black", Font.ITALIC, 14));
        Edit.setForeground(Color.GRAY);
        Edit.setFont(new Font("Arial Black", Font.ITALIC, 14));
        Delete.setForeground(Color.GRAY);
        Delete.setFont(new Font("Arial Black", Font.ITALIC, 14));
        Clear.setForeground(Color.GRAY);
        Clear.setFont(new Font("Arial Black", Font.ITALIC, 14));

        //SET GUI COLOR AND FONT FOR Table
        Table.setBackground(Color.LIGHT_GRAY);
        Table.setFont(new Font("Arial Black",Font.BOLD,10));
        Table.setForeground(Color.BLACK);
        ClearTableInformation.setForeground(Color.GRAY);
        ClearTableInformation.setFont(new Font("Arial BLACK", Font.ITALIC,13));

        //SET GUI COLOR AND FONT FOR FILE BUTTON
        CreateTextFile.setForeground(Color.GRAY);
        CreateTextFile.setFont(new Font("Arial BLACK", Font.ITALIC,13));
        ReadTextFile.setForeground(Color.GRAY);
        ReadTextFile.setFont(new Font("Arial BLACK", Font.ITALIC,13));
        WriteToFile.setForeground(Color.GRAY);
        WriteToFile.setFont(new Font("Arial BLACK", Font.ITALIC,13));

        DataFromWEB.setForeground(Color.GRAY);
        DataFromWEB.setFont(new Font("Arial BLACK", Font.ITALIC,15));

        //SET GUI COLOR AND FONT FOR STUDENT INFORMATION
        FirstNameLabel.setBackground(Color.BLACK);
        FirstNameLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
        LastNameLabel.setBackground(Color.BLACK);
        LastNameLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
        StudentIdLabel.setBackground(Color.BLACK);
        StudentIdLabel.setFont(new Font("Arial Black", Font.BOLD, 14));

        AddressLabel.setBackground(Color.BLACK);
        AddressLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
        PostalCodeLabel.setBackground(Color.BLACK);
        PostalCodeLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
        CityLabel.setBackground(Color.BLACK);
        CityLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
        EmailLabel.setBackground(Color.BLACK);
        EmailLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
        DegreeLabel.setBackground(Color.BLACK);
        DegreeLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
        DegreeProgramLabel.setBackground(Color.BLACK);
        DegreeProgramLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
        DepartmentLabel.setBackground(Color.BLACK);
        DepartmentLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
        CreditCompletedLabel.setBackground(Color.BLACK);
        CreditCompletedLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
    }
}