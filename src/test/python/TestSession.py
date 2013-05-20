import unittest

# Dependencies
import socket
from BaseXClient import Session, SocketInputReader

class TestSession(unittest.TestCase):

    def setUp(self):
        
        pass

    def testConstructor(self):

        # Testing the default constructor

        self.__session = Session('localhost', 1984, 'admin', 'admin')
        self.assertEqual(self.__session._Session__info, None)

        # 2.7 Only
        #self.assertIsInstance(self.__session._Session__s, socket.socket)
        #self.assertIsInstance(self.__session._Session__sreader, SocketInputReader)

        self.assertEqual(type(self.__session._Session__s), socket.socket)
        self.assertEqual(type(self.__session._Session__sreader), SocketInputReader)

        self.assertEqual(self.__session._Session__event_socket, None)

        self.assertEqual(self.__session._Session__event_host, 'localhost')

        self.assertEqual(self.__session._Session__event_listening_thread, None)
        self.assertEqual(self.__session._Session__event_callbacks, {})

        # Testing the constructor with a database name
        
        # Ensure that the proper exception is raised for attempting to open a non-existent database

        # 2.7 only
        #with self.assertRaises(IOError):

        #    self.__session = Session('localhost', 1984, 'admin', 'admin', 'noExist')

        self.__session = Session('localhost', 1984, 'admin', 'admin', 'RePEc')

    def tearDown(self):

        pass

suite = unittest.TestLoader().loadTestsFromTestCase(TestSession)
unittest.TextTestRunner(verbosity=2).run(suite)
