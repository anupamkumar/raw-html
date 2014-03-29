#Plugin source code
#### The default version puts in html content as-is. This can be easily modified to take in very specific tags (like only **p** tag) or to strip specific tags (like **script** tag). 
###To do modification you need the following prerequisites
- Download apache-nutch source - http://nutch.apache.org/downloads.html
- Download and install apache-ant build system - https://ant.apache.org/bindownload.cgi
- Download raw-html source code folder and put it in $APACHE_NUTCH_SRC/plugins/ folder
- You would want to make changes to changes to the NoTrimHTMLParseFilter.java file and add capabilities there

The ideal way to make changes would be to write a function that takes in the the HTMLString stored in **str** variable  and creates a **Node object** provided by a HTML parser like [HTMLParser](http://htmlparser.sourceforge.net/) or something similar using other Parsers like [tagsoup or Xerces](http://htmlparsing.com/java.html).

Although you could probably do it quick and dirty using regular expressions as well.

####Eg:
```java
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.Node;
import org.htmlparser.util.ParserException;
```
...
```java
//parse html and extract all <p> tags from html and return them as string
public String parseHTMLParser(String HTML) throws ParserException
{
    System.out.println("*** HTMLPARSER ***");
    Parser parser = new Parser(HTML);
    NodeList nl = parser.parse(null);
    NodeList p = nl.extractAllNodesThatMatch(new TagNameFilter("p"), true);
    String s = printHtmlParserTagContents(p);
    return s;        
}
private String printHtmlParserTagContents(NodeList nodes) {
    StringBuilder sb = new StringBuilder();
     for(int i=0;i<nodes.size();i++) {
        final Node node = nodes.elementAt(i);
        sb.append("<"+node.getText()+">"+ node.getChildren().asString()+"</"+node.getText()+">");
    }
    return sb.toString();
}
```

Use the function as follows: In **ParseResult filter()** function call the function **String parseHTMLParser()**
```java
String str = new String(rawContent, "UTF-8");
str = parseHTMLParser(str);
```
And now you're ready to build it.

 ### How to build after modification 
 - add any external libraries used into the build system