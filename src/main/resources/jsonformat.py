#!/usr/bin/python2.7
import json
import socket
import sys

def readOutput(host, port):
	data = None
	s = None
	try:
		s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		s.connect((host, int(port)))
	except socket.error as msg:
		s = None
		print msg
	if s is None:
		return None
	try:
		data = s.recv(1024)
	except socket.error as msg:
		print msg
	if s is not None:
		s.close
	return data

def parseData(jsonData, metric, key):
	data = json.loads(jsonData)
	for x in data:
		if not 'name' in x:
			continue
		if x['name'] == metric:
			if not 'datapoint' in x:
				continue
			monitorData = x['datapoint']
			for k in monitorData:
				if k == key:
					return monitorData[k]
	return 'Metric [%s:%s] not found'%(metric,key)

if __name__ == '__main__':
	if len(sys.argv) < 4:
		print 'Usage python jsonformat.py host port metric:key ...'
		print 'The output like:'
		print '[value1,value2,...]'
	else:
		jsonData = readOutput(sys.argv[1], sys.argv[2])
		if jsonData is None:
			print 'Read JSON data error'
		else:
			l = []			
			for x in sys.argv[3:]:
				args = x.split(':')
				if len(args) != 2:
					continue
				value = parseData(jsonData, args[0], args[1])
				l.append(value)
			print l		
