package org.basex.test;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.basex.data.SAXSerializer;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import junit.framework.TestCase;

/**
 * This class tests the XMLDB features.
 * @author Workgroup DBIS, University of Konstanz 2005-07, ISC License
 * @author Andreas Weiler
 */
public class XMLDBTest extends TestCase {
  /** XMLDB driver. */
  static String driver = "org.basex.api.xmldb.BXDatabaseImpl";
  /** Database/document path. */
  static String url = "xmldb:basex://localhost:8080/input";
  /** Query. */
  static String query = "//li";
  /** Collection. */
  Collection collection = null;

  @Before
  @Override
  protected void setUp() throws Exception {
    try {
      Class<?> c = Class.forName(driver);
      Database database = (Database) c.newInstance();
      DatabaseManager.registerDatabase(database);
      collection = DatabaseManager.getCollection(url);
    } catch(XMLDBException e) {
      System.err.println("XML:DB Exception occured " + e.errorCode);
      e.printStackTrace();
    }/* finally {
              if(collection != null) {
                collection.close();
              }
            }*/
  }

  /**
   * Test for XPath.
   * @throws Exception exception
   */
  @Test
  public void test1() throws Exception {
    XPathQueryService service = (XPathQueryService) collection.getService(
        "XPathQueryService", "1.0");
    ResourceSet resultSet = service.query(query);

    ResourceIterator results = resultSet.getIterator();

    while(results.hasMoreResources()) {
      Resource res = results.nextResource();
      System.out.println(res.getContent());
    }
  }

  /**
   * Test XML Document Retrieval.
   * @throws Exception exception
   */
  @Test
  public void test2() throws Exception {
    String id = "input";
    XMLResource resource = (XMLResource) collection.getResource(id);

    String cont = (String) resource.getContent();
    System.out.println("------XML Document Retrieval------");
    System.out.println(cont);
    System.out.println("------XML Document Retrieval END------");
  }

  /**
   * Test DOM Document Retrieval.
   * @throws Exception exception
   */
  @Test
  public void test3() throws Exception {
    String id = "input";
    XMLResource resource = (XMLResource) collection.getResource(id);
    Document doc = (Document) resource.getContentAsDOM();
    System.out.println("------DOC Document Retrieval------");
    TransformerFactory.newInstance().newTransformer().transform(
        new DOMSource(doc), new StreamResult(System.out));
    System.out.println("------DOC Document Retrieval END------");
  }

  /**
   * Test SAX Document Retrieval.
   * @throws Exception exception
   */
  @Test
  public void test4() throws Exception {
    String id = "input";
    XMLResource resource = (XMLResource) collection.getResource(id);
    SAXSerializer sax = new SAXSerializer(null);
    // A custom SAX Content Handler is required to handle the SAX events
    ContentHandler handler = sax.getContentHandler();
    resource.getContentAsSAX(handler);
  }

  /**
   * Test DOM Node Retrieval.
   * @throws Exception exception
   */
  @Test
  public void test5() throws Exception {
    String id = "input";
    XMLResource resource = (XMLResource) collection.getResource(id);
    Node node = resource.getContentAsDOM();
    System.out.println("------DOM Node Retrieval------");
    TransformerFactory.newInstance().newTransformer().transform(
        new DOMSource(node), new StreamResult(System.out));
    System.out.println("------DOM Node Retrieval END------");
  }

  /**
   * Test Deleting a Resource.
   * @throws Exception exception
   */
  @Test
  public void test6() throws Exception {
    String id = "input";
    collection.removeResource(collection.getResource(id));
  }

  /**
   * Test DOM Node Retrieval.
   * @throws Exception exception
   */
  @Test
  public void test7() throws Exception {
    String id = "input";
    String document = "<xml>kjhjhjhj</xml>";

    XMLResource res = (XMLResource) collection.createResource(id,
        XMLResource.RESOURCE_TYPE);
    res.setContent(document);
    collection.storeResource(res);
  }

}