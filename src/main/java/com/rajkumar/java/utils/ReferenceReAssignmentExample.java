package com.rajkumar.java.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Constants;

/**
 * References example.
 * 
 * @author Rajkumar
 *
 */
public class ReferenceReAssignmentExample {

  private String name = "Hello ==> 1";
  private static Logger logger = LogManager.getLogger();

  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    ReferenceReAssignmentExample refExample = new ReferenceReAssignmentExample();
    logger.info("Actual First = " + refExample.name);

    refExample.name = "Hello ==> 2";
    logger.info("Modified Now = " + refExample.name);

    callAnotherMethod(refExample);
    logger.info("After Method returns = " + refExample.name);

    logger.info(Constants.LINE_SEPERATOR);
  }

  /**
   * To check changed variable value.
   * 
   * @param refExample parameter to be checked
   */
  private static void callAnotherMethod(ReferenceReAssignmentExample refExample) {

    logger.info("Received Inside Method = " + refExample.name);

    refExample.name = "Hello ==> 3";
    logger.info("Modified Inside Method = " + refExample.name);

    refExample = new ReferenceReAssignmentExample();  //NOSONAR
    logger.info("After ReAssignment = " + refExample.name);

    refExample.name = "Hello ==> 4";
    logger.info("After ReAssignment Inside Method = " + refExample.name);

  }
}
