from socket import *
import json

import despachante as desp

serverPort = 12001
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))
serverSocket.listen(1)


lastRequestId = -1
lastArguments = ""


def getRequest():
    try:
        request = connectionSocket.recv(1024)
    except Exception as identifier:
        print(identifier)
    
    return request


def sendReply(requestId, argument):
    try:
        jReply = {
            'messageType': int(1),
            'requestId': requestId,
            'objectReference': "",
            'methodId': int(0),
            'arguments': argument
        }

        reply = json.dumps(jReply)

        connectionSocket.send(bytes(reply + "\r\n", 'UTF-8'))

    except Exception as identifier:
        print(identifier)




print('O servidor está pronto para receber')

while True:
    connectionSocket, addr = serverSocket.accept()
    print('Chamada de ', addr)

    while True:
        byteRequest = getRequest()

        if not byteRequest: break

        request = json.loads(byteRequest)

        while True:
            if(request['requestId'] == lastRequestId):
                sendReply(lastRequestId ,lastArguments)
            else:
                break

        lastRequestId = int(request['requestId'])
        reply = desp.invoke(request['objectReference'], request['methodId'], request['arguments'])
        lastArguments = reply

        sendReply(request['requestId'], reply)
    
    lastRequestId = -1
    lastArguments = ""
    connectionSocket.close()