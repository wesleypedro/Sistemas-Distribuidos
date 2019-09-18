import esqueleto as esq

def invoke(objectReference, methodId, arguments):
    reply = None

    if(objectReference == 'login'):
        reply = esq.login(methodId, arguments)
    
    elif(objectReference == 'admController'):
        reply = esq.admController(methodId, arguments)

    elif(objectReference == 'vendController'):
        reply = esq.vendController(methodId, arguments)
    
    return reply