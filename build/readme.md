## Compiled version
### Use this plugin if you want to store raw, unstripped html in your nutch-crawl. Default nutch settings strips HTML tags by default. 
Use this if you don't want to build the plugin from source yourself and want to use the plugin, as-is.
### How to :: Steps to add plugin it in your nutch deployment
- download raw-html from github
- copy the raw-html folder to your *$NUTCH_HOME/plugin folder*
- Add *"raw-html", "parse-metatags", "index-metadata"* into *plugin.includes* property in *$NUTCH_HOME/conf/nutch-site.xml / $NUTCH_HOME/conf/nutch-default.xml*
- Add field *<field name="metatag.rawcontent" type="text" stored="true" indexed="true" multiValued="false"/>* in *$NUTCH_HOME/conf/schema.xml*. This field will catch the rawcontent.
- **OPTIONAL** Add mapping in *$NUTCH_HOME/conf/solr-mapping.xml* file if you want to save the rawcontent to Solr.
