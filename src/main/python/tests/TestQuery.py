import unittest

# Dependencies
import socket, array
from BaseXClient import Query, Session

class TestQuery(unittest.TestCase):

    def setUp(self):
        
        self.__session = Session('localhost', 1984, 'admin', 'admin', 'RePEc')
        pass

    def testConstructor(self):

        self.__query = Query(self.__session, '')

        # Test for the ID assigned to the query
        self.assertEqual(self.__query._Query__id, '0')

    def testExecute(self):

        self.__query = Query(self.__session, '//amf:text', {'amf': 'http://amf.openlib.org'})
        self.__query.execute()
    
    def tearDown(self):

        pass

suite = unittest.TestLoader().loadTestsFromTestCase(TestQuery)
unittest.TextTestRunner(verbosity=2).run(suite)
