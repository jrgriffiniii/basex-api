import unittest

# Dependencies
import socket, array
from BaseXClient import SocketInputReader

class TestSocketInputReader(unittest.TestCase):

    def setUp(self):
        
        self.__socket = socket
        pass

    def testConstructor(self):

        self.__socketInputReader = SocketInputReader(self.__socket)
        self.assertEqual(self.__socketInputReader._SocketInputReader__buf, array.array('B', chr(0) * 0x1000))
    
    def testInit(self):

        self.__socketInputReader = SocketInputReader(self.__socket)
        self.__socketInputReader.init()
        self.assertEqual(self.__socketInputReader._SocketInputReader__bpos, 0)
        self.assertEqual(self.__socketInputReader._SocketInputReader__bsize, 0)

    def tearDown(self):

        pass

suite = unittest.TestLoader().loadTestsFromTestCase(TestSocketInputReader)
unittest.TextTestRunner(verbosity=2).run(suite)
