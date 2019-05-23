package com.module.connect;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.module.common.Constant.*;

public class DBConnect {
  public static Connection getConnecttion() {
    Connection cons = null;
    try {
      Class.forName(JDBC_DRIVER);
      cons = DriverManager.getConnection(URL_DB, USER, PASSWORD);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("data: "+cons);
    return cons;
  }

  public static void main(String[] args) {
    System.out.println(getConnecttion());
  }
}
