package com.svalero.biblioteca.util;

public class Constants {

    public static final String DATABASE = "BIBLIOTECA";
    public static final String USERNAME = "BIBLIOTECA";
    public static final String PASSWORD = "diciembre22";

  //  public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  //  public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/" + DATABAS3E;
  public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
   public static final String CONNECTION_STRING = "jdbc:oracle:thin:@//localhost:1521/XE PDB1";

    public static final String DATE_PATTERN;

    static {
        DATE_PATTERN = "yyyy-MM-dd";
    }
}
