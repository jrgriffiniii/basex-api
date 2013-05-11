import unittest

# Dependencies
import socket
from BaseXClient import Session, SocketInputReader

class TestSession(unittest.TestCase):

    def setUp(self):
        
        pass

    def testConstructor(self):

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

    def tearDown(self):

        pass

suite = unittest.TestLoader().loadTestsFromTestCase(TestSession)
unittest.TextTestRunner(verbosity=2).run(suite)
