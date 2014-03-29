package org.apache.nutch.parse;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.nutch.metadata.Metadata;
import org.apache.nutch.parse.HTMLMetaTags;
import org.apache.nutch.parse.HtmlParseFilter;
import org.apache.nutch.parse.ParseResult;
import org.apache.nutch.protocol.Content;
import org.w3c.dom.DocumentFragment;

import org.apache.hadoop.conf.Configuration;

/**
 *
 * @author anupam
 */
public class NoTrimHTMLParseFilter implements HtmlParseFilter {
  private Configuration conf;
  @Override
 public Configuration getConf() {
  return conf;
 }

 @Override
 public void setConf(Configuration arg0) {
  conf = arg0;
 }
   @Override
public ParseResult filter(Content content, ParseResult parseResult, HTMLMetaTags metaTags, DocumentFragment doc) {
       try 
       {
           Metadata metadata = parseResult.get(content.getUrl()).getData().getParseMeta();
           byte[] rawContent = content.getContent();
           String str = new String(rawContent, "UTF-8");
           metadata.add("rawcontent", str);    
           return parseResult;
       } 
       catch (UnsupportedEncodingException ex) 
       {
           Logger.getLogger(NoTrimHTMLParseFilter.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }
}