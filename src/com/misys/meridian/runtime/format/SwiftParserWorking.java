package com.misys.meridian.runtime.format;

import com.misys.meridian.devtools.CurrencyEx;
import com.misys.meridian.runtime.message.FieldImpl;
import com.misys.meridian.runtime.message.MessageImpl;
import com.misys.meridian.runtime.message.RepeatBlockImpl;
import com.misys.meridian.userapi.Element;
import com.misys.meridian.userapi.Field;
import com.misys.meridian.userapi.Message;
import com.misys.meridian.userapi.MessageException;
import com.misys.meridian.userapi.MessageFormatException;
import com.misys.meridian.userapi.MessageTransformerProperties;
import com.misys.meridian.userapi.MetaDataException;
import com.misys.meridian.userapi.MetaDataMap;
import com.misys.meridian.userapi.format.BlockInfo;
import com.misys.meridian.userapi.format.FieldAttributes;
import com.misys.meridian.userapi.format.Parser;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;

public class SwiftParserWorking
  extends Parser
{
  public static final String _revision = "$Id: SwiftParser.java,v 1.57.6.2 2010/07/21 07:39:54 placiol1 Exp $";
  private Logger logger = Logger.getLogger(SwiftParserWorking.class.getName());
  private Map startedSequences = new HashMap();
  private String block1;
  private String block2;
  private String block3;
  private String block4;
  private String block5;
  private boolean repeatBlockEndCharacterFound = false;
  private String currentGroup = "";
  private String firstTagInGroup = "";
  private boolean alreadyFound;
  private String trnValue;
  private int headerValueDate;
  private String headerCurrencyCode;
  private String headerAmount;
  private int headerDealMaturityDate;
  private LinkedList block4elements = new LinkedList();
  private String messageName;
  private boolean isCommonGrp;
  private boolean isSystemMessage = false;
  private long field32B = 0L;
  private boolean isMT210 = false;
  
  public void setSerialisedMessage(String message)
  {
    super.setSerialisedMessage(message);
    this.messageName = null;
  }
  
  public String getMessageName()
    throws MessageFormatException
  {
    try
    {
      if (this.logger.isDebugEnabled()) {
        this.logger.debug("In getMessageName with serialisedMessage: \n" + this.serialisedMessage);
      }
      checkForSystemMessages();
      
      int indexOfFirstBlock1 = this.serialisedMessage.indexOf("{1:");
      if (indexOfFirstBlock1 < 0) {
        throw new MessageFormatException("Missing SWIFT Basic Header (block 1)");
      }
      String firstServiceId = this.serialisedMessage.substring(indexOfFirstBlock1 + 4, indexOfFirstBlock1 + 6);
      
      String infNotification = this.serialisedMessage.substring(indexOfFirstBlock1 + 3, indexOfFirstBlock1 + 6);
      
      int indexOfSecondBlock1 = this.serialisedMessage.indexOf("{1:", indexOfFirstBlock1 + 6);
      
      int startOfBlock2 = this.serialisedMessage.indexOf("{2:");
      if ((this.isSystemMessage) && (firstServiceId.equals("21")))
      {
        this.messageName = "SWIFT_AckNak";
        return this.messageName;
      }
      if ((this.isSystemMessage) && (infNotification.equals("INF")))
      {
        this.messageName = "SWIFT_PanNan";
        return this.messageName;
      }
      String msgType = this.serialisedMessage.substring(startOfBlock2 + 4, startOfBlock2 + 7);
      if (this.logger.isDebugEnabled()) {
        this.logger.debug("getMessageName(): msgType = '" + msgType + "'");
      }
      if ((!this.isSystemMessage) && (msgType.charAt(1) == '9'))
      {
        this.messageName = ("SWIFT_MTn" + msgType.substring(1));
        this.isCommonGrp = true;
      }
      else
      {
        this.messageName = ("SWIFT_MT" + msgType);
        this.isCommonGrp = false;
      }
      return this.messageName;
    }
    catch (MessageFormatException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new MessageFormatException("SwiftParser.getMessageName: can't extract message name", e);
    }
  }
  
  public String beginMessage(Message message, boolean isTopLevel)
    throws MessageFormatException
  {
    if (!isTopLevel) {
      throw new MessageFormatException("SwiftParser.begin: cannot parse SWIFT sub-messages");
    }
    if (this.messageName == null) {
      getMessageName();
    }
    if (this.logger.isDebugEnabled()) {
      this.logger.debug("beginMessage(): messageName = '" + this.messageName + "'");
    }
    this.isMT210 = this.messageName.equals("SWIFT_MT210");
    
    int end = -1;
    try
    {
      this.serialisedMessage = checkForCRLF(this.serialisedMessage);
      if (this.logger.isDebugEnabled()) {
        this.logger.debug("In beginMessage with serialisedMessage: \n" + this.serialisedMessage);
      }
      int begin;
      if (this.isSystemMessage)
      {
        int posInfBlock1 = this.serialisedMessage.indexOf("{1:INF");
        //int begin;
        if (posInfBlock1 >= 0) {
          begin = posInfBlock1;
        } else {
          begin = this.serialisedMessage.indexOf("{1:F");
        }
      }
      else
      {
        begin = this.serialisedMessage.indexOf("{1:F01");
      }
      if (begin >= 0)
      {
        end = this.serialisedMessage.indexOf("}", begin + 3);
        this.block1 = this.serialisedMessage.substring(begin + 3, end);
      }
      else
      {
        begin = this.serialisedMessage.indexOf("{1:");
        if (begin >= 0)
        {
          end = this.serialisedMessage.indexOf("}", begin + 3);
          this.block1 = this.serialisedMessage.substring(begin + 3, end);
        }
      }
      if (this.logger.isDebugEnabled()) {
        this.logger.debug("beginMessage(): block1 = '" + this.block1 + "'");
      }
      begin = this.serialisedMessage.indexOf("{2:");
      if (begin >= 0)
      {
        end = this.serialisedMessage.indexOf("}", begin + 3);
        this.block2 = this.serialisedMessage.substring(begin + 3, end);
        if (this.logger.isDebugEnabled()) {
          this.logger.debug("beginMessage(): block2 = '" + this.block2 + "'");
        }
      }
      begin = this.serialisedMessage.indexOf("{3:");
      if (begin >= 0)
      {
        end = this.serialisedMessage.indexOf("}", begin + 3);
        this.block3 = this.serialisedMessage.substring(begin + 3, end + 2);
        if (this.logger.isDebugEnabled()) {
          this.logger.debug("beginMessage(): block3 = '" + this.block3 + "'");
        }
      }
      begin = this.serialisedMessage.indexOf("{4:");
      int MT096idx = 0;
      if (begin >= 0)
      {
        if (this.isSystemMessage)
        {
          if (this.messageName.equals("SWIFT_MT096"))
          {
            if (this.serialisedMessage.indexOf("{5:") > -1)
            {
              int indexOfBlock5 = this.serialisedMessage.indexOf("{5:");
              int lastIndexOfBlock5 = this.serialisedMessage.lastIndexOf("{5:");
              if (lastIndexOfBlock5 > indexOfBlock5)
              {
                end = lastIndexOfBlock5;
              }
              else if (lastIndexOfBlock5 == indexOfBlock5)
              {
                String beforeBLock5 = this.serialisedMessage.substring(indexOfBlock5 - 2);
                if (beforeBLock5.indexOf("}}") > -1) {
                  end = indexOfBlock5;
                } else {
                  end = this.serialisedMessage.length();
                }
              }
            }
            else
            {
              end = this.serialisedMessage.lastIndexOf("}}");
            }
          }
          else if (this.messageName.equals("SWIFT_MT056"))
          {
            if (this.serialisedMessage.indexOf("{270:") > -1)
            {
              int indexOfBlock270 = this.serialisedMessage.indexOf("{270:");
              int lastIndexOfBlock270 = this.serialisedMessage.lastIndexOf("{270:");
              
              String beforeNextBlock270 = this.serialisedMessage.substring(indexOfBlock270);
              if (lastIndexOfBlock270 == indexOfBlock270)
              {
                if (beforeNextBlock270.indexOf("}}}}") > -1) {
                  end = lastIndexOfBlock270;
                } else {
                  end = this.serialisedMessage.length();
                }
              }
              else {
                end = lastIndexOfBlock270;
              }
            }
            else
            {
              end = this.serialisedMessage.lastIndexOf("}}}}");
            }
          }
          else {
            end = this.serialisedMessage.indexOf("}}", begin + 3);
          }
        }
        else {
          end = this.serialisedMessage.indexOf("\r\n-}", begin + 3);
        }
        if (this.logger.isDebugEnabled()) {
          this.logger.debug("In beginMessage with begin = " + begin + " and end = " + end);
        }
        this.block4 = this.serialisedMessage.substring(begin + 3, end);
        if (this.messageName.equals("SWIFT_MT096")) {
          MT096idx = end;
        }
        if (this.logger.isDebugEnabled()) {
          this.logger.debug("beginMessage(): block4 = '" + this.block4 + "'");
        }
      }
      if ((this.isSystemMessage) && (!this.messageName.startsWith("SWIFT_MT0")) && (begin >= 0))
      {
        begin = this.serialisedMessage.indexOf("{1:", end + 2);
        if (begin >= 0)
        {
          end = this.serialisedMessage.indexOf("\r\n-}");
          if (end >= 0)
          {
            String embeddedMessage = this.serialisedMessage.substring(begin + 3, end);
            message.setField("EmbeddedMessage", embeddedMessage);
          }
        }
      }
      if ((this.messageName.equals("SWIFT_MT096")) && (begin >= 0))
      {
        String finCopy = this.serialisedMessage.substring(begin + 3, end);
        message.setField("FINCopyMessage", finCopy);
      }
      begin = this.serialisedMessage.indexOf("{5:", MT096idx);
      if (begin >= 0)
      {
        end = this.serialisedMessage.indexOf("}}", begin + 3);
        if (end < 0) {
          end = this.serialisedMessage.length() - 1;
        }
        this.block5 = this.serialisedMessage.substring(begin + 3, end + 1);
        if (this.logger.isDebugEnabled()) {
          this.logger.debug("beginMessage(): block5 = '" + this.block5 + "'");
        }
      }
      if (this.isSystemMessage)
      {
        if (this.messageName.equals("SWIFT_PanNan")) {
          if (message.fieldExists("Information")) {
            message.setField("Information", getInformationBlock());
          }
        }
        tokenizeSystemMessageElements();
      }
      else
      {
        tokenizeBlock4Elements();
      }
    }
    catch (Exception e)
    {
      throw new MessageFormatException("SwiftParser.begin: unable to setup parsing environment", e);
    }
    return null;
  }
  
  public void endMessage(Message message, boolean isTopLevel)
    throws MessageFormatException
  {
    if (!this.block4elements.isEmpty())
    {
      Block4Data field = (Block4Data)this.block4elements.getFirst();
      
      throw new MessageFormatException("SwiftParser.end: block 4 data doesn't match message definition at " + field.tag + field.data);
    }
    String fixedBlock = "Meridian/SWIFT header";
    
    int startOfBlock2 = this.serialisedMessage.indexOf("{2:");
    String msgType = this.serialisedMessage.substring(startOfBlock2 + 4, startOfBlock2 + 7);
    try
    {
      fixedBlock = "block 1";
      message.setField("B1AppID", this.block1.substring(0, 1));
      
      message.setField("B1ServiceID", this.block1.substring(1, 3));
      
      message.setField("B1Session", this.block1.substring(15, 19));
      
      message.setField("B1Sequence", this.block1.substring(19, 25));
      
      message.setField("ExternalMessageType", "MT" + msgType);
      if ((this.messageName.equals("SWIFT_AckNak")) || (this.messageName.equals("SWIFT_PanNan")))
      {
        message.setField("MeridianMessageType", this.messageName);
        
        message.setField("DestinationAddress", this.block1.substring(3, 15));
        
        message.setField("HostType", "SYSTEM");
        message.setField("HostID", "SYSTEM");
        if (this.messageName.equals("SWIFT_AckNak")) {
          message.setField("ExternalMessageType", "F21");
        }
        return;
      }
      char msgDirection = this.block2.charAt(0) == 'I' ? 'O' : 'I';
      
      message.setField("MeridianMessageType", this.messageName);
      if (message.fieldExists("TRNO")) {
        message.setField("TRNO", this.trnValue);
      }
      if (message.fieldExists("MessageStatus")) {
        message.setField("MessageStatus", "C");
      }
      message.setField("Direction", String.valueOf(msgDirection));
      
      message.setField("Network", "SWIFT");
      
      String vDat = "" + this.headerValueDate;
      if (vDat.length() == 8)
      {
        if (message.getField("ValueDate").getFieldAttributes().type == 0) {
          message.setField("ValueDate", vDat);
        }
        this.headerValueDate = 0;
      }
      if ((this.headerCurrencyCode != null) && (this.headerCurrencyCode.length() == 3))
      {
        message.setField("CurrencyCode", this.headerCurrencyCode);
        this.headerCurrencyCode = null;
      }
      if ((this.headerAmount != null) && (this.headerAmount.length() > 0))
      {
        try
        {
          long minorAmount = CurrencyEx.convertSwiftAmountToMinorUnits(message.getField("CurrencyCode").getString(), this.headerAmount);
          if (message.getField("Amount").getFieldAttributes().type == 1) {
            message.setField("Amount", minorAmount);
          }
        }
        catch (IllegalArgumentException e)
        {
          this.logger.info("SwiftParser : IllegalArgumentException encountered: " + e + ". Attempting to set the Amount field...");
          message.setField("Amount", this.headerAmount);
        }
        this.headerAmount = null;
      }
      if (Integer.toString(this.headerDealMaturityDate).length() == 8)
      {
        message.setField("DealMaturityDate", this.headerDealMaturityDate);
        this.headerDealMaturityDate = 0;
      }
      if (msgDirection == 'I')
      {
        Field workHostField = message.getField("HostReference");
        if (workHostField.isEmpty()) {
          message.setField("HostReference", this.block2.substring(8, 36));
        }
        workHostField = message.getField("HostType");
        if (workHostField.isEmpty()) {
          message.setField("HostType", "SYSTEM");
        }
        workHostField = message.getField("HostID");
        if (workHostField.isEmpty()) {
          message.setField("HostID", "SYSTEM");
        }
        workHostField = null;
      }
      fixedBlock = "block 2";
      if (msgDirection == 'O')
      {
        message.setField("SenderAddress", this.block1.substring(3, 15));
        message.setField("DestinationAddress", this.block2.substring(4, 16));
        message.setField("Priority", this.block2.substring(16, 17));
        if (this.block2.length() >= 18) {
          message.setField("B2IDeliveryMonitoring", this.block2.substring(17, 18));
        } else {
          message.setField("B2IDeliveryMonitoring", "0");
        }
        if (this.block2.length() >= 21) {
          message.setField("B2IObsolescencePeriod", this.block2.substring(18, 21));
        }
      }
      else
      {
        message.setField("DestinationAddress", this.block1.substring(3, 15));
        message.setField("SenderAddress", this.block2.substring(14, 26));
        if (this.block2.length() > 46) {
          message.setField("Priority", this.block2.substring(46, 47));
        } else {
          message.setField("Priority", "N");
        }
        message.setField("B2OInputTime", this.block2.substring(4, 8));
        message.setField("B2OMIR", this.block2.substring(8, 36));
        message.setField("B2OOutputDate", this.block2.substring(36, 42));
        message.setField("B2OOutputTime", this.block2.substring(42, 46));
      }
      fixedBlock = "block 3";
      if (this.block3 != null)
      {
        getB3(message, "B3ServiceId", "103:");
        getB3(message, "B3BankingPriority", "113:");
        getB3(message, "B3MUR", "108:");
        getB3(message, "B3Validation", "119:");
        getB3(message, "B3AddresseeInfo", "115:");
      }
      fixedBlock = "block 5";
      message.setField("B5content", this.block5);
    }
    catch (Exception e)
    {
      this.logger.error("In endMessage with serialisedMessage: \n" + this.serialisedMessage);
      this.logger.error("In endMessage with block1: \n" + this.block1);
      this.logger.error("In endMessage with block2: \n" + this.block2);
      throw new MessageFormatException("SwiftParser.endMessage: error processing " + fixedBlock, e);
    }
  }
  
  public void cleanUp()
    throws MessageFormatException
  {
    this.block1 = null;
    this.block2 = null;
    this.block3 = null;
    this.block4 = null;
    this.block5 = null;
    this.block4elements.clear();
  }
  
  public boolean hasNextBlock(BlockInfo blockInfo)
    throws MessageFormatException
  {
    if (this.block4elements.isEmpty()) {
      return false;
    }
    Block4Data field = (Block4Data)this.block4elements.getFirst();
    
    boolean rtnValue = false;
    if (((!this.messageName.equals("SWIFT_MT104")) && (!this.messageName.equals("SWIFT_MT107"))) || (((this.messageName.equals("SWIFT_MT104")) || (this.messageName.equals("SWIFT_MT107"))) && (field.tag.equals(":21:")))) {
      rtnValue = ((RepeatBlockImpl)blockInfo.getRepeatBlock()).containsTag(field.tag, field.data);
    }
    if (rtnValue)
    {
      MessageImpl proto = ((RepeatBlockImpl)blockInfo.getRepeatBlock()).getPrototype();
      Iterator iter = proto.iterator();
      if (iter.hasNext())
      {
        Element firstEl = (Element)iter.next();
        if (firstEl.getElementType() == 3)
        {
          FieldImpl firstField = (FieldImpl)firstEl;
          FieldAttributes attributes = firstField.getFieldAttributes();
          String firstTag = attributes.tag;
          if (firstTag.equals(":16R:"))
          {
            String firstFixedText = attributes.fixedText;
            if ((!firstTag.equals(field.tag)) || (!firstFixedText.equals(field.data))) {
              rtnValue = false;
            }
          }
          else if (checkMessageType())
          {
            MetaDataMap metaMap = firstField.getMetaDataMap();
            rtnValue = isSequenceStarted(metaMap, firstTag, firstField.getFieldName());
          }
        }
      }
    }
    return rtnValue;
  }
  
  public String parseString(Field field)
    throws MessageFormatException
  {
    FieldAttributes attributes = field.getFieldAttributes();
    String fieldName = attributes.name;
    String fieldValue = null;
    if (this.block4elements.isEmpty())
    {
      fieldValue = attributes.defaultValue;
    }
    else
    {
      String name = this.messageName;
      
      String newMessageName = getMessageTransformerProps().getMessageName();
      if (newMessageName.equals("SWIFT_Generic")) {
        name = newMessageName;
      }
      boolean specialCase = ((name.equals("SWIFT_MTn98")) && (attributes.tag != null) && (attributes.tag.equals(":77E:"))) || ((fieldName.equals("Fields_OriginalMsg")) && ((name.equals("SWIFT_MTn92")) || (name.equals("SWIFT_MTn95")) || (name.equals("SWIFT_MTn96")))) || ((name.equals("SWIFT_MT121")) && (fieldName.equals("MessageContents"))) || (name.equals("SWIFT_MT096"));
      
      boolean swiftGeneric = (name.equals("SWIFT_Generic")) && (fieldName.equals("NetworkDependentFormat"));
      if (specialCase)
      {
        StringBuffer restOfMessage = new StringBuffer();
        while (!this.block4elements.isEmpty())
        {
          Block4Data b4Field = (Block4Data)this.block4elements.removeFirst();
          if (!b4Field.tag.equals(":77E:"))
          {
            restOfMessage.append(b4Field.tag);
            
            String b4FieldData = b4Field.getData();
            if ((b4FieldData != null) && (!b4FieldData.equals("@"))) {
              restOfMessage.append(b4FieldData);
            }
            restOfMessage.append("\\n");
          }
          else
          {
            String b4FieldData = b4Field.getData();
            if ((b4FieldData != null) && (!b4FieldData.equals("@"))) {
              restOfMessage.append(b4FieldData);
            }
            restOfMessage.append("\\n");
          }
        }
        restOfMessage.setLength(restOfMessage.length() - 2);
        fieldValue = restOfMessage.toString();
      }
      else if (swiftGeneric)
      {
        fieldValue = this.serialisedMessage;
        
        int trnStart = 0;
        int trnEnd = 0;
        if (this.serialisedMessage.indexOf(":20:") >= 0)
        {
          trnStart = this.serialisedMessage.indexOf(":20:") + 4;
          trnEnd = this.serialisedMessage.indexOf("\r\n:", trnStart);
        }
        if (this.serialisedMessage.indexOf(":20C:") >= 0)
        {
          trnStart = this.serialisedMessage.indexOf(":20C:") + 5;
          trnEnd = this.serialisedMessage.indexOf("\r\n:", trnStart);
        }
        this.trnValue = this.serialisedMessage.substring(trnStart, trnEnd);
        
        this.block4elements.clear();
      }
      else
      {
        MetaDataMap metaMap = ((FieldImpl)field).getMetaDataMap();
        String thisTag = attributes.tag;
        String group = "";
        if (metaMap.containsMetaData("MultiChoiceGroupId")) {
          try
          {
            group = metaMap.getMetaData("MultiChoiceGroupId");
          }
          catch (MetaDataException e)
          {
            throw new MessageFormatException("Unable to parse string", e);
          }
        }
        if ((group.equals("")) || (!group.equals(this.currentGroup)) || ((thisTag != null) && (thisTag.equals(this.firstTagInGroup))))
        {
          this.firstTagInGroup = thisTag;
          this.currentGroup = group;
          this.alreadyFound = false;
        }
        Block4Data b4Field = (Block4Data)this.block4elements.getFirst();
        if ((this.alreadyFound) || (attributes.tag == null) || (!attributes.tag.equals(b4Field.tag)))
        {
          fieldValue = attributes.defaultValue;
        }
        else if ((attributes.fixedText != null) && (!attributes.fixedText.equals(b4Field.data)))
        {
          fieldValue = attributes.defaultValue;
        }
        else if (isSequenceValid(metaMap, thisTag, fieldName))
        {
          this.block4elements.removeFirst();
          fieldValue = b4Field.getData();
          this.alreadyFound = true;
        }
        else
        {
          fieldValue = attributes.defaultValue;
        }
      }
    }
    if ((this.isMT210) && (attributes.tag != null)) {
      if ((attributes.tag.equals(":32B:")) && (fieldValue != null) && (!fieldValue.equals("")))
      {
        long currentAmount = CurrencyEx.convertSwiftAmountToMinorUnits(fieldValue.substring(0, 3), fieldValue.substring(3, fieldValue.length()));
        this.field32B += currentAmount;
        if (this.logger.isDebugEnabled()) {
          this.logger.debug(" *********** - Total Amount = " + this.field32B);
        }
      }
    }
    MetaDataMap metaMap = ((FieldImpl)field).getMetaDataMap();
    try
    {
      if (metaMap.containsMetaData("IsTRN"))
      {
        String isTRN = field.getMetaData("IsTRN");
        if ((isTRN != null) && (isTRN.equalsIgnoreCase("true"))) {
          this.trnValue = fieldValue;
        }
      }
      if ((fieldValue != null) && (metaMap.containsMetaData("SetHeaderFields")))
      {
        String fieldNotation = field.getMetaData("SetHeaderFields").trim();
        if (fieldNotation.length() > 0) {
          extractHeaderFields(fieldValue, fieldNotation);
        }
      }
    }
    catch (MetaDataException e)
    {
      throw new MessageFormatException("Unable to parse string", e);
    }
    return fieldValue;
  }
  
  private void updateSequences(MetaDataMap metaMap, String tag)
    throws MessageFormatException
  {
    if (tag.equals(":16R:"))
    {
      if (metaMap.containsMetaData("Sequence")) {
        try
        {
          String sequence = metaMap.getMetaData("Sequence");
          this.startedSequences.put(sequence, sequence);
          this.logger.debug("Starting sequence " + sequence);
        }
        catch (MetaDataException e)
        {
          throw new MessageFormatException("Unable to parse string", e);
        }
      }
    }
    else if (tag.equals(":16S:")) {
      if (metaMap.containsMetaData("Sequence")) {
        try
        {
          String sequence = metaMap.getMetaData("Sequence");
          if (this.startedSequences.containsKey(sequence))
          {
            this.startedSequences.remove(sequence);
            this.logger.debug("Ending sequence " + sequence);
          }
          else
          {
            this.logger.debug("Sequence " + sequence + " not available to be ended?");
          }
        }
        catch (MetaDataException e)
        {
          throw new MessageFormatException("Unable to parse string", e);
        }
      }
    }
  }
  
  private boolean checkMessageType()
  {
    return (this.messageName.equals("SWIFT_MT502")) || (this.messageName.equals("SWIFT_MT508")) || (this.messageName.equals("SWIFT_MT513")) || (this.messageName.equals("SWIFT_MT514")) || (this.messageName.equals("SWIFT_MT515")) || (this.messageName.equals("SWIFT_MT518")) || (this.messageName.equals("SWIFT_MT524")) || (this.messageName.equals("SWIFT_MT528")) || (this.messageName.equals("SWIFT_MT529")) || (this.messageName.equals("SWIFT_MT540")) || (this.messageName.equals("SWIFT_MT541")) || (this.messageName.equals("SWIFT_MT542")) || (this.messageName.equals("SWIFT_MT543")) || (this.messageName.equals("SWIFT_MT578")) || (this.messageName.equals("SWIFT_MT586"));
  }
  
  private boolean isSequenceValid(MetaDataMap metaMap, String tag, String fieldName)
    throws MessageFormatException
  {
    boolean isStarted = true;
    if (!checkMessageType())
    {
      this.logger.debug("No need to check sequences for " + this.messageName);
      return true;
    }
    if ((tag != null) && (tag.equals(":16R:"))) {
      updateSequences(metaMap, tag);
    }
    if (metaMap.containsMetaData("Sequence")) {
      try
      {
        String sequence = metaMap.getMetaData("Sequence");
        if ((sequence != null) && (sequence.length() > 0)) {
          if (this.startedSequences.containsKey(sequence))
          {
            this.logger.debug("Field " + fieldName + " in sequence " + sequence + " is available");
            isStarted = true;
          }
          else
          {
            this.logger.debug("Field " + fieldName + " in sequence " + sequence + " is NOT available");
            isStarted = false;
          }
        }
      }
      catch (MetaDataException e)
      {
        throw new MessageFormatException("Unable to parse string", e);
      }
    }
    if ((tag != null) && (tag.equals(":16S:"))) {
      updateSequences(metaMap, tag);
    }
    return isStarted;
  }
  
  private boolean isSequenceStarted(MetaDataMap metaMap, String tag, String fieldName)
    throws MessageFormatException
  {
    boolean isStarted = true;
    if (metaMap.containsMetaData("Sequence")) {
      try
      {
        String sequence = metaMap.getMetaData("Sequence");
        if ((sequence != null) && (sequence.length() > 0)) {
          if (this.startedSequences.containsKey(sequence))
          {
            this.logger.debug("Field " + fieldName + " in sequence " + sequence + " is available");
            isStarted = true;
          }
          else
          {
            this.logger.debug("Field " + fieldName + " in sequence " + sequence + " is NOT available");
            isStarted = false;
          }
        }
      }
      catch (MetaDataException e)
      {
        throw new MessageFormatException("Unable to parse string", e);
      }
    }
    return isStarted;
  }
  
    private void tokenizeBlock4Elements() {
        String toParse = this.block4;
        while (toParse != null && toParse.length() > 0) {
            Block4Data field = new Block4Data();
            int start = toParse.indexOf("\r\n:");
            int end = toParse.indexOf(":", start + 3) + 1;
            field.tag = toParse.substring(start + 2, end);
            toParse = toParse.substring(end);
            end = toParse.indexOf("\r\n:");
            if (end < 0) {
                field.data = toParse;
                toParse = null;
            } else {
                field.data = toParse.substring(0, end);
                toParse = toParse.substring(end);
            }
            this.block4elements.add(field);
        }
    }
  
    private void tokenizeSystemMessageElements() {
        String toParse = this.block4;
        while (toParse != null && toParse.length() > 0) {
            Block4Data field = new Block4Data();
            int start = toParse.indexOf("{");
            if (start < 0) break;
            int end = toParse.indexOf(":", start + 1);
            field.tag = toParse.substring(start + 1, end);
            toParse = toParse.substring(end + 1);
            end = toParse.indexOf("}");
            if (this.messageName.equals("SWIFT_MT056") && field.tag.equals("270")) {
                end = toParse.indexOf("}}}");
            }
            if (end < 0) {
                field.data = toParse;
                toParse = null;
            } else {
                field.data = toParse.substring(0, end);
                toParse = toParse.substring(end);
            }
            if (this.messageName.equals("SWIFT_AckNak") && field.tag.equals("20") || this.messageName.equals("SWIFT_PanNan") || this.messageName.equals("SWIFT_MT096")) continue;
            this.block4elements.add(field);
        }
    }
  
  private void getB3(Message message, String field, String tag)
    throws MessageException
  {
    int pos = this.block3.indexOf(tag);
    if (pos < 0) {
      return;
    }
    int begin = pos + tag.length();
    int end = this.block3.indexOf("}", begin);
    
    message.setField(field, this.block3.substring(begin, end));
  }
  
  private void extractHeaderFields(String fieldValue, String fieldNotation)
  {
    String toParse = fieldNotation;
    while ((toParse != null) && (toParse.length() > 0)) {
      try
      {
        int endOfNamePos = toParse.indexOf("]");
        String fieldName = toParse.substring(1, endOfNamePos);
        int semiColonPos = toParse.indexOf(";");
        
        String fieldLengthAndFormat = "";
        if (semiColonPos > 0)
        {
          fieldLengthAndFormat = toParse.substring(endOfNamePos + 1, semiColonPos);
          
          toParse = toParse.substring(semiColonPos + 1);
        }
        else
        {
          fieldLengthAndFormat = toParse.substring(endOfNamePos + 1);
          toParse = "";
        }
        int commaPos = fieldLengthAndFormat.indexOf(",");
        
        int dataStartPos = Integer.parseInt(fieldLengthAndFormat.substring(0, commaPos)) - 1;
        
        String fieldFormat = fieldLengthAndFormat.substring(commaPos + 1);
        
        int fixLengthIndicatorPos = fieldFormat.indexOf("!");
        boolean isFixedLength = fixLengthIndicatorPos > -1;
        
        int maxDataLength = 1;
        int minDataLength = 1;
        String dataType = fieldFormat.substring(fieldFormat.length() - 1);
        if (isFixedLength)
        {
          maxDataLength = Integer.parseInt(fieldFormat.substring(0, fixLengthIndicatorPos));
        }
        else
        {
          String dataLengthStr = fieldFormat.substring(0, fieldFormat.length() - 1);
          
          int hyphonPos = dataLengthStr.indexOf("-");
          if (hyphonPos > -1)
          {
            minDataLength = Integer.parseInt(dataLengthStr.substring(0, hyphonPos));
            
            maxDataLength = Integer.parseInt(dataLengthStr.substring(hyphonPos + 1));
          }
          else
          {
            maxDataLength = Integer.parseInt(dataLengthStr);
          }
        }
        String dataFromField = "";
        if (isFixedLength) {
          dataFromField = fieldValue.substring(dataStartPos, maxDataLength + dataStartPos);
        } else if (dataStartPos + maxDataLength > fieldValue.length()) {
          dataFromField = fieldValue.substring(dataStartPos, fieldValue.length());
        } else {
          dataFromField = fieldValue.substring(dataStartPos, maxDataLength + dataStartPos);
        }
        if (fieldName.equalsIgnoreCase("ValueDate"))
        {
          if (dataFromField.length() == 6)
          {
            int dateYears = Integer.parseInt(dataFromField.substring(0, 2));
            if (dateYears < 71) {
              dataFromField = "20" + dataFromField;
            } else {
              dataFromField = "19" + dataFromField;
            }
          }
          this.headerValueDate = Integer.parseInt(dataFromField);
        }
        else if (fieldName.equalsIgnoreCase("CurrencyCode"))
        {
          this.headerCurrencyCode = dataFromField;
        }
        else if (fieldName.equalsIgnoreCase("Amount"))
        {
          if (this.headerCurrencyCode != null) {
            if (!this.isMT210) {
              this.headerAmount = dataFromField;
            } else {
              this.headerAmount = CurrencyEx.formatSwiftAmount(this.headerCurrencyCode, this.field32B);
            }
          }
        }
        else if (fieldName.equalsIgnoreCase("MaturityDate"))
        {
          if (dataFromField.length() == 6)
          {
            int dateYears = Integer.parseInt(dataFromField.substring(0, 2));
            if (dateYears < 71) {
              dataFromField = "20" + dataFromField;
            } else {
              dataFromField = "19" + dataFromField;
            }
          }
          this.headerDealMaturityDate = Integer.parseInt(dataFromField);
        }
      }
      catch (Exception e)
      {
        this.logger.error("Unable to extract all header fields from data\nField supplied    : " + fieldValue + "\n" + "Notation supplied : " + fieldNotation, e);
      }
    }
  }
  
  private class Block4Data
  {
    String tag;
    String data;
    
    private Block4Data() {}
    
    public String getData()
    {
      if ((this.data == null) || (this.data.length() <= 0)) {
        return "@";
      }
      if ((this.data.indexOf('\r') >= 0) || (this.data.indexOf('\n') >= 0))
      {
        StringBuffer b = new StringBuffer();
        StringTokenizer st = new StringTokenizer(this.data, "\r\n");
        while (st.hasMoreTokens())
        {
          if (b.length() > 0) {
            b.append("\\n");
          }
          b.append(st.nextToken());
        }
        return b.toString();
      }
      return this.data;
    }
  }
  
  private double convertSwiftAmountToDouble(String swiftAmount)
  {
    double doubleAmount = 0.0D;
    if (swiftAmount.equals("")) {
      swiftAmount = "0,";
    }
    String majorCurrencyAmount = swiftAmount.substring(0, swiftAmount.indexOf(","));
    String minorCurrencyAmount = swiftAmount.substring(swiftAmount.indexOf(",") + 1);
    
    doubleAmount = Double.parseDouble(majorCurrencyAmount + "." + minorCurrencyAmount);
    
    return doubleAmount;
  }
  
  public void setIsCensored(String value) {}
  
  private void checkForSystemMessages()
  {
    int posSystemBlock4 = this.serialisedMessage.indexOf("{4:{");
    boolean containsSystemBlock4 = posSystemBlock4 > 0;
    if (this.logger.isDebugEnabled()) {
      this.logger.debug("In checkForSystemMessages ");
    }
    if (posSystemBlock4 > 0)
    {
      int posBlock2 = this.serialisedMessage.indexOf("{2:");
      boolean containsSWIFTInBlock2 = this.serialisedMessage.indexOf("{2:I") > 0;
      if (this.logger.isDebugEnabled()) {
        this.logger.debug("In checkForSystemMessages block 2 pos = " + posBlock2);
      }
      if ((posBlock2 > 0) && ((posBlock2 < posSystemBlock4) || (containsSWIFTInBlock2))) {
        this.isSystemMessage = true;
      } else if ((posBlock2 <= 0) && (posSystemBlock4 > 0)) {
        this.isSystemMessage = (this.serialisedMessage.indexOf("{451:") > 0);
      }
    }
    boolean containsSystemMT = (this.serialisedMessage.indexOf("{2:O0") > 0) || (this.serialisedMessage.indexOf("{2:I0") > 0);
    
    this.isSystemMessage = ((this.isSystemMessage) || (containsSystemMT));
  }
  
  private String getInformationBlock()
  {
    int posInfBlock1 = this.serialisedMessage.indexOf("{1:INF");
    String msgInformation = "";
    if (posInfBlock1 >= 0)
    {
      int posBlock1 = this.serialisedMessage.indexOf("{1:F");
      if (posBlock1 > posInfBlock1)
      {
        msgInformation = this.serialisedMessage.substring(posInfBlock1, posBlock1);
        if (this.logger.isDebugEnabled()) {
          this.logger.debug("SWIFT_PanNan information = " + msgInformation);
        }
      }
    }
    return msgInformation;
  }
}
