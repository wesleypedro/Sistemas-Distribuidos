import json
import login as log
import adm
import vend

def login(methodId, jArguments):
    arguments = json.loads(jArguments)

    cpf = arguments['cpf']
    password = arguments['pass']

    if(methodId == 1):
        (username, stat, category) = log.logar(cpf, password)

    jReply = {
        'cpf': cpf,
        'user': username,
        'pass': password,
        'stat': stat,
        'cate': category
    }

    return json.dumps(jReply)


def admController(methodId, jArguments):
    arguments = json.loads(jArguments)

    # Cadastrar Ingresso
    if(methodId == 1):
        reply = adm.cadastrarIngresso(arguments)
        return json.dumps(reply)

    # Vender Ingresso
    elif(methodId == 2):
        reply = adm.venderIngresso(arguments)
        return json.dumps(reply)

    # Listar Ingresso
    elif(methodId == 3):
        reply = adm.listarIngressos(arguments)
        return json.dumps(reply)
    
    # Pesquisar Ingresso
    elif(methodId == 4):
        reply = adm.pesquisarIngresso(arguments)
        return json.dumps(reply)
    
    # Atualizar Ingresso
    elif(methodId == 5):
        reply = adm.atualizarIngresso(arguments)
        return json.dumps(reply)

    # Remover Ingresso
    elif(methodId == 6):
        reply = adm.removerIngresso(arguments)
        return json.dumps(reply)
    
    # Cadastrar Usuario
    elif(methodId == 7):
        reply = adm.cadastrarUsuario(arguments)
        return json.dumps(reply)
    
    # Remover Usuario
    elif(methodId == 8):
        reply = adm.removerUsuario(arguments)
        return json.dumps(reply)
    
    # Alterar Senha
    elif(methodId == 9):
        reply = adm.alterarSenha(arguments)
        return json.dumps(reply)

    elif(methodId == 0):
        reply = adm.pesquisarUsuario(arguments)
        return json.dumps(reply)

    elif(methodId == 11):
        reply = adm.pesquisarUsuarioSenha(arguments)
        return json.dumps(reply)


def vendController(methodId, jArguments):
    arguments = json.loads(jArguments)

    # Vender Ingresso
    if(methodId == 1):
        reply = vend.venderIngresso(arguments)
        return json.dumps(reply)
    
    # Listar Ingresso
    elif(methodId == 2):
        reply = vend.listarIngressos(arguments)
        return json.dumps(reply)
    
    # Pesquisar Ingresso
    elif(methodId == 3):
        reply = vend.pesquisarIngresso(arguments)
        return json.dumps(reply)
    
    # Alterar Senha
    elif(methodId == 4):
        reply = vend.alterarSenha(arguments)
        return json.dumps(reply)
    
    elif(methodId == 5):
        reply = vend.pesquisarUsuarioSenha(arguments)
        return json.dumps(reply)