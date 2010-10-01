package com.gain.api;

/**
 * Title:        Margin Trader Development
 * Description:  Simple XML Node Parser
 * Copyright:    Copyright (c) 2001
 * Company:      Gain Capital
 * @author       Phil Cave
 * @version 1.0
 */

/* @PHILCAVE - Added File */

/**
 * SimpleXMLNodeParser.
 * Parses XML Content using String functions.  Requires the XML
 * be well formed. Does do any schema checking
 */
public class SimpleXMLNodeParser {

  // NOTE: Usually these would be < >.  However we are
  // dealing with Microsoft XML Content from Soap Service
  // This is encoded as data is contained within a string tag
  private static final String LEFT_BRACE = "&lt;";
  private static final String RIGHT_BRACE = "&gt;";

  private String xml_;
  private int    index_;

  /**
   * Constructs the Node Parser with the specified xml content
   */
  public SimpleXMLNodeParser( String xml ) {
    xml_ = xml;
    index_=0;
  }

  /**
   * getNextNode - Get the contents of the contents of the specified node name;
   */
  String getNextNode( String nodeName ) {

    String content = null;

    // Get the index of the start node
    String startNode = LEFT_BRACE + nodeName + RIGHT_BRACE;
    int startIndex = xml_.indexOf( startNode, index_ );

    // If we have a start, get the end node
    if( startIndex != -1 ) {
      String endNode = LEFT_BRACE + "/" + nodeName  +  RIGHT_BRACE;
      int endIndex   = xml_.indexOf( endNode, startIndex + startNode.length() );

      // set the content and the index for the Next Node
      content = xml_.substring( startIndex + startNode.length(), endIndex );
      index_ = endIndex + endNode.length();
    }

    // return the content
    return content;
  }

}